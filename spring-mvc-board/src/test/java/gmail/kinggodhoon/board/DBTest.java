package gmail.kinggodhoon.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import gmail.kinggodhoon.board.dao.MemberDao;
import gmail.kinggodhoon.board.domain.Member;

//스프링의 테스트 클래스로 설정
@RunWith(SpringJUnit4ClassRunner.class)
//스프링의 설정 파일을 실행해서 bean을 객체로 만들도록 설정
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration
@Transactional
public class DBTest {
	
//	@Autowired
//	private DataSource dataSource;
//	
//	@Test
//	public void dbConnect() {
//		try {
//			Connection con = dataSource.getConnection();
//			
//			System.out.println("connection : "+con);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		
//		
//	}
	
	
	
//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	@Test
//	public void sessionConnect() {
//		try {
//			Session session = sessionFactory.getCurrentSession();
//			System.out.println("session : "+session);
//		}catch(Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}
	
	
	@Autowired
	private MemberDao dao;
	
	@Test
	public void sessionConnect() {
		try {
			dao.getWithNick("kinggodhoon");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
