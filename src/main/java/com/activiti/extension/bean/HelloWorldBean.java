package com.activiti.extension.bean;

import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activiti.service.runtime.FormProcessingService;

@Component
public class HelloWorldBean implements JavaDelegate {

//    @Autowired
//    private SampleService sampleService;

	@Autowired
    protected TaskService taskService;

	@Autowired
    protected FormProcessingService formProcessingService;


	@Override
    public void execute(DelegateExecution delegateExecution) {
    	 System.out.println("execute  myvar = ${myvar} ...");
    	 String myvar = (String) delegateExecution.getVariable("myvar");
    	 System.out.println("myvar = " + myvar);
        //sampleService.doSomeTask();
    }

}