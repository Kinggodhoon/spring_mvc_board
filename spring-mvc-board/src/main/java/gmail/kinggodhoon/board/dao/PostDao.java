package gmail.kinggodhoon.board.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectWriter.GeneratorSettings;

import gmail.kinggodhoon.board.domain.Post;

@Repository
public class PostDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Post post) {
		Session session = sessionFactory.getCurrentSession();
		session.save(post);
	}
	
	public void delete(Post post) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(post);
	}
	
	public List<Post> listPage(int boardid,int page){
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("from Post where boardid = :boardid order by updatetime desc");

		query.setParameter("boardid", boardid);
		
		query.setFirstResult(15*(page-1));
		query.setMaxResults(15);
		
		List<Post> list = query.list();
		
		return list;
	}
	
	public Post getPost(int postid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Post> query = session.createQuery("from Post where postid = :postid");
		
		query.setParameter("postid", postid);
		
		return query.getSingleResult();
	}
	
	public String getNick(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<String> query = session.createQuery("select distinct mb.nickname from Post as post join Member as mb on post.username = mb.username where post.username = :username");
		
		query.setParameter("username", username);
		
		return query.getSingleResult();
	}
	
	public int getCountOfPost(int boardid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Long> query = session.createQuery("select count(*) from Post where boardid = :boardid");
		
		query.setParameter("boardid", boardid);
		
		int result = query.getSingleResult().intValue();
		
		return result;
	}
	
	public int getViews(int postid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery("select post.views from Post as post where postid = :postid");
		
		query.setParameter("postid", postid);
		
		return query.getSingleResult();
	}
	public int increaseViews(int views, int postid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery("update Post set views = :views + 1 where postid = :postid");
		
		query.setParameter("views", views);
		query.setParameter("postid", postid);
		
		return query.executeUpdate();
	}
	
	public int getRecommands(int postid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery("select post.recommand from Post as post where postid = :postid");
		
		query.setParameter("postid", postid);
		
		return query.getSingleResult();
	}
	public int increaseRecommands(int recommand, int postid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery("update Post set recommand = :recommand + 1 where postid = :postid");
		
		query.setParameter("recommand", recommand);
		query.setParameter("postid", postid);
		
		return query.executeUpdate();
	}
	
}
