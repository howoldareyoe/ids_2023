package com.example.idsdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName FeedbackInfo
 * @Description TODO
 * @Author YU
 * @Date 2023/7/31 19:39
 * @Version 1.0
 **/
@Data
@TableName(value = "feedback_info")
public class FeedbackInfo {
    private String feedbackId;
    private String carNumber;
    private String detectionSrc;
    private Date feedbackTime;
    private String feedbackDesc;
}
