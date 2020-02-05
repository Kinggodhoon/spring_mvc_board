package gmail.kinggodhoon.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmail.kinggodhoon.board.domain.Member;
import gmail.kinggodhoon.board.service.MemberService;

@RestController
public class MemberJsonController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="member/usernamecheck",method=RequestMethod.GET)
	public Map<String,Object> usernamecheck(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		
		String username = request.getParameter("username");
		
		boolean result = memberService.usernamecheck(username);
		
		map.put("result", result+"");
		
		return map;
	}
	
	@RequestMapping(value="member/nickcheck",method=RequestMethod.GET)
	public Map<String,Object> nicknamecheck(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		
		String nickname = request.getParameter("nickname");
		
		boolean result = memberService.nicknamecheck(nickname);
		
		map.put("result", result+"");
		
		return map;
	}
	
	@RequestMapping(value="member/updatenick",method=RequestMethod.POST)
	public Map<String,Object> updatenick(HttpServletRequest request, HttpSession session){
		Map<String,Object> map = new HashMap<>();
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		String nickname = request.getParameter("nickname");
		
		memberService.updateNickname(userid, nickname);
		
		Member member = (Member)session.getAttribute("member");
		
		session.removeAttribute("member");
		
		member.setNickname(nickname);
		
		session.setAttribute("member", member);
		
		map.put("result", "true");
		
		return map;
	}
	
	@RequestMapping(value="member/leavemember",method=RequestMethod.POST)
	public Map<String,Object> leavemember(HttpServletRequest request, HttpSession session){
		Map<String,Object> map = new HashMap<>();
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		String password = request.getParameter("password");
		Member prevMember = memberService.getMemberWithMemberId(userid);
		
		Member nowMember = memberService.login(prevMember.getUsername());
		
		
		if(BCrypt.checkpw(password, nowMember.getPassword())) {
			memberService.leaveMember(userid);
			
			session.removeAttribute("member");
			
			map.put("result", "true");
		}else {
			map.put("result", "false");
		}
		
		return map;
	}

}
