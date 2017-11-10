package com.cg.project.controller;

import java.sql.Date;
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
import com.cg.project.service.IBankingService;

@Controller
public class BankController {

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
		userBean.setAccountId(0L);
		userBean.setSecretQuestion(secQues);
		userBean.setLockStatus("nope");
		
		service.registerUser(userBean);
		
		msg = "You are successfully registered with our bank\nThank you";
		map.addAttribute("message", msg);
		
		return "Temp";
	}
*/
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
		}
		else
		{
		     String message = "wrong password..";
				model.addAttribute("message", message);
				return "Error";
		}
			
	}
	
	@RequestMapping("/openAccountRequest")
	public String sendRequestToAdmin(@RequestParam("userName") String uid,Model model)
	{
		//openAccount call //request sent to admin...
		//model.addAttribute("choice", choice);
		model.addAttribute("username", uid);
	return "Admin";
	}
	
	@RequestMapping("/adminValidate")
	public String validateAdmin(@RequestParam("adminId") String adminId,@RequestParam("adminPassword") String adminPassword,Model model)
	{
		int count =service.validateAdmin(adminId,adminPassword);
		if(count!=0)
		{
			return "AccountOpen";
		}
		else
		{
			return "Error";
		}
		
	}
	
	@RequestMapping("/openAccount")
	public String openAccount(@ModelAttribute("customer") CustomerBean customer,@RequestParam("username") String username ,Model model)
	{
		//insert accountid in accountmaster...
        //update into user...
		customerBean = service.insertIntoCustomer(customer);
		//fetch account id from customerbean..
		//userBean.set
		UserBean user =service.updateAccountIdinUser(customerBean.getAccountId());
		//fetch updated account id from usertable ...
		model.addAttribute("user",user);
		
		
		//service.updateUser();
		//model.addAttribute("accountId",accountBean.getAccountId());
		//model.addAttribute("balance", accountBean.getAccountBalance());
	//	model.addAttribute("count",count);        
		return "Success" ;
	}
	*/
	@RequestMapping("/balance")
	public String viewStatement()
	{
		return null;

	}
	
//	By Vaibhav new functions
	@RequestMapping("/changeaddress.htm")
	public String changeRequest()
	{
		return "changeaddress";
	}
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
	}
	
	@RequestMapping(value = "/changeAddress.htm", method = RequestMethod.POST)
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
		
	}

	@RequestMapping(value = "/fundTransfer.htm")
	public String fundTransfer()
	{
		
		return "fundTransfer";

	}
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

	@RequestMapping("/chequeRequest")
	public String chequeRequest()
	{
		return null;

	}
	
	@RequestMapping("/trackService")
	public String trackService()
	{
		return null;

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
	
}