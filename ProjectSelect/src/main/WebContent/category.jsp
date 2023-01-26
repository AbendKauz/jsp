<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 선택</title>
<script type="text/javascript">
	
	function btn(num) { 
		console.log(num.value);
		frm.resultCate.value = num.value;
		frm.submit();
	}
	
</script>
</head>
<body>
	<form name="frm" action="/result.do" method="post">
		<input type="hidden" id="no1" name="no1" value="<%= request.getParameter("no1") %>" readonly="readonly">
		<input type="hidden" id="no2" name="no2" value="<%= request.getParameter("no2") %>" readonly="readonly">
		<input type="hidden" id="no3" name="no3" value="<%= request.getParameter("no3") %>" readonly="readonly">
		<input type="hidden" id="no4" name="no4" value="<%= request.getParameter("no4") %>" readonly="readonly">
		<input type="hidden" id="no5" name="no5" value="<%= request.getParameter("no5") %>" readonly="readonly">
		<input type="hidden" name="resultCate" value="">
		<img src="../resources/images/<%= request.getParameter("no1") %>.jpg" width="150px;" height="100px;">
		<img src="../resources/images/<%= request.getParameter("no2") %>.jpg" width="150px;" height="100px;">
		<img src="../resources/images/<%= request.getParameter("no3") %>.jpg" width="150px;" height="100px;">
		<img src="../resources/images/<%= request.getParameter("no4") %>.jpg" width="150px;" height="100px;">
		<img src="../resources/images/<%= request.getParameter("no5") %>.jpg" width="150px;" height="100px;">
		<p>카테고리를 고르시오.</p>
		<button type="button" value="0" onclick="btn(this)">카테고리0</button>
		<button type="button" value="1" onclick="btn(this)">카테고리1</button>
		<button type="button" value="2" onclick="btn(this)">카테고리2</button>
		<button type="button" value="3" onclick="btn(this)">카테고리3</button>
		<button type="button" value="4" onclick="btn(this)">카테고리4</button>
		<button type="button" value="5" onclick="btn(this)">카테고리5</button><br/>
		<button type="button" value="6" onclick="btn(this)">카테고리6</button>
		<button type="button" value="7" onclick="btn(this)">카테고리7</button>
		<button type="button" value="8" onclick="btn(this)">카테고리8</button>
		<button type="button" value="9" onclick="btn(this)">카테고리9</button>
	</form>
</body>
</html>