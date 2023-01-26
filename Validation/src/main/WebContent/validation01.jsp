<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function CheckForm(){
		if(document.loginForm.id.value == ""){
			alert("아이디를 입력해주세요.");
			document.loginForm.id.select();
			return false;
		}else if(document.loginForm.pw.value == ""){
			alert("비밀번호를 입력해주세요.");
			document.loginForm.pw.select();
			return false;
		}else{
			document.loginForm.submit();
		}
	}
</script>
</head>
<body>
	<form action="validation01_ok.jsp" name="loginForm" method="post">
		<p> 아이디 : <input type="text" name="id">
		<p> 비밀번호 : <input type="password" name="pw">
		<p> <input type="submit" value="전송" onclick="return CheckForm()">
	</form>
</body>
</html>