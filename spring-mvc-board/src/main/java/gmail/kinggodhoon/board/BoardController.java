package gmail.kinggodhoon.board;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gmail.kinggodhoon.board.domain.Board;
import gmail.kinggodhoon.board.domain.Comment;
import gmail.kinggodhoon.board.domain.Member;
import gmail.kinggodhoon.board.domain.Post;
import gmail.kinggodhoon.board.service.BoardService;
import gmail.kinggodhoon.board.service.CommentService;
import gmail.kinggodhoon.board.service.PostService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="board/main", method=RequestMethod.GET)
	public String boardmain(Model model) {
		List<Board> list = boardService.list();
		model.addAttribute("board", list);
		
		return "board/main";
	}
	
	@RequestMapping(value="board/create", method=RequestMethod.GET)
	public String boardcreate() {
		return "board/create";
	}
	
	@RequestMapping(value="board/create", method=RequestMethod.POST)
	public String boardcreate(HttpServletRequest request) {
		int boardid = boardService.getLastNumber() + 1;
		String boardname = request.getParameter("boardname");
		
		Board board = new Board();
		
		board.setBoardid(boardid);
		board.setBoardname(boardname);
		
		boardService.insert(board);
		
		return "redirect:main";
	}
	
	@RequestMapping(value="board/list",method=RequestMethod.GET)
	public String boardlist(HttpServletRequest request,Model model) {
//		try {
			int boardid = Integer.parseInt(request.getParameter("boardid"));
			int page = Integer.parseInt(request.getParameter("page"));
			
			Board board = boardService.getBoard(boardid);
			
			List<Post> list = postService.processForView(postService.listPage(boardid, page));
			
			Map<String,Integer> pageInfo = postService.getPageInfo(boardid, page);
			
			model.addAttribute("board", board);
			model.addAttribute("list", list);
			model.addAttribute("page", pageInfo);
			
			return "board/list";
//		}catch(Exception e) {
//			return "redirect:/error";
//		}
	}
	
	@RequestMapping(value="board/write",method=RequestMethod.GET)
	public String postwrite(HttpServletRequest request,Model model) {
		try {
			int boardid = Integer.parseInt(request.getParameter("boardid"));
			return "board/write";
		}catch(Exception e) {
			return "redirect:/error";
		}
	}
	
	@RequestMapping(value="board/write",method=RequestMethod.POST)
	public String postwrite(HttpServletRequest request,HttpSession session) {
		try {
			int boardid = Integer.parseInt(request.getParameter("boardid"));
			String title = request.getParameter("title");
			String origContent = request.getParameter("content");
			boolean ismember = true;
			String username = null;
			String userpw = null;
			if(request.getParameter("password") != null) {
				ismember = false;
				username = request.getParameter("username");
				userpw = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
				 
			}else {
				Member member = (Member)session.getAttribute("member");
				username = member.getUsername();
			}
			String userip = request.getRemoteAddr();
			Calendar cal = new GregorianCalendar();
			Date now = new Date(cal.getTimeInMillis());
			
			String content = postService.stringToHtml(origContent);
			
			Post post = new Post();
			post.setBoardid(boardid);
			post.setTitle(title);
			post.setContent(content);
			post.setIsmember(ismember);
			post.setUsername(username);
			post.setUserpw(userpw);
			post.setUserip(userip);
			post.setPosttime(now);
			post.setUpdatetime(now);
			
			System.out.println(post);
			
			postService.insert(post);
			
			
			return "redirect:/board/list?boardid="+boardid+"&page=1";
		}catch(Exception e) {
			return "redirect:/error";
		}
	}
	
	@RequestMapping(value="board/modify/{postid}",method=RequestMethod.GET)
	public String postmodify(@PathVariable int postid,HttpServletRequest request) {
		
		return "board/modify";		
	}
	
	@RequestMapping(value="board/read/{postid}",method=RequestMethod.GET)
	public String postread(@PathVariable int postid, Model model) {
//		try{
			postService.increaseViews(postid);
			
			Post post = postService.getPost(postid);
			 
			if(post.isIsmember() == true) {
				post.setNickname(postService.getNickname(post.getUsername()));
			}else {
				post.setNickname(post.getUsername());
			}
			
			List<Comment> comments = commentService.getComments(postid);
			
			System.out.println(comments);
			
			for(Comment comment : comments) {
				if(comment.isIsmember() == true) {
					comment.setNickname(commentService.getNickname(comment.getUsername()));
				}else {
					comment.setNickname(comment.getUsername());
				}
			}
			
			model.addAttribute("post", post);
			model.addAttribute("comments",comments);
			
			return "board/read";
//		}catch(Exception e) {
//			return "redirect:/error";
//		}
		
	}
	
	@RequestMapping(value="board/read/{postid}",method=RequestMethod.POST)
	public String comment(@PathVariable int postid, HttpServletRequest request, HttpSession session) {
		Calendar cal = new GregorianCalendar();
		Date now = new Date(cal.getTimeInMillis());
		boolean ismember = false;
		String username = null;
		String userpw = null;
		String ip = request.getRemoteAddr();
		String content = request.getParameter("comment");
		
		if(session.getAttribute("member") != null) {
			Member member = (Member)session.getAttribute("member");
			System.out.println(member);
			ismember = true;
			username = member.getUsername();
		}else {
			username = request.getParameter("username");
			userpw = BCrypt.hashpw(request.getParameter("userpw"), BCrypt.gensalt());
		}
		
		Comment comment = new Comment();
		
		comment.setPostid(postid);
		comment.setContent(content);
		comment.setPosttime(now);
		comment.setIsmember(ismember);
		comment.setUsername(username);
		comment.setUserpw(userpw);
		comment.setUserip(ip);
		
		commentService.insert(comment);
		
		
		return "redirect:/board/read/"+postid;
	}
	
	@RequestMapping(value="board/delete/{postid}",method=RequestMethod.GET)
	public String delete(@PathVariable int postid,Model model) {
		
		return "board/delete";
	}
	
	@RequestMapping(value="board/delete/{postid}",method=RequestMethod.POST)
	public String delete(@PathVariable int postid) {
		
		postService.delete(postid);
		
		return "redirect:/board/main";
	}
	
	@RequestMapping(value="board/recommand/{postid}",method=RequestMethod.POST)
	public String recommand(@PathVariable int postid) {
		
		postService.increaseRecommand(postid);
		
		return "redirect:/board/read/"+postid;
	}
	
}
