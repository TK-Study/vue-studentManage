package com.example.manage.config.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.manage.common.ConstantsImpl;
import com.example.manage.entity.User;
import com.example.manage.exception.ServiceException;
import com.example.manage.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        //执行认证
        if(StringUtils.isBlank(token)){
            throw new ServiceException(ConstantsImpl.CODE_401, "无Token，请重新登录");
        }

        //获取token中的userId
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException(ConstantsImpl.CODE_401, "token验证失败，请重新登录");
        }
        //根据token中的userId查询id存不存在
        User user = userService.getById(userId);
        if(user == null){
            throw new ServiceException(ConstantsImpl.CODE_401, "用户不存在，请重新登录");
        }
        //用户密码加签验证Token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new ServiceException(ConstantsImpl.CODE_401, "token验证失败，请重新登录");
        }
        return true;
    }
}