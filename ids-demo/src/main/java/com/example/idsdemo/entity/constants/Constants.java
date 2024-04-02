package com.example.idsdemo.entity.constants;

/**
 * @ClassName Constants
 * @Description TODO
 * @Author YU
 * @Date 2023/7/18 19:30
 * @Version 1.0
 **/
public class Constants {
    public static final Integer LENGTH_5 = 5;
    public static final Integer LENGTH_10 = 10;
    public static final Integer LENGTH_15 = 15;
    public static final Integer LENGTH_20 = 20;
    public static final Integer LENGTH_30 = 30;
    public static final Integer LENGTH_50 = 50;
    public static final Integer LENGTH_150 = 150;
    public static final String SESSION_KEY = "session_key";
    public static final String REDIS_KEY_SYS_SETTING = "ids2023:syssetting:";
    public static final Integer REDIS_KEY_EXPIRES_ONE_MIN = 60;
    public static final Integer REDIS_KEY_EXPIRES_FIVE_MIN = REDIS_KEY_EXPIRES_ONE_MIN * 5;

    public static final String FILE_FOLDER_FILE ="file/";
    public static final String FILE_FOLDER_AUDIO ="audio/";
    public static final String FILE_FOLDER_DETECTION = "detection/";
    public static final String AUDIO_SUFFIX = ".mp3";
    public static final String CSV_SUFFIX = ".csv";
}
