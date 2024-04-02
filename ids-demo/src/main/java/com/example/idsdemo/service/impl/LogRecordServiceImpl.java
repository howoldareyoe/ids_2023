package com.example.idsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.entity.LogRecord;
import com.example.idsdemo.entity.constants.Constants;
import com.example.idsdemo.mapper.CarMapper;
import com.example.idsdemo.mapper.LogRecordMapper;
import com.example.idsdemo.service.LogRecordService;
import com.example.idsdemo.utils.DateUtils;
import com.example.idsdemo.utils.StringTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LogRecordServiceImpl
 * @Description TODO
 * @Author YU
 * @Date 2023/7/22 14:22
 * @Version 1.0
 **/
@Service
public class LogRecordServiceImpl implements LogRecordService {
    @Resource
    private LogRecordMapper logRecordMapper;
    @Resource
    private CarMapper carMapper;
    @Override
    public void getStart(String carNumber) {
        LogRecord logRecord = new LogRecord();
        logRecord.setLogId(StringTools.getRandomNumber(Constants.LENGTH_5));
        logRecord.setCarNumber(carNumber);
        logRecord.setStartTime(new Date());
        logRecord.setEndTime(null);
        logRecordMapper.insert(logRecord);
    }

    @Override
    public void getEnd(String carNumber) {
        UpdateWrapper<LogRecord> updateWrapper = new UpdateWrapper<LogRecord>().eq("car_number", carNumber)
                .isNull("end_time").set("end_time", new Date());
        logRecordMapper.update(null,updateWrapper);
    }

    @Override
    public Double getDayDetectionTime(String carNumber) {
        List<LogRecord> logRecords = logRecordMapper.selectList(new QueryWrapper<LogRecord>()
                .eq("car_number", carNumber)
                .ge("start_time", DateUtils.getTodayZero()));
        Double time = 0.0;
        for(LogRecord item : logRecords){
            if(item.getEndTime() == null){
                time+=DateUtils.hoursBetween(new Date(),item.getStartTime());
            }else{
                time+=DateUtils.hoursBetween(item.getEndTime(),item.getStartTime());
            }
        }
        return time;
    }

    @Override
    public Double getTotalDetectionTime(String carNumber) {
        Double dayDetectionTime = getDayDetectionTime(carNumber);
        Car car = carMapper.selectOne(new QueryWrapper<Car>().eq("car_number", carNumber));
        return dayDetectionTime+car.getTotalDuration();
    }

}
