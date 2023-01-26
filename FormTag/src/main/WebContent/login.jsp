<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resources/css/login.css">
</head>
<body>
	<div id="login">
		<img alt="" src="">
		<form action="loginProcess.jsp" method="post">
			<input type="text" name="id" class="in" placeholder="아이디"><br>
			<input type="password" name="pw" class="in" placeholder="비밀번호"><br>
			<input type="submit" value="로그인" class="in" id="button">
		</form>
	</div>
</body>
</html>