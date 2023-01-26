<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function CheckLogin(){
		if(document.loginForm.id.value == ""){
			alert("아이디를 입력해주세요.");
			document.loginForm.id.focus();
			return false;
		}else if(document.loginForm.pw.value == ""){
			alert("비밀번호를 입력해주세요.");
			document.loginForm.pw.focus();
			return false;
		}else{
			document.loginForm.submit();
		}
	}
</script>
<body>
	<form action="validation01_ok.jsp" name="loginForm">
		<p> 아이디 : <input type="text" name="id">
		<p> 비밀번호 : <input type="password" name="pw">
		<p> <input type="button" value="전송" onclick="CheckLogin()">
	</form>
</body>
</html>