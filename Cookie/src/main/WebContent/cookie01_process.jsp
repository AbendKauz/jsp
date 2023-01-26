<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("admin") && pw.equals("1234")){
			Cookie cookie_id = new Cookie("id", id);
			Cookie cookie_pw = new Cookie("pw", pw);
			cookie_id.setMaxAge(0);	// cookie_id 삭제(유효기간 0으로 설정)
			cookie_pw.setMaxAge(0);	// cookie_pw 삭제
			response.addCookie(cookie_id);
			response.addCookie(cookie_pw);
			out.println("쿠키 생성 완료");
		} else {
			out.println("쿠키 생성 실패");
		}
		
	%>
</body>
</html>