package com.house.work.service.impl;

import com.house.work.base.BaseDao;
import com.house.work.base.BaseServiceImpl;
import com.house.work.dao.HouseworkTypeDao;
import com.house.work.entity.HouseworkType;
import com.house.work.service.HouseworkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author yufeng li
 * @title: HouseworkTypeDao
 * @description:
 * @date 2019/11/19 16:00
 */
@Service
public class HouseworkTypeServiceImpl extends BaseServiceImpl<HouseworkType,String> implements HouseworkTypeService {

    @Autowired
    private HouseworkTypeDao houseworkTypeDao;

    @Override
    public BaseDao<HouseworkType, String> getDao() {
        return houseworkTypeDao;
    }


}
