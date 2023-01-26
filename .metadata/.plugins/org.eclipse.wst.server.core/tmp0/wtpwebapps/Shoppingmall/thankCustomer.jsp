<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<title>주문 완료</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">주문 완료</h1>
		</div>
	</div>
	<div class="container">
		<h2 class="alert alert-danger">주문이 완료되었습니다.</h2>
	</div>
	<%
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++){
			
			cookies[i].setMaxAge(0);
			
			response.addCookie(cookies[i]);
		}
	%>
	<div class="container">
		<p><a href="./products.jsp" class="btn btn-secondary">상품 목록</a>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>