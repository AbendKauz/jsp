
<%@ page import="java.sql.*" %>
<%
	Connection conn = null;

	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WebMarketDB", "manager", "1234");
	} catch(SQLException e){
		out.println("데이터베이스 연결이 실패하였습니다.");
	}
%>