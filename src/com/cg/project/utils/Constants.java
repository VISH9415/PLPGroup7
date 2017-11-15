package com.cg.project.utils;

public interface Constants {

	double initBalance = 10000;
	String adminUserName = "ViVaHaKa";
	String adminLoginPassword = "ADMIN1234";
	int adminInitialCount = 0;
	String lockStatus = "nope";
	String returnToHome= "index.jsp"; //for hyperlinks
	String returnToLogin= "home.jsp";
	String SignUpDone= "Welcome, you have successfully signed up with ViVaHaKa Bank";
	String message= "message";
	String page= "page";
	String SignupFailed= "Sorry signup failed";
	String alreadySignedUp= "Dear user, you have already registered with our Bank";
	String checkOpenAccount= "check";
	String userName= "userName";
	String incorrectPassword = "You have entered incorrect password";
	String notSignedUp= "You are not registered, please signup first";
	String passwordUpdateFailure = "Login Password update failed";
	String incorrectAnswer = "Incorrect answer is provided for Secret Question";
	String incorrectQuestion = "Incorrect security Question selected";
	String newLoginPassword = "New Login Password generated is: ";
	String passwordForFutureLogin = " ,use this for future login";
	String adminLogin = "Dear Admin, you have successfully logged in";
	String adminInvalidCredentials = "Sorry, invalid login credentials";
	String addressChanged = "Addresss successfully changed";
	String accountOpen = "Your account is opened with balance Rs.10,000 only !!";
	String accountOpenFailed = "Account open failed!";
	boolean checkforMiniStatement = true;
	boolean checkforDetailStatement = false;
	boolean checkforAdminStatement = true;
	String selectTypeOfStatement = "var";
	String selectAdminStatement = "admin";
	String noOfTransactions = "numberOfTransactions";
	String miniStatement = "miniList";
	String detailedStatement = "detailList";
	String invalidAccountId = "Entered wrong account Id";
	String LoginPasswordChanged = "Dear user, your login Password is changed";
	String incorrectOldPassword = "You have entered incorrect old password";
	String transactionTypeForPayer = "sent";
	String transactionTypeForPayee = "recieved";
	String invalidPayerId = "Incorrect Payer id entered";
	String invalidPayeeId = "Incorrect Payee id entered";
	String insufficientBalance = "Sorry, insufficient balance available in your account";
	String transactionSuccessful = "You have successfully transfered the amount to payee";
	String PayeeNotRegistered = "Sorry the payee isn't registered with our bank";
	long initialAccountId = 0L;
	long initFundId = 0L;
	long initTransactionId = 0L;
	String transactionsForAdmin ="adminList";
	Object loginDone = "You're successfully logged in";

	}
