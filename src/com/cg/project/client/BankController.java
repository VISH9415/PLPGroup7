package com.cg.project.client;

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

    @RequestMapping(value = "/newuser.htm")
	public String newUser(){
		return "newuser"; 
	}
	
	@RequestMapping(value = "/adduser.htm",method = RequestMethod.POST)
	public String addUser(@RequestParam("username") String userName,@RequestParam("loginpwd") String pwd,
			@RequestParam("confirmloginpwd") String confirmPwd, @RequestParam("transpwd") String transPwd,Model map) {
		String msg = null;
		user.setUserId(userName);
		user.setLoginPassword(confirmPwd);
		user.setTransactionPassword(transPwd);
		user.setAccountId(0L);
		user.setLockStatus("nope");
		service.registerUser(user);
		msg = "You are successfully registered with our bank\nThank you";
		map.addAttribute("message", msg);
		return "success";
	}

	@RequestMapping(value = "/login.htm")
	public String existingUser(){
		return "login";
	}
	//xml of vaibhav type...
	//user inserted during signup....
	@RequestMapping("/loginMethod") 
	public String existingUserlogin(@RequestParam("userName") String uid,@RequestParam("loginPassword") String password,Model model) throws BankingException
	{
		userBean.setUserId(uid);
		UserBean user= service.fetchUserById(userBean.getUserId());
		//password also got validated...
		if(password.equals(user.getLoginPassword()))
		{
			model.addAttribute("user",user); //count is no of records..
			return "Success";
		}
		else
		{
		     String message = "wrong password..";
				model.addAttribute("message", message);
				return "error";
		}
			
	}
	
	@RequestMapping("/openAccountRequest")
	public String sendRequestToAdmin(@ModelAttribute("user") UserBean user,Model model)
	{
		//openAccount call //request sent to admin...
		//model.addAttribute("choice", choice);
		model.addAttribute("username", user.getUserId());
	return "Admin";
	}
	
	@RequestMapping("/adminValidate")
	public String validateAdmin(@RequestParam("adminId") String adminId,@RequestParam("adminPassword") String adminPassword,Model model)
	{
		int count =service.validateAdmin(adminId,adminPassword);
		if(count==1)
		{
			return "AccountOpen";
		}
		else
		{
			return "error";
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
