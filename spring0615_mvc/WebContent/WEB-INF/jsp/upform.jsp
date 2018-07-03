<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
#wrap {
	width: 300px;
	margin: auto;
}
</style>
</head>
<body>
	<div id="wrap">
		<form method="post" action="upsave" enctype="multipart/form-data">
			<input type="file" id="mfile" name="mfile">
			<input type="submit" value="send">
		</form>
	</div>
</body>
</html>