<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form_processing</title>
<link rel="stylesheet" href="./resources/css/form01.css">
</head>
<body>
	<div id="container">
		<h1>회원가입</h1>
		<form action="form01_ok.jsp" method="post">
			<p> 
				<label class="a">아이디 :</label>
				<input type="text" name="id" id="id">
				<input type="button" name="chkId" value="아이디 중복검사">
			</p>
			<p> 
				<label class="a">비밀번호 :</label> 
				<input type="password" name="pw" id="pw">
			</p>
			<p> 
				<label class="a">이름 : </label>
				<input type="text" name="name" id="name">
			</p>
			<p>
				<label class="a">연락처 : </label>
				<select name="tel1">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="017">017</option>
					<option value="019">019</option>
				</select> - 
				<input type="text" name="tel2" size="5" maxlength="4"> - 
				<input type="text" name="tel3" size="5" maxlength="4">
			</p>
			<p> 
				<label class="a">성별 : </label>
				<input type="radio" name="gender" value="남성" checked="checked"> 남성
				<input type="radio" name="gender" value="여성"> 여성 
			</p>
			<p> 
				<label class="a">취미 : </label>
				독서 <input type="checkbox" name="hobby" value="독서" checked="checked">
				운동 <input type="checkbox" name="hobby" value="운동">
				영화 <input type="checkbox" name="hobby" value="영화">
			</p>
			<p>
				<textarea rows="5" cols="30" placeholder="가입인사를 입력해주세요." name="textarea" id="textarea"></textarea>
			</p>
		<input type="submit" value="가입하기">
		<input type="reset" value="다시쓰기">
		</form>
	</div>
</body>
</html>