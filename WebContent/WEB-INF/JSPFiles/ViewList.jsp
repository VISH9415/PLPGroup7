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
			<div id="tableContainer">
				<c:if test="${var eq true && numberOfTransactions!=0}">
					<table style="display: block;" border="1">
						<tr>
							<td colspan="6">Mini Statement for ${userName}</td>
						</tr>
						<tr>
							<th>Transaction Id</th>
							<th>Transaction Description</th>
							<th>Date of Transaction</th>
							<th>Transaction Type</th>
							<th>Transaction Amount</th>
							<th>Account Number</th>
						</tr>
						<c:forEach items="${miniList}" var="trans">
							<tr>
								<td>${trans.transactionId }</td>
								<td>${trans.transDescription }</td>
								<td>${trans.dateOfTransaction }</td>
								<td>${trans.transactionType }</td>
								<td>${trans.transactionAmount }</td>
								<td>${trans.accountNumber }</td>
								</tr>
						</c:forEach>
					</table>
				</c:if>

				<c:if test="${var eq true && numberOfTransactions==0}">
					<h2>You haven't performed any transactions before</h2>
				</c:if>

				<!-- Table for detailed view -->

				<c:if test="${var eq false  && numberOfTransactions!=0}">
					<table style="display: block;" border="1">
						<tr>
							<td colspan="6" align="center">Detailed Statement for ${userName}</td>	
						</tr>
						<tr>
							<th>Transaction Id</th>
							<th>Transaction Description</th>
							<th>Date of Transaction</th>
							<th>Transaction Type</th>
							<th>Transaction Amount</th>
							<th>Account Number</th>
						</tr>
						<c:forEach items="${detailList}" var="trans">
								<tr>
									<td>${trans.transactionId }</td>
									<td>${trans.transDescription }</td>
									<td>${trans.dateOfTransaction }</td>
									<td>${trans.transactionType }</td>
									<td>${trans.transactionAmount }</td>
									<td>${trans.accountNumber }</td>
								</tr>
						</c:forEach>
					</table>
				</c:if>

				<c:if test="${var eq false && numberOfTransactions==0}">
					<h2>You haven't performed any transactions before</h2>
				</c:if>
			</div>
		</div>
		<div id="footer">&copy; Vivahaka Bank</div>
	</div>

</body>
</html>
