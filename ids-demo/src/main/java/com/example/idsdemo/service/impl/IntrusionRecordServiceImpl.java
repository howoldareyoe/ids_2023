package com.example.idsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.commons.enums.FindCarIntrusionTypeEnum;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.entity.CarIntrusion;
import com.example.idsdemo.entity.DetectionInfo;
import com.example.idsdemo.mapper.CarIntrusionMapper;
import com.example.idsdemo.mapper.CarMapper;
import com.example.idsdemo.mapper.DetectionInfoMapper;
import com.example.idsdemo.service.IntrusionRecordService;
import com.example.idsdemo.vo.IntrusionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IntrusionRecordServiceImpl
 * @Description TODO
 * @Author YU
 * @Date 2023/7/21 15:44
 * @Version 1.0
 **/
@Service
public class IntrusionRecordServiceImpl implements IntrusionRecordService {

    @Resource
    private CarIntrusionMapper carIntrusionMapper;
    @Resource
    private CarMapper carMapper;
    // 管理员查看所有记录
    public List<IntrusionVo> findAllCarIntrusions(Integer pageNo, Integer pageSize){
        List<IntrusionVo> carIntrusions = carIntrusionMapper.findAllCarIntrusions((pageNo - 1) * pageSize, pageSize);
        return carIntrusions;
    }

    @Override
    public List<IntrusionVo> findCarIntrusions(Integer pageNo, Integer pageSize, Integer byType, String by) {
        List<IntrusionVo> carIntrusions = carIntrusionMapper.findCarIntrusions((pageNo - 1) * pageSize, pageSize
                , FindCarIntrusionTypeEnum.getEnumByCode(byType).getName(), by);
        return carIntrusions;
    }

    @Override
    public List<Map<String, Integer>> findIntrusionStatistics() {
        List<Map<String, Integer>> intrusionStatistics = carIntrusionMapper.findIntrusionStatistics();
        return intrusionStatistics;
    }

    @Override
    public List<Map<String, Integer>> findCarBrandStatistics() {
        List<Map<String, Integer>> carBrandStatistics = carIntrusionMapper.findCarBrandStatistics();
        return carBrandStatistics;
    }

    //用户端查看异常记录
    public List<CarIntrusion> getIntrusion(QueryWrapper<CarIntrusion> queryWrapper, Page<CarIntrusion> page){
        List<CarIntrusion> records = carIntrusionMapper.selectPage(page, queryWrapper).getRecords();
        return records;
    }


}
