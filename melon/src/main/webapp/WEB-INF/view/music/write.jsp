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
		$("#writeForm").find("input[type=button]").click(function() {
			$("#writeForm").attr(
				{
					"method":"post",
					"action":"/melon/music/write?albumId=${param.albumId}"
				}
			);
			$("#writeForm").submit();
		});
	});
</script>
</head>
<body>

	<form id="writeForm" enctype="multipart/form-data">
		<input type="text" name="title" placeholder="title"/><br/>
		<input type="text" name="musician" placeholder="musician"/><br/>
		<input type="text" name="director" placeholder="director"/><br/>
		<input type="file" name="mp3File" placeholder="mp3 file" accept=".mp3"/><br/>
		<textarea name = "lyrics" placeholder="Lyrics"></textarea><br/>
		<input type="button" value="등록"/>
	</form>

</body>
</html>