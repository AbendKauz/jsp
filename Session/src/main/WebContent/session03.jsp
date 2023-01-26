<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% session.removeAttribute("pw"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String name;
		String value;
		
		Enumeration en = session.getAttributeNames();
		int i = 0;
		
		while(en.hasMoreElements()){
			name = en.nextElement().toString();
			value = session.getAttribute(name).toString();
			
			out.println("설정된 세션의 속성 이름 [" + i + "] : " + name + "<br>");
			out.println("설정된 세션의 속성 값 [" + i + "] : " + value + "<br>");
			i++;
		}
		session.invalidate();
		
		out.println("=================================");
	%>
	<br>
	<%
		if(request.isRequestedSessionIdValid() == true){
			out.print("세션이 유효합니다.");
		}else {
			out.print("세션이 유효하지 않습니다.");
		}
	%>
</body>
</html>