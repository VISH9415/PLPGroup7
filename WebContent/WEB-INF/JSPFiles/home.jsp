<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="styles/forms.css">
    	<link rel="stylesheet" type="text/css" href="styles/home.css">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Sans|Nunito|Spectral+SC|Ubuntu" rel="stylesheet">
        <script type="text/javascript" src="scripts/index.js"></script>
        <script type="text/javascript" src="scripts/test.js"></script>
    </head>
    <body onload="return validateDates();">
        <div id="container">
            <div id="header">
                <ul>
                    <li id="logoContainer"><a href="home.jsp"><span id="headerLogo"></span></a></li>
                    <li><span>Welcome to Vivahaka Bank Online Portal</span></li>
                    <li class="headerRight"><span><a href="">Logout</a></span></li>
                     <li class="headerRight"><span>Welcome ${userName}</span></li>
                </ul>
            </div>
            
            
            <div id="body">
 
                 <c:if test="${check==0}"> 
                <div id="openAccountDiv">  
                  <input type="checkbox" id="openAccountform-switch"/>
                    <div id="openAccountButton">
                        <label for="openAccountform-switch">
                         <div id="openAccount">Open Account</div>
                        </label>
                    </div> 
                            
                    <!-- Open Account Form -->
                    <!--  conditional open account.. if user hasn't opened only then -->
                    <%-- <c:if test="${user.accountId==0}"> --%>
                    
                    <div id="openAccountForm">
                        <h1>Enter your details</h1>
                        <form action="openAccount.htm" method="post">
                            <p>
                            <input type="hidden" name="userName" value="${userName}"/>
                            </p>
							
                            <p>
                                <label for="openFormUserName">Customer Name</label>
                                <input type="text" name="openFormUserName" id="openFormUserName" required placeholder="Enter your name"/>
                            </p>
                            
                            <p>
                                <label for="openFormUseremail">Email</label>
                                <input type="email" name="openFormUseremail" id="openFormUseremail" required placeholder="Enter your email id"/>
                            </p>
                            
                            <p>
                                <label for="openFormUserAddress">Address</label>
                                <input type="text" name="openFormUserAddress" id="openFormUserAddress" required placeholder="Enter your address"/>
                            </p>
                            
                            <p>
                                <label for="openFormUserPan">Pan Card</label>
                                <input type="text" name="openFormUserPan" id="openFormUserPan" pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}"  placeholder="Enter your Pan Number" required/>
                            </p>
                            
                            <p>
                                <label for="openUserAccountType">Select Account Type:
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </label>
                                <input type="radio" name="openUserAccountType" value="savings">&nbsp;Savings
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="openUserAccountType" value="current">&nbsp;Current  
                            </p>
                            
                            <p>
                                <input type="submit" value="Submit Details" />
                            </p>
                            
                        </form>
                    
                    </div>
                        
                    </div>
                    </c:if>
                 
                
                <c:if test="${check!=0}">
                <div id="homePage">
                    <div id="body-left">
                    
                        <div id="homeButtonsTab"> 
                            <a href="home.jsp#hr-viewBalance"><div id="h-viewBalance" class="homeButtons"> View Balance</div></a>
                            <a href="home.jsp#hr-fundTransfer"><div id="h-fundTransfer" class="homeButtons">Fund Transfer</div></a>
                            <a href="home.jsp#hr-changePassword"><div id="h-changePassword" class="homeButtons">Change Password</div></a>
                            <a href="home.jsp#hr-changeAddress"><div id="h-changeAddress" class="homeButtons">Change Address</div></a>
                        </div>       
                    </div>
                
                    <div id="body-right">
                        <div id="hr-initialView">
                            <div id="hrb-welcome"></div>
                        </div>
                        <div id="hr-viewBalance">
                            <div id="bal-container">
                                <a href="home.jsp#hr-miniStatementForm"><div id="miniStatement">Mini Statement</div></a>
                                <a href="home.jsp#hr-detailedStatementForm"><div id="detailedStatement">Detailed Statement</div></a>
                            </div>
                        </div>
                        
                        
                        <!-- Fund transfer Form -->
                        
                        <div id="hr-fundTransfer">
                            <div id="hr-ft-formContainer">
                                <h1>Fund Transfer</h1>
                                <form action="fundTransfer.htm" method="post">
								    <p>
                                <input type="hidden" name="userName" value="${userName}"/>
                                </p>
								
                                    <p>
                                        <label for="ft-userid">Enter your account id:</label>
                                        <input type="text" id="ft-userid" name="ft-userid" placeholder="Enter your account id" required/>
                                    </p>
                                    <p>
                                        <label for="ft-payeeid">Enter payee account id:</label>
                                        <input type="text" id="ft-payeeid" name="ft-payeeid" placeholder="Enter payee account id" required/>
                                    </p>
                                    <p>
                                        <label for="ft-confirmpayeeid">Re enter payee account id:</label>
                                        <input type="text" id="ft-confirmpayeeid" name="ft-confirmpayeeid" placeholder="Re enter payee account id" required/>
                                    </p>
                                    <p>
                                        <label for="ft-amount">Enter amount to transfer:</label>
                                        <input type="text" id="ft-amount" name="ft-amount" placeholder="Enter amount to transfer" pattern="[0-9]{1,}" required/>
                                    </p>
									 <p>
                                        <label for="ft-describe">Payment description:</label>
                                        <input type="text" id="ft-describe" name="ft-describe" placeholder="Enter payment description:" required/>                                    
                                    </p>
									 <p>
                                        <label for="ft-nickname">Nick name:</label>
                                        <input type="text" id="ft-nickname" name="ft-nickname" placeholder="Enter nick name for payee:" required/>                                    
                                    </p>
										
																	
                                    <p><input type="submit" value="Transfer Money"/></p>
                                </form>
                            
                            </div>
                        </div>
                        
                        
                        <!-- Change Password Form -->
                        <div id="hr-changePassword">
                            <div id="hr-ft-formContainer">
                                <h1>Change Password</h1>
                                <form action="changePassword.htm" method="post">
							<p>
                            <input type="hidden" name="userName" value="${userName}"/>
                            </p>
                                    <p>
                                        <label for="ft-oldpswd">Old Password:</label>
                                        <input type="password" id="ft-oldpswd" name="ft-oldpswd" placeholder="Enter your old password" required/>
                                    </p>
                                    <p>
                                        <label for="ft-newpswd">New Password:</label>
                                        <input type="password" id="ft-newpswd" name="ft-newpswd" placeholder="Enter your new password" required/>
                                    </p>
                                    <p>
                                        <label for="ft-confirmpswd">Confirm Password:</label>
                                        <input type="password" id="ft-confirmpswd" name="ft-confirmpswd" placeholder="Re enter new Password" required/>
                                    </p>
                                    <p><input type="submit" value="Change Password"/></p>
                                </form>
                            
                            </div>
                        </div>
                        
                        
                        <!-- Change Address Form -->
                        <div id="hr-changeAddress">
                        
                            <div id="hr-ft-formContainer">
                                <h1>Change Address</h1>
                                <form action="changeAddress.htm" method="post">
							<p>
                            <input type="hidden" name="userName" value="${userName}"/>
                            </p>	
                                    <p>
                                        <label for="ft-newAddress">New Address:</label>
                                        <input type="text" id="ft-newAddress" name="ft-newAddress" placeholder="Enter your new address" required/>
                                    </p>
                                    <p><input type="submit" value="Change Address"/></p>
                                </form>
                            
                            </div>
                        
                        </div>
                        
                        <!-- mini statement form -->
                        <div id="hr-miniStatementForm">
                            <div id="hr-miniDetailAsk">
                                <h1>View Mini Statement</h1>
                                <form action="miniStatement.htm" method="post">
							<p>
                            <input type="hidden" name="userName" value="${userName}"/>
                            </p>
                                    <p>
                                        <label for="miniAccountNumber">Account Number</label>
                                        <input type="text" id="miniAccountNumber" name="miniAccountNumber" placeholder="Enter your account number" required />
                                    </p>
                                    <p>
                                        <input type="submit" value="View Mini Statement">
                                    </p>
                                </form>
                            </div>
                        </div>
                        
                        
                        <!-- detailed statement form -->
                        <div id="hr-detailedStatementForm">
                            <div id="hr-detailedDetailAsk">
                                <h1>View Detailed Statement</h1>
                                <form action="detailedStatement.htm" method="post" onSubmit="return validateDateForm();">
                            <p>
                            <input type="hidden" name="userName" value="${userName}"/>
                            </p>    
									<p>
                                        <label for="detailedAccountNumber">Account Number</label>
                                        <input type="text" id="miniAccountNumber" name="miniAccountNumber" placeholder="Enter your account number" required />
                                    </p>
                                    <p>
                                        <label for="startDate">Enter Starting Date</label>
                                        <input type="date" id="startDate" name="startDate" required>
                                    </p>
                                    <p>
                                        <label for="endDate">Enter Ending Date</label>
                                        <input type="date" id="endDate" name="endDate" required>
                                    </p>
                                    <p>
                                        <input type="submit" value="View Detailed Statement">
                                    </p>
                                </form>
                            </div>
                        </div>
                        
                        <div id="hr-vb-ministatement">
                            <div id="miniStatementViewer">
                                <div id="miniStatementTable">
                                <form>
                                    <table>
                                        <caption>Some of your latest transactions are as:</caption>
                                        <thead>
                                            <tr>
                                                <th scope="col">Transaction id</th>
                                                <th scope="col">Transaction Description</th>
                                                <th scope="col">Date of Transaction</th>
                                                <th scope="col">Transaction Type</th>
                                                <th scope="col">Transaction Amount</th>
                                                <th scope="col">Account Number</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        <c:forEach items="${miniList}" var="trans">
                                            <tr>
                                                <!-- <td scope="row">Dummy data</td> -->
                                              <td>${trans.transactionId}</td>
                                              <td>${trans.transDescription}</td>
                                              <td>${trans.dateOfTransaction}</td>
                                              <td>${trans.transactionType}</td>
                                              <td>${trans.transactionAmount}</td>
                                              <td>${trans.accountNumber}</td>
                                            </tr>
                                            </c:forEach>                                            
                                            </tbody>
                                        </table>
                                        </form>
                                </div>
                            </div>
                        </div>
						
                        <div id="hr-vb-detailedstatement">
                            <div id="detailedStatementViewer">
                               <div id="detailedStatementTable">
                                   <table>
                                   
                                        <caption>Detailed Statement as:</caption>
                                        <thead>
                                            <tr>
                                                <th scope="col">Transaction id</th>
                                                <th scope="col">Transaction Description</th>
                                                <th scope="col">Date of Transaction</th>
                                                <th scope="col">Transaction Type</th>
                                                <th scope="col">Transaction Amount</th>
                                                <th scope="col">Account Number</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        <c:forEach items="${detailedList}" var="trans">
                                            <tr>
                                              <!--   <td scope="row">Dummy data</td> -->
                                              <td>${trans.transactionId}</td>
                                              <td>${trans.transDescription}</td>
                                              <td>${trans.dateOfTransaction}</td>
                                              <td>${trans.transactionType}</td>
                                              <td>${trans.transactionAmount}</td>
                                              <td>${trans.accountNumber}</td>
                                            </tr>
                                            </c:forEach>                                            
                                       </tbody>  
                                             
                                    </table>
                               </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                </c:if>
            </div>
            
            <div id="footer">&copy; Vivahaka Bank</div>
       </div>
    </body>
</html>

