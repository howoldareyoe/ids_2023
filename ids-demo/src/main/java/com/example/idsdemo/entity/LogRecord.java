package com.example.idsdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName LogRecord
 * @Description TODO
 * @Author YU
 * @Date 2023/7/22 14:19
 * @Version 1.0
 **/
@Data
@TableName(value = "log_record")
public class LogRecord {
    private String logId;
    private String carNumber;
    private Date startTime;
    private Date endTime;
}
