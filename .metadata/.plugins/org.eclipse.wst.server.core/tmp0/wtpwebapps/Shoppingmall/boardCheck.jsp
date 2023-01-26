<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>게시물 체크</title>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">게시물 체크</h1>
		</div>
	</div>
	<div class="container" align="center">
		<div class="col-md-4 col-md-offset-4">
			
			<form class="form-signin" action="boardresult.jsp" method="post">

				<div class="form-group">
					<label for="inputUserName" class="sr-only">게시물 번호</label> <input
						type="text" class="form-control" placeholder="검색 게시물 번호 입력" name='num'>
				</div>
				<button class="btn btn btn-lg btn-success btn-block" type="submit">전송</button>
			</form>
		</div>
	</div>
</body>
</html>
