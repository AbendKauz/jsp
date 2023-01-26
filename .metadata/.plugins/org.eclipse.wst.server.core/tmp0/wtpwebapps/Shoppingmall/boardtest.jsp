<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.min.css">
<title>게시물 test</title>
</head>
<body>
	<jsp:include page="/menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">게시물 작성</h1>
		</div>
	</div>
	<div class="container">
		<form name="newMember" action="processBoard.jsp" class="form-horizontal" method="post" onsubmit="return checkForm()">
			<div class="form-group row">
				<label class="col-sm-2">아이디</label>
				<div class="col-sm-5">
					<input type="text" name="id" class="form-control" placeholder="id"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">성명</label>
				<div class="col-sm-5">
					<input type="text" name="name" class="form-control" placeholder="이름"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">제목</label>
				<div class="col-sm-5">
					<input type="text" name="subject" class="form-control" placeholder="제목"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">내용</label>
				<div class="col-sm-6">
					<textarea rows="5" name="content" class="form-control" placeholder="내용" style="resize: none;"></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">조회수</label>
				<div class="col-sm-5">
					<input type="text" name="hit" class="form-control" placeholder="조회수"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">ip주소</label>
				<div class="col-sm-5">
					<input type="text" name="ip" class="form-control" placeholder="ip주소"/>
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록">
					<input type="reset" class="btn btn-primary" value="취소" onclick="return reset()">
				</div>
			</div>			
		</form>
	</div>
	<hr>
	<jsp:include page="/footer.jsp"/>
</body>
</html>
