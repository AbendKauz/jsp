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
		var form = document.loginForm;
		if(form.id.value.length < 4 || form.id.value.length > 10){
			alert("아이디는 4 ~ 10자 이내로 가능합니다.");
			form.id.focus();
			return;
		}else if(form.pw.value.length < 4){
			alert("비밀번호는 4자 이상으로 입력해야 합니다..");
			form.pw.focus();
			return;
		}else{
			form.submit();
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