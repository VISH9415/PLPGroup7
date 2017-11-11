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
    @Autowired
	IBankingService service;

    @RequestMapping(value = "/user.htm")
	public String newUser(){
		return "newuser"; 
	}
	
	/*@RequestMapping(value = "/signup.htm",method = RequestMethod.POST)
	public String addUser(@RequestParam("username") String userName,@RequestParam("loginpwd") String pwd,
			@RequestParam("confirmloginpwd") String confirmPwd, @RequestParam("transpwd") String transPwd,@RequestParam("secretQuestion") String secQues,Model map) {
		String msg = null;
		userBean.setUserId(userName);
		userBean.setLoginPassword(confirmPwd);
		userBean.setTransactionPassword(transPwd);
=======
	CustomerBean customerBean = new CustomerBean();

	@Autowired
	IBankingService service;

	/*
	 * @RequestMapping(value = "/user.htm") public String newUser(){ return
	 * "newuser"; }
	 */
	// user signup ...
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
				model.addAttribute("message", msg); // return "index";
				redirect = "Temp";
			} else { // ask for forget password ....
				String msg = "Please select forget password!!";
				model.addAttribute("message", msg);
				redirect = "Error";
			}
		} else {
			String msg = "user isn't registered,please signup!!";
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

	// open account request
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
	}

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
			@RequestParam("customername") String customername,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("pancard") String pancard,
			@RequestParam("accountType") String accType,
			@RequestParam("username") String username,
			Model model) throws BankingException {
		
		String redirect = "";
		CustomerBean customer = new CustomerBean();
		customer.setAccountType(accType);
		customer.setAddress(address);
		customer.setCustomerName(customername);
		customer.setEmail(email);
		customer.setPancard(pancard);
		
		//insert record into customer table..
		customerBean = service.insertIntoCustomer(customer);
		//fetch customer by account id...if required
       	//CustomerBean customer = service.fetchFromCustomer()
		//username fetched from webpage...session attribute
		userBean = service.fetchUserById(username);
		//userBean.setAccountId(0);//while opening the account
		//update user...
		userBean.setAccountId(customerBean.getAccountId());
		service.updateAccountIdinUser(userBean);
		
		//fetch account from accountmaster
		// insert accountid in accountmaster...
		AccountBean account = new AccountBean();
		account.setAccountBalance(0);
		account.setAccountId(customer.getAccountId());
		account.setAccountType(customer.getAccountType());				
		LocalDate date = LocalDate.now();
		Date opendate = Date.valueOf(date);
		account.setOpenDate(opendate);

		service.insertIntoAccountMaster(accountBean);
        
		String msg = "Account is opened !!";
		model.addAttribute("message", msg);
		redirect = "Error";
		
		return redirect;
	}

	@RequestMapping("/balance")
	public String viewStatement()
	{
		return null;
	}
/*	
//	By Vaibhav new functions
	//***************************change request*************************
	
	*//**
	 * @return
	 *//*
	@RequestMapping("/changeaddress.htm")
	public String changeRequest() {
		return "changeaddress";
	}
	//***************************validate user*************************
	
	*//**Method to validate customer
	 * @param accId
	 * @param map
	 * @return
	 *//*
	@RequestMapping(value = "/validateid.htm",method = RequestMethod.POST)
	public String viewDetails(@RequestParam("accId") long accId,Model map)
	{
		customerBean = service.viewCustomer(accId);
		String redirect,msg = null;
		if(customerBean==null){
			msg = "Customer Not Found";
			map.addAttribute("message", msg);
			redirect = "Error";
		}
		else{
			map.addAttribute("customer", customerBean);
			redirect = "change";
		}
		return redirect;
	}*/
	
	/**
	 * @param custName
	 * @param address
	 * @param accId
	 * @param email
	 * @param accType
	 * @param panCard
	 * @param map
	 * @return
	 */
	/*@RequestMapping(value = "/changeAddress.htm", method = RequestMethod.POST)
	public String changeAddress(@RequestParam("custName") String custName,@RequestParam("address") String address,
			@RequestParam("accountId") long accId, @RequestParam("email") String email,
			@RequestParam("accType") String accType, @RequestParam("panCard") String panCard,Model map){
		customerBean.setAccountId(accId);
		customerBean.setAccountType(accType);
		customerBean.setAddress(address);
		customerBean.setCustomerName(custName);
		customerBean.setPancard(panCard);
		customerBean.setEmail(email);
		service.changeAddress(customerBean);
		String msg = "Your Details have been successfully updated";
		map.addAttribute("message", msg);
		return "changeaddress";
		
	}*/

	/**
	 * @return
	 */
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
	 */
	@RequestMapping(value = "/validatebothid.htm", method = RequestMethod.POST)
	public String changePassword(@RequestParam("accId") long accId, @RequestParam("payeeId") long payeeId,
			@RequestParam("amount") double amount,Model map)
	{
		//@RequestParam("userId") String userId,
		String redirect = null;
		String msg = null;
		double balance = 0;
		/*UserBean userBean1 = service.getUserName(accId);
		String uId = userBean1.getUserId();*/
		//System.out.println("User Id"+uId);
		userBean = service.validateUser("a");
		long payerId = userBean.getAccountId();
		customerBean = service.viewCustomer(payeeId);
		long payeId = customerBean.getAccountId();
		account = service.viewAccount(accId);
		balance = account.getAccountBalance();
		if(payerId==accId&&payeId==payeeId&&balance>amount){
			msg = "Transaction Successful";
			redirect = "transfersuccess";
			map.addAttribute("message", msg);
		}
		else{
			redirect = "Error";
			msg = "Insufficient Balance";
		}
		return redirect;
	}

	@RequestMapping("/balance.htm")
	public String viewBalance(){
		return "balance";
	}
	@RequestMapping("/ministatement.htm")
	public String miniStatement(){
		return "ministatement";
	}
	
	@RequestMapping(value = "/miniStmt.htm", method = RequestMethod.POST)
	public String viewMiniStatement(@RequestParam("accId") long accId,Model map){
		List<TransactionsBean> miniStatement = service.vewMiniStatement(accId);
		map.addAttribute("miniList", miniStatement);
		return "ministatement";
	}
	
	@RequestMapping("/detailedstatement.htm")
	public String detailedStatement(){
		return "detailedstatement";
	}
	
	@RequestMapping(value = "/detailStmt.htm", method = RequestMethod.POST)
	public String viewDetailStatement(@RequestParam("accId") long accId,
			@RequestParam("inidate") Date iniDate,@RequestParam("findate") Date finDate,Model map){
		List<TransactionsBean> detailStatement = service.viewDetailStatement(accId,iniDate,finDate);
		map.addAttribute("detailList", detailStatement);
		return "detailedstatement";
	}
	
	@RequestMapping("/adminViewTransactions")
	public String adminViewTransactions(@RequestParam("accId") long accId,Model map){
		List<TransactionsBean> adminStmt = service.adminViewTransactions(accId);
		map.addAttribute("adminList", adminStmt);
		return "";
	}
	
  
	@RequestMapping("/changepass")
	public String changePassword(@RequestParam("username") String uid,@RequestParam("newpassword") String newpassword,@RequestParam("renewpassword") String renewpassword,Model model) throws BankingException {
		{
	   	//fetch user by id...
			//username shall come in almost every method from page..
			UserBean user = service.fetchUserById(uid);
			String redirect ="";
			if(user!=null)
			{ //reenter new password need to matched in the form only..
				if(renewpassword.equals(newpassword)){
			     user.setLoginPassword(newpassword);
			     String msg = "Login Password is changed !!";
				 model.addAttribute("message", msg);
			     redirect = "Temp";
			         }
				else
				{
					String msg = "password not matching !!";
					model.addAttribute("message", msg);
					redirect = "Error";
				}
			}
			else
			{
				String msg = "user doesn't exists";
				model.addAttribute("message", msg);
				redirect = "Error";
			}
		
		return redirect;
	}
	}
	
	
}
