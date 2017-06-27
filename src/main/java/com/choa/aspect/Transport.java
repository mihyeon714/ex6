package com.choa.aspect;

import org.springframework.stereotype.Component;

@Component
public class Transport {
	
	//핵심 로직 만들기
	//카드찍는거 말고 어떻게 오느냐가 핵심
	
	
	public void bus(){
		System.out.println(" 자리 싸움 ");
		System.out.println(" 옆사람 핸드폰 보기 ");
		System.out.println(" 버스버스 ");
	}
	
	
	public void subway(){
		System.out.println(" 누가누가 내릴까요 알아맞춰 보세요 ");
		System.out.println(" 영상 보기 ");
		System.out.println(" 지하철지하철 ");
	}

}
