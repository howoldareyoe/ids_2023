package com.example.idsdemo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Intrusion
 * @Description TODO
 * @Author YU
 * @Date 2023/7/21 15:13
 * @Version 1.0
 **/
@Data
public class IntrusionVo {
    private Date intrusionTime;
    private String userEmail;
    private String intrusionType;
    private String carNumber;
    private String intrusionSrc;

}
