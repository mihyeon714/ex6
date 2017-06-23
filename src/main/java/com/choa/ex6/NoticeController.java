package com.choa.ex6;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeServiceImpl;
import com.choa.util.ListInfo;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	//service객체는 하나뿐이면 된다 
	//@Inject 는 어딘가에 만들어진 객체를 주입해주셈 이라는 말임 
	//@Inject
	@Autowired
	private NoticeServiceImpl noticeService;
	

	@RequestMapping(value="noticeList",method=RequestMethod.GET)
	public String noticeList(Model model,ListInfo listInfo) throws Exception{

		//System.out.println("listInfo curPage= "+listInfo.getCurPage());
		
		List<BoardDTO> ar = noticeService.boardList(listInfo);
		
		
		model.addAttribute("list", ar);
		model.addAttribute("board", "notice");
		model.addAttribute("listInfo", listInfo);
		
		return "board/boardList";
	}
	
	
	@RequestMapping(value="noticeView",method=RequestMethod.GET)
	public String noticeView(Integer num, Model model) throws Exception{
		if(num == null){
			num = 1;
		}
		BoardDTO noticeDTO = noticeService.boardView(num);
		model.addAttribute("board", "notice");
		model.addAttribute("dto", noticeDTO);
		return "board/boardView";
	}
	
	@RequestMapping(value="noticeWrite",method=RequestMethod.GET)
	public String noticeWrite(Model model){
		model.addAttribute("path", "Write");
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	@RequestMapping(value="noticeWrite",method=RequestMethod.POST)
	public String noticeWrite(BoardDTO noticeDTO, Model model,RedirectAttributes rd) throws Exception{
		int result = noticeService.boardWrite(noticeDTO);
		String message = "Write FAIL";
		if(result > 0){
			message = "Write SUCCESS";
		}
		//model.addAttribute("message", message);
		//return "notice/noticeList?curPage=1";//이렇게 해도 리스트로 안감 기본적으로 forward 이기 때문임
		//return "notice/result"; // WEB-INF/views/notice/result.jsp //이건 fowarding 한것이고
		
		//바로 listpage로 가고싶다 //이때는 message 는 못사용한다 //redirect의 개념은 니가 주소에 쳐라 의미임
		
		
		rd.addFlashAttribute("message", message);
		return "redirect:/notice/noticeList?curPage=1"; //여기는 서버단이기 때문에 /ex1 이 아니라 / 로 쳐주면 된다
		//현재 위치가 /notice 이기 때문에 상대경로로 ./noticeList 라고 해도된다 
		//spring에서는 redirect 할 때도 message 를 보낼 수 있도록 객체를 제공한다 RedirectAttributes
	}
	
	
	//Form 호출
	@RequestMapping(value="noticeUpdate",method=RequestMethod.GET)
	public String noticeUpdate(Integer num, Model model) throws Exception{
		BoardDTO noticeDTO = noticeService.boardView(num); //DTO 하나를 받아오는것
		model.addAttribute("dto", noticeDTO);
		model.addAttribute("board", "notice");
		model.addAttribute("path", "Update");
		return "board/boardWrite";
	}
	

	//처리
	@RequestMapping(value="noticeUpdate",method=RequestMethod.POST)
	public String noticeUpdate(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception{
		System.out.println("noticeController update num= "+noticeDTO.getNum());
		int result = noticeService.boardUpdate(noticeDTO);
		String message = "Update FAIL";
		if(result > 0){
			message = "Update SUCCESS";
		}
		
		rd.addFlashAttribute("message", message);
		return "redirect:/notice/noticeList?curPage=1";
	}
	
	
	@RequestMapping(value="noticeDelete",method=RequestMethod.GET)
	public String noticeDelete(Integer num,RedirectAttributes rd) throws Exception{
		if(num == null){}
		int result = noticeService.boardDelete(num);
		String message = "Delete FAIL";
		if(result > 0){
			message = "Delete SUCCESS";
		}
		
		rd.addFlashAttribute("message", message);
		return "redirect:/notice/noticeList?curPage=1";
	}
	
	
	
	
}
