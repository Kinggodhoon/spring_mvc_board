<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		
		<link rel="stylesheet" type="text/css" href="/resources/css/board/write.css"/>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>글 작성</h2>
			<div id="article">
				<form accept-charset="UTF-8" method="post" id="create">
					
					<table class="tg" style="undefined;table-layout: fixed; width: 800px">
						<colgroup>
							<col style="width: 400px">
							<col style="width: 400px">
						</colgroup>
						<c:if test="${member == null}">
							<tr>
								<td class="tg-cly1">
									<input type="text" id="username" name="username" required="required" size="30" placeholder="닉네임"/>
								</td>
								<td class="tg-cly1">
									<input type="password" id="password" name="password" required="required" size="30" placeholder="비밀번호"/>
								</td>
							</tr>
						</c:if>
						<c:if test="${member != null }">
							<tr>
								<td class="tg-cly1">
									<input type="text" id="nickname" name="nickname" value="${member.nickname}" size="30" readonly/>
								</td>
								<td class="tg-cly1">
									로그인 된 회원입니다.
								</td>
							</tr>
						</c:if>
						<tr>
							<td class="tg-cly1" colspan="2">
								<input type="text" id="title" name="title" required="required" size="30" placeholder="제목"/>
							</td>
						</tr>
						<tr>                        
							<td class="tg-0lax" colspan="2">
								<textarea id="content" name="content" required="required"></textarea>
							</td>
						</tr>
					</table>
					<input type="button" id="cancle" value="취소">
					<input type="submit" value="작성"/>
				</form>
			</div>
		</div>
	</body>
	
	<script src="/resources/jquery/js/jquery.js"></script>
	<script>
		document.getElementById("cancle").addEventListener("click",function(e){
			
			location.href=document.referrer;
		});
	</script>
</html>