package com.house.work.base;

import java.util.List;

/**
 * @author yufeng li
 * @title: BaseService
 * @description:
 * @date 2019/11/19 17:04
 */
public interface BaseService<T,K> {
    int insert(T t);

    int delete(K k);

    int update(T t);

    List<T> select(T t);

    T selectById(K k);
}
