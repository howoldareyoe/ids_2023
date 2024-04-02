package com.example.idsdemo.utils;

import com.example.idsdemo.config.MyHandler;
import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.namednumber.TcpPort;

import java.io.EOFException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ReadPacketFile {

    private static final Logger logger = Logger.getLogger(ReadPacketFile.class.getName());

    public static void readPcapFile(String pcapFilePath) throws NotOpenException, PcapNativeException {
        PcapHandle handle;
        MyHandler myHandler = MyHandler.getInstance();
        try {
            handle = Pcaps.openOffline(pcapFilePath, PcapHandle.TimestampPrecision.NANO);
        } catch (PcapNativeException e) {
            handle = Pcaps.openOffline(pcapFilePath);
        }

        // 获取项目的根路径
        String rootPath = System.getProperty("user.dir");
        // 创建日志文件的目录
        String logDir = rootPath + "/log_pack_receive";
        Path logDirPath = Paths.get(logDir);
        if (!Files.exists(logDirPath)) {
            try {
                Files.createDirectories(logDirPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 设置日志处理器
        FileHandler fileHandler = null;
        try {
            // 获取当前日期和时间
            LocalDateTime now = LocalDateTime.now();
            // 创建日期时间格式化器
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
            // 格式化当前日期和时间
            String formattedNow = now.format(formatter);
            // 使用格式化的日期和时间作为文件名
            fileHandler = new FileHandler(logDir + "/log_" + formattedNow + ".log", 1024 * 1024 * 1024, 24, true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                Packet packet = handle.getNextPacketEx();
                logger.info(packet.toString()); // 将数据包信息写入日志
                myHandler.sendMessage(packet.toString()); // 将数据包信息发送到前端
                TcpPacket tcpPacket = packet.get(TcpPacket.class);
                if (tcpPacket != null && tcpPacket.getHeader().getDstPort().equals(TcpPort.HTTP)) {
                    if (tcpPacket.getPayload() != null) {
                        byte[] payload = tcpPacket.getPayload().getRawData();
                        logger.info("Payload: " + new String(payload)); // 将payload写入日志
                        myHandler.sendMessage("Payload: " + new String(payload)); // 将数据包信息发送到前端
                    } else {
                        logger.info("TCP packet has no payload."); // 将信息写入日志
                        myHandler.sendMessage("TCP packet has no payload."); // 将数据包信息发送到前端
                    }
                }
            } catch (TimeoutException e) {
            } catch (EOFException e) {
                logger.info("EOF"); // 将EOF写入日志
                break;
            }
        }
        fileHandler.close();
        handle.close();
    }

    public static void main(String[] args) throws NotOpenException, PcapNativeException {
        String pcapFilePath = "E:\\input_test\\logon.pcapng"; // 替换为你的 pcap 文件路径
        ReadPacketFile.readPcapFile(pcapFilePath);
    }
}
