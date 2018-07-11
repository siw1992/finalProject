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
		//실시간으로 넘어오는값을 넣어준다
		//소방관1
		var IP="192.168.0.132"
		setInterval(function() {
			$.ajax({
				url : '/spring0615_mvc/getValues',
				type : 'GET',
				data : {"name":IP},
				success : function(data) {
					//성공시에만 값 append
					if(data!="error")
					{
						var sensor = data.split("/");
						$("#g").val(sensor[0]+ "\n" + $("#g").val());
						$("#p").val(sensor[1]+ "\n" + $("#p").val());
						$("#s").val(sensor[2]+ "\n" + $("#s").val());
						$("#t").val(sensor[3]+ "\n" + $("#t").val());
					}
				},
				error : function(data) {
					//alert("에러..." + data);
				}
			});
		}, 5000);
		
		//소방관2
		var IP2="192.168.0.132"
		setInterval(function() {
			$.ajax({
				url : '/spring0615_mvc/getValues',
				type : 'GET',
				data : {"name":IP2},
				success : function(data) {
					//성공시에만 값 append
					if(data!="error")
					{
						var sensor = data.split("/");
						$("#g2").val(sensor[0]+ "\n" + $("#g2").val());
						$("#p2").val(sensor[1]+ "\n" + $("#p2").val());
						$("#s2").val(sensor[2]+ "\n" + $("#s2").val());
						$("#t2").val(sensor[3]+ "\n" + $("#t2").val());
					}
				},
				error : function(data) {
					//alert("에러..." + data);
				}
			});
		}, 5000);
		
	});
</script>
</head>
<body>
	<h1>CCTV</h1>
	<iframe width=490 height=340 src="http://192.168.0.132:5000/rec"></iframe>
	<iframe width=490 height=340 src="http://192.168.0.132:5000/rec"></iframe>
	<br>
	<pre>
	소방관1					소방관2
	</pre>
	<pre>
	gyro1(자이로) :				gyro2(자이로) :
	</pre>
	<textarea rows="5" cols="20" id="g" readonly="readonly"></textarea>
	&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
	<textarea rows="5" cols="20" id="g2" readonly="readonly"></textarea>
	<pre>	
	pulse1(심박) :				pulse2(심박) :
	</pre>
	<textarea rows="5" cols="20" id="p" readonly="readonly"></textarea>
	&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
	<textarea rows="5" cols="20" id="p2" readonly="readonly"></textarea><br>
	<pre>
	smoke1(연기)				smoke2(연기) :
	</pre>
	<textarea rows="5" cols="20" id="s" readonly="readonly"></textarea>
	&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
	<textarea rows="5" cols="20" id="s2" readonly="readonly"></textarea><br>
	<pre>
	temp1(온도) :				temp2(온도) :
	</pre>
	<textarea rows="5" cols="20" id="t" readonly="readonly"></textarea>
	&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;
	<textarea rows="5" cols="20" id="t2" readonly="readonly"></textarea><br>
	<br>
</body>
</html>