package com.example.idsdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @ClassName SysSettingsDto
 * @Description TODO
 * @Author YU
 * @Date 2023/7/7 18:10
 * @Version 1.0
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysSettingsDto implements Serializable {

    private String registerMailTitle = "邮箱验证码";

    private String registerEmailContent = "您好，您的邮箱验证码是：%s,5分钟有效";


    public String getRegisterMailTitle() {
        return registerMailTitle;
    }

    public void setRegisterMailTitle(String registerMailTitle) {
        this.registerMailTitle = registerMailTitle;
    }

    public String getRegisterEmailContent() {
        return registerEmailContent;
    }

    public void setRegisterEmailContent(String registerEmailContent) {
        this.registerEmailContent = registerEmailContent;
    }

}
