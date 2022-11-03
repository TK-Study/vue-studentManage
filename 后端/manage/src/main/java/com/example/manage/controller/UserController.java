package com.example.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.manage.common.ConstantsImpl;
import com.example.manage.common.Result;
import com.example.manage.controller.dto.UserDTO;
import com.example.manage.controller.dto.UserMottoDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.example.manage.service.IUserService;
import com.example.manage.entity.User;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tk
 * @since 2022-10-15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    //更新或修改
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    //查询所有数据
    @GetMapping("/userList")
    public Result findAll() {
        return Result.success(userService.list());
    }

    //根据id查询
    @GetMapping("/userInfo/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }

    //分页查询
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        return Result.success(userService.page(new Page<>(pageNum, pageSize)));
    }

    //登录验证
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        //校验
        String userName = userDTO.getUserName();
        String password = userDTO.getPassword();
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return Result.error(ConstantsImpl.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    //通过用户名获取个性签名
    @GetMapping("/userMotto/{userName}")
    public Result getMotto(@PathVariable String userName){
        return Result.success(userService.getUserMotto(userName));
    }

    //修改密码接口
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody User user){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", user.getId());
        return Result.success(userService.update(user, updateWrapper));
    }
}
