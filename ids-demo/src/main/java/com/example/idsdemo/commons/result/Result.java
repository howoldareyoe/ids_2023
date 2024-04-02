package com.example.idsdemo.commons.result;

import com.example.idsdemo.commons.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private Integer status;
    private Object data;
    private String msg;




    public static Result success(){
        return new Result(ResultEnum.Success.getStatus(),null,ResultEnum.Success.getMsg());
    }

    public static Result success(String msg){
        return new Result(ResultEnum.Success.getStatus(),null,msg);
    }

    public static Result success(String msg,Object data){
        return new Result(ResultEnum.Success.getStatus(),data,msg);
    }

    public static Result error(){
        return new Result(ResultEnum.Error.getStatus(),null,ResultEnum.Success.getMsg());
    }

    public static Result error(String msg){
        return new Result(ResultEnum.Error.getStatus(),null,msg);
    }

    public static Result error(String msg,Object data){
        return new Result(ResultEnum.Error.getStatus(),data,msg);
    }
}
