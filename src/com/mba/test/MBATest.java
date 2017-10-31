package com.mba.test;

import org.springframework.aop.framework.ProxyFactory;

import com.mba.beans.AuditAdvice;
import com.mba.beans.LoanManager;
import com.mba.beans.SecurityCheckAdvice;
import com.mba.helper.AuthenticationHelper;

public class MBATest {

	public static void main(String[] args) {
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(new LoanManager());
		pf.addAdvice(new AuditAdvice());
		pf.addAdvice(new SecurityCheckAdvice());

		AuthenticationHelper helper = AuthenticationHelper.getInstance();
		helper.login("john", "welcome1");//valid username & password
		
		LoanManager proxy = (LoanManager) pf.getProxy();
		boolean status = proxy.approveLoan("l1001");
		System.out.println("Status: " + status);
		
		helper.logout();
	}

}
