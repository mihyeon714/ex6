package com.choa.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//일반 클래스가 아니라 view 객체의 역할을 해야한다 
//spring 에서 제공하는 AbstractView 를 상속받으면 된다
public class Download  extends AbstractView{
	//어떤 애를 다운로드로 연결해주기 위한 class임
	
	public Download() {
		setContentType("application/download;charset=UTF-8"); 
		// 타입이 뭔지 설정해준다
		// json 일때는 application/json 이런게 넘어가는거 
		//이걸보고 받는쪽에서 미리 준비를 한다
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//ModelAndView 는 Map계열이니 Map으로 받아와진다
		File f = (File)model.get("downloadFile");
		String oriName = (String)model.get("oriName");
		System.out.println("oriName = "+oriName);
		//우리한테 오는 애가 response 임
		response.setCharacterEncoding(getContentType()); 
		response.setContentLength((int)f.length());
		//String fileName = f.getName(); //한글 깨질 가능성이 커요 //인코딩클래스사용하기
		String fileName = URLEncoder.encode(f.getName(), "UTF-8"); //f.getName() 은 실제 파일이름
		fileName = fileName.substring(fileName.lastIndexOf("_")+1);
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\""); //filename="~~"
		response.setHeader("Content-Transfer-Encoding", "binary"); //파일이 2진코드로 간다
		
		OutputStream out = response.getOutputStream(); //클라이언트쪽으로 연결된아이//response가 socket으로 보면됨
		
		FileInputStream fi = null;
		
		fi = new FileInputStream(f);//그폴더에 그 파일
		FileCopyUtils.copy(fi, out);	
		
		fi.close();
		out.close();
		
		
	}

}
