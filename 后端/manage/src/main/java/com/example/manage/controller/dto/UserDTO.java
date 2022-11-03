package com.example.manage.controller.dto;

import lombok.Data;
/**
 * 接受前端登录请求的参数，返回前端参数
 * */
@Data
public class UserDTO {
    private String userName;
    private String password;
    private String name;
    private String avatar;
    private Integer id;
    private String phone;
    private String identity;
    private String Token;       //返回Token
}
