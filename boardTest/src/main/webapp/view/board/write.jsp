<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/boardTest/css/list_layout.css" />
<link rel="stylesheet" type="text/css" href="/boardTest/css/write_layout.css" />
<jsp:include page="/template/common_header.jsp" />
	<div class="write">
		<h1>글쓰기</h1>
		<hr>
		<div class="form">
			<form method="post" action="/boardTest/doWrite">
				<!-- <input type="text" name="writer" placeholder="이름을 입력하세요."><br/> -->
				<div class="title">
					<input type="text" name="subject" placeholder="제목을 입력하세요."><br />
				</div>
				<div class="textarea">
					<textarea name="contents" placeholder="내용을 입력하세요."></textarea><br />
				</div>
				<input type="submit" value="글쓰기">
			</form>
		</div>
	</div><!-- 
	 --><div class="login">
		<c:if test="${empty sessionScope._USER_}">
			<!-- 로그인 안 됐을 경우(null=empty) : Session정보를 EL에서 가져옴 (jsp 내장 객체) -->
			<jsp:include page="/view/users/signIn.jsp" />
			<!-- signIn.jsp를 그대로 보여주기 -->
		</c:if>
		<!-- 로그인을 했을 경우 -->
		<c:if test="${not empty sessionScope._USER_}">
							${sessionScope._USER_.userName}님, 환영쓰~
		</c:if>
	</div>
<jsp:include page="/template/common_footer.jsp" />