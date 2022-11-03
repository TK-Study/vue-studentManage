package com.example.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.manage.common.Result;
import com.example.manage.entity.Student;
import com.example.manage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //获取到学生表信息
    @GetMapping("/getStudent")
    public Result findAll() {
        return Result.success(studentService.list());
    }

    //新增或更新学生表信息
    //@RequestBody注解含义：把前端传入的json数据转换为对象
    @PostMapping("/save")
    public Result save(@RequestBody Student student) {
        return Result.success(studentService.saveStudent(student));
    }

    //删除学生信息
    //@PathVariable注解含义：表示url参数
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(studentService.removeById(id));
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String stuName,             //当不传入条件时，将查询条件置为空
                                   @RequestParam(defaultValue = "") String stuId,
                                   @RequestParam(defaultValue = "") String stuSecondarySchool) {
        IPage<Student> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        //多条件查询，之间用or连接，而不是多条件一起查询
        //先判断前端是否把值传过来，再拼接字符串
        if(!"".equals(stuName)){
            queryWrapper.like("stuName", stuName);
        }
        if(!"".equals(stuId)){
            queryWrapper.like("stuId", stuId);
        }
        if(!"".equals(stuSecondarySchool)){
            queryWrapper.like("stuSecondarySchool", stuSecondarySchool);
        }
        //设置数据倒叙
        queryWrapper.orderByDesc("stuId");
        return Result.success(studentService.page(page, queryWrapper));
    }

    //获取表中所有记录
    @GetMapping("/studentCount")
    public Result getCount(){
        return Result.success(studentService.count());
    }
}
