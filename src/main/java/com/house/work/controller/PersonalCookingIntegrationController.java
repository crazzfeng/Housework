package com.house.work.controller;

import com.house.work.entity.PersonalCookingIntegration;
import com.house.work.service.LoginService;
import com.house.work.service.PersonalCookingIntegrationService;
import com.house.work.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yufeng li
 * @title: PersonalCookingIntegrationController
 * @description:
 * @date 2019/11/4 14:40
 */
@Controller
public class PersonalCookingIntegrationController{

    @Autowired
    private PersonalCookingIntegrationService personalCookingIntegrationService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,String name, String password){
        try {
            int login = loginService.login(request,response,name, password);
            if (login<=0){
                return "login";
            }else {
                return "firstPage";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }

    }

    @RequestMapping("/getPersonalInfo")
    @ResponseBody
    public Result getPersonalInfo(String id){
        try {
            PersonalCookingIntegration info = personalCookingIntegrationService.getPersonalInfoById(id);
            return Result.success(info);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询个人信息失败");
        }

    }



}
