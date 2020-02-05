package gmail.kinggodhoon.board.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gmail.kinggodhoon.board.dao.PostDao;
import gmail.kinggodhoon.board.domain.Post;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDao postDao;
	
	@Override
	public void insert(Post post) {
		postDao.insert(post);
	}
	
	@Override
	public void delete(int postid) {
		Post post = new Post();
		post.setPostid(postid);
		
		postDao.delete(post);
	}
	
	@Override
	public String stringToHtml(String origContent) {
		String content = null;
		
		InputStream is = new ByteArrayInputStream(origContent.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		StringBuilder sb = new StringBuilder();
		try {
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				if(line.length() < 1) {
					sb.append("<br>");
				}else {
					sb.append("<p>"+line+"</p>");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		content = sb.toString();
		
		return content;
	}
	
	@Override
	public List<Post> processForView(List<Post> list) {
		List<Post> result = new ArrayList<Post>();
		
		for(Post temp : list) {
			//닉네임 적용 : JOIN 사용
			if(temp.isIsmember() == true) {
				String nickname = postDao.getNick(temp.getUsername());
				temp.setNickname(nickname);
			}else {
				temp.setNickname(temp.getUsername());
			}
			//시간 적용
			//오늘 작성한 글이면 시간으로 아니면 날짜로
			Date todayDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
			
			String today = dateFormat.format(todayDate);
			String updatetime = dateFormat.format(temp.getUpdatetime());
			
			if(today.equals(updatetime)) {
				temp.setDisptime(timeFormat.format(temp.getUpdatetime()));
			}else {
				temp.setDisptime(updatetime);
			}
			
			result.add(temp);
		}
		
		return result;
	}

	@Override
	public List<Post> listPage(int boardid, int page) {
		return postDao.listPage(boardid, page);
	}

	@Override
	public Map<String, Integer> getPageInfo(int boardid, int nowPage) {
		Map<String, Integer> result = new HashMap<String,Integer>();
		//init
		int postCount = postDao.getCountOfPost(boardid);
		
		int first = 1;
		int prev = 0;
		int min = 0;
		int max = 0;
		int page = nowPage;
		int next = 0;
		int last = 0;
		
		last = postCount / 15;
		if(postCount % 15 > 0) {
			last++;
		}
		
		//페이지를 이용해서 표시할 최대 최소 페이지 구하기
		int cnt = 1;
		while(true) {
			if(1*cnt <= page && page <= 15*cnt) {
				max = 15*cnt;
				if(cnt > 1) {
					min = 1 + 15 * (cnt-1);
				}else {
					min = 1;
				}
				
				break;
			}
			cnt++;
		}
		//이전 버튼이 가능한지 확인
		if(min>=16) {
			prev = 1;
		}
		//다음 버튼이 가능한지 확인
		if(max < last) {
			next = 1;
		}
		//Last값으로 Max값 검사
		if(max >= last) {
			max = last;
		}
		
		result.put("first",new Integer(first));
		result.put("prev",new Integer(prev));
		result.put("min", new Integer(min));
		result.put("max",new Integer(max));
		result.put("page",new Integer(page));
		result.put("next",new Integer(next));
		result.put("last", new Integer(last));
		
		return result;
	}

	@Override
	public Post getPost(int postid) {
		return postDao.getPost(postid);
	}

	@Override
	public String getNickname(String username) {
		return postDao.getNick(username);
	}

	@Override
	public boolean login(int postid,String password) {
		boolean result = false;
		
		Post post = postDao.getPost(postid);
		
		result = BCrypt.checkpw(password, post.getUserpw());
		
		return result;
	}



	@Override
	public int increaseViews(int postid) {
		int result = 0;
		
		int views = postDao.getViews(postid);
		
		result = postDao.increaseViews(views, postid);
		
		return result;
	}

	@Override
	public int increaseRecommand(int postid) {
		int result = 0;
		
		int recommand = postDao.getRecommands(postid);
		
		result = postDao.increaseRecommands(recommand, postid);
		
		return result;
	}
	
	

//	@Override
//	public String getIpForView(Post post) {
//		String ip = null;
//		
//		String[] ipArr = post.getUserip().split(".");
//		
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append(ipArr[0]);
//		sb.append(".");
//		sb.append(ipArr[1]);
//				
//		ip = sb.toString();
//		
//		return ip;
//	}
	
	

}
