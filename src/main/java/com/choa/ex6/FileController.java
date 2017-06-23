package com.choa.ex6;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.choa.file.FileDTO;
import com.choa.file.MultiFileDTO;
import com.choa.file.SameMultiFileDTO;

@Controller
@RequestMapping("/file/**")
public class FileController {

	@RequestMapping(value="fileUpload", method=RequestMethod.GET)
	public void fileUpload(){}
	
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
	@RequestMapping(value="fileUpload", method=RequestMethod.POST)
	public void fileUpload(MultipartHttpServletRequest request){
		
	}
	
	//파일 받아오기 2번 : MultipartFile
	//@RequestMapping(value="fileUpload", method=RequestMethod.POST)
	public void fileUpload(MultipartFile f1){

	}
	
	
	//파일 받아오기 3번: DTO
	//@RequestMapping(value="fileUpload", method=RequestMethod.POST)
	public void fileUpload(FileDTO fileDTO){

	}
	
	
	
}
