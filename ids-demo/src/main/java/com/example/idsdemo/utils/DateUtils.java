package com.example.idsdemo.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtils
 * @Description TODO
 * @Author YU
 * @Date 2023/7/22 14:42
 * @Version 1.0
 **/
public class DateUtils {

    public static Long getTodayZero(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long todayZero = calendar.getTimeInMillis();
        return todayZero;
    }
    public static int minsBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        if (date1 == null || date2 == null) {
            return 0;
        }
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        //算上当天
        return Math.abs(Integer.parseInt(String.valueOf((time2 - time1) / 60000L)) + 1);
    }

    public static double hoursBetween(Date date1, Date date2) {
       return minsBetween(date1,date2)/60.0;
    }


}
