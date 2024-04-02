package com.example.idsdemo.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MyHandler extends TextWebSocketHandler {

    private static MyHandler instance;
    private WebSocketSession session;

    private MyHandler() {}

    public static MyHandler getInstance() {
        if (instance == null) {
            instance = new MyHandler();
        }
        return instance;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        this.session = session;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 这里处理接收到的消息
    }

    public void sendMessage(String message) {
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
