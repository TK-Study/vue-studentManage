package com.example.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.manage.common.ConstantsImpl;
import com.example.manage.controller.dto.UserDTO;
import com.example.manage.controller.dto.UserMottoDTO;
import com.example.manage.entity.User;
import com.example.manage.exception.ServiceException;
import com.example.manage.mapper.UserMapper;
import com.example.manage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.manage.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tk
 * @since 2022-10-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserDTO login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userDTO.getUserName());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try{
            one = getOne(queryWrapper);    //从数据库查询数据信息
        }catch (Exception e){
            throw new  ServiceException(ConstantsImpl.CODE_500,"系统错误");
        }
        if(one != null){
            BeanUtils.copyProperties(one, userDTO, String.valueOf(true));
            //设置Token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else {
            throw new  ServiceException(ConstantsImpl.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public UserMottoDTO getUserMotto(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", userName);
        queryWrapper.select("motto", "id");
        User userMotto = getOne(queryWrapper);
        UserMottoDTO userMottoDTO = new UserMottoDTO();
        BeanUtils.copyProperties(userMotto,userMottoDTO);
        return userMottoDTO;
    }
}
