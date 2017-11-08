<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <form action="openAccount" modelAttribute="customer">
    
     <input type="hidden" name="username" value="${username}"/> 
    
    Enter Name:<input type="text" name="customerName"/>
    Enter Address:<input type="text" name="address"/>
    Enter Email:<input type="text" name="email"/>
    
    Select account type:<select name="accountType">
    <option value="savings">Savings</option>
    <option value="current">Current</option>
    </select>
    
    Opening Balance: <input type="number" value="0" readonly/>
    
    <input type="submit" name="submit" value="OpenAccount"/>
    
    </form>
</body>
</html>