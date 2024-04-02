package com.example.idsdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Car
 * @Description TODO
 * @Author YU
 * @Date 2023/7/18 19:39
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "car")
public class Car {
    private String carNumber;
    private String carBrand;
    private String carColor;
    private String carUserEmail;
    private Date createdTime;
    private Double totalDuration;
}
