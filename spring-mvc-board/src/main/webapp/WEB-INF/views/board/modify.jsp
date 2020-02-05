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
			<h2>수정</h2>
			<div id="article">
				<form accept-charset="UTF-8" method="post" id="create">
					
					<table class="tg" style="undefined;table-layout: fixed; width: 800px">
						<colgroup>
							<col style="width: 400px">
							<col style="width: 400px">
						</colgroup>
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
					<input class="utilBtn" type="button" id="cancle" value="취소">
					<input class="utilBtn" type="submit" value="작성"/>
				</form>
			</div>
		</div>
	</body>
	
	
</html>