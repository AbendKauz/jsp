<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01", "manager", "1234");
			out.println("연결에 성공했습니다.");
		}catch(SQLException e){
			out.println("연결에 실패했습니다.");
		}finally{
			if(connection != null){
				connection.close();
			}
		}
	%>
</body>
</html>