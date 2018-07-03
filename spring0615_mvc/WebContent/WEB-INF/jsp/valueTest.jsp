<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).on("change", ".file_multi_video", function(evt) {
		var $source = $("#vid");
		$source[0].src = URL.createObjectURL(this.files[0]);
		$source.parent()[0].load();
		alert("경로 : " + $source[0].src);
	});
</script>
</head>
<body>
	<!-- 보안상의 문제로, 폴더의 mp4는 스크립트로 자동 접근 불가능!ㅠㅠ -->
	<!-- 브라우저에서 플레이하려면, h264 -> mp4로 바꿔야함 -->
	*0702 : 로컬폴더의 mp4는 html태그 또는 스크립트로 자동 접근이 불가능했다.(웹 보안상의 문제라고 함) -> 파일첨부기능을 추가하여, 플레이 할 블랙박스 영상을 직접 선택하도록 변경<br>
	*0703 : h264로 저장된 원본->mp4로 변환후 플레이 구현(!!! 버퍼링 문제  !!!)<br>
	<hr>
	파일리스트(경로 : C:\kosta182\spring\workspace\spring0615_mvc\BlackBox\mp4)<br>
	${msg}
	<hr>
	블랙박스 영상<br>
	<input type="file" name="file[]" class="file_multi_video" accept="video/*">
	<br>
	<!-- revoke해줘야함 -->
	<video width="720" height="480" controls="controls" preload = "auto">
		<source id="vid" src="">
	</video>
</html>