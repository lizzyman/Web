<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h1>글쓰기</h1>
	<hr>
	<form method="post" action="/boardTest/doWrite">
		<!-- <input type="text" name="writer" placeholder="이름을 입력하세요."><br/> -->
		<input type="text" name="subject" placeholder="제목을 입력하세요."><br/>
		<textarea name="contents" placeholder="내용을 입력하세요."></textarea><br/>
		<input type="submit" value="글쓰기">
	</form>

</body>
</html>