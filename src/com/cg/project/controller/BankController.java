package com.cg.project.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.UserBean;
import com.cg.project.exception.BankingException;
import com.cg.project.service.IBankingService;

@Controller
public class BankController {

	UserBean userBean = new UserBean();
	AccountBean accountBean = new AccountBean();
	CustomerBean customerBean = new CustomerBean();

	@Autowired
	IBankingService service;

	@RequestMapping(value = "/user.htm")
	public String newUser(){
		return "newuser"; 
	}

	@RequestMapping(value = "/signup.htm",method = RequestMethod.POST)
	public String addUser(@RequestParam("username") String userName,@RequestParam("loginpwd") String pwd,
			@RequestParam("confirmloginpwd") String confirmPwd, @RequestParam("transpwd") String transPwd,@RequestParam("secretQuestion") String secQues,Model model) throws BankingException {
		String msg = null;
		userBean.setUserId(userName);
		//validate login pass
		//validate trans pass
		userBean.setLoginPassword(pwd);
		userBean.setTransactionPassword(transPwd);
		userBean.setAccountId(0L);
		userBean.setSecretQuestion(secQues);
		userBean.setLockStatus("nope");
		//FETCH USER BY USERID..
		UserBean user = service.fetchUserById(userName);
		if(user.getUserId().equals(null))
		{
			service.registerUser(userBean);
			msg = "You are successfully registered with our bank\nThank you";
			model.addAttribute("message", msg);
			return "Temp";
		}
		else{
			msg = "Already registered..";
			model.addAttribute("message", msg);
			return "Error";
		}
	}

	@RequestMapping(value = "/login.htm")
	public String existingUser(){
		return "Login";
	}
	//xml of vaibhav type...
	//user inserted during signup....
	@RequestMapping("/loginMethod.htm") 
	public String existingUserlogin(@RequestParam("userName") String uid,@RequestParam("loginPassword") String password,Model model) throws BankingException
	{
		userBean.setUserId(uid);
		// validating user...
		UserBean user= service.fetchUserById(userBean.getUserId());

		//ifuser!=null
		if(password.equals(user.getLoginPassword()))
		{
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

	@RequestMapping("/openAccountRequest.htm")
	public String sendRequestToAdmin(@RequestParam("userName") String uid,Model model)
	{
		//openAccount call //request sent to admin...
		//model.addAttribute("choice", choice);
		model.addAttribute("username", uid);
		return "Admin";
	}

	@RequestMapping("/adminValidate.htm")
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

	@RequestMapping("/openAccount.htm")
	public String openAccount(@ModelAttribute("customer") CustomerBean customer,@RequestParam("username") String username ,Model model)
	{
		//insert accountid in accountmaster...
		accountBean.setAccountBalance(0);
		accountBean.setAccountId(customer.getAccountId());
		accountBean.setAccountType(customer.getAccountType());
		LocalDate date = LocalDate.now();
		Date opendate = Date.valueOf(date);
		accountBean.setOpenDate(opendate);

		service.insertIntoAccountMaster(accountBean);
		//update into user...
		customerBean = service.insertIntoCustomer(customer);
		//fetch account id from customerbean..
		//userBean.set
		UserBean user =service.updateAccountIdinUser(customerBean.getAccountId());
		//fetch updated account id from usertable ...
		//AccountBean account = service.fetchAccounts()
		model.addAttribute("user",user);
		model.addAttribute("customer",customer);

		//service.updateUser();
		//model.addAttribute("accountId",accountBean.getAccountId());
		//model.addAttribute("balance", accountBean.getAccountBalance());
		//	model.addAttribute("count",count);        
		return "Success" ;
	}

	@RequestMapping("/balance")
	public String viewStatement()
	{
		return null;

	}
	@RequestMapping("/changeRequest")
	public String changeRequest()
	{
		return null;

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
	@RequestMapping("/fundTransfer")
	public String fundTransfer()
	{
		return null;

	}
	@RequestMapping("/changePass")
	public String changePassword()
	{
		return null;

	}

}
