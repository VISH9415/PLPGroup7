package com.cg.project.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.cg.project.utils.RequestPage;

@Controller
public class BankController {
	@Autowired
	IBankingService service;

	/**
	 * Method Name: addUser Description: This method will navigate the user to
	 * signup page to get registered with the Bank Return Type: String
	 * 
	 * @param username
	 * @param pwd
	 * @param confirmpwd
	 * @param transpwd
	 * @param secques
	 * @param secans
	 * @param model
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String addUser(@RequestParam("usernamesignup") String username, @RequestParam("passwordsignup") String pwd,
			@RequestParam("passwordsignup_confirm") String confirmpwd,
			@RequestParam("transactionpassword") String transpwd, @RequestParam("secretquestion") String secques,
			@RequestParam("secretanswer") String secans, Model model) {
		try {
			UserBean userFetched = service.fetchUserById(username);
			if (userFetched == null) {
				UserBean user = new UserBean();
				user.setUserId(username);
				user.setLoginPassword(pwd);
				user.setTransactionPassword(transpwd);
				user.setAccountId(0L);
				user.setSecretQuestion(secques);
				user.setSecretAnswer(secans);
				user.setLockStatus(Constants.lockStatus);
				service.registerUser(user);
				UserBean userCheck = service.fetchUserById(username);
				if (userCheck != null) {
					model.addAttribute(Constants.message, Constants.SignUpDone); // return "index";
					model.addAttribute(Constants.page, Constants.returnToHome);
					return RequestPage.redirectToPage;
				} else {
					model.addAttribute(Constants.message, Constants.SignupFailed);
					model.addAttribute(Constants.page, Constants.returnToHome);
					return RequestPage.redirectToErrorPage;
				}
			} else {
				model.addAttribute(Constants.message, Constants.alreadySignedUp);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToErrorPage;
			}
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: existingUserLogin Description: This method will navigate the
	 * user to login page. Return Type: String
	 * 
	 * @param username
	 * @param password
	 * @param model
	 */
	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public String existingUserlogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpServletRequest request, Model model) {
		try {
			UserBean user = service.fetchUserById(username);
			if (user != null) {
				if (password.equals(user.getLoginPassword())) {
					long accountId = user.getAccountId();
					model.addAttribute(Constants.checkOpenAccount, accountId);
					HttpSession session = request.getSession();
					session.setAttribute(Constants.userName, username);
					return RequestPage.redirectToLoginPage;
				} else {
					model.addAttribute(Constants.message, Constants.incorrectPassword);
					model.addAttribute(Constants.page, Constants.returnToHome);
					return RequestPage.redirectToErrorPage;
				}
			} else {
				model.addAttribute(Constants.message, Constants.notSignedUp);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToErrorPage;
			}
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name:forgetPassword Description : The user is navigated to forget
	 * password page Return Type: String
	 * 
	 * @param username
	 * @param secques
	 * @param secans
	 * @param model
	 */
	@RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
	public String forgetpassword(@RequestParam("usernameForget") String userName,
			@RequestParam("secretquestion") String secques, @RequestParam("forget-passwordq") String secans,
			Model model) {
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
							model.addAttribute(Constants.message,
									Constants.newLoginPassword + newPassword + Constants.passwordForFutureLogin);
							model.addAttribute(Constants.page, Constants.returnToHome);
							return RequestPage.redirectToPage;
						} else {
							model.addAttribute("message", Constants.passwordUpdateFailure);
							model.addAttribute(Constants.page, Constants.returnToHome);
							return RequestPage.redirectToErrorPage;
						}
					} else {
						model.addAttribute(Constants.message, Constants.incorrectAnswer);
						model.addAttribute(Constants.page, Constants.returnToHome);
						return RequestPage.redirectToErrorPage;
					}
				} else {
					model.addAttribute(Constants.message, Constants.incorrectQuestion);
					model.addAttribute(Constants.page, Constants.returnToHome);
					return RequestPage.redirectToErrorPage;
				}

			} else {
				model.addAttribute(Constants.message, Constants.notSignedUp);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToErrorPage;
			}
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: validateAdmin Description: The bank administrator is navigated
	 * to login page. Return Type: String
	 * 
	 * @param adminId
	 * @param adminPass
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
	public String validateAdmin(@RequestParam("admin-username") String adminId,
			@RequestParam("admin-password") String adminPass, Model model) {
		try {
			int count = service.validateAdmin(adminId, adminPass);
			if (count != 0) {
				model.addAttribute(Constants.message, Constants.adminLogin);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToPage;
			}
			model.addAttribute(Constants.message, Constants.adminInvalidCredentials);
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: openAccount Description: This method will navigate the user to
	 * open account page Return Type: String
	 * 
	 * @param customername
	 * @param email
	 * @param address
	 * @param pancard
	 * @param accType
	 * @param userName
	 * @param model
	 */
	@RequestMapping(value = "/openAccount", method = RequestMethod.POST)
	public String openAccount(@RequestParam("openFormUserName") String customerName,
			@RequestParam("openFormUseremail") String email, @RequestParam("openFormUserAddress") String address,
			@RequestParam("openFormUserPan") String panCard, @RequestParam("openUserAccountType") String accountType,
			@RequestParam("userName") String userName, Model model) {
		try {
			long initialAccountId = Constants.initialAccountId;
			service.insertIntoCustomer(initialAccountId, customerName, email, address, panCard, accountType, userName);
			UserBean userFetched = service.fetchUserById(userName);
			long updatedAccountId = service.fetchAccountIdFromCustomer(userName);
			userFetched.setAccountId(updatedAccountId);
			service.updateAccountIdinUser(userFetched);
			service.insertIntoAccountMaster(updatedAccountId, accountType, Date.valueOf(LocalDate.now()),
					Constants.initBalance);
			model.addAttribute(Constants.message, Constants.accountOpen);
			model.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToPage;
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: changeRequest Description: This method will navigate user to
	 * change address page. Return Type: String
	 * 
	 * @param newAddress
	 * @param userName
	 * @param model
	 */
	@RequestMapping(value = "/changeAddress", method = RequestMethod.POST)
	public String changeRequest(@RequestParam("ft-newAddress") String newAddress,
			@RequestParam("userName") String userName, Model model) {
		try {
			long accountId = service.fetchAccountIdFromCustomer(userName);
			CustomerBean customer = service.fetchCustomerByAccountId(accountId);
			customer.setAddress(newAddress);
			service.updateCustomerAddress(customer);
			model.addAttribute(Constants.message, Constants.addressChanged);
			model.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToPage;
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * 
	 * @param accountId
	 * @param amount
	 * @param transactionDescription
	 * @return
	 */
	private TransactionsBean getTransactionRecordForPayer(long accountId, double amount,
			String transactionDescription) {
		TransactionsBean transactionByActId = new TransactionsBean();
		transactionByActId.setAccountNumber(accountId);
		LocalDate dt = LocalDate.now();
		Date dateOfTransactionForPayer = Date.valueOf(dt);
		transactionByActId.setDateOfTransaction(dateOfTransactionForPayer);
		transactionByActId.setTransactionAmount(amount);
		transactionByActId.setTransactionType(Constants.transactionTypeForPayer);
		transactionByActId.setTransDescription(transactionDescription);
		return transactionByActId;
	}

	/**
	 * 
	 * @param payeId
	 * @param amount
	 * @param transactionDescription
	 * @return
	 */
	private TransactionsBean getTransactionsRecordForPayee(long payeId, double amount, String transactionDescription) {
		TransactionsBean transactionByPayeeId = new TransactionsBean();
		transactionByPayeeId.setAccountNumber(payeId);
		LocalDate dt1 = LocalDate.now();
		Date dateOfTransactionForPayee = Date.valueOf(dt1);
		transactionByPayeeId.setDateOfTransaction(dateOfTransactionForPayee);
		transactionByPayeeId.setTransactionAmount(amount);
		transactionByPayeeId.setTransactionType(Constants.transactionTypeForPayee);
		transactionByPayeeId.setTransDescription(transactionDescription);
		return transactionByPayeeId;
	}

	/**
	 * 
	 * @param accountId
	 * @param payeeId
	 * @param nickName
	 * @return
	 */
	private PayeeBean getPayeeRecord(long accountId, long payeeId, String nickName) {
		PayeeBean payee = new PayeeBean();
		payee.setAccountId(accountId);
		payee.setPayeeAccountId(payeeId);
		payee.setNickName(nickName);
		return payee;
	}

	/**
	 * 
	 * @param accountId
	 * @param payeeId
	 * @param amount
	 * @return
	 */
	private FundTransferBean getFundTransferRecord(long accountId, long payeeId, double amount) {
		FundTransferBean fundTransfer = new FundTransferBean();
		fundTransfer.setAccountId(accountId);
		LocalDate dt1 = LocalDate.now();
		Date dateOfTransactionForPayee = Date.valueOf(dt1);
		fundTransfer.setDateOfTransfer(dateOfTransactionForPayee);
		fundTransfer.setPayeeAccountId(payeeId);
		fundTransfer.setTransferAmount(amount);
		return fundTransfer;
	}

	/**
	 * Method Name: fundTransfer Description: This method will navigate user to
	 * funds transfer page. Return Type: String
	 * 
	 * @param accId
	 * @param payeeId
	 * @param amount
	 * @param map
	 */
	@RequestMapping(value = "/fundTransfer.htm", method = RequestMethod.POST)
	public String fundTransfer(@RequestParam("ft-userid") long accId, @RequestParam("ft-payeeid") long payeeId,
			@RequestParam("ft-amount") double amount, @RequestParam("userName") String userName,
			@RequestParam("ft-describe") String transactionDescription, @RequestParam("ft-nickname") String nickName,
			Model map) {
		try {
			UserBean userByName = new UserBean();
			userByName = service.fetchUserById(userName);
			long payerId = userByName.getAccountId();
			if (payerId == accId) {
				CustomerBean customerByActId = new CustomerBean();
				customerByActId = service.fetchCustomerByAccountId(payeeId);
				if (customerByActId != null) {
					long payeId = customerByActId.getAccountId();
					if (payeId == payeeId) {
						AccountBean accountByActId = new AccountBean();
						accountByActId = service.fetchAccountByAccountId(accId);
						double balance = accountByActId.getAccountBalance();
						if (balance >= amount) {
							TransactionsBean transactionByActId = new TransactionsBean();
							transactionByActId = getTransactionRecordForPayer(accId, amount, transactionDescription);
							service.insertTransactionDetails(transactionByActId);
							TransactionsBean transactionByPayeeId = new TransactionsBean();
							transactionByPayeeId = getTransactionsRecordForPayee(payeId, amount,
									transactionDescription);
							service.insertTransactionDetails(transactionByPayeeId);
							PayeeBean payee = new PayeeBean();
							payee = getPayeeRecord(accId, payeeId, nickName);
							service.insertPayeeDetails(payee);
							FundTransferBean fundTransfer = new FundTransferBean();
							fundTransfer = getFundTransferRecord(accId, payeeId, amount);
							service.insertFundTransferDetails(fundTransfer);
							double updatedBalanceForPayer = balance - amount;
							accountByActId.setAccountBalance(updatedBalanceForPayer);
							service.updateBalance(accountByActId);
							AccountBean accountByPayeeId = new AccountBean();
							accountByPayeeId = service.fetchAccountByAccountId(payeeId);
							double updatedBalanceForPayee = accountByPayeeId.getAccountBalance() + amount;
							accountByPayeeId.setAccountBalance(updatedBalanceForPayee);
							service.updateBalanceForPayee(accountByPayeeId);
							map.addAttribute(Constants.message, Constants.transactionSuccessful);
							map.addAttribute(Constants.page, Constants.returnToLogin);
							return RequestPage.redirectToPage;
						} else {
							map.addAttribute(Constants.message, Constants.insufficientBalance);
							map.addAttribute(Constants.page, Constants.returnToLogin);
							return RequestPage.redirectToErrorPage;
						}
					} else {
						map.addAttribute(Constants.message, Constants.invalidPayeeId);
						map.addAttribute(Constants.page, Constants.returnToLogin);
						return RequestPage.redirectToErrorPage;
					}
				} else {
					map.addAttribute(Constants.message, Constants.PayeeNotRegistered);
					map.addAttribute(Constants.page, Constants.returnToLogin);
					return RequestPage.redirectToErrorPage;
				}
			} else {
				map.addAttribute(Constants.message, Constants.invalidPayerId);
				map.addAttribute(Constants.page, Constants.returnToLogin);
				return RequestPage.redirectToErrorPage;

			}
		} catch (BankingException be) {
			map.addAttribute(Constants.message, be.getMessage());
			map.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * 
	 * @param accountId
	 * @param userName
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/miniStatement.htm", method = RequestMethod.POST)
	public String viewMiniStatement(@RequestParam("miniAccountNumber") long accountId,
			@RequestParam("userName") String userName, Model map) {
		try {
			long accountIdForUser = service.fetchUserById(userName).getAccountId();
			if (accountIdForUser == accountId) {
				List<TransactionsBean> miniStatement = service.viewMiniStatement(accountId);
				map.addAttribute(Constants.miniStatement, miniStatement);
				map.addAttribute(Constants.selectTypeOfStatement, Constants.checkforMiniStatement);
				map.addAttribute(Constants.noOfTransactions, miniStatement.size());
				return RequestPage.redirectToListPage;
			}
			map.addAttribute(Constants.message, Constants.invalidAccountId);
			map.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToErrorPage;
		} catch (BankingException be) {
			map.addAttribute(Constants.message, be.getMessage());
			map.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * 
	 * @param accountId
	 * @param userName
	 * @param startDate
	 * @param endDate
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/detailedStatement.htm", method = RequestMethod.POST)
	public String viewDetailedStatement(@RequestParam("miniAccountNumber") long accountId,
			@RequestParam("userName") String userName, @RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate, Model map) {
		try {
			long accountIdForUser = service.fetchUserById(userName).getAccountId();
			if (accountIdForUser == accountId) {
				List<TransactionsBean> detailStatement = service.viewDetailStatement(accountId, startDate, endDate);
				map.addAttribute(Constants.noOfTransactions, detailStatement.size());
				map.addAttribute(Constants.detailedStatement, detailStatement);
				map.addAttribute(Constants.selectTypeOfStatement, Constants.checkforDetailStatement);
				return RequestPage.redirectToListPage;
			} 
				map.addAttribute(Constants.message, Constants.invalidAccountId);
				map.addAttribute(Constants.page, Constants.returnToLogin);
			    return RequestPage.redirectToErrorPage;
		} catch (BankingException be) {
			map.addAttribute(Constants.message, be.getMessage());
			map.addAttribute(Constants.page, Constants.returnToLogin);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: adminViewTransactions Description: This method will navigate the
	 * bank administrator to view transactions page Return Type: String
	 * 
	 * @param accId
	 * @param map
	 */
	@RequestMapping("/adminViewTransactions")
	public String adminViewTransactions(@RequestParam("accId") long accId, Model map) {
		try {
			List<TransactionsBean> adminStmt = service.adminViewTransactions(accId);
			map.addAttribute("adminList", adminStmt);
			return "Temp";
		} catch (BankingException be) {
			map.addAttribute("message", be.getMessage());
			return "Error";
		}
	}

	/**
	 * Method Name: changePassword Description: Method to change password for the
	 * particular user Return Type: String
	 * 
	 * @param userName
	 * @param oldpassword
	 * @param newpassword
	 * @param model
	 */
	@RequestMapping(value = "/changePassword.htm", method = RequestMethod.POST)
	public String changePassword(@RequestParam("userName") String userName,
			@RequestParam("ft-oldpswd") String oldpassword, @RequestParam("ft-newpswd") String newpassword,
			Model model) {
		try {
			UserBean user = service.fetchUserById(userName);
			String loginPass = user.getLoginPassword();
			if (loginPass.equals(oldpassword)) {
				user.setLoginPassword(newpassword);
				service.updateloginpassword(user);
				model.addAttribute(Constants.message, Constants.LoginPasswordChanged);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToPage;
			}
			model.addAttribute(Constants.message, Constants.incorrectOldPassword);
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

}
