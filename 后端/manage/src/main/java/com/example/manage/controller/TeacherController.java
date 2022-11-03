package com.example.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.manage.common.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.example.manage.service.ITeacherService;
import com.example.manage.entity.Teacher;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tk
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private ITeacherService teacherService;

    //更新或修改
    @PostMapping("/save")
    public Result save(@RequestBody Teacher teacher){
        return Result.success(teacherService.saveOrUpdate(teacher));
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(teacherService.removeById(id));
    }

    //查询所有数据
    @GetMapping("/getTeacher")
    public Result findAll() {
        return Result.success(teacherService.list());
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(teacherService.list());
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String teaName,             //当不传入条件时，将查询条件置为空
                                  @RequestParam(defaultValue = "") String teaId,
                                  @RequestParam(defaultValue = "") String teaSecondarySchool) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        //多条件查询，之间用or连接，而不是多条件一起查询
        //先判断前端是否把值传过来，再拼接字符串
        if(!"".equals(teaName)){
            queryWrapper.like("teaName", teaName);
        }
        if(!"".equals(teaId)){
            queryWrapper.like("teaId", teaId);
        }
        if(!"".equals(teaSecondarySchool)){
            queryWrapper.like("teaSecondarySchool", teaSecondarySchool);
        }
        //设置数据倒叙
        queryWrapper.orderByDesc("teaId");
        return Result.success(teacherService.page(new Page<>(pageNum, pageSize),queryWrapper));
    }

    //获取表记录条数
    @GetMapping("/teacherCount")
    public Result getCount(){
        return Result.success(teacherService.count());
    }
}
