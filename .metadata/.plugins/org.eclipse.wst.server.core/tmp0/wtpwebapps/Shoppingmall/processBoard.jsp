<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String subject = request.getParameter("subject");
	String content = request.getParameter("content");
	String hit = request.getParameter("hit");
	String ip = request.getParameter("ip");
	
	Calendar date = Calendar.getInstance();
	SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
	String timestamp = today.format(date.getTime());
	
%>
<!-- DB연결 -->
<sql:setDataSource var="dataSource" url="jdbc:mysql://localhost:3306/WebMarketDB"
	driver="com.mysql.jdbc.Driver" user="manager" password="1234"/>

<sql:update dataSource="${dataSource}" var="resultSet">
	insert into board(id, name, subject, content, regist_day, hit, ip) values (?,?,?,?,?,?,?)
	<sql:param value="<%=id %>"/>
	<sql:param value="<%=name %>"/>
	<sql:param value="<%=subject %>"/>
	<sql:param value="<%=content %>"/>
	<sql:param value="<%=timestamp %>"/>
	<sql:param value="<%=hit %>"/>
	<sql:param value="<%=ip %>"/>
</sql:update>

<c:if test="${resultSet >= 1 }">
	<c:redirect url="boardResult.jsp"/>
</c:if>
