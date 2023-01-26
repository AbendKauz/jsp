<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<%
	String num = request.getParameter("num");
%>
<sql:setDataSource var="dataSource"
	url="jdbc:mysql://localhost:3306/WebMarketDB"
	driver="com.mysql.jdbc.Driver" user="manager" password="1234" />
<sql:query dataSource="${dataSource}" var="resultSet">
   select * from board where num=?
   <sql:param value="<%=num%>" />
</sql:query>
<title>게시물 출력</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">게시글 출력</h1>
		</div>
	</div>
	<c:forEach var="row" items="${resultSet.rows}">
	<div class="container">
		<form name="newMember" class="form-horizontal" action="#" method="post">
			<div class="form-group row">
				<label class="col-sm-2 ">게시물 번호</label>
				<div class="col-sm-3">
					<input name="num" type="text" class="form-control"
						value="<c:out value='${row.num}'/>" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 ">이름</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control"
						value="<c:out value='${row.name}'/>" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 ">제목</label>
				<div class="col-sm-3">
					<input name="subject" type="text" class="form-control"
						value="<c:out value='${row.subject}'/>" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 ">내용</label>
				<div class="col-sm-3">
					<input name="content" type="text" class="form-control" value="<c:out value='${row.content}'/>" />
				</div>
			</div>
		</form>	
	</div>
	</c:forEach>
</body>
</html>
