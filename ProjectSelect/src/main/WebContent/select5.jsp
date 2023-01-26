<%@page import="mvc.model.SelectDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%
	SelectDTO list = (SelectDTO)request.getAttribute("list");

	if(Integer.parseInt(list.getImg1()) < 10){
		list.setImg1("0" + list.getImg1());
	}
	if(Integer.parseInt(list.getImg2()) < 10){
		list.setImg2("0" + list.getImg2());
	}
	if(Integer.parseInt(list.getImg3()) < 10){
		list.setImg3("0" + list.getImg3());
	}
	if(Integer.parseInt(list.getImg4()) < 10){
		list.setImg4("0" + list.getImg4());
	}
	if(Integer.parseInt(list.getImg5()) < 10){
		list.setImg5("0" + list.getImg5());
	}
	if(Integer.parseInt(list.getImg6()) < 10){
		list.setImg6("0" + list.getImg6());
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>5</title>
<script type="text/javascript">
	
	function clickTest(num) { 
		console.log(num.value);
		frm.no5.value = num.value;
		frm.submit();
	}
	
</script>
</head>
<body>
	<form name="frm" action="/category.do" method="post">
	<input type="hidden" id="no1" name="no1" value="<%= request.getParameter("no1") %>" readonly="readonly">
	<input type="hidden" id="no2" name="no2" value="<%= request.getParameter("no2") %>" readonly="readonly">
	<input type="hidden" id="no3" name="no3" value="<%= request.getParameter("no3") %>" readonly="readonly">
	<input type="hidden" id="no4" name="no4" value="<%= request.getParameter("no4") %>" readonly="readonly">
	<input type="hidden" id="no5" name="no5" value="">
		<table>	
			<tr>
				<td><button type="button" class="btn" onclick="clickTest(this)" value="<%= list.getImg1()%>"><img src="../resources/images/<%= list.getImg1() %>.jpg"></button></td>
				<td><button type="button" class="btn" onclick="clickTest(this)" value="<%= list.getImg2()%>"><img src="../resources/images/<%= list.getImg2() %>.jpg"></button></td>
				<td><button type="button" class="btn" onclick="clickTest(this)" value="<%= list.getImg3()%>"><img src="../resources/images/<%= list.getImg3() %>.jpg"></button></td>
			</tr>
			<tr>
				<td><button type="button" class="btn" onclick="clickTest(this)" value="<%= list.getImg4()%>"><img src="../resources/images/<%= list.getImg4() %>.jpg"></button></td>
				<td><button type="button" class="btn" onclick="clickTest(this)" value="<%= list.getImg5()%>"><img src="../resources/images/<%= list.getImg5() %>.jpg"></button></td>
				<td><button type="button" class="btn" onclick="clickTest(this)" value="<%= list.getImg6()%>"><img src="../resources/images/<%= list.getImg6() %>.jpg"></button></td>
			</tr>
		</table>
	</form>
</body>
</html>