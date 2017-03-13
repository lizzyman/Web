<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/melon/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("input[type=button]").click(function() {
			/*
				새로운 창 띄우기
			*/
			window.open("/melon/music/write?albumId=${param.albumId}", "mp3 등록", "resizable=no, scrollbars=yes, toolbar=no, width=300px, height=500px, menubar=no");
			
			
		});
		
		$(".play").click(function() {
			var albumid = $(this).data("albumid");
			var mp = $(this).data("mp");
			var source = $("<source src='' type='audio/mp3'></source>");
			source.attr("src",'/melon/mp3/' + albumid + "/" +mp);
			
			$("#mp3player").find("video").html(source);
			//$("#mp3player").find("video")[0].load();
			$("#mp3player").find("video")[0].play();
		});
	});
</script>
<body>

	<input type="button" value="MP3 등록"/>
	<p>총 ${totalCount}건의 음악이 검색되었습니다.</p>
	<table>
		<tr>
			<th>번호</th>
			<th>곡 명</th>
			<th>아티스트</th>
			<th>앨범</th>
			<th>좋아요</th>
		</tr>
	<c:forEach items="${musicList}" var="music" varStatus="index">
		<tr>
			<td>${index.index +1}</td>
			<td>${music.title}</td>
			<td>${music.albumVO.artistVO.member}</td>
			<td>${music.albumVO.albumName}</td>
			<td>${music.likeCount}</td>
			<%-- <td><a href="/melon/music/post?musicId=${music.musicId}">듣기</a></td> --%>
			<td class="play" data-albumid="${music.albumId}" data-mp="${music.mp3File}">듣기</td>
		</tr>
	</c:forEach>
	</table>
	
	<form id="searchForm">
		${pager}
	</form>
	
	<div id="mp3player">
		<video controls="controls" preload="auto" buffered></video>
	</div>
	
</body>
</html>