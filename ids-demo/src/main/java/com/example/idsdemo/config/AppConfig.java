package com.example.idsdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @ClassName AppConfig
 * @Description TODO
 * @Author YU
 * @Date 2023/7/7 17:39
 * @Version 1.0
 **/
@Component("appConfig")
//@Configuration
public class AppConfig {
    @Value("${spring.mail.username:}")
    private String sendUserName;

    @Value("${admin.emails:}")
    private String adminEmails;

    @Value("${project.folder:}")
    private String projectFolder;

    @Value("${csv.out.folder}")
    private String csvOutFolder;

    @Value("${pcap.in.folder}")
    private String pcapInFolder;

    public String getAdminEmails() {
        return adminEmails;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public String getProjectFolder() {
        return projectFolder;
    }

    public String getCsvOutFolder() {
        return csvOutFolder;
    }

    public String getPcapInFolder() {
        return pcapInFolder;
    }
}
