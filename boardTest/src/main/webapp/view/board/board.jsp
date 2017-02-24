<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<input type="button" value="Sign Up" onclick="self.location='/boardTest/user/signUp'">
	<input type="button" value="Login" onclick="self.location='/boardTest/user/signIn'">
	
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>좋아요</th>
		</tr>
		
		<c:forEach items="${articles}" var="article">
			<tr>
				<td>${article.boardId}</td>
				<td>
					<a href="/boardTest/detail?boardId=${article.boardId}">${article.subject}</a>
				</td>
				<td>${article.writer}</td>
				<td>${article.writeDate}</td>
				<td>${article.likeCount}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/boardTest/write">글쓰기</a>
</body>
</html>