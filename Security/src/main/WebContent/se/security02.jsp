<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p> 사용자명 : <%= request.getRemoteUser() %>
	<p> 인증방법 : <%= request.getAuthType() %>
	<p> 역할이 톰캣입니까? <%= request.isUserInRole("tomcat") %>
	<p> 역할이 roll입니까? <%= request.isUserInRole("role1") %>
</body>
</html>
