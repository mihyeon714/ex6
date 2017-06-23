package com.choa.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.ListInfo;
import com.choa.util.PageMaker;
import com.choa.util.RowMaker;

//NoticeService noticeService = new NoticeService();
@Service
public class NoticeServiceImpl  implements BoardService{

	//@Inject
	@Autowired //타입으로 찾지만
	@Qualifier("notice")  //이름이 이거인 친구를 찾아요
	private NoticeDAOImpl noticeDAO;

	public void test(){
		System.out.println("noticeDAO= "+noticeDAO);
	}


	@Override
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
		//curPage, Paging처리 해야함
		int totalCount = noticeDAO.boardCount(listInfo);
		listInfo.makePage(totalCount);
		listInfo.makeRow();
		return noticeDAO.boardList(listInfo);
	}


	@Override
	public BoardDTO boardView(int num) throws Exception {
		return noticeDAO.boardView(num);
	}


	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return noticeDAO.boardWrite(boardDTO);
	}


	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		System.out.println("noticeService boardUpdate = "+boardDTO.getNum());
		return noticeDAO.boardUpdate(boardDTO);
	}


	@Override
	public int boardDelete(int num) throws Exception {
		return noticeDAO.boardDelete(num);
	}





}
