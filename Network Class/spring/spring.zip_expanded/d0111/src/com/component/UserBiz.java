package com.component;

import org.springframework.stereotype.Component;

import com.biz.Biz;
@Component("ubiz")
public class UserBiz implements Biz {
	

	public UserBiz() {
		System.out.println("UserBiz constructor ");
	}
	public void startLog() {
		System.out.println("StartLog constructor ");
	}
	
	@Override
	public void register() {
		System.out.println("User register");
	}
	
	@Override
	public void remove() {
		System.out.println("User remove");

	}

	@Override
	public void modify() {
		System.out.println("User modify");

	}

}
