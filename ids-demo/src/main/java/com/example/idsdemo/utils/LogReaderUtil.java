package com.example.idsdemo.utils;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogReaderUtil {
    public static String readLogs(String directory, String date) {
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        StringBuilder logs = new StringBuilder();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            Date d = sdf.parse(date);
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfHour = new SimpleDateFormat("HH");
            String formattedDate = sdfDate.format(d);
            String formattedHour = sdfHour.format(d);

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().startsWith("log_" + formattedDate + "_" + formattedHour)) {
                    try {
                        logs.append(new String(Files.readAllBytes(Paths.get(file.getPath()))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return logs.toString();
    }
}
