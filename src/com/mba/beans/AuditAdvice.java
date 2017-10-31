package com.mba.beans;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import com.mba.helper.AuthenticationHelper;

public class AuditAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		//before target class method executes perform auditing
		//john has accessed approveLoan with the loanNo
		System.out.print(AuthenticationHelper.getInstance().getLoggedInUser()+" has accessed " + method.getName() + " with the loanNo" + "(");
		for (int i = 0; i < args.length; i++) {
			if (i == 0) {
				System.out.print(args[i]);
				continue;
			}
			System.out.print("," + args[i]);
		}
		System.out.println(")");
		/*if (method.getName().equals("approveLoan")) {
			String loanNo = (String) args[0];
			args[0] = loanNo + "-m";
		}*/
		
		// done with cross-cutting logic, don't do anything automatically 
		// control will passed to target class method
	}

}
