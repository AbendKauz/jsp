<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*" %>
<%@ include file="dbconn.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	String filename = "";				// 그림파일명
//	String realFolder = "c:\\upload";	// 상대경로
	String realFolder = request.getRealPath("./resources/image");
	int maxSize = 5 * 1024 * 1024;		// 그림파일 최대크기 5mb
	String encType = "UTF-8";			// 인코딩 방식 설정
	
	MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

	String productId = multi.getParameter("productId");
	String pname = multi.getParameter("pname");
	String unitPrice = multi.getParameter("unitPrice");
	String description = multi.getParameter("description");
	String manufacturer = multi.getParameter("manufacturer");
	String category = multi.getParameter("category");
	String unitInStock = multi.getParameter("unitsInStock");
	String condition = multi.getParameter("condition");
%>

<%-- addproduct에서 보내온 아이디, 상품명, 가격 등을 한글로 변경하여 각각 개체에 저장 --%>

<%
	Integer price;
	
	if(unitPrice.isEmpty()){
		price = 0;
	}else{
		price = Integer.valueOf(unitPrice);
	}
%>

<%-- 가격이 입력되지 않았을 때 기본값을 0으로 지정함 --%>

<%
	long stock;
	
	if(unitInStock.isEmpty()){
		stock = 0;
	}else{
		stock = Long.valueOf(unitInStock);
	}
%>

<%-- 재고수량이 입력되지 않았을 때 기본값을 0으로 지정함 --%>

<%
	Enumeration files = multi.getFileNames();	// 전송 파라미터 중 파일을 받는 메소드
	String fname = (String)files.nextElement();	// 받은 열거형을 String타입으로 변환
	String fileName = multi.getFilesystemName(fname);	// 서버에 업로드된 파일을 가져오는 메소드
%>

<%
	PreparedStatement pstmt = null;

	String sql = "insert into product values(?,?,?,?,?,?,?,?,?)"; 
	pstmt = conn.prepareStatement(sql);
	
	// mysql db product 테이블 9개 필드에 각각 값을 삽입함
	pstmt.setString(1, productId);
	pstmt.setString(2, pname);
	pstmt.setInt(3, price);
	pstmt.setString(4, description);
	pstmt.setString(5, category);
	pstmt.setString(6, manufacturer);
	pstmt.setLong(7, stock);
	pstmt.setString(8, condition);
	pstmt.setString(9, fileName);
	
	pstmt.executeUpdate();
	
	if(pstmt != null){
		pstmt.close();
	}
	if(conn != null){
		conn.close();
	}
	
	response.sendRedirect("products.jsp");
%>

<%--
	ProductRepository dao = ProductRepository.getInstance();
	
	Product newProduct = new Product();
	newProduct.setProductId(productId);
	newProduct.setPname(pname);
	newProduct.setUnitPrice(price);
	newProduct.setDescription(description);
	newProduct.setManufacturer(manufacturer);
	newProduct.setCategory(category);
	newProduct.setUnitsInStock(stock);
	newProduct.setCondition(condition);
	newProduct.setFilename(fileName);
	
	dao.addProduct(newProduct);
	
	response.sendRedirect("products.jsp");
--%>
<%-- 입력한 데이터를 newProduct 객체를 생성하여 입력 --%>
