package gmail.kinggodhoon.board.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class Member {
	private int userid;
	private String username;
	private String password;
	private String nickname;
	private Date regdate;
	private boolean isadmin;
	
	
	private String hUsername;
}
