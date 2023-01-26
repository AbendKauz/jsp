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
		
		PreparedStatement pstmt = null;
		
		try {
			String sql1 = "insert into member(id, passwd, name) values (?, ?, ?)";
			String sql2 = "update member set name=? where id=?";
			String sql3 = "delete from member where name=?";
			pstmt = conn.prepareStatement(sql1);
			
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			pstmt.setString(3, name);
			
			/* pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, "홍길동");
			pstmt.setString(2, "aaa"); */
			
			/* pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, "홍길동"); */
			
			
			pstmt.executeUpdate();
			
			out.println("연결 성공");
		} catch(SQLException e) {
			out.println("연결 실패");
		} finally {
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	%>
</body>
</html>