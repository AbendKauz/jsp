<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkMember(){
		
		// id : 영어 또는 한글로 시작
		let regExpId = /^[a-z|A-z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;	
		// 이름 : 한글만 가능
		let regExpName = /^[가-힣]*$/;
		// 비밀번호 : 숫자만 가능
		let regExpPw = /^[0-9]*$/;
		// 전화번호 : 숫자3, 숫자4, 숫자4자리로 구성
		let regExpPhone = /^\d{3}-\d{3,4}-\d{4}$/;
		// 이메일
		let regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		let form = document.Member;
		
		let id = form.id.value;
		let pw = form.pw.value;
		let name = form.name.value;
		let phone = "010-" + form.phone2.value + "-" + form.phone3.value;
		let email = form.email.value;
		
		if(!regExpId.test(id)){
			alert("아이디는 문자로 시작해주세요.");
			form.id.select();
			return;
		}
		if(!regExpName.test(name)){
			alert("이름은 한글만으로 입력해주세요.");
			form.name.select();
			return;
		}
		if(!regExpPw.test(pw)){
			alert("비밀번호는 숫자로만 입력하세요.");
			form.pw.select();
			return;
		}
		if(!regExpPhone.test(phone)){
			alert("연락처 입력을 확인해주세요.");
			form.phone2.select();
			return;
		}
		if(!regExpEmail.test(email)){
			alert("이메일 입력을 확인해주세요.");
			form.email.select();
			return;
		}
		
		form.submit();
	}
</script>
</head>
<body>
	<h3>회원 가입</h3>
	<form action="validation05_process.jsp" name="Member" method="post">
		<p> 아이디 : <input type="text" name="id">
		<p> 비밀번호 : <input type="password" name="pw">
		<p> 이름 : <input type="text" name="name">
		<p> 연락처 : 010 - 
			<input type="text" maxlength="4" size="4" name="phone2"> - 
			<input type="text" maxlength="4" size="4" name="phone3">
		<p> 이메일 : <input type="text" name="email">
		<p> <input type="button" value="가입하기" onclick="checkMember()">
	</form>
</body>
</html>