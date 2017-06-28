package com.choa.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {
	
	@Autowired
	private TableDAO tableDAO;
	
	public int insertTable(TableADTO tableADTO, int num) throws Exception{
		
		////////////////////////여기서///////////////////////
		//a가 insert 되면 자동으로 b도 insert 되야함 
		tableDAO.insertTableA(tableADTO); //Exception 나면 rollback해야하는데 던져버리고 있지요
		//Spring이 처리할 수 있는것을 또 제공해준답니다
		
		TableBDTO tableBDTO = new TableBDTO();
		tableBDTO.setWriter(tableADTO.getWriter());
		tableBDTO.setPoint(10);//무조건 10점 올라간다
		tableBDTO.setNum(num);
		
		return tableDAO.insertTableB(tableBDTO);
		
		/////////////////////여기까지가 핵심사항//////////////////
		//commit, rollback 등이 공통사항이라고 보면된다
		
	}

}
