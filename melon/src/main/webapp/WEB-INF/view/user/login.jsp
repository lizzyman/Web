<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".signUp").find("input[type=button]").click(function() {
			window.open("/melon/user/signUp", "Sign Up", "resizable=no, scrollbars=yes, toolbar=no, width=441px, height=421px, menubar=no");
		});
		
		$("#loginForm").find("input[type=button]").click(function() {
			$("#loginForm").attr(
				{
					"method":"post",
					"action":"/melon/user/login"
				}
			);
			$("#loginForm").submit();
		});
	});
</script>
</head>
<body>
	<div class="signUp">
		<input type="button" value="Sign Up"/>
	</div>
	<form id="loginForm">
		<input type="text" id="userId" name="userId" placeholder="ID"/><br/>
		<input type="text" id="userPassword" name="userPassword" placeholder="Password"/><br/>
		<input type="button" value="Login"/>
	</form>

</body>
</html>