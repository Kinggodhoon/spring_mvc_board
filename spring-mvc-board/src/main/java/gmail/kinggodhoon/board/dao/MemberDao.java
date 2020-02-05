package gmail.kinggodhoon.board.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gmail.kinggodhoon.board.domain.Member;

@Repository
//Hibernate로 Dao를 구성하고자 한다면 트랜잭션 관련 어노테이션을 필수적으로 추가해줘야한다.
public class MemberDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Member member) {
		Session session = sessionFactory.getCurrentSession();
		session.save(member);
	}
	public Member getWithUserId(int userid) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Member> query = session.createQuery("from Member where userid = :userid");
			
			query.setParameter("userid", userid);
			
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}

	public Member getWithUsername(String username) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Member> query = session.createQuery("from Member where username = :username");
			
			query.setParameter("username", username);
			
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	public Member getWithNick(String nickname) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Member> query = session.createQuery("from Member where nickname = :nickname");
			
			query.setParameter("nickname", nickname);
			
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	public void updateNickname(int userid, String nickname) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Member set nickname = :nickname where userid = :userid");
		
		query.setParameter("nickname", nickname);
		query.setParameter("userid", userid);
		
		query.executeUpdate();
	}
	
	public void leaveMember(int userid) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Member set password = :password where userid = :userid");
		
		query.setParameter("userid", userid);
		query.setParameter("password", "Leaved Member");
		
		query.executeUpdate();
	}
	
	
}
