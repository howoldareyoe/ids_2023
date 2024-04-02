package com.example.idsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.entity.LogRecord;
import com.example.idsdemo.mapper.CarMapper;
import com.example.idsdemo.mapper.LogRecordMapper;
import com.example.idsdemo.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SchTasks
 * @Description 定时任务
 * @Author YU
 * @Date 2023/7/22 15:46
 * @Version 1.0
 **/
public class SchTasks {

    @Resource
    private LogRecordMapper logRecordMapper;
    @Resource
    private CarMapper carMapper;


    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void addTotalDurationEveryDay(){
        List<LogRecord> logRecords = logRecordMapper.selectList(new QueryWrapper<LogRecord>()
                .ge("start_time", DateUtils.getTodayZero()));
        Map<String,Double> allCars = carMapper.findTotalDuration();
        double time = 0;
        for(LogRecord item : logRecords){
            if(item.getEndTime() == null){
                time=DateUtils.hoursBetween(new Date(),item.getStartTime());
            }else{
                time=DateUtils.hoursBetween(item.getEndTime(),item.getStartTime());
            }

            allCars.put(item.getCarNumber(),allCars.get(item.getCarNumber())+time);
        }

        for(Map.Entry<String,Double>entry:allCars.entrySet()){
            carMapper.update(null,new UpdateWrapper<Car>()
                    .eq("car_number",entry.getKey())
                    .set("total_duration",entry.getKey()));
        }


    }

    @Scheduled(cron = "0 0 1 1/2 * ?")
    public void clearLogEveryTwoDays(){
        logRecordMapper.delete(new QueryWrapper<LogRecord>().lt("end_time",DateUtils.getTodayZero()).isNotNull("end_time"));
    }


}
