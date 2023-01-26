<%@ page import="java.sql.*" %>

<%
	Connection conn = null;
	Class.forName("com.mysql.jdbc.Driver");
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01", "manager", "1234");
%>
