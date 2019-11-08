package com.house.work.entity;


import lombok.Data;

/**
 * @author yufeng li
 * @title: UserInfo
 * @description:
 * @date 2019/11/7 9:32
 */
@Data
public class UserInfo {

    /**
     * id
     */
    String id;

    /**
     * 登录名
     */
    String loginName;

    /**
     * 密码
     */
    String password;

}
