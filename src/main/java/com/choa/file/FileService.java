package com.choa.file;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choa.util.SeDTO;

@Service
public class FileService {
	
	//SeUpload
	public String seUpload(SeDTO seDTO, HttpSession session) throws Exception{
		
		String file_result = "";
		
		if(seDTO.getFiledata()!= null && seDTO.getFiledata().getOriginalFilename() != null && !seDTO.getFiledata().getOriginalFilename().equals("")){
			FileSaver fs = new FileSaver();
			String defaultPath = session.getServletContext().getRealPath("resources/upload");
			String realName = fs.filesave(defaultPath, seDTO.getFiledata());
			
			file_result = "&bNewLine=true&sFileName="+seDTO.getFiledata().getOriginalFilename()+"&sFileURL=/ex6/resources/upload/"+realName;	
		}else{
			file_result = "&errstr=error";
		}
		
		return "redirect:"+seDTO.getCallback()+"?callback_func="+seDTO.getCallback_func()+file_result; //redirect 해야하는 경로명 
	}
	
	
	
	//delete
	public boolean fileDelete(String fileName, HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(realPath,fileName);
		boolean result = f.delete();
		return result;
	}
	
	
	//save
	//디스크에 파일을 저장하는 코드 작성
	public String fileSave(MultipartFile m, HttpSession session) throws Exception{
		FileSaver fileSaver = new FileSaver();
		String fileName = fileSaver.filesave(session.getServletContext().getRealPath("resources/upload"), m);
		return fileName;
	}

}
