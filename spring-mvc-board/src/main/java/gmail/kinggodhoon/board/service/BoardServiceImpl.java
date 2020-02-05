package gmail.kinggodhoon.board.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gmail.kinggodhoon.board.domain.Board;
import gmail.kinggodhoon.board.dao.BoardDao;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public void insert(Board board) {
		boardDao.insert(board);
	}
	
	@Override
	public int getLastNumber() {
		return boardDao.getLastNumber();
	}

	@Override
	public boolean boardnameCheck(String boardname) {
		boolean result = false;
		
		Board board = boardDao.getWithBoardname(boardname);
		
		if(board != null) {
			result = true;
		}
		
		return result;
	}
	
	@Override
	public Board getBoard(int boardid) {
		return boardDao.getWithBoardid(boardid);
	}
	
	@Override
	public List<Board> list() {
		return boardDao.list();
	}

	
	
	

}
