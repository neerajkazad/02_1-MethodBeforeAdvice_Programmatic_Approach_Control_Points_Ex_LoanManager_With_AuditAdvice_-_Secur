package com.mba.helper;

import com.mba.beans.UserInfo;

public class AuthenticationHelper {
	private static AuthenticationHelper instance;
	ThreadLocal<UserInfo> threadLocal = null;

	private AuthenticationHelper() {
		threadLocal = new ThreadLocal<UserInfo>();
	}

	public synchronized static AuthenticationHelper getInstance() {
		if (instance == null) {
			instance = new AuthenticationHelper();
		}
		return instance;
	}

	/**
	 * Store username/password of this user at current thread of execution
	 * @param username
	 * @param password
	 */
	public void login(String username, String password) {
		/*
		 * UserInfo userInfo = null; userInfo = new UserInfo(username,
		 * password); threadLocal.set(userInfo);
		 */
		// this below line is equivalent to above three line here we are writing
		// one liner
		threadLocal.set(new UserInfo(username, password));

	}

	public void logout() {
		threadLocal.set(null);
	}

	public boolean authenticate() {
		UserInfo ui = null;
		ui = threadLocal.get();
		if (ui != null) {
			if (ui.getUsername().equals("john") && ui.getPassword().equals("welcome1")) {
				return true;
			}
		}
		return false;
	}

	public String getLoggedInUser() {
		UserInfo ui = null;
		ui = threadLocal.get();
		if (ui != null) {
			return ui.getUsername();
		}
		return null;
	}
}
