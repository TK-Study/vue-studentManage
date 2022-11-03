package com.example.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
public interface StudentMapper extends BaseMapper<Student> {
//    //查询学生的信息
//    @Select("SELECT * FROM student")
//    List<Student> findAll();
//
//    //添加学生信息
//    @Insert("INSERT INTO student(stuId, stuName, stuSex, stuNational, sfId, stuDate, stuPhone, stuAddress, stuSecondarySchool, stuClass) " +
//            "VALUES(#{stuId},#{stuName},#{stuSex}, #{stuNational}, #{sfId}, #{stuDate}, #{stuPhone}, #{stuAddress}, #{stuSecondarySchool}, #{stuClass})")
//    int insert(Student student);
//
//    //修改学生信息，SQL语句交由mybatis管理
//    int update(Student student);
//
//    //删除学生信息
//    //@param注解表示让框架知道参数
//    @Delete("DELETE FROM student WHERE stuId = #{id}")
//    Integer deleteById(@Param("id") Integer id);
}
