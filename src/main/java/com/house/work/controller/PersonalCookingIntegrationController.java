package com.house.work.controller;

import com.house.work.entity.HouseworkType;
import com.house.work.entity.PersonalCookingIntegration;
import com.house.work.entity.UserInfo;
import com.house.work.service.HouseworkTypeService;
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
@RequestMapping("/")
public class PersonalCookingIntegrationController{

    @Autowired
    private PersonalCookingIntegrationService personalCookingIntegrationService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private HouseworkTypeService houseworkTypeService;

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request, HttpServletResponse response,String name, String password){
        try {
            UserInfo login = loginService.login(request,response,name, password);
            if (login != null){
                return Result.success();
            }else {
                return Result.error("登录失败");
            }
         /*   if (login<=0){
                return "login";
            }else {
                return "firstPage";
            }*/
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("登录失败");
        }

    }

    @RequestMapping("/getPersonalInfo")
    @ResponseBody
    public Result getPersonalInfo(Integer id){
        try {
            PersonalCookingIntegration info = personalCookingIntegrationService.getPersonalInfoById(id);
            return Result.success(info);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询个人信息失败");
        }
    }


    @RequestMapping("/dowork")
    @ResponseBody
    public Result dowork(HttpServletRequest request,Integer typeId){
        try {
            UserInfo userInfo = loginService.getUserInfo(request);
            HouseworkType houseworkType = houseworkTypeService.selectById(typeId);
            PersonalCookingIntegration info = personalCookingIntegrationService.getPersonalInfoById(userInfo.getId());
            if (info == null){
                info = new PersonalCookingIntegration();
                info.setId(userInfo.getId());
                info.setName(userInfo.getLoginName());
                info.setIntegration(houseworkType.getScore());
                personalCookingIntegrationService.insert(info);
            }else {
                info.setIntegration(houseworkType.getScore()+info.getIntegration());
                personalCookingIntegrationService.update(info);
            }
            return Result.success(info);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询个人信息失败");
        }

    }


}
