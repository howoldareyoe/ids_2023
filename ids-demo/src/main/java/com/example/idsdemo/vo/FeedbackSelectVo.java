package com.example.idsdemo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName FeedbackSelectVo
 * @Description TODO
 * @Author YU
 * @Date 2023/7/31 20:59
 * @Version 1.0
 **/
@Data
public class FeedbackSelectVo {
    private String email;
    private String carNumber;
    private String detectionSrc;
    private Date feedbackTime;
    private String feedbackDesc;
}
