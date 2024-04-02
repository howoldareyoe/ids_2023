package com.example.idsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.component.RedisComponent;
import com.example.idsdemo.component.RedisUtils;
import com.example.idsdemo.config.AppConfig;
import com.example.idsdemo.dto.SessionWebUserDto;
import com.example.idsdemo.dto.SysSettingsDto;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.entity.FeedbackInfo;
import com.example.idsdemo.entity.User;
import com.example.idsdemo.entity.constants.Constants;
import com.example.idsdemo.mapper.CarMapper;
import com.example.idsdemo.mapper.FeedbackInfoMapper;
import com.example.idsdemo.mapper.UserMapper;
import com.example.idsdemo.service.UserService;
import com.example.idsdemo.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.scanner.Constant;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author YU
 * @Date 2023/7/18 18:48
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private CarMapper carMapper;
    @Resource
    private RedisComponent redisComponent;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private AppConfig appConfig;
    @Resource
    private FeedbackInfoMapper feedbackInfoMapper;

    public Result register(String email, String carNumber, String carBrand) {
        User userTOInsert = userMapper.selectOne(new QueryWrapper<User>().eq("user_email", email));
        Car carToinsert = carMapper.selectOne(new QueryWrapper<Car>().eq("car_number", carNumber));
        if (userTOInsert != null && carToinsert != null) {
            return Result.error("已有注册");
        }

        Date curDate = new Date();
        if(userTOInsert==null){
            User user = new User();
            user.setUserEmail(email);
            user.setCreatedTime(curDate);
            user.setAutoDetection(0);
            user.setWarning(1);
            userMapper.insert(user);
        }

        Car car = new Car();
        car.setCarNumber(carNumber);
        car.setCarBrand(carBrand);
        car.setCarUserEmail(email);
        car.setCreatedTime(curDate);
        car.setTotalDuration(0.0);
        carMapper.insert(car);

        return Result.success("注册成功");
    }

    // TODO 一个邮箱可用多辆车情况，登录要不要考虑车牌号加入登录条件
    public Result login(String email, String emailCode) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_email", email));
        if (user == null) {
            return Result.error("用户尚未注册");
        }
        String emailCode4Check = redisComponent.getEmailCodeByEmail(email);
        if (emailCode4Check == null) {
            return Result.error("验证码失效");
        } else if (!emailCode.equals(emailCode4Check)) {
            return Result.error("验证码错误");
        }
        SessionWebUserDto sessionWebUserDto = new SessionWebUserDto();
        sessionWebUserDto.setEmail(email);
        List<Car> cars = carMapper.selectList(new QueryWrapper<Car>().eq("car_user_email", email));
        String carNumbers = "";
        String carBrands = "";
        for (int i = 0; i < cars.size(); i++) {
            carNumbers+=cars.get(i).getCarNumber()+",";
            carBrands+=cars.get(i).getCarBrand()+",";
        }
        sessionWebUserDto.setCarNumber(carNumbers);
        sessionWebUserDto.setCarBrand(carBrands);
        return Result.success("登录成功",sessionWebUserDto);
    }


    public String getCarBrandByCarNumber(String carNumber){
        Car car = carMapper.selectOne(new QueryWrapper<Car>().eq("car_number", carNumber));
        if(car == null){
            return null;
        }
        return car.getCarBrand();
    }
    @Override
    public Result loginAdmin(String userName, String password) {
        User admin = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName).eq("user_password", password));
        if(admin == null){
            return Result.error("登录失败");
        }
        SessionWebUserDto sessionWebUserDto = new SessionWebUserDto();
        sessionWebUserDto.setAdminName(admin.getUserName());
        sessionWebUserDto.setAdminPassword(admin.getUserPassword());
        sessionWebUserDto.setRoleId(admin.getUserRoleId());
        return Result.success("登录成功",sessionWebUserDto);
    }

    @Override
    public void postAudio(MultipartFile file,String email) {
        String folderName = appConfig.getProjectFolder()+Constants.FILE_FOLDER_FILE+Constants.FILE_FOLDER_AUDIO+email+Constants.AUDIO_SUFFIX;

        File toFile = new File(folderName);
        if(!toFile.exists()){
            toFile.mkdirs();
        }
        try {
            file.transferTo(toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAudio(HttpServletResponse response, String email) {
        String from = appConfig.getProjectFolder()+Constants.FILE_FOLDER_FILE+Constants.FILE_FOLDER_AUDIO+email+Constants.AUDIO_SUFFIX;
        try {
            readFile(response,from);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void feedBack(String carNumber, String srcId,String desc) {
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        feedbackInfo.setFeedbackId(StringTools.getRandomNumber(Constants.LENGTH_5));
        feedbackInfo.setCarNumber(carNumber);
        feedbackInfo.setDetectionSrc(srcId);
        feedbackInfo.setFeedbackTime(new Date());
        feedbackInfo.setFeedbackDesc(desc);
        feedbackInfoMapper.insert(feedbackInfo);
    }

    private void readFile(HttpServletResponse response,String filePath) throws Exception {
        File from = new File(filePath);
        File defaultFile = ResourceUtils.getFile("classpath:static/default.m4a");
        FileInputStream fileInputStream = null;
        OutputStream out =null;
        if(!from.exists()){
            fileInputStream = new FileInputStream(defaultFile);
        }else{
            fileInputStream = new FileInputStream(from);
        }
        out = response.getOutputStream();
        byte[] b = new byte[64];
        int len = 0;
        while ((len = fileInputStream.read(b))!=-1) {
            out.write(b,0,len);
        }
        if(fileInputStream != null){
            fileInputStream.close();
        }
        if(out != null){
            out.close();
        }
    }


    public void sendEmailCode(String email) {
        String code = StringTools.getRandomNumber(Constants.LENGTH_5);
        sendEmailCode(email, code);
        redisComponent.setEmailCode(email, code);
    }

    public void sendEmailCode(String toEmail, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(appConfig.getSendUserName());
            helper.setTo(toEmail);

            SysSettingsDto sysSettingsDto = redisComponent.getSysSettingsDto();
            helper.setSubject(sysSettingsDto.getRegisterMailTitle());
            helper.setText(String.format(sysSettingsDto.getRegisterEmailContent(), code));
            helper.setSentDate(new Date());

            javaMailSender.send(message);
        } catch (Exception e) {
            logger.error("邮件发送失败", e);
            throw new RuntimeException("邮件发送失败");
        }
    }
}
