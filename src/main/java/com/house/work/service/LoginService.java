package com.house.work.service;

import com.house.work.entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yufeng li
 * @title: LoginService
 * @description:
 * @date 2019/11/5 16:41
 */
public interface LoginService {

   UserInfo login(HttpServletRequest request, HttpServletResponse response, String name, String password);

   UserInfo getUserInfo(HttpServletRequest request);

   UserInfo regist(HttpServletRequest request, HttpServletResponse response, String name, String password);
}
