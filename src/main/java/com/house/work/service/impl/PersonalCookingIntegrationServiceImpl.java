package com.house.work.service.impl;


import com.house.work.dao.PersonalCookingIntegrationDao;
import com.house.work.entity.PersonalCookingIntegration;
import com.house.work.service.PersonalCookingIntegrationService;
import com.house.work.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yufeng li
 * @title: PersonalCookingIntegrationServiceImpl
 * @description:
 * @date 2019/11/4 14:28
 */
@Service
public class PersonalCookingIntegrationServiceImpl implements PersonalCookingIntegrationService {

    @Autowired
    private PersonalCookingIntegrationDao personalCookingIntegrationDao;

    @Override
    public void insert(PersonalCookingIntegration personalCookingIntegration) {
        personalCookingIntegrationDao.insert(personalCookingIntegration);
    }

    @Override
    public void update(PersonalCookingIntegration personalCookingIntegration) {
        personalCookingIntegrationDao.update(personalCookingIntegration);
    }

    @Override
    public PersonalCookingIntegration getPersonalInfoById(String id) {
        return personalCookingIntegrationDao.getPersonalInfoById(id);
    }

    @Override
    public List<PersonalCookingIntegration> list(PersonalCookingIntegration personalCookingIntegration) {
        return personalCookingIntegrationDao.list(personalCookingIntegration);
    }

    @Override
    public Page<PersonalCookingIntegration> listByPage(Page<PersonalCookingIntegration> page, PersonalCookingIntegration personalCookingIntegration) {
        List<PersonalCookingIntegration> personalCookingIntegrations = personalCookingIntegrationDao.listByPage(page, personalCookingIntegration);
        int count = personalCookingIntegrationDao.count(page, personalCookingIntegration);
        page.setTotalCount(count);
        page.setData(personalCookingIntegrations);
        return page;
    }
}
