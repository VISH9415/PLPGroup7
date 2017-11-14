<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:if test="${var eq true}">
<table border="1">
<tr><td colspan="6" align="center">Mini Statement for ${userName}</td></tr>
<tr>
<th>Transaction Id</th>
<th>Transaction Description</th>
<th>Date of Transaction</th>
<th>Transaction Type</th>
<th>Transaction Amount</th>
<th>Account Number</th>
</tr>
<c:forEach items = "${miniList}" var = "trans">
<tr>
<td>${trans.transactionId }</td>
<td>${trans.transDescription }</td>
<td>${trans.dateOfTransaction }</td>
<td>${trans.transactionType }</td>
<td>${trans.transactionAmount }</td>
<td>${trans.accountNumber }</td>
</c:forEach>
</table>
</c:if>

<c:if test="${var eq false}">
<table border="1">
<tr><td colspan="6" align="center">Detailed Statement for ${userName}</td></tr>
<tr>
<th>Transaction Id</th>
<th>Transaction Description</th>
<th>Date of Transaction</th>
<th>Transaction Type</th>
<th>Transaction Amount</th>
<th>Account Number</th>
</tr>
<c:forEach items = "${detailList}" var = "trans">
<tr>
<td>${trans.transactionId }</td>
<td>${trans.transDescription }</td>
<td>${trans.dateOfTransaction }</td>
<td>${trans.transactionType }</td>
<td>${trans.transactionAmount }</td>
<td>${trans.accountNumber }</td>
</c:forEach>
</table>
</c:if>

</body>
</html>