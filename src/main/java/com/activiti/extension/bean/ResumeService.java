package com.activiti.extension.bean;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResumeService {
	
	@Autowired
	IdentityService identityService;	

    public void storeResume() {
        System.out.println("Storing resume myvar = ${myvar} ...");
        List<User> users = identityService.createUserQuery().list();
        for (User user : users) {
        	System.out.println("user=" + user.getEmail());
        }
    }

}
