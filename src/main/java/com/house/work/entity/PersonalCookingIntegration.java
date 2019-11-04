package com.house.work.entity;


import lombok.Data;

/**
 * @author yufeng li
 * @title: 个人烹饪积分
 * @description:
 * @date 2019/11/4 11:19
 */
@Data
public class PersonalCookingIntegration {

    /**
     * 个人id
     */
    private String id;

    /**
     * 个人名称
     */
    private String name;

    /**
     * 烹饪积分
     */
    private Integer integration;

    /**
     * 行为类型（1-吃饭，2-做饭，3-洗碗）
     */
    private Integer actionType;
}
