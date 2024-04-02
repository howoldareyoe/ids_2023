package com.example.idsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.idsdemo.entity.CarIntrusion;
import com.example.idsdemo.vo.IntrusionVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CarIntrusionMapper extends BaseMapper<CarIntrusion> {
    @Select("SELECT intrusion_time,car_user_email as user_email,intrusion_type,car_number,intrusion_src " +
            "FROM car_intrusion NATURAL JOIN car LIMIT #{limitStart},#{pageSize}")
    List<IntrusionVo> findAllCarIntrusions(Integer limitStart, Integer pageSize);

    @Select("SELECT intrusion_time,car_user_email as user_email,intrusion_type,car_number " +
            "FROM car_intrusion NATURAL JOIN car " +
            "WHERE #{byType} = #{by} " +
            "LIMIT #{limitStart},#{pageSize}")
    List<IntrusionVo> findCarIntrusions(Integer limitStart,Integer pageSize, String byType,String by);

    @Select("SELECT intrusion_type as intrusionDes,count(*) as count FROM car_intrusion NATURAL JOIN intrusion GROUP BY intrusionDes")
    List<Map<String,Integer>> findIntrusionStatistics();

    @Select("SELECT car_brand as carBrand,count(*) as count FROM car_intrusion NATURAL JOIN car GROUP BY carBrand")
    List<Map<String,Integer>> findCarBrandStatistics();
}
