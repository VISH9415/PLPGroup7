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
	
     //***************************user signup *************************
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
			throws BankingException {
		// String msg = null;
		System.out.println("in signup");

		userBean.setUserId(username);
		userBean.setLoginPassword(pwd);
		userBean.setTransactionPassword(transpwd);

		userBean.setAccountId(0L);
		userBean.setSecretQuestion(secques);
		userBean.setSecretAnswer(secans);
		userBean.setLockStatus("nope");
		// FETCH USER from usertable BY USERID..

		UserBean user = service.fetchUserById(username);
		System.out.println("user fetched..." + user);

		String redirect = "";
		if (user == null) {
			// user not registered
			System.out.println("userBean: " + userBean);
			service.registerUser(userBean);

			String msg = "signup done";
			model.addAttribute("message", msg); // return "index";
			redirect = "Temp";
		} else {
			String msg = "already registered!!";
			model.addAttribute("message", msg); // return "index";
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

	// user login
	
	//***************************user login*************************
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
			throws BankingException {
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
				//System.out.println("going to home temp");
				redirect = "home";
			} else { // ask for forget password ....
				String msg = "Please select forget password!!";
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
	 * Method to implement forget password..
	 * @param username
	 * @param secques
	 * @param secans
	 * @param model
	 * @return
	 * @throws BankingException
	 */
	@RequestMapping(value="/forgetPass", method = RequestMethod.POST)
	public String forgetpassword(
			@RequestParam("usernameForget") String username,
			@RequestParam("secretquestion") String secques,
			@RequestParam("forget-passwordq") String secans, Model model)
			throws BankingException {
		System.out.println("in forget pass");
		UserBean user = service.fetchUserById(username);
		System.out.println("user for forget pass:" + user);
		String redirect = "";
		if(user!=null){
		if (secques.equals(user.getSecretQuestion())) {
			if (secans.equals(user.getSecretAnswer())) {
				// generate new password...
				//make it case insensititive also...
				String newPass = "newpass";
				user.setLoginPassword(newPass);
				// update userpassword ...
				service.updateloginpassword(user);
				// user login with new password...
				String msg = "Password changed,use it for further login!!"+newPass+"";
				model.addAttribute("message", msg);
				redirect = "Temp";
			} else {
				String msg = "incorrect secret answer ";
				model.addAttribute("message", msg);
				redirect = "Error";
			}
		} else {
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
		int count = service.validateAdmin(adminId, adminPass);
		if (count != 0) {
			return "Temp";
		} else {
			return "Error";
		}
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
			Model model) throws BankingException {
		
		
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
		
		//insert record into customer table..
		service.insertIntoCustomer(customer);
		
		//fetch customer by account id...if required
       	//CustomerBean customer = service.fetchFromCustomer()
		//username fetched from webpage...session attribute
		userBean = service.fetchUserById(userName);
		//userBean.setAccountId(0);//while opening the account
		//update user...
		
		System.out.println("user fetched: "+userBean);

		 // update account id in usertable remaining...
		long accountId = service.fetchAccountIdFromCustomer(userName);
		
		userBean.setAccountId(accountId);
		
		UserBean user = service.updateAccountIdinUser(userBean);
		
		System.out.println("updated actid: "+user.getAccountId());
		
		AccountBean account = new AccountBean();
		account.setAccountBalance(0);
		account.setAccountId(accountId);
		account.setAccountType(customer.getAccountType());				
		LocalDate date = LocalDate.now();
		Date opendate = Date.valueOf(date);
		account.setOpenDate(opendate);

		service.insertIntoAccountMaster(accountBean);
        // fetch from account master..
		accountBean =service.fetchAccountByAccountId(accountId);
;
		if(accountBean!=null){
		String msg = "Account is opened !!";
		model.addAttribute("message", msg);
		model.addAttribute("userName",userName);
		redirect = "Temp";
		}
		else
		{
			String msg = "Account isn't isnerted.. for this  !!";
			model.addAttribute("message", msg);
			redirect = "Error";
		}
		
		return redirect;
	}

	@RequestMapping("/Viewbalance")
	public String viewStatement() {
		return null;
     
	}
	
	//***************************change address*************************
	@RequestMapping(value="/changeAddress",method=RequestMethod.POST)
	public String changeRequest(@RequestParam("ft-newAddress") String newAddress,@RequestParam("userName") String userName,Model model) {
		// for same user as in the form...
		//fetch account_id for user from customer...
		long accountId = service.fetchAccountIdFromCustomer(userName);
		System.out.println("actid :"+accountId);
		CustomerBean customer = service.fetchCustomerByAccountId(accountId);
		System.out.println("customer fetched:"+customer);
		customer.setAddress(newAddress);
		
	    service.updateCustomerAddress(customer);
	    System.out.println("updated customer");
	    model.addAttribute("userName",userName);
		return "home";
	}
	
	@RequestMapping(value = "/fundTransfer.htm")
	public String fundTransfer()
	{
		return "fundTransfer";
	}
	
	/**
	 * @param accId
	 * @param payeeId
	 * @param amount
	 * @param map
	 * @return
	 * @throws BankingException 
	 */
	@RequestMapping(value = "/fundTrasnfer.htm", method = RequestMethod.POST)
	public String fundTransfer(@RequestParam("ft-userid") long accId, @RequestParam("ft-payeeid") long payeeId,
			@RequestParam("ft-amount") double amount,@RequestParam("userName") String userName,@RequestParam("ft-describe") String transactionDescription,Model map) throws BankingException
	{
		//@RequestParam("userId") String userId,
		String redirect = null;
		String msg = null;
		double balance = 0;
		/*UserBean userBean1 = service.getUserName(accId);
		String uId = userBean1.getUserId();*/
		//System.out.println("User Id"+uId);
		
		userBean = service.fetchUserById(userName);
		long payerId = userBean.getAccountId();
		//if payer is the same user ...
		if(payerId==accId){
		customerBean = service.fetchCustomerByAccountId(payeeId);
		long payeId = customerBean.getAccountId();
		//customer validate
		if(payeId==payeeId){
		account = service.fetchAccountByAccountId(accId);
		balance = account.getAccountBalance();
		
		if(balance>=amount){
		    //update account master,insert into transact and payee and fund transfer
			transaction.setAccountNumber(accId);
			
			LocalDate dt = LocalDate.now();
			Date dateOfTransaction = Date.valueOf(dt);
			
			transaction.setDateOfTransaction(dateOfTransaction);
			transaction.setTransactionAmount(amount);
			transaction.setTransactionType("savings"); //to be imported from select while login
			transaction.setTransDescription(transactionDescription);
			
			service.insertTransactionDetails(transaction);
			
			payee.setAccountId(accId);
			payee.setPayeeAccountId(payeeId);
			payee.setNickName("nick");
			
			service.insertPayeeDetails(payee);
			
			fundTransfer.setAccountId(accId);
			fundTransfer.setDateOfTransfer(dateOfTransaction);
			fundTransfer.setPayeeAccountId(payeeId);
			fundTransfer.setTransferAmount(amount);
			service.insertFundTransferDetails(fundTransfer);
			
			double bal = balance-amount;
			account.setAccountBalance(bal);
			//service.updateBalance(account);
			
		//	logger.info("Transaction Successful");
			msg = "Transaction Successful";
			redirect = "transfersuccess";
			map.addAttribute("message", msg);
			msg = "Transaction Successful";
			redirect = "transfersuccess";
			map.addAttribute("message", msg);
		}
		else{
			redirect = "Error";
			msg = "Insufficient Balance";
			map.addAttribute("message", msg);
		}
		return redirect;
		}
		}
		return msg;
	}
	

	@RequestMapping("/balance.htm")
	public String viewBalance(){
		return "balance";
	}
/*	@RequestMapping("/ministatement.htm")
	public String miniStatement(){
		return "ministatement";
	}
	*/
	@RequestMapping(value = "/miniStatement.htm", method = RequestMethod.POST)
	public String viewMiniStatement(@RequestParam("accId") long accId,Model map){
		List<TransactionsBean> miniStatement = service.viewMiniStatement(accId);
		map.addAttribute("miniList", miniStatement);
		return "home";
	}
/*	
	@RequestMapping("/detailedstatement.htm")
	public String detailedStatement(){
		return "detailedstatement";
	}
	*/
	@RequestMapping(value = "/detailedStatement.htm", method = RequestMethod.POST)
	public String viewDetailedStatement(@RequestParam("accId") long accId,
			@RequestParam("inidate") Date iniDate,@RequestParam("findate") Date finDate,Model map){
		List<TransactionsBean> detailStatement = service.viewDetailStatement(accId,iniDate,finDate);
		map.addAttribute("detailList", detailStatement);
		return "home";
	}
	
	@RequestMapping("/adminViewTransactions")
	public String adminViewTransactions(@RequestParam("accId") long accId,Model map){
		List<TransactionsBean> adminStmt = service.adminViewTransactions(accId);
		map.addAttribute("adminList", adminStmt);
		return "";
	}
	
  
	@RequestMapping(value="/changePassword.htm",method=RequestMethod.POST)
	public String changePassword(@RequestParam("userName") String userName,@RequestParam("ft-oldpswd") String oldpassword,@RequestParam("ft-newpswd") String newpassword,Model model) throws BankingException {
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
