package com.choa.ex6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.choa.memo.MemoDAO;
import com.choa.memo.MemoDTO;
import com.choa.util.ListInfo;

public class MemoDAOTest extends MyAbstractTestUnit{
	
	@Autowired
	private MemoDAO dao;
	/*
	private static MemoDTO memoDTO;
	
	@BeforeClass
	private static void makeMemo(){
		memoDTO = new MemoDTO();
		memoDTO.setWriter("myeon");
		memoDTO.setContents("HI");
	}*/

	@Test
	public void test() throws Exception {
		
		/*
		assertNotNull(dao);
		System.out.println("memoDAO= "+dao);
		*/
		MemoDTO memoDTO;
		memoDTO = new MemoDTO();
		memoDTO.setWriter("myeon");
		memoDTO.setContents("HI2");
		
		//assertEquals(1, dao.boardWrite(memoDTO));
		
		/*
		MemoDTO resultDTO;
		resultDTO = dao.boardView(1);
		assertNotNull(resultDTO);
		System.out.println("resultDTO contents="+resultDTO.getContents());
		*/
		
		ListInfo listInfo = new ListInfo();
		int totalCount = dao.boardCount(listInfo);
		listInfo.makePage(totalCount);
		listInfo.makeRow();
		listInfo.setSearch("contents");
		listInfo.setFind("h");
		List<MemoDTO> ar = dao.boardList(listInfo);
		
		assertNotEquals(0, ar.size());
		for(MemoDTO m : ar){
			System.out.println("글내용 = "+m.getContents());
		}
		
		
		
		
	}

}
