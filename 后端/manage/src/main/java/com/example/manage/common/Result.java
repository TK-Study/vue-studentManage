package com.example.manage.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 包装类，和前端约束请求是否成功，接口统一返回包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;     //返回前端错误或成功信息
    private Object data;    //返回数据

    //请求成功无参的方法
    public static Result success(){
        return new Result(ConstantsImpl.CODE_200, "", null);
    }

    //请求成功有参的方法,返回前端对象
    public static Result success(Object data){
        return new Result(ConstantsImpl.CODE_200, "", data);
    }

    //请求失败有参的方法
    public static Result error(){
        return new Result(ConstantsImpl.CODE_500, "系统错误", null);
    }

    //请求失败有参的方法
    public static Result error(String code, String msg){
        return new Result(code, msg, null);
    }
}
