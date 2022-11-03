package com.example.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.manage.common.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.example.manage.service.INoticeService;
import com.example.manage.entity.Notice;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tk
 * @since 2022-10-26
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private INoticeService noticeService;

    //更新或修改
    @PostMapping("/save")
    public Result save(@RequestBody Notice notice) {
        return Result.success(noticeService.saveOrUpdate(notice));
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(noticeService.removeById(id));
    }

    //查询所有数据
    @GetMapping("/list")
    public Result findAll() {
        return Result.success(noticeService.list());
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(noticeService.getById(id));
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String annuTime,             //当不传入条件时，将查询条件置为空
                           @RequestParam(defaultValue = "") String annuType,             //公告类型
                           @RequestParam(defaultValue = "") String annuName) {           //发布者
        IPage<Notice> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        //多条件查询，之间用or连接，而不是多条件一起查询
        //先判断前端是否把值传过来，再拼接字符串
        if(!"".equals(annuTime)){
            //先经过格式转化,把data类型转换为locdataTime
            queryWrapper.like("annu_time", annuTime);
        }
        if(!"".equals(annuType)){
            queryWrapper.like("annu_type", annuType);
        }
        if(!"".equals(annuName)){
            queryWrapper.like("annu_name", annuName);
        }
        //设置数据倒叙
        queryWrapper.orderByDesc("annu_id");
        return Result.success(noticeService.page(page, queryWrapper));
    }

    //条件查询，根据是否显示查询数据

}
