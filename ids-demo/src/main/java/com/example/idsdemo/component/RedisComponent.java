package com.example.idsdemo.component;

import com.example.idsdemo.dto.SysSettingsDto;
import com.example.idsdemo.entity.constants.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName redisComponent
 * @Description TODO
 * @Author YU
 * @Date 2023/7/7 18:07
 * @Version 1.0
 **/
@Component("redisComponet")
public class RedisComponent {

    @Resource
    private RedisUtils redisUtils;

    public SysSettingsDto getSysSettingsDto() {
        SysSettingsDto sysSettingsDto = (SysSettingsDto) redisUtils.get(Constants.REDIS_KEY_SYS_SETTING);

        if (null == sysSettingsDto) {
            sysSettingsDto = new SysSettingsDto();
            redisUtils.set(Constants.REDIS_KEY_SYS_SETTING, sysSettingsDto);
        }
        return sysSettingsDto;
    }
    public String getEmailCodeByEmail(String email){
        return (String) redisUtils.get(email);
    }

    public void setEmailCode(String email,String code){
        redisUtils.setx(email,code,Constants.REDIS_KEY_EXPIRES_FIVE_MIN);
    }


}
