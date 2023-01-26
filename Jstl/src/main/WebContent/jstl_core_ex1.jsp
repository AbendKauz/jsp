<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="test" value="hello jstl"/>
	<h3>set태그 사용 후 : <c:out value="${test}"/></h3>
	
	<c:remove var="test"/>
	<h3>remove태그 사용 후 : <c:out value="${test}"/></h3>
	
	<c:catch var="err"><%=10/0 %></c:catch>
	<h3>catch로 잡아낸 오류 : <c:out value="${err}"/></h3>
	
	<c:if test="${5 lt 10 }"><h3>5는 10보다 작다</h3></c:if>
	
	<c:choose>
		<c:when test="${5 + 10 == 50 }"><h3>5 더하기 10은 50이다</h3></c:when>
		<c:otherwise><h3>5 더하기 10은 50이 아니다</h3></c:otherwise>
	</c:choose>
</body>
</html>
