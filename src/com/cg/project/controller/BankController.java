package com.cg.project.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.FundTransferBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.exception.BankingException;
import com.cg.project.service.IBankingService;
import com.cg.project.utils.Constants;

@Controller
public class BankController {

	//check if autowiring for bean is required....
	UserBean userBean = new UserBean();
	AccountBean accountBean = new AccountBean();
    CustomerBean customerBean = new CustomerBean();
    AccountBean account = new AccountBean();
    TransactionsBean transaction = new TransactionsBean();
    PayeeBean payee = new PayeeBean();
    FundTransferBean fundTransfer = new  FundTransferBean(); 
    
    @Autowired
	IBankingService service;

    @RequestMapping(value = "/user.htm")
	public String newUser(){
		return "newuser"; 
	}
	

	/**
	 * Method used to sign up
	 * 
	 * @param username
	 * @param pwd
	 * @param confirmpwd
	 * @param transpwd
	 * @param secques
	 * @param secans
	 * @param model
	 * @return
	 * @throws BankingException
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String addUser(@RequestParam("usernamesignup") String username,
			@RequestParam("passwordsignup") String pwd,
			@RequestParam("passwordsignup_confirm") String confirmpwd,
			@RequestParam("transactionpassword") String transpwd,
			@RequestParam("secretquestion") String secques,
			@RequestParam("secretanswer") String secans, Model model)
			throws BankingException{
		
		System.out.println("in signup");

		// FETCH USER from usertable BY USERID..
		UserBean userFetched = service.fetchUserById(username);
		 
		System.out.println("user fetched..." + userFetched);

		String redirect = "";
		if (userFetched == null) {
			// user not registered
	         UserBean user = new UserBean();
	 		user.setUserId(username);
	 		user.setLoginPassword(pwd);
	 		user.setTransactionPassword(transpwd);
	 		user.setAccountId(0L);
	 		user.setSecretQuestion(secques);
	 		user.setSecretAnswer(secans);
	 		user.setLockStatus(Constants.lockStatus);
			System.out.println("userBean: " + user);
			
			service.registerUser(user);
            //fetch user to validate if inserted on not..
			UserBean userCheck = service.fetchUserById(username);
			System.out.println("userCheck "+userCheck);
			if(userCheck!=null)
			{String msg = "signup done!";
			System.out.println(msg);
			model.addAttribute("message", msg); // return "index";
			redirect = "Temp";
		   } else {
			String msg = "Signup failed!!";
			System.out.println(msg);
			model.addAttribute("message", msg); 
			redirect = "Error";
		   }
		}
		else {
			String msg = "User already registered";
			System.out.println(msg);
			model.addAttribute("message", msg); 
			redirect = "Error";
		     }

			return redirect;
			}		

	/*@RequestMapping(value = "/login.htm")
	public String existingUser(){
		return "Login";
	}*/
	//xml of vaibhav type...
	//user inserted during signup....
	/*@RequestMapping("/loginMethod") 
	public String existingUserlogin(@RequestParam("userName") String uid,@RequestParam("loginPassword") String password,Model model) throws BankingException
	{
		userBean.setUserId(uid);
		UserBean user= service.fetchUserById(userBean.getUserId());
		UserBean userBean = service.validateUser(uid);
		UserBean user1 = service.viewAccountId(uid);
		customerBean = service.viewCustomer(user1.getAccountId());
//		long accId = customerBean.getAccountId();
	     //user fetched from usertable..
		//if user!=null
 		if(password.equals(user.getLoginPassword()))
		{
 			model.addAttribute("customer", customerBean);
 			model.addAttribute("user1", user1);
 			model.addAttribute("user", userBean);
			model.addAttribute("username",user.getUserId()); 
			return "Success";
=======
	
	/**
	 * Method for user login 
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 * @throws BankingException
	 */
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String existingUserlogin(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model)
			throws BankingException{
		// userBean.setUserId(username);
		// validating user...
		
		UserBean user = service.fetchUserById(username);
		// checking user record in usertable..
		String redirect = "";
		System.out.println("for login user fetched: " + user);
		
		if (user != null) {
			System.out.println("exists");
			// validate login password..
			if (password.equals(user.getLoginPassword())) {
				// logged in...
				// send username to forget password page...
				// model.addAttribute("username",user.getUserId());
				
				String msg = "correctPassword!!";
				// set username as session object and send to home page...
				System.out.println(msg);
				model.addAttribute("message", msg); // return "index";
				//redirect = "home";
				model.addAttribute("userName",username);
				model.addAttribute("user", user);
				//System.out.println("going to home temp");
				//customer fetch by userId..
				redirect = "home";
			} else { // ask for forget password ....
				String msg = "Password incorrect, try again !!";
				System.out.println(msg);
				model.addAttribute("message", msg);
				redirect = "Error";
			}
		} else {
			String msg = "user isn't registered,please signup!!";
			System.out.println(msg);
			model.addAttribute("message", msg);
			redirect = "Error";
		}
		return redirect;
	}

	//***************************forget password*************************
	/**
	 * Method to implement forget password from index.jsp
	 * @param username
	 * @param secques
	 * @param secans
	 * @param model
	 * @return
	 * @throws BankingException
	 */
	@RequestMapping(value="/forgetPass", method = RequestMethod.POST)
	public String forgetpassword(
			@RequestParam("usernameForget") String userName,
			@RequestParam("secretquestion") String secques,
			@RequestParam("forget-passwordq") String secans, Model model)
			throws BankingException{
		System.out.println("in forget pass");
		
		UserBean user = new UserBean();
		user = service.fetchUserById(userName);
		
		System.out.println("user fetched: " + user);
		String redirect = "";
		if(user!=null){
		if (secques.equals(user.getSecretQuestion())) {
			if (secans.equals(user.getSecretAnswer())) {
				// generate new password...
				//make it case insensititive also...
				String newPassword = service.randPassword();
				user.setLoginPassword(newPassword);
				// update userpassword ...
				service.updateloginpassword(user);
				UserBean userUpdated = service.fetchUserById(userName);
				// user login with new password...
				if(userUpdated.getLoginPassword().equals(newPassword)) {
				String msg = "Your new login password is: "+newPassword+"";
				model.addAttribute("message", msg);
				redirect = "Temp";
			} else {
				String msg = "password isn't updated ";
				model.addAttribute("message", msg);
				redirect = "Error";
			}
		}else
		{
			String msg = "incorrect secret answer ";
			model.addAttribute("message", msg);
			redirect = "Error";
		}
			}
			else {
			String msg = "incorrect secret question chosen ";
			model.addAttribute("message", msg);
			redirect = "Error";
		}

		}
		else
		{
			String msg = "user not registered... ";
			model.addAttribute("message", msg);
			redirect = "Error";
		}
		// enter new pass and set ..
		return redirect;
	}

/*	// open account request
	@RequestMapping("/openAccountRequest")
	public String sendRequestToAdmin(@RequestParam("userName") String uid,
			Model model) {
		// openAccount call //request sent to admin...
		// model.addAttribute("choice", choice);
		String redirect = "";
		String msg = "Dear Admin you have recieved an open account request from user"+uid+"";
		model.addAttribute("username", uid);
		model.addAttribute("message", msg);
		redirect = "Temp";
		return redirect;
	}*/

	//***************************admin login*************************
	/**
	 * Method for bank administrator login...
	 * @param adminId
	 * @param adminPass
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/adminlogin", method = RequestMethod.POST)
	public String validateAdmin(@RequestParam("admin-username") String adminId,
			@RequestParam("admin-password") String adminPass, Model model) {
		System.out.println("in admin login method");
		String redirect = "";
		try {
		int count = service.validateAdmin(adminId, adminPass);
		if (count != 0) {
			String msg = "Admin Login Successful";
			model.addAttribute("message",msg);
			redirect ="Temp";
		} else {
			redirect = "Error";
		}
		}
		catch(BankingException be){
			be.getMessage();
		}
		return redirect;
	}

	//***************************open account*************************
	/**
	 * Method to open account of user 
	 * @param customername
	 * @param email
	 * @param address
	 * @param pancard
	 * @param accType
	 * @param username
	 * @param model
	 * @return
	 * @throws BankingException
	 */
	
	@RequestMapping(value="/openAccount",method=RequestMethod.POST)
	public String openAccount(
			@RequestParam("openFormUserName") String customername,
			@RequestParam("openFormUseremail") String email,
			@RequestParam("openFormUserAddress") String address,
			@RequestParam("openFormUserPan") String pancard,
			@RequestParam("openUserAccountType") String accType,
			@RequestParam("userName") String userName,
			Model model) throws BankingException{
		
		
		String redirect = "";
		System.out.println("in open account..."+userName);
		/*
		//fetch user record so as to obtain its account id..
		userBean = service.fetchUserById(userName);
		//checking if updated actid is already there in usertable.. 
		customerBean = service.fetchCustomerByAccountId(userBean.getAccountId());
		
        if(customerBean==null)
        { // customer doesn't exists..
        */	
        //disable open account option if already opened account...
		
		CustomerBean customer = new CustomerBean();
		
		customer.setAccountType(accType);
		customer.setAddress(address);
		customer.setCustomerName(customername);
		customer.setEmail(email);
		customer.setPancard(pancard);
		customer.setUserId(userName);
		
		//insert record into customer table..
		service.insertIntoCustomer(customer);

		//fetch customer by account id...if required
       	//CustomerBean customer = service.fetchFromCustomer()
		//username fetched from webpage...session attribute
		UserBean userFetched = service.fetchUserById(userName);
		//userBean.setAccountId(0);//while opening the account
		//update user...
		
		System.out.println("user fetched: "+userFetched);
        
		 // update account id in usertable remaining...
		long accountId = service.fetchAccountIdFromCustomer(userName);
		System.out.println("hi");
		userFetched.setAccountId(accountId);
		
		UserBean user = service.updateAccountIdinUser(userFetched);
		
		System.out.println("updated actid: "+user.getAccountId());
		//System.out.println("ACCOUNT ID IS "+accountId);
		
		AccountBean account = new AccountBean();
		
		account.setAccountBalance(Constants.initBalance);
		account.setAccountId(accountId);
		account.setAccountType(customer.getAccountType());				
		LocalDate date = LocalDate.now();
		Date opendate = Date.valueOf(date);
		account.setOpenDate(opendate);
        
		service.insertIntoAccountMaster(account);
        // fetch from account master..
		AccountBean accountFetched =service.fetchAccountByAccountId(accountId);
         System.out.println("act fetched: "+accountFetched);
         
		if(accountFetched!=null){
		String msg = "Account is opened with balance Rs.10K only !!";
		model.addAttribute("message", msg);
		model.addAttribute("userName",userName);
		redirect = "TempHome";
		}
		else
		{
			String msg = "Account isn't isnerted.. for this  !!";
			model.addAttribute("message", msg);
			redirect = "ErrorHome";
		}
		
		return redirect;
	}

	@RequestMapping("/Viewbalance")
	public String viewStatement() {
		return null;
     
	}
	
	//***************************change address*************************
	@RequestMapping(value="/changeAddress",method=RequestMethod.POST)
	public String changeRequest(@RequestParam("ft-newAddress") String newAddress,@RequestParam("userName") String userName,Model model) throws BankingException{
		// for same user as in the form...
		//fetch account_id for user from customer...
		String redirect = "";
		long accountId = service.fetchAccountIdFromCustomer(userName);
		System.out.println("actid :"+accountId);
		CustomerBean customer = service.fetchCustomerByAccountId(accountId);
		System.out.println("customer fetched:"+customer);
		customer.setAddress(newAddress);
		
	    service.updateCustomerAddress(customer);
	    System.out.println("updated customer");
	    model.addAttribute("userName",userName);
		redirect =  "home";
		return redirect;
	}
	
	/**
	 * @param accId
	 * @param payeeId
	 * @param amount
	 * @param map
	 * @return
	 * @throws BankingException 
	 */
	@RequestMapping(value = "/fundTransfer.htm", method = RequestMethod.POST)
	public String fundTransfer(@RequestParam("ft-userid") long accId, @RequestParam("ft-payeeid") long payeeId,
			@RequestParam("ft-amount") double amount,@RequestParam("userName") String userName,@RequestParam("ft-describe") String transactionDescription,@RequestParam("ft-nickname") String nickName,Model map)
	throws BankingException{
		System.out.println("in funds...");
		System.out.println("nickname "+nickName);
		//@RequestParam("userId") String userId,
		String redirect = null;
		String msg = null;
		double balance = 0;

		UserBean userByName = new UserBean();
		userByName = service.fetchUserById(userName);
		System.out.println("pay to "+payeeId);
		long payerId = userByName.getAccountId();
		//if payer is the same user ...
		System.out.println("pay by "+payerId);
		if(payerId==accId){
			
			CustomerBean customerByActId = new CustomerBean();
			customerByActId = service.fetchCustomerByAccountId(payeeId);
		    long payeId = customerByActId.getAccountId();
		    System.out.println("payeId :"+payeId); 
		//customer validate
		if(payeId==payeeId){
			
		AccountBean accountByActId = new AccountBean();	
		accountByActId = service.fetchAccountByAccountId(accId);
		System.out.println("accountByActId"+accountByActId);
		balance = accountByActId.getAccountBalance();
		
		System.out.println("balance: "+balance);
		if(balance>=amount){
		    //update account master,insert into transact and payee and fund transfer
			System.out.println("in validaton of balance");
			//insert transactions for payer...
			TransactionsBean transactionByActId = new TransactionsBean();
			transactionByActId.setAccountNumber(accId);
					
			LocalDate dt = LocalDate.now();
			Date dateOfTransactionForPayer = Date.valueOf(dt);
			
			transactionByActId.setDateOfTransaction(dateOfTransactionForPayer);
			transactionByActId.setTransactionAmount(amount);
			transactionByActId.setTransactionType("savings"); //to be imported from select while login
			transactionByActId.setTransDescription(transactionDescription);
			
			service.insertTransactionDetails(transactionByActId);
			System.out.println("Inserting data into transactions of payer");
			
			//insert transactions for payee...
			TransactionsBean transactionByPayeeId = new TransactionsBean();
			transactionByPayeeId.setAccountNumber(payeId);
			
			LocalDate dt1 = LocalDate.now();
			Date dateOfTransactionForPayee = Date.valueOf(dt1);
			
			transactionByPayeeId.setDateOfTransaction(dateOfTransactionForPayee);
			transactionByPayeeId.setTransactionAmount(amount);
			transactionByPayeeId.setTransactionType("savings"); //to be imported from select while login
			transactionByPayeeId.setTransDescription(transactionDescription);
			
			service.insertTransactionDetails(transactionByPayeeId);
			System.out.println("isnerting data into transaction of payee");
			
			//insert into payee table..
			PayeeBean payee = new PayeeBean();
			payee.setAccountId(accId);
			payee.setPayeeAccountId(payeeId);
			payee.setNickName(nickName);
			
			System.out.println("before inserting into payee");
			service.insertPayeeDetails(payee);
			System.out.println("inserting into payee");
			
			//insert into fundtransfer table..
			FundTransferBean fundTransfer = new FundTransferBean();
			fundTransfer.setAccountId(accId);
			fundTransfer.setDateOfTransfer(dateOfTransactionForPayee);
			fundTransfer.setPayeeAccountId(payeeId);
			fundTransfer.setTransferAmount(amount);
			
			System.out.println("before fund insertion");
			service.insertFundTransferDetails(fundTransfer);
			System.out.println("after fund insertion");
			
			//update balance for payer
			double updatedBalanceForPayer = balance-amount;
		   	accountByActId.setAccountBalance(updatedBalanceForPayer);
		   	System.out.println("before updation of balance of payer");
			service.updateBalance(accountByActId);
            System.out.println("after updation of balance of payer");
			
			//update balance for payee
			
			AccountBean accountByPayeeId = new AccountBean();
			accountByPayeeId=service.fetchAccountByAccountId(payeeId);
			double updatedBalanceForPayee =  accountByPayeeId.getAccountBalance()+amount;
			accountByPayeeId.setAccountBalance(updatedBalanceForPayee);
			
			System.out.println("before updation of balance of payee");
			service.updateBalanceForPayee(accountByPayeeId);
			System.out.println("after updation of balance of payee");
			
			msg = "Transaction Successful";
			redirect = "Temp";
			map.addAttribute("message", msg);
		}
		else{
			redirect = "Error";
			msg = "Insufficient Balance";
			map.addAttribute("message", msg);
		}
		}
		else {
			msg = "Invalid payee Id";
			map.addAttribute("message", msg);
			redirect="Error";
		}
		}
		else
		{
			msg = "Invalid payer Id";
			map.addAttribute("message", msg);
			redirect="Error";
		
	}
		return redirect;
	}
	

	@RequestMapping("/balance.htm")
	public String viewBalance(){
		return "balance";
	}
	
	@RequestMapping("/home.html#hr-miniStatementForm")
	public String miniStatement(){
		return "ministatement";
	}
	
	@RequestMapping(value = "/miniStatement.htm", method = RequestMethod.POST)
	public String viewMiniStatement(@RequestParam("accId") long accId,@RequestParam("userName") String userName,Model map) throws BankingException{
		long actIdForUser = service.fetchUserById(userName).getAccountId();
		String redirect = "";
		if(actIdForUser==accId) {
		List<TransactionsBean> miniStatement = service.viewMiniStatement(accId);
		map.addAttribute("miniList", miniStatement);
		redirect = "ViewList";
		// redirect to home to view list
		 }
		else
		{ String msg= "wrong account Id..";
		map.addAttribute("message", msg);
			redirect = "Error";
		}
		return redirect;
	}
/*	
	@RequestMapping("/detailedstatement.htm")
	public String detailedStatement(){
		return "detailedstatement";
	}
	*/
	@RequestMapping(value = "/detailedStatement.htm", method = RequestMethod.POST)
	public String viewDetailedStatement(@RequestParam("accId") long accId,@RequestParam("userName") String userName,
			@RequestParam("inidate") Date iniDate,@RequestParam("findate") Date finDate,Model map) throws BankingException{
		long actIdForUser = service.fetchUserById(userName).getAccountId();
		String redirect = "";
		if(actIdForUser==accId) {
		List<TransactionsBean> detailStatement = service.viewDetailStatement(accId,iniDate,finDate);
		map.addAttribute("detailList", detailStatement);
		  redirect = "ViewList";}
		else
		{
		redirect = "Error";
		}
		return redirect;
	}
	
	@RequestMapping("/adminViewTransactions")
	public String adminViewTransactions(@RequestParam("accId") long accId,Model map){
		String redirect = ""; 
		try {
		List<TransactionsBean> adminStmt = service.adminViewTransactions(accId);
		map.addAttribute("adminList", adminStmt);
		redirect = "temp";}
		catch(BankingException be){
			be.getMessage();
		}
		return redirect;
	}
	
  
	@RequestMapping(value="/changePassword.htm",method=RequestMethod.POST)
	public String changePassword(@RequestParam("userName") String userName,@RequestParam("ft-oldpswd") String oldpassword,@RequestParam("ft-newpswd") String newpassword,Model model) throws BankingException{
		{
	   	//fetch user by id...
			//username shall come in almost every method from page..
			UserBean user = service.fetchUserById(userName);
			System.out.println("in change password");
			String loginPass = user.getLoginPassword();
			
			String redirect ="";
			
			if(user!=null)
			{ //reenter new password need to matched in the form only..
				
				if(loginPass.equals(oldpassword)){
					
			     user.setLoginPassword(newpassword);
			     service.updateloginpassword(user);
			     
			     String msg = "Login Password is changed !!";
				 model.addAttribute("message", msg);
			     redirect = "Temp";
			         }
				else
				{
					String msg = "incorrect oldpassword !!";
					model.addAttribute("message", msg);
					redirect = "Error";
				}
			}
		return redirect;
	}
	
	
	
}
}
