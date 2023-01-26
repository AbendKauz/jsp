<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/css/board.css"/>
<meta charset="UTF-8">
<script type="text/javascript">
	function check(form){
		if(!form.name.value.trim()){
			alert("이름을 입력해주세요.");
			form.name.focus();
			return false;
		}
		if(!form.title.value.trim()){
			alert("제목을 입력해주세요.");
			form.title.focus();
			return false;
		}
		if(!form.content.value.trim()){
			alert("내용을 입력해주세요.");
			form.content.focus();
			return false;
		}
		if(!form.pass.value){
			alert("비밀번호를 입력해주세요.");
			form.pass.focus();
			return false;
		}
		if(!form.ofile.value.trim()){
			alert("첨부파일을 등록해주세요.");
			return false;
		}
	}
</script>
<title>게시판</title>
</head>
<body>
	<h1> 파일 첨부형 게시판 - 글쓰기(Write) </h1>
	<form method="post" name="WriteFrm" enctype="multipart/form-data" action="./write.do" onsubmit="return check(this);">
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="5" cols="50" name="content" id="content"></textarea></td>
			</tr>
			<tr>
				<td>첨부 파일</td>
				<td><input type="file" name="ofile"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass" id="pass"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">작성 완료</button>
					<button type="reset">RESET</button>
					<button type="button" onclick="location.href='./list.do';">목록 바로가기</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>