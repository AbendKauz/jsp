<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbconn.jsp" %>

<%
	String productId = request.getParameter("id");
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "select * from product";
	
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	if(rs.next()){
		sql = "delete from product where p_id=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, productId);
		pstmt.executeUpdate();
	%>
		<script>
			alert("상품을 삭제했습니다.");
		</script>
	<% } else { %>
		<script>
			alert("해당 상품이 없습니다.");
		</script>
	<% }
	
	if(rs != null) rs.close();
	if(pstmt != null) pstmt.close();
	if(conn != null) conn.close();
	
	/* response.sendRedirect("editProduct.jsp?edit=delete"); */
%>
	<script>
		location.href="editProduct.jsp?edit=delete";
	</script>
