package com.choa.file;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
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
