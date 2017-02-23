<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Article</title>
</head>
<body>
	<h1>Modify Article</h1>
	<hr/>
	
	<form method="post" action="/Homework/doModify">
		<input type="hidden" name="departmentId" value="${department.departmentId}"/>
		<table>
			<tr>
				<th>Dept Name</th>
				<td><input type="text" name="departmentName" value="${department.departmentName}"/></td>
			</tr>
			<tr>
				<th>Manager ID</th>
				<td><input type="text" name="managerId" value="${department.managerId}"/></td>
			</tr>
			<tr>
				<th>Location ID</th>
				<td><input type="text" name="locationId" value="${department.locationId}"/></td>
			</tr>
		</table>
		
		<input type="submit" value="Submit"/>
	</form>	
	
</body>
</html>