package gmail.kinggodhoon.board.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Post {
	private int postid;
	private int boardid;
	private String title;
	private String content;
	private int views;
	private int recommand;
	private Date posttime;
	private Date updatetime;
	private boolean ismember;
	private String username;
	private String userpw;
	private String userip;
	
	private int comments;
	private String disptime;
	private String nickname;
}