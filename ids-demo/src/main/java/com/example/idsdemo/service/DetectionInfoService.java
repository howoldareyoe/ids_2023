package com.example.idsdemo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.entity.DetectionInfo;
import com.example.idsdemo.vo.FeedbackSelectVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DetectionInfoService {
    FeedbackSelectVo[] findFeedback();
    ArrayList<Map<String,String>> doDetection(String carNumber,String path);
    List<DetectionInfo> getDetectionres(QueryWrapper<DetectionInfo> queryWrapper, Page<DetectionInfo> page);
    DetectionInfo findIntrusionDetails(String no);
}
