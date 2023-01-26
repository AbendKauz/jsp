<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkForm(){
		/* 
			정규표현식 -> var 변수이름 = /정규표현식/flag값
			
			flag값	i -> 대소문자 구별없이 가져옴
					g -> 문자열 내의 모든 패턴 검출
					m -> 줄 바꿈 행 검출
		*/
		let regExp = /Java/i;	// java라는 글자를 대소문자 구별없이 가져옴
		let str = document.frm.title.value;
		let result = regExp.exec(str);	// exec() : 정규표현식에 부합된 문자열을 추출하여 반환
		alert(result[0]);	// 추출한 문자열 중 첫번째 데이터를 경고창으로 출력
		
		/* 입력란에 Java Server를 입력하면 Java가 출력됨 */
		
	}
	
	/* 이름 입력 시 숫자로 시작하지 않는 정규표현식 */
		
	function checkName(){
		
		let name = document.frm.name.value;	// 입력한 이름을 가져옴
		let regExp = /^[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		
		if(!regExp.test(name)){	// test() : 정규식이 맞는지 검사하는 메소드
			alert("이름은 숫자로 시작할 수 없습니다.");
			return;
		}
		
	}
		
	/* 
		영어 + 숫자	영어로만 시작
		let exp1 = /^[a-z|A-Z]/
	*/
	
</script>
</head>
<body>
	<form action="#" name="frm" method="post">
<!-- 		<p> 제목 : <input type="text" name="title">
		<input type="submit" value="전송" onclick="checkForm()"> -->
		<p> 이름 : <input type="text" name="name">
		<input type="submit" value="전송" onclick="checkName()">
	</form>
</body>
</html>