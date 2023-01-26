<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<title>상품 목록</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품목록</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row" align="center">
			<%@ include file="dbconn.jsp" %>
			<%
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = "select * from product";	// product 테이블의 모든 정보 가져오기
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
			%>
			<div class="col-md-4">
				<img alt="" src="./resources/image/<%= rs.getString("p_filename") %>" style="width: 100%">
				<h3><%= rs.getString("p_name") %> </h3>
				<p><%= rs.getString("p_description") %>
				<p><%= rs.getString("p_unitPrice") %>원
				<p><a href="./product.jsp?id=<%= rs.getString("p_id") %>"
				class="btn btn-secondary" role="button">상세 정보</a>
			</div>
			<%	} %>
			<%
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			%>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>
