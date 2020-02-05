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
		<ul>
			<c:forEach var="list" items="${board}">
				<li>
					<a href="list?boardid=${list.boardid}&page=1">${list.boardname}</a>
				</li>
			</c:forEach>
		</ul>
		<c:if test="${member.isadmin != null}">
			<a href="/board/create">게시판 생성</a>
		</c:if>
	</body>
</html>