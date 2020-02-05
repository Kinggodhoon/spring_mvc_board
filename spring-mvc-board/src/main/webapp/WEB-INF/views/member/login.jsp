<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		
		<link rel="stylesheet" type="text/css" href="/resources/css/member/login.css"/>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>로그인</h2>
			<div id="article">
				<form accept-charset="UTF-8" method="post">
					
					<input type="text" name="username" size="30" required="required" placeholder="아이디"/></br>
					<input type="password" name="password" size="30" required="required" placeholder="비밀번호"/></br>
					
					<input type="submit" value="로그인"/>
					<input type="button" id="mainbtn" value="취소"/>
					
				</form>
			</div>
			<div id="error">
				<p>${error}</p>
			</div>
			<p>
				<a href="register">회원가입</a>
			</p>
		</div>
	</body>
	<script>
		document.getElementById("mainbtn").addEventListener("click",function(e){
			location.href="/"
		});
	</script>
</html>