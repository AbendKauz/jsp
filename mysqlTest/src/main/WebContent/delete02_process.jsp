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
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		// 아이디와 비밀번호가 맞는지 점검하고, 맞으면 삭제하는 처리구문
		try {
			String sql = "select id, passwd from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String id2 = rs.getString("id");
				String passwd2 = rs.getString("passwd");
				if(id2.equals(id) && passwd2.equals(passwd)){
					sql = "delete from member where id=? and passwd =?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, passwd);
					pstmt.executeUpdate();
					out.println("해당 내용을 정상적으로 삭제했습니다.");
				}else{
					out.println("아이디나 비밀번호를 확인하세요.");
				}
			}else{
				out.println("정상적으로 작동하지 않았습니다.");
			}
		} catch(SQLException e){
			out.println("DB연결이 안되었습니다.");
		} finally {
			if(rs != null){
				rs.close();
			}
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