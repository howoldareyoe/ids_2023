package com.example.idsdemo.controllor;

import com.example.idsdemo.annotation.RolePermissionVerificationAnno;
import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.dto.SessionWebUserDto;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.entity.DetectionInfo;
import com.example.idsdemo.entity.constants.Constants;
import com.example.idsdemo.service.*;
import com.example.idsdemo.service.impl.UserServiceImpl;
import com.example.idsdemo.utils.StringTools;
import com.example.idsdemo.vo.CarDetailVo;
import com.example.idsdemo.vo.FeedbackSelectVo;
import com.example.idsdemo.vo.IntrusionVo;
import com.example.idsdemo.vo.UserCommandVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminControllor
 * @Description TODO
 * @Author YU
 * @Date 2023/7/21 15:04
 * @Version 1.0
 **/
@RestController("adminControllor")
@RequestMapping("/admin")
public class AdminControllor extends BaseControllor{
    @Resource
    private UserServiceImpl userService;
    @Resource
    private IntrusionRecordService intrusionRecordServiceservice;
    @Resource
    private CarService carService;
    @Resource
    private DetectionInfoService detectionInfoService;
    @Resource
    private ReceiveLogService receiveLogService;
    @Resource
    private AbDetailsService abDetailsService;

    // 管理端登录
    @RequestMapping(value = "/loginAdmin", method = RequestMethod.GET)
    public Result loginAdmin(HttpSession session, String userName, String password){
        if(StringTools.isEmpty(userName)||StringTools.isEmpty(password)){
            return Result.error("参数错误");
        }
        Result login  = userService.loginAdmin(userName,password);
        SessionWebUserDto sessionWebUserDto = (SessionWebUserDto)login.getData();
        session.setAttribute(Constants.SESSION_KEY,sessionWebUserDto);
        return login;
    }

    // 管理端数据管理查询所有异常记录
    @RolePermissionVerificationAnno(roleIdList = {1,2})
    @RequestMapping(value = "/findAllCarIntrusions", method = RequestMethod.GET)
    public Result findAllCarIntrusions(Integer pageNo,Integer pageSize){
        List<IntrusionVo> carIntrusions = intrusionRecordServiceservice.findAllCarIntrusions(pageNo, pageSize);
        return Result.success("查询成功",carIntrusions);
    }

    // 管理端查看异常记录详细
    @RequestMapping(value = "/findIntrusionDetails",method = RequestMethod.GET)
    public Result findIntrusionDetails(String no){
        DetectionInfo intrusionDetails = detectionInfoService.findIntrusionDetails(no);
        return Result.success("查询成功",intrusionDetails);
    }

    // 管理端数据管理入侵种类统计
    @RequestMapping(value = "/findIntrusionStatistics",method = RequestMethod.GET)
    public Result findIntrusionStatistics(){
        List<Map<String, Integer>> intrusionStatistics = intrusionRecordServiceservice.findIntrusionStatistics();
        return Result.success("查询成功",intrusionStatistics);
    }
    // 管理端数据管理受入侵车品牌统计
    @RequestMapping(value = "/findCarBrandStatistics",method = RequestMethod.GET)
    public Result findCarBrandStatistics(){
        List<Map<String, Integer>> carBrandStatisticsStatistics = intrusionRecordServiceservice.findCarBrandStatistics();
        return Result.success("查询成功",carBrandStatisticsStatistics);
    }

     // 管理端数据管理按类搜索记录
    @RequestMapping(value = "/findCarIntrusions", method = RequestMethod.GET)
    public Result findCarIntrusions(Integer pageNo,Integer pageSize,Integer byType,String by){
        List<IntrusionVo> carIntrusions = intrusionRecordServiceservice.findCarIntrusions(pageNo, pageSize,byType,by);
        return Result.success("查询成功",carIntrusions);
    }

    // 管理端用户管理所有车辆
    // email非必须
    @RolePermissionVerificationAnno(roleIdList = {1,3})
    @RequestMapping(value = "/findAllCars", method = RequestMethod.GET)
    public Result findAllCars(Integer pageNo,Integer pageSize,String email){
        List<UserCommandVo> allCars = carService.findAllCars(pageNo, pageSize,email);
        return Result.success("查询成功",allCars);
    }

    // 管理端用户管理查看具体信息
    @RequestMapping(value = "/findDetails", method = RequestMethod.GET)
    public Result findDetails(String carNumber){
        CarDetailVo details = carService.findDetails(carNumber);
        return Result.success("查询成功",details);
    }

    // 管理端查看反馈信息
    @RequestMapping(value = "/findFeedback", method = RequestMethod.GET)
    public Result findFeedback(){
        FeedbackSelectVo[] feedback = detectionInfoService.findFeedback();
        return Result.success("查询成功",feedback);
    }

    //日志按时间返回
    @RolePermissionVerificationAnno(roleIdList = {1,2})
    @RequestMapping(value = "/getLogOneHour", method = RequestMethod.GET)
    public Result getLogOneHour(String date){
        return receiveLogService.getLogOneHour(date);
    }

    //最新一小时的日志返回(上一小时)
    @RolePermissionVerificationAnno(roleIdList = {1,2})
    @RequestMapping(value = "/getLatestLog", method = RequestMethod.GET)
    public Result getLatestLog(){
        return receiveLogService.getLatestLog();
    }

    //查看异常详情（异常数据集）
    @RolePermissionVerificationAnno(roleIdList = {1,2})
    @RequestMapping(value = "/getAbDetails", method = RequestMethod.GET)
    public Result getAbDetails(){
        return abDetailsService.getAllAb();
    }




}
