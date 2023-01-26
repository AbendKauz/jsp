<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form_result</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>요청 파라미터 이름</th>
			<th>요청 파라미터 값</th>
		</tr>
	<%
		Enumeration name1 = request.getParameterNames();
		while(name1.hasMoreElements()){
			String name2 = (String) name1.nextElement();
			out.println("<tr><td>" + name2 + "</td>\n");
			String name3 = request.getParameter(name2);
			out.println("<td>" + name3 + "</td></tr>");
		}
		
	%>
	</table>
</body>
</html>