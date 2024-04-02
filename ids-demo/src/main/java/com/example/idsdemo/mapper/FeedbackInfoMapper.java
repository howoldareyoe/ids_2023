package com.example.idsdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.idsdemo.entity.FeedbackInfo;
import com.example.idsdemo.vo.FeedbackSelectVo;
import org.apache.ibatis.annotations.Select;

public interface FeedbackInfoMapper extends BaseMapper<FeedbackInfo> {

    @Select("SELECT car_user_email as email, car_number, detection_src, feedback_time,feedback_desc " +
            "FROM car NATURAL JOIN feedback_info")
    FeedbackSelectVo[] findFeedback();
}
