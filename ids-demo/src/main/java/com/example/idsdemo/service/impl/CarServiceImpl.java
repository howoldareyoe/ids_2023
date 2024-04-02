package com.example.idsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.entity.Car;
import com.example.idsdemo.mapper.CarMapper;
import com.example.idsdemo.service.CarService;
import com.example.idsdemo.utils.CopyTools;
import com.example.idsdemo.utils.StringTools;
import com.example.idsdemo.vo.CarDetailVo;
import com.example.idsdemo.vo.UserCommandVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CarServiceImpl
 * @Description TODO
 * @Author YU
 * @Date 2023/7/21 16:41
 * @Version 1.0
 **/
@Service
public class CarServiceImpl implements CarService {
    @Resource
    private CarMapper carMapper;
    @Override
    public List<UserCommandVo> findAllCars(Integer pageNo,Integer pageSize,String email) {
        QueryWrapper<Car> queryWrapper = new QueryWrapper<Car>().orderByDesc("created_time");
        if(!StringTools.isEmpty(email)){
            queryWrapper.eq("car_user_email",email);
        }
        Page<Car> page = new Page<>(pageNo,pageSize);

        List<Car> carList = carMapper.selectPage(page, queryWrapper).getRecords();
        List<UserCommandVo> userCommandVos = CopyTools.copyList(carList, UserCommandVo.class);
        return userCommandVos;
    }

    @Override
    public CarDetailVo findDetails(String carNumber) {
        CarDetailVo details = carMapper.findDetails(carNumber);
        return details;
    }


}
