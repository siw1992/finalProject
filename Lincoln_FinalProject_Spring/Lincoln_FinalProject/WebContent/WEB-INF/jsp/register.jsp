<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
//���̵� üũ���� Ȯ�� (���̵� �ߺ��� ��� = 0 , �ߺ��� �ƴҰ�� = 1 )
var idck = 0;
$(function() {
    //idck ��ư�� Ŭ������ �� 
    $("#idck").click(function() {
        
        //userid �� param.
        var fireFighterCode =  $("#fireFighterCode").val(); 
        
        $.ajax({
            type : 'POST',
            data : {"fireFighterCode":fireFighterCode},
            url : "checkid",
            success : function(data) {
            	console.log(data);
                if (data > 0) {
                    
                    alert("���̵� �����մϴ�. �ٸ� ���̵� �Է����ּ���.");
                    //���̵� ������ ��� �������� , �ƴϸ� �Ķ����� ó���ϴ� ������
                    $("#divInputId").addClass("has-error")
                    $("#divInputId").removeClass("has-success")
                    $("#fireFighterCode").focus();
                    
                
                } else {
                    alert("��밡���� ���̵��Դϴ�.");
                    //���̵� ������ ��� �������� , �ƴϸ� �Ķ����� ó���ϴ� ������
                    $("#divInputId").addClass("has-success")
                    $("#divInputId").removeClass("has-error")
                    $("#fireFighterCode").focus();
                    //���̵� �ߺ����� ������  idck = 1 
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
<h3 align="center">ȸ������ ȭ�� </h3>

<form action="insert_action" method=post>

	<table border=1 style="align-content: center; align-items: center;">
		<tr>
			<td>FIREFIGHTERCODE</td>
			<td><input type=text id="fireFighterCode" name=fireFighterCode></td>
			<td><input
            type="button" value="�ߺ�Ȯ��" id="idck">
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
				<input type=submit name=submit value="Ȯ��">
			</td>
		</tr>
	</table>
</form>


</body>
</html>