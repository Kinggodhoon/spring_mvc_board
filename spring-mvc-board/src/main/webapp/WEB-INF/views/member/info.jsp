<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 response.setHeader("Cache-Control","no-cache");
 response.setHeader("Pragma","no-cache");
 response.setDateHeader("Expires",0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		
		<link rel="stylesheet" type="text/css" href="/resources/css/member/info.css"/>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>회원 정보</h2>
			<div id="article">
				<!-- 유저 코드 -->
				<div id="con-userid" style="display:none;">${member.userid}</div>
				
				<!-- 아이디 -->
				<div id="con-username">
					<table class="tg" style="undefined;table-layout: fixed; width: 161px; height:120px;">
						<tr>
							<th class="tg-cly1">아이디</th>
							<td class="tg-cly1">${member.username}</td>
						</tr>
					</table>
				</div>
				
				<!-- 닉네임 -->
				<div id="con-nickname">
					<table class="tg" style="undefined;table-layout: fixed; width: 330px;">
						<tr id="disp-nickname">
							<th class="tg-cly1">닉네임</th>
							<td class="tg-cly1">${member.nickname}</td>
							<td class="tg-cly1">
								<a id="btn-change-nick" href="#" onClick="return false;">변경하기</a>
							</td>
						</tr>
						<tr>
							<td class="tg-0lax" colspan="3">
								<div id="div-change-nick" style="display:none;">
									<table class="tg">
										<tr>
											<th class="tg-cly1">변경 할 닉네임</th>
											<td class="tg-cly1">
												<input type="text" size="20" id="changeNick"/>
											</td>
											<td class="tg-cly1">
												<a id="btn-check-nick" href="#" onClick="return false;">검사</a>
											</td>
										</tr>
										<tr>
											<td class="tg-cly1" colspan="3"> 
												<div id="disp-check-nick">2~20자 닉네임을 입력해주세요.(띄어쓰기 불가능)</div>
											</td>
										</tr>
										<tr>
											<td class="tg-cly1" colspan="3">
												<a id="btn-cancle-nick" href="#" onClick="return false;">취소</a>&emsp;&emsp;&emsp;
												<a id="btn-submit-nick" href="#" onClick="return false;">확인</a>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<!-- 가입일 -->
				<div id="con-regdate">
					<table class="tg" style="undefined;table-layout: fixed; width: 180px">
						<tr>
							<th class="tg-cly1">가입일</th>
							<td class="tg-cly1" style="width:80px;">${member.regdate}</td>
						</tr>
					</table>
				</div>
				
				<!-- 가입일 -->
				<div id="con-regdate">
					<table class="tg" style="undefined;table-layout: fixed; width: 360px">
						<tr>
							<th class="tg-cly1">회원 탈퇴</th>
							<td class="tg-cly1" style="width:80px;">
								<a href="#" id="btn-leave" onClick="return false;">탈퇴</a>
							</td>
						</tr>
						<tr>
							<td class="tg-cly1" colspan="2">
								<div id="div-leave" style="display:none">
									<table class="tg">
										<tr>
											<td class="tg-cly1" id="disp-leave" colspan="3">
												정말 탈퇴하시겠습니까?<br/>작성된 글과 댓글은 모두 보존되며,<br/>탈퇴시 사용하던 아이디와 닉네임은 사용 불가능합니다.
											</td>
										</tr>
										<tr>
											<th class="tg-cly1">비밀번호 확인</th>
											<td class="tg-cly1">
												<input type="password" id="password" size="20">
											</td>
											<td class="tg-cly1" style="width:30px;">
												<a href="#" id="btn-check-leave" onClick="return false;">탈퇴</a>
											</td>
										</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<!-- 유틸리티 -->
				<div id="util">
					<input type="button" id="mainbtn" value="메인으로"/>
				</div>
			</div>
		</div>
	</body>
	
	<script src="/resources/jquery/js/jquery.js"></script>
	<script src="/resources/js/member/info.js"></script>
	<script>
		document.getElementById("mainbtn").addEventListener("click",function(e){
			location.href="/"
		});
	</script>
</html>