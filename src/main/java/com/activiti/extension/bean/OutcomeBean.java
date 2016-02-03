package com.activiti.extension.bean;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activiti.domain.runtime.Form;
import com.activiti.exception.FormValidationException;
import com.activiti.service.runtime.FormProcessingService;

@Component
public class OutcomeBean {
	
	@Autowired
	IdentityService identityService;	

	@Autowired
    protected TaskService taskService;
	
	@Autowired
    protected RuntimeService runtimeService;

	@Autowired
    protected FormProcessingService formProcessingService;

	public boolean checkOutcomeVar(String taskName, String outcome, DelegateExecution delegateExecution) {
		Boolean result = false;
		ProcessInstance process = runtimeService.createProcessInstanceQuery()
				.processInstanceId(delegateExecution.getProcessInstanceId()).singleResult();
		Task task = taskService.createTaskQuery().processInstanceId(process.getId()).taskName(taskName).singleResult();
		if (task == null) {
			throw new FormValidationException("Task '" + taskName + "' was not found");
		}
		Form form = formProcessingService.getTaskForm(task.getId());
		String formNNoutcome = "form" + Long.toString(form.getId()) + "outcome";
		String outcomeResult = (String) delegateExecution.getVariable(formNNoutcome);
		if (outcomeResult.equals(outcome)) {
			result = true;
		}
		return result;
	}
}
