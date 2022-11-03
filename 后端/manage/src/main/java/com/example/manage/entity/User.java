package com.example.manage.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author tk
 * @since 2022-10-15
 */
@Getter
@Setter
  @ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        private Integer id;

      @ApiModelProperty("用户名")
      @TableField("userName")
      private String userName;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("身份")
      private String identity;

      @ApiModelProperty("电话号码")
      private String phone;

      @ApiModelProperty("真实姓名")
      private String name;

      @ApiModelProperty("头像")
      private String avatar;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("座右铭")
      private String motto;

      @ApiModelProperty("部门")
      private String department;


}
