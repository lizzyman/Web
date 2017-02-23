<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Department</title>
</head>
<body>
	
	<h1>Add Department</h1>
	<hr/>
	
	<form action="/Homework/doAdd" method="post">
		<input type="text" name="departmentId" placeholder="Write Department Id">
		<input type="text" name="departmentName" placeholder="Write Department Name">
		<input type="text" name="managerId" placeholder="Write Manager Id">
		<input type="text" name="locationId" placeholder="Write Loaction Id">
		<input type="submit" value="submit">
	</form>
	
</body>
</html>