package com.example.idsdemo;

import com.example.idsdemo.config.AppConfig;
import com.example.idsdemo.utils.WatchServiceUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication
@MapperScan("com.example.idsdemo.mapper")
@EnableScheduling
@EnableEurekaClient
public class IdsDemoApplication {

    @Resource
    private AppConfig appConfig;
    @Autowired
    private WatchServiceUtil watchServiceUtil;

    public static void main(String[] args) {
        SpringApplication.run(IdsDemoApplication.class, args);

    }
    // 在 Spring 上下文初始化后执行
    @PostConstruct
    public void startWatchService() throws NotOpenException, PcapNativeException {
        new Thread(() -> {
            try {
                watchServiceUtil.startWatching("E:\\output_test\\");
            } catch (NotOpenException | PcapNativeException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
