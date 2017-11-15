<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles/newWindow.css">
<link rel="stylesheet" type="text/css" href="styles/home.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<ul>
				<li id="logoContainer"><a href="home.html"><span
						id="headerLogo"></span></a></li>
				<li><span>Welcome to Vivahaka Bank Online Portal</span></li>
				<li class="headerRight"><span><a href="">Logout</a></span></li>
				<li class="headerRight"><span>Welcome ${userName}</span></li>
			</ul>
		</div>
		<div id="body">
			<div id="bodyContainer">
				<p>${message}</p>
				<c:if test="${admin eq true}">
					<form action="adminViewTransactions.htm" method="post">
						<table>
							<tr>
								<td>Enter Account Id</td>
								<td><input type="text" name="accountId"></td>
							</tr>
							<tr>
								<td><input type="submit" value="Submit"></td>
							</tr>
						</table>
					</form>
				</c:if>
				<p>
					<a href="${page}">Home</a>
				</p>
			</div>
		</div>
		<div id="footer">&copy; Vivahaka Bank</div>
	</div>
</body>
</html>