package com.example.idsdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.vo.IntrusionVo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface IntrusionRecordService {
    List<IntrusionVo> findAllCarIntrusions(Integer limitStart, Integer pageSize);

    List<IntrusionVo> findCarIntrusions(Integer limitStart, Integer pageSize,Integer byType,String by);

    List<Map<String, Integer>> findIntrusionStatistics();
    List<Map<String, Integer>> findCarBrandStatistics();

}
