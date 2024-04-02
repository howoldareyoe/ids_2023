package com.example.idsdemo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName UserInfo
 * @Description TODO
 * @Author YU
 * @Date 2023/7/18 18:33
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User {
    private String userEmail;
    private String userName;
    private String userPassword;
    private int autoDetection;
    private int warning;
    private Date createdTime;
    private Integer userRoleId;
}
