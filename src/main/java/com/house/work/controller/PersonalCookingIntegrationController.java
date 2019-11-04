package com.house.work.controller;

import com.house.work.service.PersonalCookingIntegrationService;
import com.house.work.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yufeng li
 * @title: PersonalCookingIntegrationController
 * @description:
 * @date 2019/11/4 14:40
 */
@Controller("/personal/cooking/")
public class PersonalCookingIntegrationController {

    @Autowired
    private PersonalCookingIntegrationService personalCookingIntegrationService;


    @RequestMapping("/getPersonalInfo")
    public Result getPersonalInfo(){
        try {
            return Result.success("查询个人信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("查询个人信息失败");
        }

    }
}
