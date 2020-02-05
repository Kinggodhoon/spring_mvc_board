package gmail.kinggodhoon.board.service;

import java.util.List;

import gmail.kinggodhoon.board.domain.Comment;

public interface CommentService {
	public void insert(Comment comment);
	
	public List<Comment> getComments(int postid);
	
	public String getNickname(String username);
	
	
}
