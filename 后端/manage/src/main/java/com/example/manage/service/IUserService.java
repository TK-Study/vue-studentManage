package com.example.manage.service;

import com.example.manage.controller.dto.UserDTO;
import com.example.manage.controller.dto.UserMottoDTO;
import com.example.manage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tk
 * @since 2022-10-15
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    UserMottoDTO getUserMotto(String userName);
}
