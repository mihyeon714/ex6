package com.choa.ex6;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//Advice Controller 립니다
//한번에 처리하기 위함이 ControllerAdvice 여라
@ControllerAdvice
public class ExceptionController {
	
	

	@ExceptionHandler(Exception.class) // Exception 종류별로 설정가능 
	public String exception(Exception e, Model model){
		model.addAttribute("e", e.getMessage()); //개발 단계에서 사용하자
		return "error/notFound";
	}
	
	
	
	

}
