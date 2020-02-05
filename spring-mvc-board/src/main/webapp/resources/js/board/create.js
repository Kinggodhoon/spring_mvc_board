var boardname = document.getElementById("boardname");
var disp = document.getElementById("disp");

var namechk = false;


boardname.addEventListener("focusout",function(e){
	boardname.value = boardname.value.trim();
	
	if(boardname.value.length < 2){
		namechk = false;
		disp.innerHTML = "게시판 이름이 너무 짧습니다.";
		disp.style.color = "red";
		
		return;
	}
	
	//JQUERY를 사용한 AJAX를 이용해서 중복검사 실행
	$.ajax({
		url:'boardnamecheck?boardname='+boardname.value,
		data:'json',
		success:function(data){
			if(data.result === 'false'){
				namechk = true;
				disp.innerHTML = "사용 가능한 게시판 이름입니다.";
				disp.style.color = 'green';
			}else{
				namechk = false;
				disp.innerHTML = "이미 존재하는 게시판 이름입니다.";
				disp.style.color = 'red';
			}
		}
	});
	
});

document.getElementById("create").addEventListener("submit",function(e){
	//검사
	if(namechk == false){
		disp.innerHTML = "사용 할 수 없는 게시판 이름입니다.";
		disp.style.color = "red";
		boardname.focus();
		
		e.preventDefault();
	}
});