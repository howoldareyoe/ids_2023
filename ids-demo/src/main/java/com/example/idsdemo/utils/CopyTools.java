package com.example.idsdemo.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CopyTools
 * @Description TODO
 * @Author YU
 * @Date 2023/7/14 11:30
 * @Version 1.0
 **/
public class CopyTools {
    public static <T,S> List<T>copyList(List<S> slist,Class<T>classz) {
        List<T> list = new ArrayList<T>();
        for (S s : slist) {
            T t = null;
            try {
                t = classz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(s, t);
            list.add(t);

        }
        return list;
    }

    public static <T,S> T copy(S s ,Class<T> classz){
        T t = null;
        try {
            t = classz.newInstance();
        } catch (Exception e) {
           e.printStackTrace();
        }
        BeanUtils.copyProperties(s,t);
        return t;
    }
}
