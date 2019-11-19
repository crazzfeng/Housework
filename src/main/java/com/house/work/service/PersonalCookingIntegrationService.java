package com.house.work.service;

import com.house.work.entity.PersonalCookingIntegration;
import com.house.work.util.Page;

import java.util.List;

/**
 * @author yufeng li
 * @title: PersonalCookingIntegration
 * @description:
 * @date 2019/11/4 11:31
 */
public interface PersonalCookingIntegrationService {

    void insert(PersonalCookingIntegration personalCookingIntegration);

    void update(PersonalCookingIntegration personalCookingIntegration);

    PersonalCookingIntegration getPersonalInfoById(Integer id);

    List<PersonalCookingIntegration> list(PersonalCookingIntegration personalCookingIntegration);

    Page<PersonalCookingIntegration> listByPage(Page<PersonalCookingIntegration> page, PersonalCookingIntegration personalCookingIntegration);

}
