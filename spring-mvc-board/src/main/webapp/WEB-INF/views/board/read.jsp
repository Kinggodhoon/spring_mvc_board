<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		
		<link rel="stylesheet" type="text/css" href="/resources/css/board/read.css"/>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>
				${post.title}			
			</h2>
			<div id="article">

				<table class="tg" style="undefined;table-layout: fixed; width: 700px">
					<colgroup>
						<col style="width: 200px">
						<col style="width: 200px">
						<col style="width: 100px">
						<col style="width: 100px">
						<col style="width: 100px">
					</colgroup>
					<tr>
						<th class="tg-cly1">${post.nickname}</th>
						<th class="tg-cly1">${post.updatetime}</th>
						<th class="tg-cly1">조회 ${post.views}</th>
						<th class="tg-cly1">추천 ${post.recommand}</th>
						<th class="tg-cly1">댓글 </th>
					</tr>
					<tr>
						<td class="tg-cly1" colspan="5">
							${post.content}
						</td>
					</tr>
				</table>
				<button>수정</button>
				<!-- <button id="modify">수정</button> -->
				<button id="delete">삭제</button>
				<button id="back">뒤로가기</button>
				
				<br/>
				
				<div id="sec_comment_recommand" style="align:center;display:inline-block; margin-top:20px;margin-bottom:20px;">
					<button id="recommand">추천</button>
				</div>
				
				<br/>
				
				<div id="sec_comment_write">
					<form accept-charset="UTF-8" method="post" id="form_comment">
						<div id="sec_coomment_account">
							<c:if test="${member == null}">
								<input type="text" id="username" name="username" size="20" required="required" placeholder="닉네임">
								<br/>
								<input type="password" id="userpw" name="userpw" size="100" required="required" placeholder="비밀번호">
							</c:if>
						</div>
						&nbsp;
						<div id="sec_comment_input">
							<input type="text" id="comment" name="comment" size="100" autocomplete=off>
						</div>
						&nbsp;
						<div id="sec_comment_util">
							<input id="btn_comment_submit" type="submit" value="등록"/>
						</div>
					</form>
				</div>
				
				<div id="sec_comment_read">
					<table class="table_comment_read" style="undefined;table-layout: fixed; width: 700px">
						<colgroup>
							<col style="width: 100px">
							<col style="width: 450px">
							<col style="width: 150px">
						</colgroup>
						
						<c:forEach var="comment" items="${comments}">
							<tr>
								<td class="tg-cly2">${comment.nickname}</td>
								<td class="tg-cly2">${comment.content}</td>
								<td class="tg-cly2">${comment.posttime}</td>
							</tr>
						</c:forEach>
					</table>

				</div>
				
			</div>
		</div>
	</body>
	<script src="/resources/js/board/read.js"></script>
	<script>
		/* document.getElementById("modify").addEventListener("click",function(e){
			location.href="/board/modify/"+${post.postid};
		}); */
	
		document.getElementById("delete").addEventListener("click",function(e){
			location.href="/board/delete/"+${post.postid};
		});
		
		document.getElementById("recommand").addEventListener("click",function(e){
			var	f = document.createElement("form");
			f.setAttribute("method","post");
			f.setAttribute("action", "/board/recommand/"+${post.postid} );
			
			document.body.appendChild(f);
			
			f.submit();
		});
	</script>
</html>