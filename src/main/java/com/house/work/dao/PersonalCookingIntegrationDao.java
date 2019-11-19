package com.house.work.dao;

import com.house.work.entity.PersonalCookingIntegration;
import com.house.work.util.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yufeng li
 * @title: PersonalCookingIntegration
 * @description:
 * @date 2019/11/4 11:31
 */
@Repository
//@Mapper   //第一种方式 为再Application类中增加注解 @MapperScan(basePackages = "com.house.work.dao")
            //第二种方式 为到层增加@Mapper 但其Sql语句 需以注解的方式实现
public interface PersonalCookingIntegrationDao {


    /*@Insert("INSERT INTO account_electricitybill_detail (id, name, integration, actionType)" +
            "VALUES (#{cookingIntegration.id}, #{cookingIntegration.name}, #{cookingIntegration.integration}," +
            "#{cookingIntegration.actionType})")*/
    @BatchDataSource
    void insert(/*@Param("cookingIntegration")*/ PersonalCookingIntegration personalCookingIntegration);

    /*@Update("UPDATE account_electricitybill_detail" +
            "SET name = #{cookingIntegration.name},integration = #{cookingIntegration.integration}" +
            ",actionType = #{cookingIntegration.actionType}" +
            "where id = #{cookingIntegration.id}")*/
    void update(/*@Param("cookingIntegration")*/ PersonalCookingIntegration personalCookingIntegration);

    /*@Select("SELECT id, name, integration, actionType  FROM account_electricitybill_detail  where id = #{id}")*/
    PersonalCookingIntegration getPersonalInfoById(/*@Param("id")*/ Integer id);

   /* @Select("<script> SELECT id, name, integration, actionType  " +
            "FROM account_electricitybill_detail " +
            "where 1=1 " +
            "<if test='id!=null'> " +
            "   and id = #{id}" +
            "</if>" +
            "<if test='name!=null'> " +
            "   and name = #{name}" +
            "</if>" +
            "<if test='integration!=null'> " +
            "   and integration = #{integration}" +
            "</if>" +
            "<if test='actionType!=null'> " +
            "   and actionType = #{actionType}" +
            "</if>"
    )*/
    List<PersonalCookingIntegration> list(/*@Param("cookingIntegration")*/PersonalCookingIntegration personalCookingIntegration);

    List<PersonalCookingIntegration> listByPage(Page page, PersonalCookingIntegration personalCookingIntegration);
    int count(Page page, PersonalCookingIntegration personalCookingIntegration);

}
