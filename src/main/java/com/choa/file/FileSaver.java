package com.choa.file;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	
	//저장한 파일NAME을 넘겨준다
	
	//디스크에 저장하기 1번째 방법 : FileCopyUtils.copy(fileData, target);
	public String filesave(String realPath, String oriName , byte [] fileData) throws Exception{
		
		File f = new File(realPath);
		//폴더가 안만들어져있을 경우
		if(!f.exists()){
			f.mkdirs();
		}
		
		String fileName = UUID.randomUUID().toString()+"_"+oriName; // upload(만들어준)폴더에 저장되는 실제 파일이름 >DB저장
		File target = new File(f,fileName);
		FileCopyUtils.copy(fileData, target); //데이터 저장방법1
		return fileName;
	}
	
	//디스크에 저장하기 2번째 방법  : MultipartFile m.transferTo
	public String filesave(String realPath, MultipartFile m)throws Exception{
	      File f = new File(realPath);
	      System.out.println("realPath = "+realPath);
	      if(!f.exists()){
	         f.mkdirs();      //f가 없다면, f의파일을 만들어주세요->mkdirs()
	      }
	      
	      //1번
	      //String fileName = UUID.randomUUID().toString()+"_"+m.getOriginalFilename();
	      
	      //2번 중복을 피해서 시간으로 설정
	      Calendar ca = Calendar.getInstance();   //현재시간을 받아오기
	      String fileName = ca.getTimeInMillis()+"_"+m.getOriginalFilename();
	      
	      File target = new File(f, fileName);
	      m.transferTo(target);
	      
	      return fileName;
	      
	   }
	
}
