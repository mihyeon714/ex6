package com.choa.ex6;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.choa.memo.MemoDTO;
import com.choa.memo.MemoService;
import com.choa.util.ListInfo;

@Controller
@RequestMapping(value="memo/**")
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	//list
	@RequestMapping(value="memoList")
	public void memoList(){}
	
	
	@RequestMapping(value="getMemoList/{curPage}/{search}/{find}" , method=RequestMethod.GET)
	@ResponseBody
	public List<MemoDTO> memoList(@PathVariable("curPage") int curPage,@PathVariable("search") String search,@PathVariable("find") String find) throws Exception{

		ListInfo listInfo = new ListInfo();
		listInfo.setCurPage(curPage);
		listInfo.setSearch(search);
		listInfo.setFind(find);
		List<MemoDTO> list = memoService.boardList(listInfo);
		return list;
	}
	
	
	
	/*
	
	
	@RequestMapping(value="getMemoList" , method=RequestMethod.GET)
	@ResponseBody
	public List<MemoDTO> memoList(ListInfo listInfo) throws Exception{
	//public void memoList(ListInfo listInfo, Model model) throws Exception{
		List<MemoDTO> list = memoService.boardList(listInfo);
		return list; //원리는 같습니다 
		//그래서 이 list 가 view 가 아니라 body로 들어가도록 설정해주기
		//@ResponseBody annotation 사용
		// 지금 리턴하는 애를 jsp body 에 넣어버려
		
		
		// 이것 또한 너무나 불필요한 것 그래서 api 사용할거다
		//JSON  형태로 보내기
		JSONArray ar = new JSONArray();
		for(MemoDTO m: list){
			JSONObject obj = new JSONObject();
			obj.put("num", m.getNum().toString());
			obj.put("writer", m.getWriter());
			obj.put("contents", m.getContents());
			obj.put("reg_date", m.getReg_date().toString());
			ar.add(obj);	
		}
		model.addAttribute("list", ar.toJSONString());
		
		
		//평번하게 DTO리스트로 데이터 보내기 
		//model.addAttribute("list", list);
		
	}
	
	*/
	
	/*
	@RequestMapping(value="memoView")
	@ResponseBody
	public MemoDTO memoView(int num) throws Exception{
		MemoDTO memoDTO = memoService.boardView(num);
		return memoDTO; //알아서 JSON 형태로 바꿔서 jsp로 보내줌 //annotation 필수
	}
	*/
	
	//rest Service 이용하기
	@RequestMapping(value="memoView/{num}")
	@ResponseBody
	public MemoDTO memoView(@PathVariable("num") int num) throws Exception{
		MemoDTO memoDTO = memoService.boardView(num);
		return memoDTO; //알아서 JSON 형태로 바꿔서 jsp로 보내줌 //annotation 필수
	}
	
	
	

	
	@RequestMapping(value="memoWrite", method=RequestMethod.POST)
	@ResponseBody
	public List<MemoDTO> memoWrite(MemoDTO memoDTO)throws Exception{
	//public String memoWrite(MemoDTO memoDTO,Model model) throws Exception{
		
		//들어온 내용 확인
		System.out.println("writer= "+memoDTO.getWriter()+" contents= "+memoDTO.getContents());
		
		int result = memoService.boardWrite(memoDTO);
		
		/*
		//방법1
		//메세지 보내기
		String message = "MEMO 등록 실패";
		if(result > 0){
			message = "MEMO 등록  성공";
		}
		model.addAttribute("message", message);
		return "commons/ajaxResult";
		*/
		
		/*
		//방법2
		//message가 아닌 DTO 자체를 다시 보낸다
		model.addAttribute("dto", memoDTO);
		return "commons/ajaxResult";
		*/
		
		/*
		//방법3
		//DTO list를 보낸다
		ListInfo listInfo = new ListInfo();
		listInfo.setCurPage(1);
		List<MemoDTO> list = memoService.boardList(listInfo);
		model.addAttribute("list", list);
		return "memo/getMemoList";
		*/
		
		
		
		//jackson api 사용하기
		ListInfo listInfo = new ListInfo();
		listInfo.setCurPage(1);
		List<MemoDTO> list = memoService.boardList(listInfo);
		return list;
		
	}
	
	
	
	
	
}
