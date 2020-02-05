var nickChangeDiv = document.getElementById("div-change-nick");
var nickname = document.getElementById("changeNick");
var nickchkdisp = document.getElementById("disp-check-nick");
var nickdisp = document.getElementById("disp-nickname");
var userid = document.getElementById("con-userid").innerHTML;

var password = document.getElementById("password");

var nickcheck = false;

var leavedisp = document.getElementById("disp-leave");


document.getElementById("btn-change-nick").addEventListener("click",function(e){
	nickChangeDiv.style.display = "block";
});


document.getElementById("btn-check-nick").addEventListener("click",function(e){
	//닉네임 길이
	if(nickname.value.length < 2){
		nickcheck = false;
		nickchkdisp.innerHTML = "닉네임이 너무 짧습니다.";
		nickchkdisp.style.color = "red";
		
		return;
	}
	
	//닉네임 공백
	if(nickname.value.search(/\s/) != -1){
		nickcheck = false;
		nickchkdisp.innerHTML = "공백은 포함 될 수 없습니다.";
		nickchkdisp.style.color = "red";
		
		return;
	}
	
	//JQUERY를 사용한 AJAX를 이용해서 중복검사 실행
	$.ajax({
		url:'nickcheck?nickname='+nickname.value,
		data:'json',
		success:function(data){
			if(data.result === 'false'){
				nickcheck = true;
				nickchkdisp.innerHTML = "사용 가능한 닉네임입니다.";
				nickchkdisp.style.color = 'green';
			}else{
				nickcheck = false;
				nickchkdisp.innerHTML = "사용 불가능한 닉네임입니다.";
				nickchkdisp.style.color = 'red';
			}
		}
	});
});
document.getElementById("btn-cancle-nick").addEventListener("click",function(e){
	nickcheck = false;
	nickchkdisp.innerHTML = "2~20자 닉네임을 입력해주세요.(띄어쓰기 불가능)";
	nickchkdisp.style.color = "black";
	
	nickname.value = "";
	 
	nickChangeDiv.style.display = "none";
});


document.getElementById("btn-submit-nick").addEventListener("click",function(e){
	if(nickcheck === true){
		$.post({
			url:'updatenick',
			type:'post',
			data:{userid:parseInt(userid,10),nickname:nickname.value},
			success:function(data){
				if(data.result === 'false'){
					nickdisp.innerHTML = "닉네임 변경에 실패했습니다.";
					nickdisp.style.color = 'red';
					
					nickcheck = false;
					nickchkdisp.innerHTML = "2~20자 닉네임을 입력해주세요.(띄어쓰기 불가능)";
					nickchkdisp.style.color = "black";
					
					nickname.value = "";
					 
					nickChangeDiv.style.display = "none";
				}else{
					nickdisp.innerHTML = "닉네임이 성공적으로 변경되었습니다.";
					nickdisp.style.color = 'green';
					
					nickcheck = false;
					nickchkdisp.innerHTML = "2~20자 닉네임을 입력해주세요.(띄어쓰기 불가능)";
					nickchkdisp.style.color = "black";
					
					nickname.value = "";
					 
					nickChangeDiv.style.display = "none";
					
				}
			}
		});
	}
	else{
		nickcheck = false;
		nickchkdisp.innerHTML = "닉네임 검사를 통과한 후 진행해주세요.";
		nickchkdisp.style.color = "red";
	}
});

document.getElementById("btn-leave").addEventListener("click",function(e){
	document.getElementById("div-leave").style.display = "block";
});

document.getElementById("btn-check-leave").addEventListener("click",function(e){
	$.post({
		url:'leavemember',
		type:'post',
		data:{userid:parseInt(userid,10),password:password.value},
		success:function(data){
			if(data.result === 'true'){
				location.href = "/";
			}else{
				leavedisp.innerHTML = "회원 탈퇴에 실패했습니다. 다음에 다시 시도해주세요.";
				leavedisp.style.color = "red";
			}
		}
	});
});
