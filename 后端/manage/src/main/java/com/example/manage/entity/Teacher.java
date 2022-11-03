package com.example.manage.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author tk
 * @since 2022-10-15
 */
@ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("教师工号")
      @TableId(value = "teaId")
      private Integer teaId;

      @ApiModelProperty("教师姓名")
      @TableField("teaName")
      private String teaName;

      @ApiModelProperty("教师性别")
      @TableField("teaSex")
      private String teaSex;

      @ApiModelProperty("教师政治面貌")
      @TableField("teaPolitical")
      private String teaPolitical;

      @ApiModelProperty("教师民族")
      @TableField("teaNational")
      private String teaNational;

      @ApiModelProperty("身份证号")
      @TableField("sfId")
      private String sfId;

      @ApiModelProperty("教师出生日期")
      @TableField("teaDate")
      private LocalDate teaDate;

      @ApiModelProperty("教师家庭住址")
      @TableField("teaAddress")
      private String teaAddress;

      @ApiModelProperty("教师电话号码")
      @TableField("teaPhone")
      private String teaPhone;

      @ApiModelProperty("教师所属二级学院")
      @TableField("teaSecondarySchool")
      private String teaSecondarySchool;

    
    public Integer getTeaId() {
        return teaId;
    }

      public void setTeaId(Integer teaId) {
          this.teaId = teaId;
      }
    
    public String getTeaName() {
        return teaName;
    }

      public void setTeaName(String teaName) {
          this.teaName = teaName;
      }
    
    public String getTeaSex() {
        return teaSex;
    }

      public void setTeaSex(String teaSex) {
          this.teaSex = teaSex;
      }
    
    public String getTeaPolitical() {
        return teaPolitical;
    }

      public void setTeaPolitical(String teaPolitical) {
          this.teaPolitical = teaPolitical;
      }
    
    public String getTeaNational() {
        return teaNational;
    }

      public void setTeaNational(String teaNational) {
          this.teaNational = teaNational;
      }
    
    public String getSfId() {
        return sfId;
    }

      public void setSfId(String sfId) {
          this.sfId = sfId;
      }
    
    public LocalDate getTeaDate() {
        return teaDate;
    }

      public void setTeaDate(LocalDate teaDate) {
          this.teaDate = teaDate;
      }
    
    public String getTeaAddress() {
        return teaAddress;
    }

      public void setTeaAddress(String teaAddress) {
          this.teaAddress = teaAddress;
      }
    
    public String getTeaPhone() {
        return teaPhone;
    }

      public void setTeaPhone(String teaPhone) {
          this.teaPhone = teaPhone;
      }
    
    public String getTeaSecondarySchool() {
        return teaSecondarySchool;
    }

      public void setTeaSecondarySchool(String teaSecondarySchool) {
          this.teaSecondarySchool = teaSecondarySchool;
      }

    @Override
    public String toString() {
        return "Teacher{" +
              "teaId=" + teaId +
                  ", teaName=" + teaName +
                  ", teaSex=" + teaSex +
                  ", teaPolitical=" + teaPolitical +
                  ", teaNational=" + teaNational +
                  ", sfId=" + sfId +
                  ", teaDate=" + teaDate +
                  ", teaAddress=" + teaAddress +
                  ", teaPhone=" + teaPhone +
                  ", teaSecondarySchool=" + teaSecondarySchool +
              "}";
    }
}
