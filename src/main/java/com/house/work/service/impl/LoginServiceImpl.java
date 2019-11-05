package com.house.work.service.impl;

import com.house.work.dao.LoginDao;
import com.house.work.service.LoginService;
import com.house.work.util.MD5Util;
import com.house.work.util.Seq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yufeng li
 * @title: LoginServiceImpl
 * @description:
 * @date 2019/11/5 16:42
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    private ValueOperations<String, String> redisCache;

    @Override
    public int login(HttpServletRequest request, HttpServletResponse response, String name, String password) {
        String s = MD5Util.MD5(password);
        int login = loginDao.login(name, s);
        if (login>0){
            String token = Seq.createSeqS();
            redisCache.append("name",name);
            redisCache.append("password",s);
            redisCache.append("token", token);
            Cookie cookie = new Cookie("token",token);
            cookie.setPath("/");
            if ("https".equals(request.getScheme())) {
                cookie.setSecure(true);
            }
            response.addCookie(cookie);
            return 1;
        }else {
            return 0;
        }
    }
}
