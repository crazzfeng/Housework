package com.house.work.dao;

import com.house.work.entity.UserInfo;
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

    UserInfo login(@Param("loginName") String loginName, @Param("password") String password);

    int regist(@Param("id") String id, @Param("loginName") String loginName, @Param("password") String password);

}
