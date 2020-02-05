package gmail.kinggodhoon.board.service;

import java.util.List;
import java.util.Map;

import gmail.kinggodhoon.board.domain.Post;

public interface PostService {
	public void insert(Post post);
	
	public void delete(int postid);
	
	public String stringToHtml(String origContent);
	
	public List<Post> processForView(List<Post> list);
	
	public List<Post> listPage(int boardid, int page);

	public Map<String,Integer> getPageInfo(int boardid, int nowPage);
	
	public Post getPost(int postid);
	
	public String getNickname(String username);
	
	public boolean login(int postid, String password);
	
	public int increaseViews(int postid);
	
	public int increaseRecommand(int postid);
//	public String getIpForView(Post post);
	
}
