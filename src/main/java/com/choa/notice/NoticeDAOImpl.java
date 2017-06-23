package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.DBConnect;
import com.choa.util.ListInfo;
import com.choa.util.RowMaker;

//@Repository //NoticeDAO noticeDAO = new NoticeDAO();
@Repository("notice")  //NoticeDAO notice = new NoticeDAO();
public class NoticeDAOImpl implements BoardDAO{
	

	@Inject
	private SqlSession sqlSession; //test 에서 확인완료
	private static final String NAMESPACE = "NoticeMapper."; 
	//final은 바뀌면 안될때 상수처럼(변수명은대문자로사용), static 은 ...
	//private DataSource dataSource;
	
	

	@Override
	public BoardDTO boardView(int num) throws Exception {
		
		
		BoardDTO boardDTO = sqlSession.selectOne(NAMESPACE+"view",num);

		return boardDTO;
		

	}
	
	
	
	
	@Override
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
		
		//넘겨줄 것이 rowMaker, search , find 이니 여러 데이터를 하나로 만들어주는 Collection 개열을 사용하자
		//나는 rowMaker 할때 애초에 search , find 가 필요하기 때문에 상관 없지만 알아 두는 것이 좋겠다 
		//하나로 묵어주는 방법은 2가지다 (객체를 만들거나 , Collection(list//index사용, map//key사용)을 사용하거나)
		//key 를 사용하는것이 이때는 더 편하다
		/*		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rowMaker", rowMaker);
		map.put("search", search);
		map.put("find", find);*/
	
		
		return sqlSession.selectList(NAMESPACE+"list", listInfo);
		//return sqlSession.selectList(NAMESPACE+"list",rowMaker);
		
	}


	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		
		return sqlSession.insert(NAMESPACE+"write",boardDTO);
	
	}

	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		
		return sqlSession.update(NAMESPACE+"update", boardDTO);
		
	
	}

	@Override
	public int boardDelete(int num) throws Exception {
		
		return sqlSession.delete(NAMESPACE+"delete",num);
	
	}

	@Override
	public int boardCount(ListInfo listInfo) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+"count");
		

		
	}

	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}
	



}
