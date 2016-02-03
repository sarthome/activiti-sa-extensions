package com.activiti.extension.bean;

import java.util.List;
import java.util.Random;

import com.activiti.domain.idm.User;
import com.activiti.service.api.UserService;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DFUserService {
	
	@Autowired
	UserService userService;
	
	static final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	String randomString(int len){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}

    public void showUsers(String value) {
        System.out.println("DFUserService.showUsers(value) started ... and  value: " + value);
        List<User> users = userService.getAllUsers(0, 20);
        for (User user : users) {
        	System.out.println("user=" + user.getEmail());
        }
    }

    
    public void getUsersCount(String value, DelegateExecution delegateExecution) {
        System.out.println("DFUserService.showUsers(value) started ...  value: " + value);
        System.out.println("delegateExecution.getVariable: " + (String)delegateExecution.getVariable("value"));
        
        Long cnt = userService.getUserCount();
        System.out.println("users count =" + cnt);
        delegateExecution.setVariable("outValue", cnt);
        
    }
    
    public void createDemoUsers(DelegateExecution delegateExecution) {
        System.out.println("DFUserService.createDemoUsers started ... ");
        System.out.println("delegateExecution.getVariable: " + (String)delegateExecution.getVariable("value"));

        long cnt = Long.valueOf((String)delegateExecution.getVariable("value"));
        
        long cntUsers = userService.getUserCount().longValue();
        System.out.println("cntUsers:" + cntUsers);
        
        for (long i = 1; i <= cnt; i++) {
        	String user = randomString(10);
        	String firstName = user+String.valueOf(i+cntUsers);
        	String lastName = "User"+String.valueOf(i+cntUsers); 
        	String email = firstName+"@mail.com";
        	String password = "user";
        	System.out.println(email+", "+firstName+", "+lastName+", "+password);
        	userService.createNewUser(email, firstName, lastName, password, "");
        }
        delegateExecution.setVariable("outValue", cnt);
    }

}
