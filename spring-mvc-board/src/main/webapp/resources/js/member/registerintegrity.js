var username = document.getElementById("username");
var nickname = document.getElementById("nickname");
var password = document.getElementById("password");
var passwordcheck = document.getElementById("passwordcheck");


var usernamedisp = document.getElementById("usernamedisp");
var nickdisp = document.getElementById("nickdisp");
var pwdisp = document.getElementById("pwdisp");
var pwchkdisp = document.getElementById("pwchkdisp");

var idcheck = false;
var nickcheck = false;

var pwchkcheck = false;
var pwlenhcheck = false;
var pwlevelcheck = false;



//아이디
username.addEventListener("focusout",function(e){
	//아이디 길이
	if(username.value.length < 5){
		idcheck = false;
		usernamedisp.innerHTML = "아이디가 너무 짧습니다.";
		usernamedisp.style.color = "red";
		
		return;
	}
	
	//아이디 공백
	if(username.value.search(/\s/) != -1){
		idcheck = false;
		usernamedisp.innerHTML = "공백은 포함 될 수 없습니다.";
		usernamedisp.style.color = "red";
		
		return;
	}
	
	//JQUERY를 사용한 AJAX를 이용해서 중복검사 실행
	$.ajax({
		url:'usernamecheck?username='+username.value,
		data:'json',
		success:function(data){
			if(data.result === 'false'){
				idcheck = true;
				usernamedisp.innerHTML = "사용 가능한 아이디입니다.";
				usernamedisp.style.color = 'green';
			}else{
				idcheck = false;
				usernamedisp.innerHTML = "사용 불가능한 아이디입니다.";
				usernamedisp.style.color = 'red';
			}
		}
	});
});

//닉네임
nickname.addEventListener("focusout",function(e){
	//닉네임 길이
	if(nickname.value.length < 2){
		nickcheck = false;
		nickdisp.innerHTML = "닉네임이 너무 짧습니다.";
		nickdisp.style.color = "red";
		
		return;
	}
	
	//닉네임 공백
	if(nickname.value.search(/\s/) != -1){
		nickcheck = false;
		nickdisp.innerHTML = "공백은 포함 될 수 없습니다.";
		nickdisp.style.color = "red";
		
		return;
	}
	
	//JQUERY를 사용한 AJAX를 이용해서 중복검사 실행
	$.ajax({
		url:'nickcheck?nickname='+nickname.value,
		data:'json',
		success:function(data){
			if(data.result === 'false'){
				nickcheck = true;
				nickdisp.innerHTML = "사용 가능한 닉네임입니다.";
				nickdisp.style.color = 'green';
			}else{
				nickcheck = false;
				nickdisp.innerHTML = "사용 불가능한 닉네임입니다.";
				nickdisp.style.color = 'red';
			}
		}
	});
});

//비밀번호
password.addEventListener("keyup",function(e){
	var pwvalue = password.value;
	
	//비밀번호가 8자리 미만 일 때
	if(pwvalue.length < 8){
		pwcheck = false;
		pwdisp.innerHTML = "비밀번호가 너무 짧습니다.";
		pwdisp.style.color = "red";
		
		return;
	}
	//비밀번호에 공백이 포함될 때
	if(pwvalue.search(/\s/) != -1){
		pwcheck = false;
		pwdisp.innerHTML = "공백은 포함 될 수 없습니다.";
		pwdisp.style.color = "red";
		
		return;
	}
	
	var number = /[0-9]/;
	var small = /[a-z]/;
	var big = /[A-Z]/;
	var special = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
	
	var cnt = 0;
	
	if(number.test(pwvalue)){
		cnt++;
	}
	if(small.test(pwvalue)){
		cnt++;
	}
	if(big.test(pwvalue)){
		cnt++;
	}
	if(special.test(pwvalue)){
		cnt++;
	}
	
	if(cnt == 4){
		pwlevelcheck = true;
		pwdisp.innerHTML = "매우 강함";
		pwdisp.style.color = "green";
	}else if(cnt == 3){
		pwlevelcheck = true;
		pwdisp.innerHTML = "강함";
		pwdisp.style.color = "yellow";
	}else if(cnt == 2){
		pwlevelcheck = false;
		pwdisp.innerHTML = "보통";
		pwdisp.style.color = "orange";
	}else if(cnt == 1){
		pwlevelcheck = false;
		pwdisp.innerHTML = "약함";
		pwdisp.style.color = "red";
	}else{
		pwlevelcheck = false;
		pwdisp.innerHTML = "";
		pwdisp.style.color = "black";
	}
});

//비밀번호 확인
passwordcheck.addEventListener('focusout',function(e){
	var pwvalue = password.value;
	var pwchkvalue = passwordcheck.value;
	
	if(pwvalue === pwchkvalue){
		pwchkcheck = true;
		pwchkdisp.innerHTML = "비밀번호가 일치합니다.";
		pwchkdisp.style.color = "green";
	}else{
		pwchkcheck = false;
		pwchkdisp.innerHTML = "비밀번호가 일치하지 않습니다.";
		pwchkdisp.style.color = "red";
	}
});



//유효성 검사를 통과하지 못했을 때 submit 이벤트가 넘어가는 것을 막는 코드
document.getElementById("joinform").addEventListener("submit",function(e){
	//아이디 중복검사
	if(idcheck == false){
		usernamedisp.innerHTML = "아이디 유효성 검사를 통과해야합니다.";
		usernamedisp.style.color = "red";
		username.focus();
		
		e.preventDefault();
	}
	//닉네임 중복검사
	if(nickcheck == false){
		nickdisp.innerHTML = "닉네임 유효성 검사를 통과해야합니다.";
		nickdisp.style.color = "red";
		nickname.focus();
		
		e.preventDefault();
	}
	if(pwlencheck == false){
		pwdisp.innerHTML = "비밀번호가 너무 짧습니다.";
		pwdisp.style.color = "red";
		password.focus();
		
		e.preventDefault();
	}
	if(pwlevelcheck == false){
		pwdisp.innerHTML = "비밀번호 강도를 강함이상으로 다시 설정해주세요.";
		pwdisp.style.color = "red";
		password.focus();
		
		e.preventDefault();
	}
	if(pwchkcheck == false){
		pwchkdisp.innerHTML = "비밀번호가 일치하지 않습니다!";
		pwchkdisp.style.color = "red";
		passwordcheck.focus();
		
		e.preventDefault();
	}
});