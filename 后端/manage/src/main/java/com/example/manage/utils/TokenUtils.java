package com.example.manage.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.manage.entity.User;
import com.example.manage.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 生成Token的工具类
 * */

@Component
public class TokenUtils {

    public static IUserService staticUserService;

    @Resource
    public IUserService userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }


    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    public static String genToken(String userId, String sign){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withAudience(userId)       //将user id保存到Token，作为载荷
                .withExpiresAt(date)    //一小时后Token过期
                .sign(Algorithm.HMAC256(sign));     //以password作为token的秘钥
    }


    /**
     * 获取当前用户信息
     * User对象
     * */
    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StringUtils.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
