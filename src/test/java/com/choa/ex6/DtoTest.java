package com.choa.ex6;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDAOImpl;

public class DtoTest extends MyAbstractTestUnit{
	
	@Autowired
	private NoticeDAOImpl dao;

	@Test
	public void test() throws Exception{
		
		//assertNotNull(dao);
		//System.out.println("dao = "+dao);
		
		BoardDTO boardDTO = dao.boardView(363);
		assertNotNull(boardDTO);
		
	}

}
