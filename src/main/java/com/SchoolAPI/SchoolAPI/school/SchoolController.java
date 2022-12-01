package com.SchoolAPI.SchoolAPI.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class SchoolController  {
    @Autowired
    public SchoolService schoolService;

    @GetMapping("/schoolAccount")
    public String schoolAccount() {
        return this.schoolService.displaySchoolAccount();
    }
}
