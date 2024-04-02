package com.example.idsdemo.controllor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idsdemo.commons.result.Result;
import com.example.idsdemo.entity.DetectionInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * @ClassName WsTestControllor
 * @Description TODO
 * @Author YU
 * @Date 2023/8/12 15:24
 * @Version 1.0
 **/
@Component
@ServerEndpoint("/ws")
public class WsTestControllor {

    @OnMessage
    public void getDetectionRes(Session session, String message) throws IOException {
        System.out.println(message);
        sendMessage(session,"收到拉");

    }
    public void sendMessage(Session session, String message) throws IOException {
            session.getBasicRemote().sendText(message);
    }


    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ip= inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }

}
