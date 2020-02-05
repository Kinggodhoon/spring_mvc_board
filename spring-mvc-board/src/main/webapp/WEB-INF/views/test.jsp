<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="tg">
		<tr>
			<td class="tg-cly1">변경 할 비밀번호</td>
			<td class="tg-cly1">
				<input type="text" size="20" id="changeNick"/>
			</td>
			<td class="tg-cly1">
				<a id="btn-check-nick" href="#" onClick="return false;">검사</a>
			</td>
		</tr>
		<tr>
			<td class="tg-cly1" colspan="3"> 
				<div id="disp-check-nick"></div>
			</td>
		</tr>
		<tr>
			<td class="tg-cly1" colspan="3">
				<a id="btn-cancle-nick" href="#" onClick="return false;">취소</a>
				<a id="btn-submit-nick" href="#" onClick="return false;">확인</a>
			</td>
		</tr>
	</table>
</body>
</html>