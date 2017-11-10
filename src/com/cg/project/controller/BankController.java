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

	//check if autowiring for bean is required....
	UserBean userBean = new UserBean();
	AccountBean accountBean = new AccountBean();
	CustomerBean customerBean = new CustomerBean();

	@Autowired
	IBankingService service;

	/*
	 * @RequestMapping(value = "/user.htm") public String newUser(){ return
	 * "newuser"; }
	 */
	// user signup ...
     //***************************user signup *************************
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

	// user login
	//***************************user login*************************
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
	// admin login
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

	@RequestMapping("/Viewbalance")
	public String viewStatement() {
     return null;
	}

	@RequestMapping("/changeAddress")
	public String changeRequest() {
		return null;
	}


	@RequestMapping("/fundTransfer")
	public String fundTransfer() {
		return null;
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
