package gmail.kinggodhoon.board.service;

import java.util.List;

import gmail.kinggodhoon.board.domain.Board;

public interface BoardService {
	public void insert(Board board);
	
	public int getLastNumber();
	
	public boolean boardnameCheck(String boardname);
	
	public Board getBoard(int boardid);
	
	public List<Board> list();
	
}
