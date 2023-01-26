<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<%-- <%@ include file="menu.jsp" %> --%>
	<jsp:include page="menu.jsp"/>
	<%!
		String greeting = "쇼핑몰";
		String tagline = "웹 쇼핑몰에 오신걸 환영합니다.";
	%>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-4">
				<%=greeting %>
			</h1>
		</div>
	</div>
	<main role="main">
		<div class="container">
			<div class="text-center">
				<h3>
					<%=tagline %>
				</h3>
				<%
					response.setIntHeader("Refresh", 1);
					/* 5초마다 자동갱신 */
					Date day = new java.util.Date();
					String am_pm;
					int hour = day.getHours();
					int minute = day.getMinutes();
					int second = day.getSeconds();
					if(hour / 12 == 0){
						am_pm = "오전";
					}else{
						am_pm = "오후";
						hour = hour - 12;
					}
					out.println("지금은 " + am_pm + " " + hour + "시 " + minute + "분 " + second + "초 입니다.");
				%>
			</div>
			<hr>
		</div>
	</main>
	<%-- <%@ include file="footer.jsp" %> --%>
	<jsp:include page="footer.jsp"/>
</body>
</html>
