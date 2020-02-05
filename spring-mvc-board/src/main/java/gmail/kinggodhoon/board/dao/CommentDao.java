package gmail.kinggodhoon.board.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gmail.kinggodhoon.board.domain.Comment;

@Repository
public class CommentDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(comment);
	}
	
	public List<Comment> listWithPostid(int postid){
		Session session = sessionFactory.getCurrentSession();
		Query<Comment> query = session.createQuery("from Comment where postid = :postid");
		
		query.setParameter("postid", postid);
		
		return query.getResultList();
	}
	
	public String getNickname(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<String> query = session.createQuery("select distinct mb.nickname from Comment as comment join Member as mb on comment.username = mb.username where comment.username = :username");
		
		query.setParameter("username", username);
		
		return query.getSingleResult();
	}
	
	
	
}
