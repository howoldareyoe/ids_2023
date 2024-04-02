package com.example.idsdemo.controllor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.dto.SessionWebUserDto;
import com.example.idsdemo.entity.CarIntrusion;
import com.example.idsdemo.entity.DetectionInfo;
import com.example.idsdemo.service.DetectionInfoService;
import com.example.idsdemo.service.LogRecordService;
import com.example.idsdemo.service.impl.DetectionInfoServiceImpl;
import com.example.idsdemo.service.impl.IntrusionRecordServiceImpl;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DetectionControllor
 * @Description TODO
 * @Author YU
 * @Date 2023/7/22 11:45
 * @Version 1.0
 **/
@RestController
public class DetectionControllor extends BaseControllor{
    @Resource
    private LogRecordService logRecordService;
    @Resource
    private DetectionInfoServiceImpl detectionInfoService;
    @Resource
    private IntrusionRecordServiceImpl intrusionRecordService;

    @RequestMapping(value = "/getStart",method = RequestMethod.POST)
    public void getStart(HttpSession session){
        SessionWebUserDto userDto = getUserInfoFromSession(session);
        logRecordService.getStart(userDto.getCarNumber());
    }

    @RequestMapping(value = "/getEnd",method = RequestMethod.POST)
    public void getEnd(HttpSession session){
        SessionWebUserDto userDto = getUserInfoFromSession(session);
        logRecordService.getEnd(userDto.getCarNumber());
    }

    // 今日检测时间
    @RequestMapping(value = "/getDayDetectionTime",method = RequestMethod.GET)
    public Result getDayDetectionTime(HttpSession session){
        SessionWebUserDto userDto = getUserInfoFromSession(session);
        Double dayDetectionTime = logRecordService.getDayDetectionTime(userDto.getCarNumber());
        return Result.success("查询成功",dayDetectionTime);
    }

    //总检测时间
    @RequestMapping(value = "/getTotalDetectionTime",method = RequestMethod.POST)
    public Result getTotalDetectionTime(HttpSession session){
        SessionWebUserDto userDto = getUserInfoFromSession(session);
        Double totalDetectionTime = logRecordService.getTotalDetectionTime(userDto.getCarNumber());
        return Result.success("查询成功",totalDetectionTime);
    }

    // 用户端检测
    @RequestMapping(value = "/doDetection",method = RequestMethod.POST)
    public Result doDetection(HttpSession session, MultipartFile file){
        SessionWebUserDto userDto = getUserInfoFromSession(session);
        ArrayList<Map<String, String>> maps = detectionInfoService.doDetection(userDto.getCarNumber(), detectionInfoService.postDetectioFile(userDto.getCarNumber(), file));
        return Result.success("检测完成",maps);
    }

    // 用户端检测结果
    @RequestMapping(value = "/getDetectionRes",method = RequestMethod.GET)
    public Result getDetectionRes(HttpSession session, Integer pageNo,Integer pageSize){
        QueryWrapper<DetectionInfo> queryWrapper = new QueryWrapper<DetectionInfo>()
                .eq("car_number", getUserInfoFromSession(session).getCarNumber());
        Page<DetectionInfo> ppage = new Page<>(pageNo,pageSize);
        List<DetectionInfo> detectionres = detectionInfoService.getDetectionres(queryWrapper, ppage);
        return Result.success("查询成功",detectionres);
    }



    // 用户端异常结果查询
    @RequestMapping(value = "/getIntrusion",method = RequestMethod.GET)
    public Result getIntrusion(HttpSession session, Integer pageNo,Integer pageSize){
        QueryWrapper<CarIntrusion> queryWrapper = new QueryWrapper<CarIntrusion>()
                .eq("car_number", getUserInfoFromSession(session).getCarNumber());
        Page<CarIntrusion> ppage = new Page<>(pageNo,pageSize);
        List<CarIntrusion> intrusion = intrusionRecordService.getIntrusion(queryWrapper, ppage);
        return Result.success("查询成功",intrusion);
    }

}
