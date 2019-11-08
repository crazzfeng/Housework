package com.house.work.service.impl;

import com.house.work.dao.LoginDao;
import com.house.work.entity.UserInfo;
import com.house.work.service.LoginService;
import com.house.work.util.MD5Util;
import com.house.work.util.RedisUtil;
import com.house.work.util.Seq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int login(HttpServletRequest request, HttpServletResponse response, String name, String password) {
        String s = MD5Util.MD5(password);
        UserInfo login = loginDao.login(name, s);
        if (login != null) {
            String token = Seq.createSeqS();
            redisUtil.set("loginName", login.getLoginName(), 1800L);
            redisUtil.set("id", login.getId().getBytes(), 1800L);
            redisUtil.set("password", login.getPassword().getBytes(), 1800L);
            redisUtil.set("token", token.getBytes(), 1800L);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            if ("https".equals(request.getScheme())) {
                cookie.setSecure(true);
            }
            response.addCookie(cookie);
            return 1;
        } else {
            return 0;
        }
    }
}
