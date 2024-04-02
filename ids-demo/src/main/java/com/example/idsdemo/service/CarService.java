package com.example.idsdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.vo.CarDetailVo;
import com.example.idsdemo.vo.UserCommandVo;

import java.util.List;
import java.util.Map;

public interface CarService {
    List<UserCommandVo> findAllCars(Integer pageNo, Integer pageSize,String email);
    CarDetailVo findDetails(String carNumber);
}
