package com.example.manage.exception;

import lombok.Getter;

/*
* 自定义异常，继承类
* */
@Getter
public class ServiceException extends RuntimeException{
    private String code;

    //构造器
    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
