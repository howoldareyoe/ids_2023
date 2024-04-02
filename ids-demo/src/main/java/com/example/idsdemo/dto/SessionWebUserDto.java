package com.example.idsdemo.dto;

import lombok.Data;

/**
 * @ClassName SessionWebUserDto
 * @Description TODO
 * @Author YU
 * @Date 2023/7/19 11:04
 * @Version 1.0
 **/
@Data
public class SessionWebUserDto {
    private String email;
    private String carNumber;
    private String carBrand;
    private String adminName;
    private String adminPassword;
    private Integer roleId;
}
