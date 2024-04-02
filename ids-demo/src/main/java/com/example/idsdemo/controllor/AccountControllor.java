package com.example.idsdemo.controllor;

import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.dto.SessionWebUserDto;
import com.example.idsdemo.entity.constants.Constants;
import com.example.idsdemo.mapper.CarMapper;
import com.example.idsdemo.service.impl.UserServiceImpl;
import com.example.idsdemo.utils.StringTools;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AccountControllor
 * @Description TODO
 * @Author YU
 * @Date 2023/7/18 19:55
 * @Version 1.0
 **/
@RestController
public class AccountControllor extends BaseControllor{
    @Resource
    private UserServiceImpl userService;

    // 验证码发送
    @RequestMapping(value = "/sendEmailCode", method = RequestMethod.POST)
    public Result sendEmail(String email) {
        System.out.println(email+"--------------");
        userService.sendEmailCode(email);
        return Result.success("成功");
    }

    // 用户端注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(String email,String carNumber,String carBrand){
        if(StringTools.isEmpty(email)||StringTools.isEmpty(carBrand)||StringTools.isEmpty(carNumber)){
            return Result.error("参数错误");
        }
        return userService.register(email,carNumber,carBrand);
    }

    // 用户端登录
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result login(HttpSession session,String email,String emailCode){
        if(StringTools.isEmpty(email)||StringTools.isEmpty(emailCode)){
            return Result.error("参数错误");
        }
        Result login  = userService.login(email,emailCode);
        SessionWebUserDto sessionWebUserDto = (SessionWebUserDto)login.getData();
        System.out.println(sessionWebUserDto);
        session.setAttribute(Constants.SESSION_KEY,sessionWebUserDto);
        return login;
    }

    // 用户端登录后设置车牌号
    @RequestMapping(value = "/setCarNumber", method = RequestMethod.POST)
    public Result setCarNumber(HttpSession session,String carNumber){
        SessionWebUserDto userInfoFromSession = getUserInfoFromSession(session);
        userInfoFromSession.setCarNumber(carNumber);
        if(userService.getCarBrandByCarNumber(carNumber) == null){
            return Result.error("车不存在");
        }
        userInfoFromSession.setCarBrand(userService.getCarBrandByCarNumber(carNumber));
        session.setAttribute(Constants.SESSION_KEY,userInfoFromSession);
        return Result.success("设置成功",userInfoFromSession);
    }

    // 获取用户信息
    @RequestMapping(value = "/getEmailAndCarNumber",method = RequestMethod.GET)
    public Result getEmailAndCarNumber(HttpSession session){
        SessionWebUserDto userInfoFromSession = getUserInfoFromSession(session);
        Map<String,String> res = new HashMap<>();
        res.put("email",userInfoFromSession.getEmail());
        res.put("carNumber",userInfoFromSession.getCarNumber());
        return Result.success("查询成功",res);
    }

    //上传音频文件
    @RequestMapping(value = "/postAudio",method = RequestMethod.POST)
    public void postAudio(HttpSession session,MultipartFile file){
        userService.postAudio(file,getUserInfoFromSession(session).getEmail());
    }

    // 获取音频文件
    @RequestMapping(value = "/getAudio",method = RequestMethod.GET)
    public void getAudio(HttpServletResponse response,HttpSession session){
        System.out.println(getUserInfoFromSession(session).getEmail());
        userService.getAudio(response,getUserInfoFromSession(session).getEmail());
    }

    // 用户反馈
    @RequestMapping(value = "/feedBack",method = RequestMethod.POST)
    public void feedBack(HttpSession session,String detecId,String desc){
        userService.feedBack(getUserInfoFromSession(session).getCarNumber(),detecId,desc);
    }






}
