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
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01", "manager", "1234");
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("insert into student(id, name, passwd) values ('6', '니나뇨', '5678')");
		} catch(SQLException e) {
			out.println("연결 실패");
		} finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	%>
</body>
</html>