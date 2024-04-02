package com.example.idsdemo.service;

public interface LogRecordService {
    void getStart(String carNumber);

    void getEnd(String carNumber);

    Double getDayDetectionTime(String carNumber);

    Double getTotalDetectionTime(String carNumber);
}
