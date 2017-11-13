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

	@Autowired
	IBankingService service;

	/**
	 * Method Name: addUser
	 * Description: This method will navigate the user to signup page to get registered with the Bank 
	 * Return Type: String
	 * @param username
	 * @param pwd
	 * @param confirmpwd
	 * @param transpwd
	 * @param secques
	 * @param secans
	 * @param model
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String addUser(@RequestParam("usernamesignup") String username,
			@RequestParam("passwordsignup") String pwd,
			@RequestParam("passwordsignup_confirm") String confirmpwd,
			@RequestParam("transactionpassword") String transpwd,
			@RequestParam("secretquestion") String secques,
			@RequestParam("secretanswer") String secans, Model model) {

		System.out.println("in signup");

		try {
			UserBean userFetched = service.fetchUserById(username);

			System.out.println("user fetched..." + userFetched);

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
				// fetch user to validate if inserted on not..
				UserBean userCheck = service.fetchUserById(username);
				System.out.println("userCheck " + userCheck);
				if (userCheck != null) {
					String msg = "signup done!";
					System.out.println(msg);
					model.addAttribute("message", msg); // return "index";
					return "Temp";
				} else {
					String msg = "Signup failed!!";
					System.out.println(msg);
					model.addAttribute("message", msg);
					return "Error";
				}
			} else {
				String msg = "User already registered";
				System.out.println(msg);
				model.addAttribute("message", msg);
				return "Error";
			}
		} catch (BankingException be) {
			model.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name: existingUserLogin
	 * Description: This method will navigate the user to login page.
	 * Return Type: String
	 * @param username
	 * @param password
	 * @param model 
	 */
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String existingUserlogin(@RequestParam("username") String username,
			@RequestParam("password") String password, Model model) {
		try {
			UserBean user = service.fetchUserById(username);
			System.out.println("for login user fetched: " + user);

			if (user != null) {
				System.out.println("exists");
				if (password.equals(user.getLoginPassword())) {
					String msg = "correctPassword!!";
					// set username as session object and send to home page...
					System.out.println(msg);
					model.addAttribute("message", msg); // return "index";
					model.addAttribute("userName", username);
					model.addAttribute("user", user);
					return "home";
				} else { 
					String msg = "Password incorrect, try again !!";
					System.out.println(msg);
					model.addAttribute("message", msg);
					return "Error";
				}
			} else {
				String msg = "user isn't registered,please signup!!";
				System.out.println(msg);
				model.addAttribute("message", msg);
				return "Error";
			}
		} catch (BankingException be) {
			model.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name:forgetPassword
	 * Description : The user is navigated to forget password page
	 * Return Type: String 
	 * @param username
	 * @param secques
	 * @param secans
	 * @param model
	 */
	@RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
	public String forgetpassword(
			@RequestParam("usernameForget") String userName,
			@RequestParam("secretquestion") String secques,
			@RequestParam("forget-passwordq") String secans, Model model) {
		System.out.println("in forget pass");
		try {
			UserBean user = new UserBean();
			user = service.fetchUserById(userName);
			if (user != null) {
				if (secques.equals(user.getSecretQuestion())) {
					if (secans.equals(user.getSecretAnswer())) {
						String newPassword = service.randPassword();
						user.setLoginPassword(newPassword);
						service.updateloginpassword(user);
						UserBean userUpdated = service.fetchUserById(userName);
						if (userUpdated.getLoginPassword().equals(newPassword)) {
							String msg = "Your new login password is: "
									+ newPassword + "";
							model.addAttribute("message", msg);
							return "Temp";
						} else {
							String msg = "password isn't updated ";
							model.addAttribute("message", msg);
							return "Error";
						}
					} else {
						String msg = "incorrect secret answer ";
						model.addAttribute("message", msg);
						return "Error";
					}
				} else {
					String msg = "incorrect secret question chosen ";
					model.addAttribute("message", msg);
					return "Error";
				}

			} else {
				String msg = "user not registered... ";
				model.addAttribute("message", msg);
				return "Error";
			}
		} catch (BankingException be) {
			model.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name: validateAdmin
	 * Description: The bank administrator is navigated to login page.
	 * Return Type: String 
	 * @param adminId
	 * @param adminPass
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public String validateAdmin(@RequestParam("admin-username") String adminId,
			@RequestParam("admin-password") String adminPass, Model model) {
		System.out.println("in admin login method");
		// String redirect = "";
		try {
			int count = service.validateAdmin(adminId, adminPass);
			if (count != 0) {
				String msg = "Admin Login Successful";
				model.addAttribute("message", msg);
				// redirect ="Temp";
				return "Temp";
			} else {
				// redirect = "Error";
				String msg = "Entered Invalid login credentials!";
				model.addAttribute("message", msg);
				return "Error";
			}
		} catch (BankingException be) {
			model.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name: openAccount
	 * Description: This method will navigate the user to open account page
	 * Return Type: String
	 * @param customername
	 * @param email
	 * @param address
	 * @param pancard
	 * @param accType
	 * @param userName
	 * @param model
	 */
	@RequestMapping(value = "/openAccount", method = RequestMethod.POST)
	public String openAccount(
			@RequestParam("openFormUserName") String customername,
			@RequestParam("openFormUseremail") String email,
			@RequestParam("openFormUserAddress") String address,
			@RequestParam("openFormUserPan") String pancard,
			@RequestParam("openUserAccountType") String accType,
			@RequestParam("userName") String userName, Model model) {

		System.out.println("in open account..." + userName);
		try {
			CustomerBean customer = new CustomerBean();

			customer.setAccountType(accType);
			customer.setAddress(address);
			customer.setCustomerName(customername);
			customer.setEmail(email);
			customer.setPancard(pancard);
			customer.setUserId(userName);

			service.insertIntoCustomer(customer);

			UserBean userFetched = service.fetchUserById(userName);


			System.out.println("user fetched: " + userFetched);
			long accountId = service.fetchAccountIdFromCustomer(userName);
			System.out.println("hi");
			userFetched.setAccountId(accountId);

			UserBean user = service.updateAccountIdinUser(userFetched);

			System.out.println("updated actid: " + user.getAccountId());

			AccountBean account = new AccountBean();

			account.setAccountBalance(Constants.initBalance);
			account.setAccountId(accountId);
			account.setAccountType(customer.getAccountType());
			LocalDate date = LocalDate.now();
			Date opendate = Date.valueOf(date);
			account.setOpenDate(opendate);

			service.insertIntoAccountMaster(account);
			// fetch from account master..
			AccountBean accountFetched = service
					.fetchAccountByAccountId(accountId);
			System.out.println("act fetched: " + accountFetched);

			if (accountFetched != null) {
				String msg = "Account is opened with balance Rs.10K only !!";
				model.addAttribute("message", msg);
				model.addAttribute("userName", userName);
				return "TempHome";
			} else {
				String msg = "Account isn't inserted !!";
				model.addAttribute("message", msg);
				return "ErrorHome";
			}
		} catch (BankingException be) {
			model.addAttribute("message", be.getMessage());
			return "ErrorHome";
		}
	}

	@RequestMapping("/Viewbalance")
	public String viewStatement() {
		return null;
	}
 
	/**
	 * Method Name: changeRequest
	 * Description: This method will navigate user to change address page.
	 * Return Type: String
	 * @param newAddress
	 * @param userName
	 * @param model
	 */
	@RequestMapping(value = "/changeAddress", method = RequestMethod.POST)
	public String changeRequest(
			@RequestParam("ft-newAddress") String newAddress,
			@RequestParam("userName") String userName, Model model) {
		try {
			long accountId = service.fetchAccountIdFromCustomer(userName);
			CustomerBean customer = service.fetchCustomerByAccountId(accountId);
			customer.setAddress(newAddress);

			service.updateCustomerAddress(customer);
			model.addAttribute("userName", userName);
			return "TempHome";
		} catch (BankingException be) {
			model.addAttribute("message", be.getMessage());
			return "ErrorHome";
		}
	}

	/**
	 * Method Name: fundTransfer
	 * Description: This method will navigate user to funds transfer page.
	 * Return Type: String
	 * @param accId
	 * @param payeeId
	 * @param amount
	 * @param map
	 */
	@RequestMapping(value = "/fundTransfer.htm", method = RequestMethod.POST)
	public String fundTransfer(@RequestParam("ft-userid") long accId,
			@RequestParam("ft-payeeid") long payeeId,
			@RequestParam("ft-amount") double amount,
			@RequestParam("userName") String userName,
			@RequestParam("ft-describe") String transactionDescription,
			@RequestParam("ft-nickname") String nickName, Model map) {
		try {
			double balance = 0;

			UserBean userByName = new UserBean();
			userByName = service.fetchUserById(userName);

			System.out.println("pay to " + payeeId);
			System.out.println("user is " + userByName);

			long payerId = userByName.getAccountId();

			System.out.println("pay by " + payerId);

			if (payerId == accId) {

				CustomerBean customerByActId = new CustomerBean();
				customerByActId = service.fetchCustomerByAccountId(payeeId);
				long payeId = customerByActId.getAccountId();

				System.out.println("payeId :" + payeId);

				if (payeId == payeeId) {

					System.out.println("in if payid is actid");

					AccountBean accountByActId = new AccountBean();
					accountByActId = service.fetchAccountByAccountId(accId);

					System.out.println("accountByActId" + accountByActId);

					balance = accountByActId.getAccountBalance();

					System.out.println("balance: " + balance);

					if (balance >= amount) {
						System.out.println("in validaton of balance");

						TransactionsBean transactionByActId = new TransactionsBean();
						transactionByActId.setAccountNumber(accId);

						LocalDate dt = LocalDate.now();
						Date dateOfTransactionForPayer = Date.valueOf(dt);

						transactionByActId.setDateOfTransaction(dateOfTransactionForPayer);
						transactionByActId.setTransactionAmount(amount);
						transactionByActId.setTransactionType(service.fetchCustomerByAccountId(accId).getAccountType()); 
						transactionByActId.setTransDescription(transactionDescription);

						service.insertTransactionDetails(transactionByActId);

						System.out.println("Inserting data into transactions of payer");

						// insert transactions for payee...
						TransactionsBean transactionByPayeeId = new TransactionsBean();
						transactionByPayeeId.setAccountNumber(payeId);

						LocalDate dt1 = LocalDate.now();
						Date dateOfTransactionForPayee = Date.valueOf(dt1);

						transactionByPayeeId.setDateOfTransaction(dateOfTransactionForPayee);
						transactionByPayeeId.setTransactionAmount(amount);
						transactionByPayeeId.setTransactionType("savings"); 
						transactionByPayeeId.setTransDescription(transactionDescription);

						service.insertTransactionDetails(transactionByPayeeId);
						System.out.println("isnerting data into transaction of payee");

						PayeeBean payee = new PayeeBean();
						payee.setAccountId(accId);
						payee.setPayeeAccountId(payeeId);
						payee.setNickName(nickName);

						System.out.println("before inserting into payee");
						service.insertPayeeDetails(payee);
						System.out.println("inserting into payee");

						FundTransferBean fundTransfer = new FundTransferBean();
						fundTransfer.setAccountId(accId);
						fundTransfer
								.setDateOfTransfer(dateOfTransactionForPayee);
						fundTransfer.setPayeeAccountId(payeeId);
						fundTransfer.setTransferAmount(amount);

						System.out.println("before fund insertion");
						service.insertFundTransferDetails(fundTransfer);
						System.out.println("after fund insertion");

						double updatedBalanceForPayer = balance - amount;
						accountByActId.setAccountBalance(updatedBalanceForPayer);
						System.out.println("before updation of balance of payer");
						service.updateBalance(accountByActId);
						System.out.println("after updation of balance of payer");

						AccountBean accountByPayeeId = new AccountBean();
						accountByPayeeId = service.fetchAccountByAccountId(payeeId);
						double updatedBalanceForPayee = accountByPayeeId.getAccountBalance() + amount;
						accountByPayeeId.setAccountBalance(updatedBalanceForPayee);

						System.out.println("before updation of balance of payee");
						service.updateBalanceForPayee(accountByPayeeId);
						System.out.println("after updation of balance of payee");

						String msg = "Transaction Successful";
						map.addAttribute("message", msg);
						return "Temp";
					} else {
						String msg = "Insufficient Balance";
						map.addAttribute("message", msg);
						return "Error";
					}
				} else {
					String msg = "Invalid payee Id";
					map.addAttribute("message", msg);
					return "Error";
				}
			} else {
				String msg = "Invalid payer Id";
				map.addAttribute("message", msg);
				return "Error";

			}
		} catch (BankingException be) {
			map.addAttribute("message", be.getMessage());
			return "ErrorHome";
		}
	}

	@RequestMapping("/balance.htm")
	public String viewBalance() {
		return "balance";
	}

	@RequestMapping("/home.htm")
	public String miniStatement() {
		return "ministatement";
	}

	@RequestMapping(value = "/miniStatement.htm", method = RequestMethod.POST)
	public String viewMiniStatement(@RequestParam("accId") long accId,
			@RequestParam("userName") String userName, Model map) {
		try {
			long actIdForUser = service.fetchUserById(userName).getAccountId();
			if (actIdForUser == accId) {
				List<TransactionsBean> miniStatement = service
						.viewMiniStatement(accId);
				map.addAttribute("miniList", miniStatement);
				return "ViewList";
			} else {
				String msg = "wrong account Id..";
				map.addAttribute("message", msg);
				return "Error";
			}
		} catch (BankingException be) {
			map.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name: viewDetailedStatement
	 * Description: This method will navigate user to detailed Statement section of view balance page.
	 * Return Type: String
	 * @param accId
	 * @param userName
	 * @param iniDate
	 * @param finDate
	 * @param map
	 */
	@RequestMapping(value = "/detailedStatement.htm", method = RequestMethod.POST)
	public String viewDetailedStatement(@RequestParam("accId") long accId,
			@RequestParam("userName") String userName,
			@RequestParam("inidate") Date iniDate,
			@RequestParam("findate") Date finDate, Model map) {
		try {
			long actIdForUser = service.fetchUserById(userName).getAccountId();
			if (actIdForUser == accId) {
				List<TransactionsBean> detailStatement = service
						.viewDetailStatement(accId, iniDate, finDate);
				map.addAttribute("detailList", detailStatement);
				return "ViewList";
			} else {
				String msg = "Incorrect account Id";
				map.addAttribute("message", msg);
				return "Error";
			}
		} catch (BankingException be) {
			map.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name: adminViewTransactions
	 * Description: This method will navigate the bank administrator to view transactions page
	 * Return Type: String
	 * @param accId
	 * @param map
	 */
	@RequestMapping("/adminViewTransactions")
	public String adminViewTransactions(@RequestParam("accId") long accId,
			Model map) {
		try {
			List<TransactionsBean> adminStmt = service
					.adminViewTransactions(accId);
			map.addAttribute("adminList", adminStmt);
			return "Temp";
		} catch (BankingException be) {
			map.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name: changePassword
	 * Description: Method to change password for the particular user
	 * Return Type: String 
	 * @param userName
	 * @param oldpassword
	 * @param newpassword
	 * @param model
	 */
	@RequestMapping(value = "/changePassword.htm", method = RequestMethod.POST)
	public String changePassword(@RequestParam("userName") String userName,
			@RequestParam("ft-oldpswd") String oldpassword,
			@RequestParam("ft-newpswd") String newpassword, Model model) {
		try {
			UserBean user = new UserBean();
			user = service.fetchUserById(userName);
			System.out.println("in change password");
			String loginPass = user.getLoginPassword();

				if (loginPass.equals(oldpassword)) {
					user.setLoginPassword(newpassword);
					service.updateloginpassword(user);

					String msg = "Login Password is changed !!";
					model.addAttribute("message", msg);
					return "Temp";
				} else {
					String msg = "incorrect oldpassword !!";
					model.addAttribute("message", msg);
					return "Error";
				}
		} catch (BankingException be) {
			model.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

}
