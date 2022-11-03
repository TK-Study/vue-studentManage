package com.example.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2022-10-26
 */
@Getter
@Setter
  @ApiModel(value = "Notice对象", description = "")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("公告ID")
        @TableId(value = "annu_id", type = IdType.AUTO)
      private Integer annuId;

      @ApiModelProperty("标题")
      private String annuTitle;

      @ApiModelProperty("类型")
      private String annuType;

      @ApiModelProperty("主体")
      private String annuBody;

      @ApiModelProperty("作者")
      private String annuName;

      @ApiModelProperty("作者身份")
      private String annuIdentity;

      @ApiModelProperty("公告发布时间")
      private String annuTime;

      @ApiModelProperty("是否显示")
      private Boolean isShow;
}
