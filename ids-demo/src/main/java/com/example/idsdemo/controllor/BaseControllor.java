package com.example.idsdemo.controllor;

import com.example.idsdemo.dto.SessionWebUserDto;
import com.example.idsdemo.entity.constants.Constants;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

/**
 * @ClassName BaseControllor
 * @Description TODO
 * @Author YU
 * @Date 2023/7/19 15:02
 * @Version 1.0
 **/
public class BaseControllor {
    protected SessionWebUserDto getUserInfoFromSession(HttpSession session){
        SessionWebUserDto sessionWebUserDto= (SessionWebUserDto) session.getAttribute(Constants.SESSION_KEY);
        return sessionWebUserDto;
    }


}
