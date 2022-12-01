package com.SchoolAPI.SchoolAPI.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    @Autowired
    public School school;
    public String displaySchoolAccount() {
        return "School account is $" + school.getSchool_Account();
    }
    public void depositSchoolAccount(int money) {
        school.setSchool_Account(school.getSchool_Account() + money);
    }
    public void payment(int money) {
        school.setSchool_Account(school.getSchool_Account() - money);
    }
}
