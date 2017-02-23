<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Departments Board</title>
</head>
<body>
	<h1>Departments Board</h1>
	<hr/>
	<table>
		<tr>
			<th>Department ID</th>
			<th>Department Name</th>
		</tr>
		
		<c:forEach items="${departments}" var="department">
			<tr>
				<td>${department.departmentId}</td>
				<td>
					<a href="/Homework/detail?departmentId=${department.departmentId}">${department.departmentName}</a>
				</td>
			</tr>
		</c:forEach>
	</table>

	<a href="/Homework/add">New</a>
</body>
</html>