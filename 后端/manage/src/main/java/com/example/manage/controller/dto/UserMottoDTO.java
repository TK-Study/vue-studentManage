package com.example.manage.controller.dto;

import lombok.Data;

/**
 * 返回前端用户的id和motto
 * */
@Data
public class UserMottoDTO {
    private Integer id;
    private String motto;
}
