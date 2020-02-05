<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		
		<link rel="stylesheet" type="text/css" href="/resources/css/member/register.css"/>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>회원가입</h2>
			<div id="article">
				<form accept-charset="UTF-8" method="post" id="joinform">
				
					<table class="tg">
						<!-- 아이디 -->
						<tr>
							<th class="tg-0pky" rowspan="2">아이디</th>
							<th class="tg-0pky" colspan="4">
								<input type="text" id="username" name="username" size="20" required="required" placeholder="아이디를 입력하세요."/>
							</th>
						</tr>
						<tr>
							<td class="tg-0pky" colspan="4">
								<div id="usernamedisp">5~20자 영문,숫자 조합으로 입력해주세요.</div>
								<br/>
							</td>
						</tr>
					  
						<!-- 닉네임 -->
						<tr>
							<th class="tg-0pky" rowspan="2">닉네임</th>
							<th class="tg-0pky" colspan="4">
								<input type="text" id="nickname" name="nickname" size="20" required="required" placeholder="닉네임을 입력하세요."/>
							</th>
						</tr>
						<tr>
							<td class="tg-0pky" colspan="4">
					   			<div id="nickdisp">2~20자 닉네임을 입력해주세요.(띄어쓰기 불가능)</div>
					   			<br/>
							</td>
						</tr>
						
						<!-- 비밀번호 -->
						<tr>
							<th class="tg-0lax" rowspan="3">비밀번호</th>
							<th class="tg-0lax" colspan="4">
								<input type="password" id="password" name="password" size="20" required="required" placeholder="비밀번호를 입력하세요."/>
							</th>
						</tr>
						<tr>
							<td class="tg-0lax" colspan="4">
								<div id="pwdisp">대문자, 소문자, 숫자, 특수문자를 포함하는 8~20자의 비밀번호를 입력해주세요.</div>
							</td>
						</tr>
						<tr>
							<th class="tg-0lax" colspan="4">
								<input type="password" id="passwordcheck" size="20" required="required" placeholder="비밀번호 확인"/>
								<div id="pwchkdisp"></div>
								<br/>
							</th>
						</tr>
						<tr>
							<td class="tg-0lax" colspan="2">
								&emsp;&emsp;&emsp;<input type="submit" value="가입"/>	
							</td>
							<td class="tg-0lax">&emsp;&emsp;</td>
							<td class="tg-0lax" colspan="2">
								&emsp;&emsp;&emsp;<input type="button" id="mainbtn" value="메인으로"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
	<script src="/resources/jquery/js/jquery.js"></script>
	<script src="/resources/js/member/registerintegrity.js"></script>
	<script>
		document.getElementById("mainbtn").addEventListener("click",function(e){
			location.href="/"
		});
	</script>
</html>