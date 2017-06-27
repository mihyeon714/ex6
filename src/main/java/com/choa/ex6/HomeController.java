package com.choa.ex6;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.choa.aspect.Transport;
import com.choa.aspect.Trip;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//AOP 연습하기
	@Autowired
	private Transport transport;
	
	@Autowired
	private Trip trip;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		// 루트 호출시 버스 타러가자
		transport.bus(); //앞뒤로 알아서 카드찍기가 실행되야한다 //핵심로직에만 신경쓰는 것이지요 
		transport.subway();
		
		//여행가기
		trip.go();
		
		
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
