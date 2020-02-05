package gmail.kinggodhoon.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmail.kinggodhoon.board.service.BoardService;
import gmail.kinggodhoon.board.service.PostService;

@RestController
public class BoardJsonController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="board/boardnamecheck",method=RequestMethod.GET)
	public Map<String,Object> boardnameCheck(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		
		String boardname = request.getParameter("boardname");
		
		boolean result = boardService.boardnameCheck(boardname);
		
		map.put("result", result+"");
		
		return map;
	}
	
	@RequestMapping(value="board/auth",method=RequestMethod.POST)
	public Map<String,Object> nonMemberAuth(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		
		int postid = Integer.parseInt(request.getParameter("postid"));
		String password = request.getParameter("password");
		
		boolean result = postService.login(postid, password);
		
		map.put("result", result+"");
		
		return map;
	}
}
