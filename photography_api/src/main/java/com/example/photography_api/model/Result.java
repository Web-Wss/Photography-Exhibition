package com.example.photography_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Result<T> implements Serializable {


    private Integer code;//状态码
    private String msg;//提示信息
    private T data;//数据


    //    成功
    public static <T> Result<T> success(T data){
        return new Result<>(200,"success",data);
    }

    public static <T> Result<T> success(String msg,T data){
        return new Result<>(200,msg,data);
    }

    public static <T> Result<T> success(String msg){
        return new Result<>(200,msg,null);
    }

    //    失败
    public static <T> Result<T> fail() {
        return new Result<>(400,"fail",null);
    }

    public static <T> Result<T> fail(String msg){
        return new Result<>(400,msg,null);
    }

    public static <T> Result<T> fail(String msg,T data){
        return new Result<>(400,msg,data);
    }

}
