package gmail.kinggodhoon.board;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gmail.kinggodhoon.board.domain.Member;
import gmail.kinggodhoon.board.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="member/register", method=RequestMethod.GET)
	public String register(Model model) {
		return "member/register";
	}
	
	@RequestMapping(value="member/register", method=RequestMethod.POST)
	public String register(HttpServletRequest request,RedirectAttributes attr) {
		
		Calendar cal = new GregorianCalendar();
		Date regdate = new Date(cal.getTimeInMillis());
		
		Member member = new Member();
		
		member.setUsername(request.getParameter("username"));
		member.setNickname(request.getParameter("nickname"));
		member.setPassword(BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()));
		member.setRegdate(regdate);
		
		memberService.insert(member);
		
		attr.addFlashAttribute("msg","회원가입에 성공했습니다.");
		return "redirect:login";
		
	}
	
	@RequestMapping(value="member/login", method=RequestMethod.GET)
	public String login(Model model) {
		return "member/login";
	}
	
	@RequestMapping(value="member/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpSession session, RedirectAttributes attr) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Member member = memberService.login(username);
		
		if(member != null) {
			if(BCrypt.checkpw(password, member.getPassword())) {
				session.removeAttribute("member");
				member.setPassword("");
				
				//회원정보에 출력 할 아이디 추가
				String hashedId = member.getUsername().substring(0, 2);
				StringBuffer sb = new StringBuffer();
				sb.append(hashedId);
				
				for(int i = 0; i < member.getUsername().length(); i++) {
					sb.append("*");
				}
				hashedId = sb.toString();
				member.setHUsername(hashedId);
				
				session.setAttribute("member", member);
				
				return "redirect:/";
			}
		}
		
		attr.addFlashAttribute("error", "잘못된 아이디나 비밀번호입니다. 다시 시도해주세요.");
		
		return "redirect:login";
	}
	
	@RequestMapping(value="member/info", method=RequestMethod.GET)
	public String info(Model model,HttpSession session) {
		if(session.getAttribute("member") == null) {
			return "redirect:login";
		}
		
		return "member/info";
	}
	
	@RequestMapping(value="member/logout",method=RequestMethod.GET)
	public String info(HttpSession session) {
		if(session.getAttribute("member") != null) {
			session.removeAttribute("member");
		}
		return "redirect:/";
	}
	
}
