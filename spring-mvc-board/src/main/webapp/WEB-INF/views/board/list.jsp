<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		
		<link rel="stylesheet" type="text/css" href="/resources/css/board/list.css"/>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>${board.boardname}</h2>
			<a href="write?boardid=${board.boardid}">글 쓰기</a>
			<div id="article">
				<table class="tg" style="undefined;table-layout: fixed; width: 750px">
					<colgroup>
						<col style="width: 60px"><!-- 글번호 -->
						<col style="width: 400px"><!-- 제목 -->
						<col style="width: 100px"><!-- 작성자 -->
						<col style="width: 70px"><!-- 작성일 -->
						<col style="width: 60px"><!-- 조회수 -->
						<col style="width: 60px"><!-- 추천 -->
					</colgroup>
					<tr>
						<th class="tg-cly1">글 번호</th>
						<th class="tg-cly1">제목</th>
						<th class="tg-cly1">작성자</th>
						<th class="tg-cly1">작성일</th>
						<th class="tg-cly1">조회수</th>
						<th class="tg-cly1">추천</th>
					</tr>
					<c:forEach var="post" items="${list}">
						<tr>
							<td class="tg-cly1">${post.postid}</td>
							<td class="tg-cly1">
								<a href="/board/read/${post.postid}">${post.title}</a>
							</td>
							<td class="tg-cly1">${post.nickname}</td>
							<td class="tg-cly1">${post.disptime}</td>
							<td class="tg-cly1">${post.views}</td>
							<td class="tg-cly1">${post.recommand}</td>
						</tr>
					</c:forEach>
					
				</table>
				<br/>
				<div id=paging>
					<c:if test="${page.prev > 0}">
						<a href="/board/list?boardid=${board.boardid}&page=1">처음</a>&nbsp;
						<a href="/board/list?boardid=${board.boardid}&page=${page.min - 15}">이전</a>&nbsp;
					</c:if>
					<c:forEach var="cnt" begin="${page.min}" end="${page.max}" step="1">
						<a href="/board/list?boardid=${board.boardid}&page=${cnt}">${cnt}</a>&nbsp;
					</c:forEach>
					<c:if test="${page.next > 0}">
						<a href="/board/list?boardid=${board.boardid}&page=${page.max+1}">다음</a>&nbsp;
						<a href="/board/list?boardid=${board.boardid}&page=${page.last}">끝</a>&nbsp;
					</c:if>
				</div>
			</div>
		</div>
	</body>

</html>