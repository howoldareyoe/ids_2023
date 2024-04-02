package com.example.idsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.vo.CarDetailVo;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @ClassName CarMapper
 * @Description TODO
 * @Author YU
 * @Date 2023/7/18 19:44
 * @Version 1.0
 **/
public interface CarMapper extends BaseMapper<Car> {

    @Select("SELECT car_number,car_brand,total_duration,count(*) as abnormalCount FROM car_intrusion NATURAL JOIN car " +
            "WHERE car_number = #{carNumber}" +
            "GROUP BY car_number")
    CarDetailVo findDetails(String carNumber);

    @Select("SELECT car_number as carNumber,total_duration FROM car")
    Map<String,Double> findTotalDuration();
}
