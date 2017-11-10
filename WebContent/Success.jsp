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
<h3>Thank you are successfully logged in ...</h3>


    <c:if test="${user.accountId==0}">
 
    <form>
    <input type="hidden" name="username" value="${uid}"/>
    <!-- <input type="submit" name="submit" value="OpenAccount"/> -->
     <a href="AccountOpen.htm">OpenAccount</a> 
    </form> 
 	</c:if>    
 	
 	
 	<c:if test="${user.accountId!=0 }">
    <h3>Hello ${customer.customerName} your account is already opened with account number ${user1.accountId}</h3>
    <h3>Your Account Balance is {}</h3>
<form>
Please select:

<a href="balance.htm">View Mini/Detailed Statement</a><br>
<a href="changeaddress.htm">Change in address</a><br>
<a href="chequeRequest">ChequeBook request</a><br>
<a href="trackService">Track Service Request</a><br>
<a href="fundTransfer.htm">Fund Transfer</a><br>
<a href="changePass">Change Password</a>

</form>
</c:if>

</body>
</html>