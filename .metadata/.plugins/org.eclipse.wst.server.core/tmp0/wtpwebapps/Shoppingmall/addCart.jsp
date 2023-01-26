<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Product" %>
<%@ page import="dao.ProductRepository" %>

<%
	String id = request.getParameter("id");
	if(id == null || id.trim().equals("")){
		response.sendRedirect("products.jsp");
		return;
	}
	
	ProductRepository dao = ProductRepository.getInstance();
	
	Product product = dao.getProductById(id);
	if(product == null){
		response.sendRedirect("exceptionNoProductId.jsp");
	}
	
	ArrayList<Product> goodsList = dao.getAllProducts();
	// goodsList에 상품 전체 정보를 가져와 저장함
	Product goods = new Product();
	
	for(int i = 0; i < goodsList.size(); i++){	// 요청한 상품이 존재하는지 검사
		goods = goodsList.get(i);
		if(goods.getProductId().equals(id)){
			break;
		}
	}
	
	ArrayList<Product> list = (ArrayList<Product>)session.getAttribute("cartlist");
	if(list == null){	// 세션정보가 없으면 ArrayList 객체 생성하고 세션 속성값 설정
		list = new ArrayList<Product>();
		session.setAttribute("cartlist", list);
	}
	
	int cnt = 0;
	Product goodsQnt = new Product();
	for(int i = 0; i < list.size(); i++){
		goodsQnt = list.get(i);
		if(goodsQnt.getProductId().equals(id)){
			// 이미 장바구니에 해당 상품이 있으면 원래 목록에 개수 1개 추가
			cnt++;
			int orderQuantity = goodsQnt.getQuantity() + 1;
			goodsQnt.setQuantity(orderQuantity);
		}
	}
	
	if(cnt == 0){	// 장바구니에 해당 상품이 없으면
		goods.setQuantity(1);
		list.add(goods);
	}
	
	response.sendRedirect("product.jsp?id=" + id);
%>
