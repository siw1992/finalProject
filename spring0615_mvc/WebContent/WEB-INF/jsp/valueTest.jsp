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
		alert("��� : " + $source[0].src);
	});
</script>
</head>
<body>
	<!-- ���Ȼ��� ������, ������ mp4�� ��ũ��Ʈ�� �ڵ� ���� �Ұ���!�Ф� -->
	<!-- ���������� �÷����Ϸ���, h264 -> mp4�� �ٲ���� -->
	*0702 : ���������� mp4�� html�±� �Ǵ� ��ũ��Ʈ�� �ڵ� ������ �Ұ����ߴ�.(�� ���Ȼ��� ������� ��) -> ����÷�α���� �߰��Ͽ�, �÷��� �� ���ڽ� ������ ���� �����ϵ��� ����<br>
	*0703 : h264�� ����� ����->mp4�� ��ȯ�� �÷��� ����(!!! ���۸� ����  !!!)<br>
	<hr>
	���ϸ���Ʈ(��� : C:\kosta182\spring\workspace\spring0615_mvc\BlackBox\mp4)<br>
	${msg}
	<hr>
	���ڽ� ����<br>
	<input type="file" name="file[]" class="file_multi_video" accept="video/*">
	<br>
	<!-- revoke������� -->
	<video width="720" height="480" controls="controls" preload = "auto">
		<source id="vid" src="">
	</video>
</html>