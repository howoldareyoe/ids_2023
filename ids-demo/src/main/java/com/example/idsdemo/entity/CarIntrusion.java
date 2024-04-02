package com.example.idsdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName CarIntrusion
 * @Description TODO
 * @Author YU
 * @Date 2023/7/21 15:17
 * @Version 1.0
 **/
@Data
@TableName(value = "car_intrusion")
public class CarIntrusion {
    private String carIntrusionId;
    private String carNumber;
    private String intrusionType;
    private Date intrusionTime;
    private String intrusionSrc;
}
