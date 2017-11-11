<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="changeAddress.htm" method = "post">
<B>Details of Account Holder</B>
<table>
<tr>
<td>Customer Name</td>
<td><input type = "text" name = "custName" value = "${customer.customerName}" readonly></td>
</tr>
<tr>
<td>Account Id</td>
<td><input type = "text" name = "accountId" value = "${customer.accountId}" readonly></td>
</tr>
<tr>
<td>Email</td>
<td><input type = "text" name = "email" value = "${customer.email}" readonly></td>
</tr>
<tr>
<td>Address</td>
<td><input type = "text" name = "address" value = "${customer.address}"></td>
</tr>
<tr>
<td>Pancard</td>
<td><input type = "text" name = "panCard" value = "${customer.pancard}" readonly></td>
</tr>
<tr>
<td>Account Type</td>
<td><input type = "text" name = "accType" value = "${customer.accountType}" readonly></td>
</tr>
<tr>
<td><input type = "submit" value = "Update" readonly></td>
</tr>
</table>
${message }
</form>
</body>
</html>