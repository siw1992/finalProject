<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#switch").click(function() {
			var v = $("#fName").val();
			if (v == "A") {
				alert("A -> B");
				//예진주소
				$("#vid").attr("src", "http://192.168.0.122:5000");
				$("#fName").val("B");
				$("#t").text("CCTV B");
			} else {
				alert("B -> A");
				//시원주소
				$("#vid").attr("src", "http://192.168.0.132:5000/");
				$("#fName").val("A");
				$("#t").text("CCTV A");
			}
		});
	});
</script>
</head>
<body>
	<h1 id=t>CCTV A</h1>
	<input type="hidden" id=fName value="A">
	<br>
	<input type="submit" value="소방관A <-> 소방관B" id="switch">
	<br>
	<br>
	<iframe id=vid width=800 height=600 src="http://192.168.0.132:5000"></iframe>
</body>
</html>