package com.example.idsdemo;

import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.mapper.CarIntrusionMapper;
import com.example.idsdemo.mapper.CarMapper;
import com.example.idsdemo.mapper.FeedbackInfoMapper;
import com.example.idsdemo.service.AbDetailsService;
import com.example.idsdemo.service.CarService;
import com.example.idsdemo.service.IntrusionRecordService;
import com.example.idsdemo.service.LogRecordService;
import com.example.idsdemo.utils.AlgoUtils;
import com.example.idsdemo.vo.CarDetailVo;
import com.example.idsdemo.vo.IntrusionVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class IdsDemoApplicationTests {
    @Resource
    private CarMapper carMapper;

    @Resource
    private CarIntrusionMapper carIntrusionMapper;
    @Resource
    private CarService carService;

    @Resource
    private LogRecordService logRecordService;

    @Resource
    private IntrusionRecordService intrusionRecordService;

    @Resource
    private FeedbackInfoMapper feedbackInfoMapper;

    @Resource
    private AbDetailsService abDetailsService;
    @Test
    void contextLoads() {
//        List<IntrusionVo> carIntrusions = carIntrusionMapper.findAllCarIntrusions(0, 1);
//        List<IntrusionVo> carIntrusions = carIntrusionMapper.findCarIntrusions(0, 1, "user_email", "1");
//        List<UserCommandVo> allCars = carService.findAllCars(1, 1,"191272786@qq.com");
//        Map<String, Integer> intrusionStatistics = carIntrusionMapper.findIntrusionStatistics();
//        Map<String, Integer> carBrandStatistics = carIntrusionMapper.findCarBrandStatistics();
//        CarDetailVo details = carMapper.findDetails("浙A.VR44R");
//        Map<String, Double> totalDuration = carMapper.findTotalDuration();
//        FeedbackSelectVo[] feedback = feedbackInfoMapper.findFeedback();
//        for (int i = 0; i < feedback.length; i++) {
//            System.out.println(feedback[i]);
//        }
        List<IntrusionVo> carIntrusions = carIntrusionMapper.findCarIntrusions(0, 2, "car_number", "浙A.VR44R");
        System.out.println(carIntrusions.toString());
    }

    @Test
    void carMapperTest(){
        CarDetailVo details = carMapper.findDetails("浙B.VR44R");
        System.out.println(details);

    }
    // 统计测试
    @Test
    void statistics(){
//        List<Map<String, Integer>> intrusionStatistics = intrusionRecordService.findIntrusionStatistics();
        List<Map<String, Integer>> carBrandStatistics = intrusionRecordService.findCarBrandStatistics();
        System.out.println(carBrandStatistics.toString());

    }
    @Test
    void testTime(){
//        logRecordService.getStart("浙A.VR44R");
        logRecordService.getEnd("浙A.VR44R");
//        Double dayDetectionTime = logRecordService.getDayDetectionTime("浙A.VR44R");
//        System.out.println("dayDetectionTime"+dayDetectionTime);
    }
    @Test
    void testAl(){
        ArrayList<String> result = AlgoUtils.getResult("E:\\国创\\algorithm\\test01.csv");
        System.out.println("----------------"+result.toString());
    }

    @Test
    void testAbDetails(){
        Result allAb = abDetailsService.getAllAb();
        System.out.println(allAb);
    }

}
