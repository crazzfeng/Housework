package com.house.work.base;

import java.util.List;

/**
 * @author yufeng li
 * @title: BaseService
 * @description:
 * @date 2019/11/19 17:04
 */
public abstract class BaseServiceImpl<T,K> implements  BaseService<T,K>{

    public abstract BaseDao<T, K> getDao();

    @Override
    public int insert(T t) {
        return this.getDao().insert(t);
    }

    @Override
    public int delete(K k) {
        return this.getDao().delete(k);
    }

    @Override
    public int update(T t) {
        return this.getDao().update(t);
    }

    @Override
    public List<T> select(T t) {
        return this.getDao().select(t);
    }

    @Override
    public T selectById(K k) {
        return this.getDao().selectById(k);
    }
}
