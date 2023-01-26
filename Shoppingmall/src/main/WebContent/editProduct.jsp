<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<title>상품 수정 및 삭제</title>
</head>
<body>
	<% 
		String edit = request.getParameter("edit");
	%>
	
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<% if(edit.equals("update")){ %>
				<h1 class="display-3">상품 수정</h1>
			<% } else if(edit.equals("delete")){%>
				<h1 class="display-3">상품 삭제</h1>
			<% } %>
		</div>
	</div>
	
	<div class="container">
		<div class="row" align="center">
			<%@ include file="dbconn.jsp" %>
			<%
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sql = "select * from product";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
			%>
				<div class="col-md-4">
					<img src="./resources/image/<%= rs.getString("p_filename") %>" style="width: 100%">
					<h3><%= rs.getString("p_name") %> </h3>
					<p><%= rs.getString("p_description") %>
					<p><%= rs.getString("p_unitPrice") %>원
					<p><% if(edit.equals("update")){ %>
							<a href="./updateProduct.jsp?id=<%= rs.getString("p_id") %>" class="btn btn-success" role="button">수정</a>
					<%	} else if(edit.equals("delete")){ %>
							<a href="#" onclick="deleteConfirm('<%= rs.getString("p_id") %>')" class="btn btn-danger" role="button">삭제</a>
					<%	}	%>
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
	</div>
	<script type="text/javascript">
		function deleteConfirm(id){
			if(confirm("해당 상품을 삭제합니다!!") == true){
				location.href="./deleteProduct.jsp?id=" + id;
			}else{
				return;
			}
		}
	</script>
	<jsp:include page="footer.jsp"/>
</body>
</html>