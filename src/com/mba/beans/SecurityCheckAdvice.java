package com.mba.beans;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import com.mba.helper.AuthenticationHelper;

public class SecurityCheckAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		AuthenticationHelper helper = AuthenticationHelper.getInstance();
		if(helper.authenticate()== false) {
			throw new IllegalAccessException("Invalid username & password");
		}
	}

}
