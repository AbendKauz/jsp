<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.Product" %>
<%@ page import="dao.ProductRepository" %>
<%@ page import="dto.Product" %>
<%@ page import="dao.ProductRepository" %>
<%@ page errorPage="exceptionNoProductId.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>상품 목록</title>
<script type="text/javascript">
	function addToCart(){
		if(confirm("상품을 장바구니에 추가하시겠습니까?")){
			document.addForm.submit();
		}else{
			document.addForm.reset();
		}
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 상세 정보</h1>
		</div>
	</div>
	<%
		String id = request.getParameter("id");
		ProductRepository dao = ProductRepository.getInstance();
		Product product = dao.getProductById(id);
	%>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<img alt="" src="./resources/image/<%= product.getFilename() %>" style="width: 100%">
				<h3><%= product.getPname() %></h3>
				<p><%= product.getDescription() %></p>
				<p>상품 코드 : <%= product.getProductId() %></p>
				<p>제조사 : <%= product.getManufacturer() %></p>
				<p>분류 : <%= product.getCategory() %></p>
				<p>재고 수량 : <%= product.getUnitsInStock() %>개</p>
				<h3><%= product.getUnitPrice() %>원</h3>
				<p>
				<form name="addForm" method="post" action="./addCart.jsp?id=<%=product.getProductId()%>" >
					<a href="#" class="btn btn-info" onclick="addToCart()">상품 주문</a>
					<a href="./cart.jsp" class="btn btn-warning">장바구니</a>
					<a href="./products.jsp" class="btn btn-outline-danger"> 상품 목록</a>
				</form>
			</div>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>