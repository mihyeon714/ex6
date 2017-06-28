package com.choa.ex6;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.choa.table.TableADTO;
import com.choa.table.TableService;

public class TableTest extends MyAbstractTestUnit{
	
	@Autowired
	private TableService tableService;

	@Test
	public void test() throws Exception {
		TableADTO tableADTO = new TableADTO();
		tableADTO.setNum(7);
		tableADTO.setTitle("t2");
		tableADTO.setWriter("t2");
		int result = tableService.insertTable(tableADTO, 6); //B넣을때 오류납니다
		
		assertNotEquals(0, result);
	}

}
