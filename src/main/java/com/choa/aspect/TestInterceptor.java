package com.choa.aspect;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.choa.board.BoardDTO;
import com.choa.util.ListInfo;

public class TestInterceptor extends HandlerInterceptorAdapter{

	//무조건 Controller 빠져나온 후 하는 것이기 때문에 DispatcherServlet 으로 간다
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("Controller 나온 후 Dispatcher 가기전 확인 ");
		
		Map<String, Object> map =  modelAndView.getModel();
		Object list = map.get("list"); //list변수가 ar 이 될테지요
		Object listInfo = map.get("listInfo");
		Object board = map.get("board");
		
		modelAndView.setViewName("home"); // 이것이 DispatcherServlet으로 가기 때문에 view 가 바뀌게 되는 것 
		
		System.out.println("list 는 list 타입일것이야 0번째 contents = " + ((List<BoardDTO>)list).get(0).getContents());
		System.out.println("listInfo curBlock = " + ((ListInfo)listInfo).getCurBlock());
		System.out.println("board = " + board);
	}
	

	//Controller 가기전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		System.out.println("Controller 가기전 뭔가 체크해볼게요");
		
		
		//Controller로 보내고 싶으면 true, 아니라면 false 
		return true;
	}
	
	

}
