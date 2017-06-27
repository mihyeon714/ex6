package com.choa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CardCheck {
	
	//부가적인 요소
	//카드찍기
	//예약하기
	
	@Before("execution(* com.choa.aspect..Trip.*(..))")
	public void reservation(){
		System.out.println("예약 완료");
		System.out.println("=============================");
	}
	
	
	//around할때 필요한것
	@Around("execution(* com.choa.aspect..Transport.*(..))")
	public Object check(ProceedingJoinPoint join){ //join에 핵심함수 객체가 들어오는것 //bus, subway 
		
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
