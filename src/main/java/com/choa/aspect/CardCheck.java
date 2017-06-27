package com.choa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class CardCheck {
	
	//부가적인 요소
	//카드찍기
	//예약하기
	
	
	public void reservation(){
		System.out.println("예약 완료");
		System.out.println("=============================");
	}
	
	
	
	public Object check(ProceedingJoinPoint join){ //join에 핵심함수 객체가 들어오는것 //bus, subway //around할때 필요한것
		
		System.out.println("들어갈 때 카드 찍기");
		
		
		Object obj = null;
		
		try {
			obj = join.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		System.out.println("나갈 때 카드 찍기");
		
		return obj;
		
	}
	
	
	

}
