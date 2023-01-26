<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		session.setAttribute("id", id);
		session.setAttribute("pw", pw);
	%>
</head>
<body>
	아이디 : <%= id %><br>
	비밀번호 : <%= pw %>
</body>
</html>