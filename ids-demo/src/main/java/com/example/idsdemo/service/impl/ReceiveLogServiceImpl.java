package com.example.idsdemo.service.impl;

import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.service.ReceiveLogService;
import com.example.idsdemo.utils.LogReaderUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName ReceiveLogServiceImpl
 * @Description TODO
 * @Author YU
 * @Date 2023/11/29 10:12
 * @Version 1.0
 **/
@Service
public class ReceiveLogServiceImpl implements ReceiveLogService {

    private static final String logDir="log_pack_receive";
    @Override
    public Result getLogOneHour(String date) {
        String logs = LogReaderUtil.readLogs(logDir, date);

        return Result.success("查询成功",logs);
    }

    @Override
    public Result getLatestLog() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Calendar calendar = Calendar.getInstance();
        // Subtract two hours from the current time
        calendar.add(Calendar.HOUR, -2);
        Date previousHour = calendar.getTime();
        String previousHourDate = sdf.format(previousHour);

        String logs = LogReaderUtil.readLogs(logDir, previousHourDate);
        return Result.success("查询成功",logs);
    }
}
