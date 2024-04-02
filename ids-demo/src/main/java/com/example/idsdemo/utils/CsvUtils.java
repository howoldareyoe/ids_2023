package com.example.idsdemo.utils;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CsvUtils
 * @Description TODO
 * @Author YU
 * @Date 2023/8/9 11:47
 * @Version 1.0
 **/
public class CsvUtils {

    public static ArrayList getResult(String path) {


        ArrayList<Map<String,String>> res = new ArrayList<>();
        System.out.println(path);

            // "E:\\国创\\algorithm\\test01.csv"
            try{
                // 第一参数：读取文件的路径 第二个参数：分隔符（不懂仔细查看引用百度百科的那段话） 第三个参数：字符集
                CsvReader csvReader = new CsvReader(path, ',', Charset.forName("UTF-8"));

                // 如果你的文件没有表头，这行不用执行
                // 这行不要是为了从表头的下一行读，也就是过滤表头
                csvReader.readHeaders();

                // 读取每行的内容
                while (csvReader.readRecord()) {
                    HashMap<String, String> currentRow = new HashMap<>();
                    // 获取内容的两种方式
                    // 1. 通过下标获取
//                    System.out.println(csvReader.get(0));
                    // 2. 通过表头的文字获取

                    System.out.println(" " + csvReader.get("Dst Port"));
                    currentRow.put("Dst Port",csvReader.get("Dst Port"));

                    System.out.println(" " + csvReader.get("Protocol"));
                    currentRow.put("Protocol",csvReader.get("Protocol"));

                    System.out.println(" " + csvReader.get("Timestamp"));
                    currentRow.put("Timestamp",csvReader.get("Timestamp"));

                    System.out.println(" " + csvReader.get("Flow Duration"));
                    currentRow.put("Flow Duration",csvReader.get("Flow Duration"));

                    System.out.println(" " + csvReader.get("Tot Fwd Pkts"));
                    currentRow.put("Tot Fwd Pkts",csvReader.get("Tot Fwd Pkts"));

                    System.out.println(" " + csvReader.get("Tot Bwd Pkts"));
                    currentRow.put("Tot Bwd Pkts",csvReader.get("Tot Bwd Pkts"));

                    System.out.println(" " + csvReader.get("Flow Byts/s"));
                    currentRow.put("Flow Byts/s",csvReader.get("Flow Byts/s"));

                    System.out.println(" " + csvReader.get("Flow Pkts/s"));
                    currentRow.put("Flow Pkts/s",csvReader.get("Flow Pkts/s"));

                    System.out.println(" " + csvReader.get("Fwd PSH Flags"));
                    currentRow.put("Fwd PSH Flags",csvReader.get("Fwd PSH Flags"));
                    System.out.println(" " + csvReader.get("SYN Flag Cnt"));
                    currentRow.put("SYN Flag Cnt",csvReader.get("SYN Flag Cnt"));
                    System.out.println(" " + csvReader.get("TotLen Fwd Pkts"));
                    currentRow.put("TotLen Fwd Pkts",csvReader.get("TotLen Fwd Pkts"));
                    System.out.println(" " + csvReader.get("TotLen Bwd Pkts"));
                    currentRow.put("TotLen Bwd Pkts",csvReader.get("TotLen Bwd Pkts"));

                    res.add(currentRow);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        return res;

    }
}
