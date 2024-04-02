package com.example.idsdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class ConvertPcap2CsvRestTemplateClient {

    @Autowired
    RestTemplate restTemplate;

    public void convertPcap2Csv(String inputFile, String outputFile) {
        if (restTemplate == null) {
            System.err.println("restTemplate is null");
            return;
        }
        String url = "http://localhost:6081/api/convert";

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 封装请求体
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("inputFile", inputFile);
        map.add("outputFile", outputFile);

        // 发送 POST 请求
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, new HttpEntity<>(map, headers), String.class);

        // 处理返回结果
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String result = responseEntity.getBody();
            System.out.println("Conversion result: " + result);
        } else {
            System.err.println("Error during conversion. Status code: " + responseEntity.getStatusCode());
        }
    }
}