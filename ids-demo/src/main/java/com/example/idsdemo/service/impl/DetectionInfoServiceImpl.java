package com.example.idsdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.config.AppConfig;
import com.example.idsdemo.entity.CarIntrusion;
import com.example.idsdemo.entity.DetectionInfo;
import com.example.idsdemo.entity.constants.Constants;
import com.example.idsdemo.mapper.CarIntrusionMapper;
import com.example.idsdemo.mapper.DetectionInfoMapper;
import com.example.idsdemo.mapper.FeedbackInfoMapper;
import com.example.idsdemo.service.DetectionInfoService;
import com.example.idsdemo.utils.AlgoUtils;
import com.example.idsdemo.utils.CsvUtils;
import com.example.idsdemo.utils.StringTools;
import com.example.idsdemo.vo.FeedbackSelectVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DetectionInfoServiceImpl
 * @Description TODO
 * @Author YU
 * @Date 2023/7/31 21:16
 * @Version 1.0
 **/
@Service
public class DetectionInfoServiceImpl implements DetectionInfoService {
    @Resource
    private FeedbackInfoMapper feedbackInfoMapper;
    @Resource
    private DetectionInfoMapper detectionInfoMapper;
    @Resource
    private CarIntrusionMapper carIntrusionMapper;
    @Resource
    private AppConfig appConfig;
    @Override
    public FeedbackSelectVo[] findFeedback() {
        FeedbackSelectVo[] feedback = feedbackInfoMapper.findFeedback();
        return feedback;
    }

    public String postDetectioFile(String carNumber, MultipartFile file){
        String folderName = appConfig.getProjectFolder()+Constants.FILE_FOLDER_FILE+Constants.FILE_FOLDER_DETECTION+carNumber+"/"+System.currentTimeMillis()+Constants.CSV_SUFFIX;

        File toFile = new File(folderName);
        if(!toFile.exists()){
            toFile.mkdirs();
        }
        try {
            file.transferTo(toFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return folderName;
    }
    @Override
    public ArrayList<Map<String,String>> doDetection(String carNumber,String path){
        ArrayList<Map<String,String>> from = CsvUtils.getResult(path);
        ArrayList<String> result = AlgoUtils.getResult(path);
        DetectionInfo detectionInfo = new DetectionInfo();
        CarIntrusion carIntrusion = new CarIntrusion();
        for (int i = 0; i < from.size(); i++) {
            int isAbnormal = 0;
            if(!result.get(i).equals("Benign")){
                isAbnormal=1;
            }
            String detectionInfoId = StringTools.getRandomNumber(Constants.LENGTH_5);
            detectionInfo.setDetectionId(detectionInfoId);
            detectionInfo.setCarNumber(carNumber);
            detectionInfo.setDetectionTime(new Date());
            detectionInfo.setIsAbnormal(isAbnormal);
            detectionInfo.setDetectionDesc(from.get(i).toString());
            System.out.println(from.get(i).toString());
            detectionInfoMapper.insert(detectionInfo);


            if(isAbnormal==1){
                carIntrusion.setCarIntrusionId(StringTools.getRandomNumber(Constants.LENGTH_5));
                carIntrusion.setCarNumber(carNumber);
                carIntrusion.setIntrusionTime(new Date());
                carIntrusion.setIntrusionType(result.get(i));

                carIntrusion.setIntrusionSrc(detectionInfoId);
                carIntrusionMapper.insert(carIntrusion);
            }

            from.get(i).put("isAbnormal",isAbnormal==1?"异常":"正常");
        }
        return from;
    }

    public List<DetectionInfo> getDetectionres(QueryWrapper<DetectionInfo> queryWrapper, Page<DetectionInfo> page){
        List<DetectionInfo> records = detectionInfoMapper.selectPage(page, queryWrapper).getRecords();
        return records;
    }

    // 异常记录详细
    public DetectionInfo findIntrusionDetails(String no){
        DetectionInfo detectionInfo = detectionInfoMapper.selectOne(new QueryWrapper<DetectionInfo>().eq("detection_id", no));
        return detectionInfo;
    }



}
