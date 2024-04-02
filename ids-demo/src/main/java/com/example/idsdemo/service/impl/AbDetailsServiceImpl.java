package com.example.idsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.entity.AbDetails;
import com.example.idsdemo.mapper.AbDetailsMapper;
import com.example.idsdemo.service.AbDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName AbDetailsServiceImpl
 * @Description TODO
 * @Author YU
 * @Date 2023/11/29 13:38
 * @Version 1.0
 **/
@Service
public class AbDetailsServiceImpl implements AbDetailsService {
    @Resource
    private AbDetailsMapper abDetailsMapper;

    @Override
    public Result getAllAb() {
        QueryWrapper<AbDetails> query = new QueryWrapper<>();
        List<AbDetails> abDetails = abDetailsMapper.selectList(query);

        return Result.success("查询成功",abDetails);
    }
}
