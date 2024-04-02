package com.example.idsdemo.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @ClassName ProcessUtils
 * @Description TODO
 * @Author YU
 * @Date 2023/7/15 17:18
 * @Version 1.0
 **/
public class ProcessUtils extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(ProcessUtils.class);
    public static String executeCommand(String cmd, Boolean outprintLog){
        if(StringTools.isEmpty(cmd)){
            logger.error("指令执行失败，因为要执行的指令为空");
            return null;
        }
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try{
            process = Runtime.getRuntime().exec(cmd);

            PrintStream errorStream = new PrintStream(process.getErrorStream());
            PrintStream inputStream = new PrintStream(process.getInputStream());
            errorStream.start();
            inputStream.start();

            process.waitFor();
            // 获取执行结果字符串
            String result = errorStream.stringBuffer.append(inputStream.stringBuffer+"\n").toString();
            // 输出执行命令信息

            if(outprintLog){
                logger.info("执行命令：{}，已执行完毕，执行结果：{}",cmd,result);
            }else{
                logger.info("执行命令：{}，已执行完成",cmd);
            }
            return result;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("失败");
        }finally {
            if(null!=process){
                ProcessKiller ffmpegKiller = new ProcessKiller(process);
                runtime.addShutdownHook(ffmpegKiller);
            }
        }
    }

    private static class ProcessKiller extends Thread{
        private Process process;
        public ProcessKiller(Process process){
            this.process = process;
        }
        @Override
        public void run(){this.process.destroy();}
    }

    /*
     * @Author YU
     * @Description 用于取出线程执行过程中产生的各种输出和错误流的信息
     * @Date 14:20 2023/7/17
     * @Param
     * @return
     **/
    static class PrintStream extends Thread{
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();

        public PrintStream(InputStream inputStream){this.inputStream = inputStream;}

        @Override
        public void run(){
            try {
                if(null == inputStream){
                    return;
                }
                bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
                String line = null;
                while((line = bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                }
            } catch (Exception e){
                logger.error("读取输入流出错了。错误信息："+e.getLocalizedMessage());
            }finally {
                try {
                    if(null!=bufferedReader){
                        bufferedReader.close();
                    }
                    if(null!=inputStream){
                        inputStream.close();
                    }
                }catch (Exception e){
                    logger.error("调用PrintStream读取输出流后，关闭流时出错");
                }
            }
        }
    }



}
