<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Detail Info</title>
</head>
<body>

	<h1>Department's detail info</h1>
	<hr/>
	
	<table>
		<tr>
			<th>Department ID</th>
			<th>Department Name</th>
			<th>Manager ID</th>
			<th>Location ID</th>
		</tr>
		<tr>
			<td>${department.departmentId}</td>
			<td>${department.departmentName}</td>
			<td>${department.managerId}</td>
			<td>${department.locationId}</td>
		</tr>
	</table>
	
	<br/>
	<a href="/Homework/departmentsBoard">List</a> | <a href="/Homework/modify?departmentId=${department.departmentId}">Modify</a> |
	<a href="/Homework/doDelete?departmentId=${department.departmentId}">Delete</a> | <a href="/Homework/add">New</a>
	
</body>
</html>