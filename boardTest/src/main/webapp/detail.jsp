<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h1>${board.subject}</h1>
	<hr/>
	<span>${board.writer}</span> <!-- 단어를 강조하는 Tag --> /
	<span>${board.writeDate}</span> /
	<span>${board.likeCount}</span>
	<br/>
	<p>
		${board.contents}
	</p>
	<hr/>
	<a href="/boardTest/modify?boardId=${board.boardId}">수정</a> | <a href="/boardTest/delete?boardId=${board.boardId}">삭제</a> | <a href="/boardTest/board">목록으로 돌아가기</a> | <a href="/boardTest/write">글쓰기</a>
	
</body>
</html>