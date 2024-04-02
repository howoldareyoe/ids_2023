package com.example.idsdemo.service;

import com.example.idsdemo.commons.result.Result;

public interface ReceiveLogService {
    Result getLogOneHour(String date);
    Result getLatestLog();
}
