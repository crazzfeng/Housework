package com.house.work.dao;

import com.house.work.entity.PersonalCookingIntegration;
import com.house.work.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yufeng li
 * @title: PersonalCookingIntegration
 * @description:
 * @date 2019/11/4 11:31
 */
@Repository
public interface PersonalCookingIntegrationDao {

    void insert(PersonalCookingIntegration personalCookingIntegration);

    void update(PersonalCookingIntegration personalCookingIntegration);

    PersonalCookingIntegration getPersonalInfoById(String id);

    List<PersonalCookingIntegration> list(PersonalCookingIntegration personalCookingIntegration);

    List<PersonalCookingIntegration> listByPage(Page page, PersonalCookingIntegration personalCookingIntegration);
    int count(Page page, PersonalCookingIntegration personalCookingIntegration);

}
