<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로케일 표현식</title>
</head>
<body>
	<%
		Locale locale = request.getLocale();
		String date = DateFormat.getDateTimeInstance(DateFormat.FULL, 
				DateFormat.SHORT, locale).format(new Date());
	%>
	<p> 현재 로케일 날짜 형식
	<p> <%= date %>
</body>
</html>
