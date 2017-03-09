<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Artist List</title>
</head>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
		$("input[type=button]").click(function() {
			var writeDiv = $("<div id='writeDiv'></div>");
			writeDiv.css({
				position:'absolute', // absolute : 절대 좌표 (화면의 (0,0)에서 부터) / relative : 상대 좌표 (소속이 되어있는 위치부터)
				top:'50%',
				left:'50%',
				width: '200px',
				height:'100px',
				'margin-top':'-57.5px',
				'margin-left':'-92.5px',
				border:'1px solid #333333',
				padding:'15px',
				'z-index': 3, // z축으로 올린다 -> layout 개념 (1보다 크면 위로 올라감)
				'background-color' : '#FFFFFF'
			});
			writeDiv.load("/melon/artist/write"); // jsp파일을 load (client-side language)
			$(this).before(writeDiv);
		});
		
		/* $(".member").click(function() {
			$.post("/melon/artist/delete",
					{
						'artistId':$(this).data("name")
					},
					function(response){
						if (response == 'OK') {
							location.reload(); // 화면 새로 고침
						}
						else {
							alert("아티스트 삭제 실패!\n관리자에게 문의하세요.");
						}
					});
		}); */
		
	});
</script>
<body>

	<input type="button" value="아티스트 등록"/>
	
	<p>${artistCount}명의 아티스가 검색되었습니다.</p>
	<table>
		<tr>
			<th>번호</th>
			<th>아티스트 명</th>
			<th>데뷔 일자</th>
		</tr>
		<c:forEach items="${artistList}" var="artist">
			<tr>
				<td>
					<fmt:parseNumber>
						${fn:split(artist.artistId, '-')[2]}
					</fmt:parseNumber>
				</td>
				<td class="member" data-name="${artist.artistId}">
					<a href="/melon/album/list?artistId=${artist.artistId}">${artist.member}</a>
				</td>
				<td>${artist.debutDate}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<form id="searchForm">
			${pager}
		</form>
	</div>
	
</body>
</html>