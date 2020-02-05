<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>게시판</h2>
			<div id="article">
				<c:if test="${member == null}">
					<ul>
						<li><a href="/member/register">회원가입</a><br/>
						<li><a href="/member/login">로그인</a><br/>
					</ul>
				</c:if>
				<c:if test="${member != null}">
					${member.nickname }님 환영합니다!
					<br/>
					<ul>
						<li><a href="/member/logout">로그아웃</a>
						<li><a href="/member/info">내 정보</a>
					</ul>
				</c:if>
				<ul>
					<li><a href="/board/main">게시판</a><br/>
				</ul>
				
			</div>
		</div>
		
	</body>
</html>
