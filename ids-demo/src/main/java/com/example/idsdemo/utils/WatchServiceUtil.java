package com.example.idsdemo.utils;

import com.example.idsdemo.config.AppConfig;
import com.example.idsdemo.service.impl.ConvertPcap2CsvRestTemplateClient;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WatchServiceUtil {

    @Autowired
    private ConvertPcap2CsvRestTemplateClient convert;
    private final WatchService watchService;
    private final Path folderPath;
    @Resource
    private AppConfig appConfig;

    public WatchServiceUtil() {
        // "E:\\input_test"
        this.folderPath = Paths.get("E:\\input_test");
        try {
            this.watchService = FileSystems.getDefault().newWatchService();
            this.folderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize WatchServiceUtil", e);
        }
    }

    public void startWatching(String outputFolderPath) throws NotOpenException, PcapNativeException {
        System.out.println("Watching folder: " + folderPath);



        while (true) {
            WatchKey key;
            // 创建线程池
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            try {
                key = watchService.take(); // 阻塞等待事件
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // 线程被中断，结束监听
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    Path filePath = (Path) event.context();
                    if (filePath.toString().endsWith(".pcap")) {
                        String inputFile = folderPath.resolve(filePath).toString();
                        String outputFile = outputFolderPath;
//                                + filePath.getFileName().toString() + ".csv";
                        System.out.println("Detected new file: " + inputFile);
                        System.out.println("Output file: " + outputFile);

                        //pcap写日志
                        // 创建两个任务分别执行两个方法
                        Runnable task1 = () -> {
                            try {
                                ReadPacketFile.readPcapFile(inputFile);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };
                        ReadPacketFile.readPcapFile(inputFile);
//                         调用转换方法
                        Runnable task2 = () -> {
                            try {
                                convert.convertPcap2Csv(inputFile, outputFile);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        };
                        convert.convertPcap2Csv(inputFile, outputFile);

                        // 提交任务给线程池
                        executorService.submit(task1);
                        executorService.submit(task2);
                    }
                }
            }
            // 关闭线程池
            executorService.shutdown();
            key.reset(); // 重置 key
        }


    }

    public void stopWatching() {
        try {
            watchService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
