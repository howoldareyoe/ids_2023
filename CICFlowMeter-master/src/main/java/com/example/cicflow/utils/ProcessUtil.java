package com.example.cicflow.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName ProcessUtil
 * @Description TODO
 * @Author YU
 * @Date 2023/11/17 10:29
 * @Version 1.0
 **/
public class ProcessUtil {
    public static void executeGradleTask(String inputFile, String outputFile) {
        // 获取操作系统名称
        String osName = System.getProperty("os.name").toLowerCase();

        // 根据操作系统类型选择命令
        String command;
        if (osName.contains("win")) {
            command = "cmd /c gradlew.bat exeCMD -PinputFile=" + inputFile + " -PoutputFile=" + outputFile;
        } else {
            command = "./gradlew exeCMD -PinputFile=" + inputFile + " -PoutputFile=" + outputFile;
        }

        // 设置工作目录
        File projectDir = new File(System.getProperty("user.dir"));
        File targetDir = new File(projectDir, "CICFlowMeter-master\\");

        try {
            Process process = Runtime.getRuntime().exec(command, null, targetDir);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 处理子进程的输出
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            assert exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
