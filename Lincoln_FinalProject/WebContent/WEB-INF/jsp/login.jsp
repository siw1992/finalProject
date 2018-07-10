<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>login.jsp</title>
</head>
<body>
    <div>
        <form method="post" action="loginProcess">
            <input type="hidden" name="num" value="100">
            ID : <input type="text" name="monitorId"><br>
            passwd : <input type="password" name="monitorPwd"><br>
            <input type="submit" value="send">
        </form>
    </div>
</body>
</html>