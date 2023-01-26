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
	<%@ include file="dbconn.jsp" %>
	<%
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "insert into member values('" + id + "', '" + passwd + "', '" + name + "')";
			String sql2 = "update member set name = '홍길동' where id = 'abc'";
			String sql3 = "delete from member where name = '홍길동'";
			
			stmt.execute(sql3);
			out.println("연결 성공");
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