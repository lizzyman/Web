<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 
	1. c:if
	2. c:foreach
	3. c:choose ~ c:when ~c:otherwise
	4. c:set
	5. c:out
	6. c:import
 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>My Page</title>
</head>
<body>

	${introduce}
	
	<h1>My name</h1>
	<h2>${introduce.name}</h2>
	<!-- introduceVO.name : introduce의 getName()함수를 호출 -->
	${introduceList[0].name}<br/>
	
	<h1>Age</h1>
	<h2>${introduce.age}</h2>
	${introduceList[0].age}<br/>
	<h1>Alias</h1>
	<h2>${introduce.alias}</h2>
	${introduceList[0].alias}<br/>
	
	<c:forEach items="${introduceList}" var="intro">
		${intro.name} <br/>
		${intro.age} <br/>
		${intro.alias} <br/>
	</c:forEach>


</body>
</html>