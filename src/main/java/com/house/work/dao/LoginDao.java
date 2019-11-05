package com.house.work.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yufeng li
 * @title: LoginDao
 * @description:
 * @date 2019/11/5 16:43
 */
@Repository
public interface LoginDao {

    int login(@Param("name") String name, @Param("password") String password);
}
