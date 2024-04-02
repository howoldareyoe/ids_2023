package com.example.idsdemo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CarDetailVo
 * @Description TODO
 * @Author YU
 * @Date 2023/7/22 11:22
 * @Version 1.0
 **/
@NoArgsConstructor
@Data
public class CarDetailVo {
    private String carNumber;
    private String carBrand;
    private double totalDuration;
    private Integer abnormalCount;

    @Override
    public String toString() {
        return "CarDetailVo{" +
                "carNumber='" + carNumber + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", totalDuration=" + totalDuration +
                ", abnormalCount=" + abnormalCount +
                '}';
    }
}
