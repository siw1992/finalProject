<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
var idck = 0;
$(function() {
    //idck 버튼을 클릭했을 때 
    $("#idck").click(function() {
        
        //userid 를 param.
        var fireFighterCode =  $("#fireFighterCode").val(); 
        
        $.ajax({
            type : 'POST',
            data : {"fireFighterCode":fireFighterCode},
            url : "checkid",
            success : function(data) {
            	console.log(data);
                if (data > 0) {
                    
                    alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
                    $("#divInputId").addClass("has-error")
                    $("#divInputId").removeClass("has-success")
                    $("#fireFighterCode").focus();
                    
                
                } else {
                    alert("사용가능한 아이디입니다.");
                    //아이디가 존재할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
                    $("#divInputId").addClass("has-success")
                    $("#divInputId").removeClass("has-error")
                    $("#fireFighterCode").focus();
                    //아이디가 중복하지 않으면  idck = 1 
                    idck = 1;
                    
                }
            },
            error : function(error) {
                
                alert("error : " + error);
            }
        });
    });
});
 
</script>


</head>
<body>
<h3 align="center">회원가입 화면 </h3>

<form action="insert_action" method=post>

	<table border=1 style="align-content: center; align-items: center;">
		<tr>
			<td>FIREFIGHTERCODE</td>
			<td><input type=text id="fireFighterCode" name=fireFighterCode></td>
			<td><input
            type="button" value="중복확인" id="idck">
            <div id="txt"></div></td>
            
		</tr>
		<tr>
			<td>FIRESTATIONCODE</td>
			<td><input type=text name=fireStationCode></td>
		</tr>
		<tr>
			<td>IP</td>
			<td><input type=text name=IP></td>
		</tr>
		<tr>
			<td>FIREFIGHTERNAME</td>
			<td><input type=text name=fireFighterName></td>
		</tr>
		<tr>
			<td colspan=2>
				<input type=submit name=submit value="확인">
			</td>
		</tr>
	</table>
</form>


</body>
</html>