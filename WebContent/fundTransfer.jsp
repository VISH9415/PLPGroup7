<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action = "validatebothid.htm" method = "post">
<table>
<tr>
<td>Enter Your Account Id</td>
<td><input type = "text" name = "accId" value="${accountId}"></td>

</tr>

<tr>
<td>Enter Payee's Account Id</td>
<td><input type = "text" name = "payeeId"></td>
</tr>

<tr>
<td>Enter Amount</td>
<td><input type = "text" name = "amount"></td>
</tr>

<tr>
<td><input type = "submit" value = "Submit"></td>
</tr>

</table>



</form>

</body>
</html>