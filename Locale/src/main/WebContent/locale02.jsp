<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Locale" %>
<%
	response.setContentType("text/html");
	response.setHeader("Content-Language", "es");
	String title = "Espa&ntilde;ol";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<% out.print(title); %>
</title>
</head>
<body>
	<p> 나라 : Espa&ntilde;ol </p>
	<p> &iexcl;Hola Mundo!</p>
</body>
</html>