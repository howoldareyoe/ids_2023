package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName CicFlow
 * @Description TODO
 * @Author YU
 * @Date 2023/11/17 10:22
 * @Version 1.0
 **/
@EnableEurekaClient
@SpringBootApplication
public class CicFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(CicFlowApplication.class,args);
    }
}
