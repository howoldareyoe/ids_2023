package com.example.idsdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName DetectionInfo
 * @Description TODO
 * @Author YU
 * @Date 2023/7/31 19:34
 * @Version 1.0
 **/
@Data
@TableName(value = "detection_info")
public class DetectionInfo {

    private String detectionId;
    private String carNumber;
    private Date detectionTime;
    private int isAbnormal;
    private String detectionDesc;

}
