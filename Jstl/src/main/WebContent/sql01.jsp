<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sql:setDataSource var="conn"
		driver="com.mysql.jdbc.Driver" 
		url="jdbc:mysql://localhost:3306/test01"
		user="manager"
		password="1234"/>
	<%-- <sql:update dataSource="${conn}">
		insert into test(num, name) values(1, '홍길동')
	</sql:update> --%>
	
	<%-- <sql:update dataSource="${conn}">
		insert into test(num, name) values(2, '장동건')
	</sql:update>
	<sql:update dataSource="${conn}">
		insert into test(num, name) values(3, '원빈')
	</sql:update>
	<sql:update dataSource="${conn}">
		insert into test(num, name) values(4, '현빈')
	</sql:update>
	<sql:update dataSource="${conn}">
		insert into test(num, name) values(5, '김연아')
	</sql:update> --%>
	
	<sql:query var="rs" dataSource="${conn}">
		select * from test where name=?
		<sql:param>홍길동</sql:param>
	</sql:query>
	
	<c:forEach var="data" items="${rs.rows}">
		<c:out value="${data['num']}"/>
		<c:out value="${data['name']}"/>
	</c:forEach>
</body>
</html>
