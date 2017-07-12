package com.choa.ex6;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.choa.file.FileDTO;
import com.choa.file.FileService;
import com.choa.file.MultiFileDTO;
import com.choa.file.SameMultiFileDTO;
import com.choa.util.SeDTO;

@Controller
@RequestMapping("/file/**")
public class FileController {
	
	
	//SmartEditor
	@RequestMapping(value="seUpload",method=RequestMethod.POST)
	public String seUpload(SeDTO seDTO, HttpSession session) throws Exception{//MultipartHttpServletRequest request){
		
		FileService fs = new FileService();
		return fs.seUpload(seDTO, session);
		
		/*
		//이 코드를 FileService에서 실행한다
		//callBack
		String callback = seDTO.getCallback();
		//callback_func
		String callback_func = seDTO.getCallback_func();
		//OriName
		String original_name = seDTO.getFiledata().getOriginalFilename();
		//defaultPath
		String defaultPath = session.getServletContext().getRealPath("resources/upload");
		
		File f = new File(defaultPath);
		//디렉토리가 존재 하지 않을 경우 디렉토리 생성
		if(!f.exists()){
			f.mkdirs();
		}
		
		//디렉토리에 저장할 파일 명 
		String realName = UUID.randomUUID().toString()+"_"+original_name;
		
		//디렉토리에 저장
		seDTO.getFiledata().transferTo(new File(defaultPath,realName));
		
		//
		String file_result = "&bNewLine=true&sFileName="+original_name+"&sFileURL=/ex6/resources/upload/"+realName;
		return "redirect:"+callback+"?callback_func="+callback_func+file_result;
		*/
		
		
		
		
		
		
		/*
		Enumeration<Object> e =  request.getParameterNames();
		while(e.hasMoreElements()){
			System.out.println("가져온 친구 확인 = "+e.nextElement());
		}
		
		Iterator<String> it = request.getFileNames();
		while(it.hasNext()){
			System.out.println("가져온 파일 친구 확인 = "+it.next()); //Filedata
		}
		
		//System.out.println("se 파일 왔니"); //확인
		*/

	}
	
	
	
	//파일 다운 //.jsp로 가는게 아니라 download가 되도록 하는 것
	@RequestMapping(value="fileDown",method=RequestMethod.GET)
	public ModelAndView fileDown(String fileName, String oriName, HttpSession session){
		String realPath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(realPath,fileName);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("download"); // 클래스명과 동일하되 첫글자를 소문자로 바꾸면됨 //즉 참조변수명
		mv.addObject("downloadFile", f);
		mv.addObject("oriName", oriName);
		
		return mv;
	}
	
	
	
	
	//파일삭제(디스크)//위치도 알아야한다 //서비스 처리로 합시다
	@RequestMapping(value="fileDelete",method=RequestMethod.GET)
	public void fileDelete(String fileName, HttpSession session) throws Exception{
		FileService f = new FileService();
		f.fileDelete(fileName, session);
	}
	
	

	@RequestMapping(value="fileUpload", method=RequestMethod.GET)
	public void fileUpload(){}
	
	
	//이거 오류쓰...잘안됨..고치기
	//upload
	///////////////////다중 파일 업로드 - 파라미터 이름이 다르고(몰라), 여러개일 경우////////////////////////////////
	//SO MultiPartFile 은 사용을 못하지, 그러니까 DTO 도 못하지 //그런데 어쨋거나 MultiPatrFile을 꺼내기는 해야지
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public void upload(MultipartHttpServletRequest request){
		//request.getParameterNames();  //일반적인 input 도 오기때문에 file 구분 불가
		Iterator<String> it = request.getFileNames(); 
		ArrayList<MultipartFile> multi = new ArrayList<MultipartFile>();
		while(it.hasNext()){
			System.out.println("파일이름 찍어보기 = "+it.next());
			MultipartFile m = request.getFile(it.next());
			multi.add(m);
		}
		//올린만큼의 MultipartFile이 배열에 저장됬음
		for(MultipartFile m : multi){
			System.out.println("...번째 파일 이름 = "+ m.getOriginalFilename());
		}
		
	}
	
	
	
	
	//sameMultiFileUpload
	///////////////////다중 파일 업로드 - 파라미터 이름이 같고, 여러개일 경우////////////////////////////////
	// input type="checkbox" 인것 처럼 배열로 받는 것이 가능하다
		
	@RequestMapping(value="sameMultiFileUpload", method=RequestMethod.POST)
	public void sameMultiFileUpload(MultipartHttpServletRequest request){
		List<MultipartFile> ar = request.getFiles("f1"); //Iterator로 가져올 수 도 있는데 그것은 파라미터 이름 모를때 사용한다
		for(MultipartFile f:ar){
			System.out.println("...번째 파일 이름 = "+ f.getOriginalFilename());
		}
	}
	
	
	//@RequestMapping(value="sameMultiFileUpload", method=RequestMethod.POST)
	public void sameMultiFileUpload(MultipartFile [] f1){
		for(int i=0;i<f1.length;i++){
			System.out.println(i+1+"번째 파일 이름 = "+ f1[i].getOriginalFilename());
		}
	}
	
	
	//@RequestMapping(value="sameMultiFileUpload", method=RequestMethod.POST)
	public void sameMultiFileUpload(SameMultiFileDTO sameMultiFileDTO){
		for(int i=0;i<sameMultiFileDTO.getF1().length;i++){
			System.out.println(i+1+"번째 파일 이름 = "+ (sameMultiFileDTO.getF1())[i].getOriginalFilename());
		}
	}
	
	
	
	
	///////////////////다중 파일 업로드 - 파라미터 이름이 다르고, 갯수 고정일 경우////////////////////////////
	
	//다중 파일 받아오기 : MultipartHttpServletRequest
	//@RequestMapping(value="multiFileUpload", method=RequestMethod.POST)
	public void multiFileUpload(MultipartHttpServletRequest request){
		MultipartFile f1 = request.getFile("f1");
		MultipartFile f2 = request.getFile("f2");
		System.out.println("1번파일 이름 = "+f1.getOriginalFilename());
		System.out.println("2번파일 이름 = "+f2.getOriginalFilename());
	}
	
	
	//다중 파일 받아오기 : MultiPartFile객체 여러개
	//@RequestMapping(value="multiFileUpload", method=RequestMethod.POST)
	public void multiFileUpload(MultipartFile f1, MultipartFile f2){
		System.out.println("1번파일 이름 = "+f1.getOriginalFilename());
		System.out.println("2번파일 이름 = "+f2.getOriginalFilename());
	}
	
	
	//다중 파일 받아오기 : DTO
	@RequestMapping(value="multiFileUpload", method=RequestMethod.POST)
	public void multiFileUpload(MultiFileDTO multiFileDTO){
		System.out.println("1번파일 이름 = "+multiFileDTO.getF1().getOriginalFilename());
		System.out.println("2번파일 이름 = "+multiFileDTO.getF2().getOriginalFilename());
	}
	
	
	
	
	
	
	//////////////////////////////////단일 파일 업로드////////////////////////////////
	
	//파일 받아오기 1번 : MultipartHttpServletRequest
	//@RequestMapping(value="fileUpload", method=RequestMethod.POST)
	public void fileUpload(MultipartHttpServletRequest request){
		
	}
	
	//파일 받아오기 2번 : MultipartFile
	@RequestMapping(value="fileUpload", method=RequestMethod.POST)
	public ModelAndView fileUpload(MultipartFile f1, HttpSession session) throws Exception{

		FileService fileService = new FileService(); // 원래는 멤버변수로 하면좋...
		String fileName = fileService.fileSave(f1, session);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("file/fileView");
		mv.addObject("fileName", fileName);
		mv.addObject("oriName",f1.getOriginalFilename());
		return mv;
		
	}
	
	
	//파일 받아오기 3번: DTO
	//@RequestMapping(value="fileUpload", method=RequestMethod.POST)
	public void fileUpload(FileDTO fileDTO){

	}
	
	
	
}
