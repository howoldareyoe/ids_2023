package com.example.idsdemo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @ClassName AlgoUtils
 * @Description TODO
 * @Author YU
 * @Date 2023/8/9 10:33
 * @Version 1.0
 **/
public class AlgoUtils {


    public static ArrayList<String> getResult(String path) {

        ArrayList<String> result =new ArrayList<>();
        StringBuilder sb= new StringBuilder();
    try{
        Process process = Runtime.getRuntime().exec("E:\\anaconda\\Scripts"+"\\activate.bat pytorch && python "+" E:\\国创\\algorithm\\gru.py "+path);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        in = new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));
        //接收错误流
        BufferedReader    isError = new BufferedReader(new InputStreamReader(process.getErrorStream(),"gbk"));

        StringBuilder sbError= new StringBuilder();
        String line=null;
        String lineError= null;
        line = in.readLine();
        line = in.readLine();
        line = in.readLine();
        while ((line = in.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
            System.out.println(line);
            result.add(line);
        }
//        System.out.println(sb);

        while ((lineError= isError.readLine()) != null) {
            sbError.append(lineError);
            sbError.append("\n");
        }
//        System.out.println(sbError);
        in.close();
        isError.close();
//        System.out.println(sb.toString());
    }catch (Exception e){
        e.printStackTrace();
    }
        return result;
    }
}
