package gmail.kinggodhoon.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gmail.kinggodhoon.board.domain.Member;

public interface MemberService {
	
	public void insert(Member member);
	
	public boolean usernamecheck(String username);
	
	public boolean nicknamecheck(String nickname);
	
	public Member getMemberWithMemberId(int userid);
	
	public Member login(String username);
	
	public void updateNickname(int userid, String nickname);
	
	public void leaveMember(int userid);
	
	
}
