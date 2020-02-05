package gmail.kinggodhoon.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gmail.kinggodhoon.board.dao.MemberDao;
import gmail.kinggodhoon.board.domain.Member;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void insert(Member member) {
		memberDao.insert(member);
	}

	@Override
	public boolean usernamecheck(String username) {
		boolean result = false;
		
		Member member = memberDao.getWithUsername(username);
		
		if(member != null) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean nicknamecheck(String nickname) {
		boolean result = false;
		
		Member member = memberDao.getWithNick(nickname);
		
		if(member != null) {
			result = true;
		}
		
		return result;
	}

	@Override
	public Member getMemberWithMemberId(int userid) {
		return memberDao.getWithUserId(userid);
	}

	@Override
	public Member login(String username) {
		return memberDao.getWithUsername(username);
	}

	@Override
	public void updateNickname(int userid, String nickname) {
		memberDao.updateNickname(userid, nickname);
	}

	@Override
	public void leaveMember(int userid) {
		memberDao.leaveMember(userid);
	}
	
	
	
}
