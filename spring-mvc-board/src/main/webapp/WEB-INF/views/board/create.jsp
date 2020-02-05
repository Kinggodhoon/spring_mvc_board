<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>게시판 생성</h2>
			<div id="article">
				<form accept-charset="UTF-8" method="post" id="create">
					
					<input type="text" name="boardname" id="boardname" size="30" required="required" placeholder="게시판 이름"/><br/>
					<div id="disp">2글자 이상으로 작성해주세요.</div>
					
					<input type="submit" value="생성"/>
					<input type="button" id="mainbtn" value="취소"/>
				</form>
			</div>
		</div>
	</body>
	
	<script src="/resources/jquery/js/jquery.js"></script>
	<script src="/resources/js/board/create.js"></script>
	<script>
		document.getElementById("mainbtn").addEventListener("click",function(e){
			location.href="/";
		});
	</script>
</html>