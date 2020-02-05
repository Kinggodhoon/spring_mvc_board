package gmail.kinggodhoon.board.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gmail.kinggodhoon.board.domain.Board;
import gmail.kinggodhoon.board.domain.Member;

@Repository
public class BoardDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Board board) {
		Session session = sessionFactory.getCurrentSession();
		session.save(board);
	}
	
	public int getLastNumber() {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Integer> query = session.createQuery("select max(board.boardid) from Board board");
			
			int result = (int)query.getSingleResult();
			System.out.println(result);
			return result;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public List<Board> list(){
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaQuery<Board> criteriaQuery = session.getCriteriaBuilder().createQuery(Board.class);
		criteriaQuery.from(Board.class);
		
		List<Board> list = session.createQuery(criteriaQuery).getResultList();
		
		return list;
	}
	
	public Board getWithBoardname(String boardname) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Board> query = session.createQuery("from Board where boardname = :boardname");
			
			query.setParameter("boardname", boardname);
			
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	public Board getWithBoardid(int boardid) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Board> query = session.createQuery("from Board where boardid = :boardid");
			
			query.setParameter("boardid", boardid);
			
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
}
