package com.house.work.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.house.work.dao.LoginDao;
import com.house.work.entity.UserInfo;
import com.house.work.service.LoginService;
import com.house.work.util.MD5Util;
import com.config.redis.RedisUtil;
import com.house.work.util.Seq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yufeng li
 * @title: LoginServiceImpl
 * @description:
 * @date 2019/11/5 16:42
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource(type = LoginDao.class)
    private LoginDao loginDao;

    @Resource(type = RedisUtil.class)
    private RedisUtil redisUtil;

    @Override
    public UserInfo login(HttpServletRequest request, HttpServletResponse response, String name, String password) {
        String s = MD5Util.MD5(password);
        UserInfo login = loginDao.login(name, s);
        if (login != null) {
            String token = Seq.createSeqS();
           /* redisUtil.set("loginName", login.getLoginName(), 1800L);
            redisUtil.set("id", login.getId().getBytes(), 1800L);
            redisUtil.set("password", login.getPassword().getBytes(), 1800L);*/
            redisUtil.set(token, JSONObject.toJSONString(login), 1800L);//登录信息放入缓存
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            if ("https".equals(request.getScheme())) {
                cookie.setSecure(true);
            }
            response.addCookie(cookie);
            return login;
        } else {
            return null;
        }
    }

    @Override
    public UserInfo getUserInfo(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //从cookie中拿到token
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0){
            return null;
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())){
                token = cookie.getValue();
            }
        }
        //通过token拿到登录信息
        String userJson = redisUtil.get(token);
        return JSON.parseObject(userJson,UserInfo.class);
    }

    @Override
    public UserInfo regist(HttpServletRequest request, HttpServletResponse response, String name, String password) {
        String s = MD5Util.MD5(password);
        String uuid = UUID.randomUUID().toString();
        loginDao.regist(uuid, name, s);
        UserInfo login = loginDao.login(name, s);
        if (login != null) {
            String token = Seq.createSeqS();
           /* redisUtil.set("loginName", login.getLoginName(), 1800L);
            redisUtil.set("id", login.getId().getBytes(), 1800L);
            redisUtil.set("password", login.getPassword().getBytes(), 1800L);*/
            redisUtil.set(token, JSONObject.toJSONString(login), 1800L);//登录信息放入缓存
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            if ("https".equals(request.getScheme())) {
                cookie.setSecure(true);
            }
            response.addCookie(cookie);
            return login;
        } else {
            return null;
        }
    }

}
