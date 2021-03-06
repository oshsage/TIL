package com.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.frame.Biz;

public class App {

	public static void main(String[] args) {
		System.out.println("App Start ....");
		AbstractApplicationContext factory =
		new GenericXmlApplicationContext("com.xml");
		System.out.println("Spring Started ....");
		
		Biz biz = (Biz)factory.getBean("ubiz");
		biz.register();
		
		factory.close();
		System.out.println("Spring End ....");
		System.out.println("App End ....");
	}

	
}


/* 
	1. ctx로 파싱할 때는 AbstractApplicationContext를 이용하고 
  		xml파일을 담을 때는 GenericXmlApplicationContext를 이용하는 구나 하고 가볍게 넘어가면 된다.
	
	2. ApplicationContext을 만들면 xml에 설정한 bean이 모두 '일단' '생성'된다.
		현재 myspring.xml에는 'UserBiz'클래스로 생성한 bean인 ubiz와 ibiz가 있다.
		일단 생성해놓고 getBean으로 가져오는 방식임.
*/