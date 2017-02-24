<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/boardTest/user/doSignUp">
	
	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" name="userId" placeholder="ID를 입력하세요."/></td>
		</tr>
		<tr>
			<th>Password</th>
			<td><input type="password" name="userPassword" placeholder="비밀번호를 입력하세요."/></td>
		</tr>
		<tr>
			<th>Name</th>	
			<td><input type="text" name="userName" placeholder="이름을 입력하세요."/></td>
		</tr>
	</table>
	
		<input type="button" onClick="self.location='/boardTest/board'"value="목록">
		<input type="submit" value="가입하기"/>
	
	</form>
	
</body>
</html>