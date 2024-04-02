package com.example.idsdemo.service;

import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.mapper.UserMapper;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserInfoService
 * @Description TODO
 * @Author YU
 * @Date 2023/7/18 18:46
 * @Version 1.0
 **/
public interface UserService {
    Result register(String email, String carNumber, String carBrand);
    Result login(String email,String emailCode);
    Result loginAdmin(String userName,String password);
    void postAudio (MultipartFile file,String email);
    void getAudio(HttpServletResponse response,String email);
    void feedBack(String carNumber,String srcId,String desc);
    String getCarBrandByCarNumber(String carNumber);

}
