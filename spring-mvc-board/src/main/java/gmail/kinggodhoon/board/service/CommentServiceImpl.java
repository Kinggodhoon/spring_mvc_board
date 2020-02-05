package gmail.kinggodhoon.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gmail.kinggodhoon.board.dao.CommentDao;
import gmail.kinggodhoon.board.domain.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public void insert(Comment comment) {
		commentDao.insert(comment);
	}

	@Override
	public List<Comment> getComments(int postid) {
		return commentDao.listWithPostid(postid);
	}

	@Override
	public String getNickname(String username) {
		return commentDao.getNickname(username);
	}

	
	
	
}
