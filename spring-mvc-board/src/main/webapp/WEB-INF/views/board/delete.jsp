<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Board</title>
		
		<link rel="stylesheet" type="text/css" href="/resources/css/board/write.css"/>
	</head>
	
	<body>
		<div id="main" align="center">
			<h2>게시물 삭제</h2>
			<div id="authentication">
				<h3>비밀번호를 입력하세요.</h3>
				<div id=inner>
					<input id="pw" type="password" required='required' size=30>
					
					<div id=btnSection>
						<button id="cancle">취소</button>
						<button id="submit">삭제</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/resources/jquery/js/jquery.js"></script>
	<script>
		var pw = document.getElementById("pw");
		var path = window.location.pathname;
		
		var postid = path.split("/")[3];
		
	
		document.getElementById("cancle").addEventListener("click",function(e){
			console.log(postid);
			//location.href=document.referrer;
		});
		
		document.getElementById("submit").addEventListener("click",function(e){
			$.post({
				url:'/board/auth',
				type:'post',
				data:{postid:postid,password:pw.value},
				success:function(data){
					if(data.result === 'true'){
						var check = confirm("정말 삭제하시겠습니까?");
						
						if(check == true){
							var	f = document.createElement("form");
							f.setAttribute("method","post");
							f.setAttribute("action", path);
							
							document.body.appendChild(f);
							
							f.submit();
						}
					}else{
						alert("비밀번호가 일치하지않습니다. 확인 후 다시 시도해주세요.");
					}
				}
			});
		});
	</script>
	
</html>