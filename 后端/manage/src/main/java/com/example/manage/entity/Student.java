package com.example.manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

//代替原始生成getter和setter方法，自动生成getter和setter方法
@Data
public class Student {
//    设置注解与框架一致
    @TableId(value = "stuId")
    private Integer stuId;
    @TableField("stuName")      //因为使用了框架，所以驼峰命名会被转化为stu_name，需要重新注解一下
    private String stuName;
    @TableField("stuSex")
    private String stuSex;
    @TableField("stuNational")
    private String stuNational;
    @TableField("sfId")
    private String sfId;
    @TableField("stuDate")
    private String stuDate;
    @TableField("stuPhone")
    private String stuPhone;
    @TableField("stuAddress")
    private String stuAddress;
    @TableField("stuSecondarySchool")
    private String stuSecondarySchool;
    @TableField("stuClass")
    private String stuClass;
}
