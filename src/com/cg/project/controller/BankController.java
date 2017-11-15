package com.cg.project.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String addUser(@RequestParam("usernamesignup") String userName,
			@RequestParam("passwordsignup") String password,
			@RequestParam("transactionpassword") String transactionPassword,
			@RequestParam("secretquestion") String secretQuestion,
			@RequestParam("secretanswer") String secretAnswer, Model model) {
		try {
			UserBean userFetched = service.fetchUserById(userName);
			if (userFetched == null) {
				service.registerUser(Constants.initialAccountId, userName,
						password, transactionPassword, Constants.lockStatus,
						secretQuestion, secretAnswer);
				model.addAttribute(Constants.message, Constants.SignUpDone);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToPage;
			}
			model.addAttribute(Constants.message, Constants.alreadySignedUp);
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
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
	public String existingUserlogin(@RequestParam("username") String userName,
			@RequestParam("password") String password,
			HttpServletRequest request, Model model) {
		try {
			UserBean user = service.fetchUserById(userName);
			if (user != null) {
				if (password.equals(user.getLoginPassword())) {
					long accountId = user.getAccountId();
					model.addAttribute(Constants.checkOpenAccount, accountId);
					HttpSession session = request.getSession();
					session.setAttribute(Constants.userName, userName);
					return RequestPage.redirectToLoginPage;
				} else {
					model.addAttribute(Constants.message,
							Constants.incorrectPassword);
					model.addAttribute(Constants.page, Constants.returnToHome);
					return RequestPage.redirectToErrorPage;
				}
			}
			model.addAttribute(Constants.message, Constants.notSignedUp);
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
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
	public String forgetpassword(
			@RequestParam("usernameForget") String userName,
			@RequestParam("secretquestion") String secretQuestion,
			@RequestParam("forget-passwordq") String secretAnswer, Model model) {
		try {
			UserBean user = new UserBean();
			user = service.fetchUserById(userName);
			if (user != null) {
				if (secretQuestion.equals(user.getSecretQuestion())) {
					if (secretAnswer.equals(user.getSecretAnswer())) {
						String newPassword = service.randPassword();
						user.setLoginPassword(newPassword);
						service.updateloginpassword(user);
						model.addAttribute(Constants.message,
								Constants.newLoginPassword + newPassword
										+ Constants.passwordForFutureLogin);
						model.addAttribute(Constants.page,
								Constants.returnToHome);
						return RequestPage.redirectToPage;
					}
					model.addAttribute(Constants.message,
							Constants.incorrectAnswer);
					model.addAttribute(Constants.page, Constants.returnToHome);
					return RequestPage.redirectToErrorPage;
				}
				model.addAttribute(Constants.message,
						Constants.incorrectQuestion);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToErrorPage;

			}
			model.addAttribute(Constants.message, Constants.notSignedUp);
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: validateAdmin Description: The bank administrator is
	 * navigated to login page. Return Type: String
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
				model.addAttribute(Constants.selectAdminStatement,
						Constants.checkforAdminStatement);
				return RequestPage.redirectToPage;
			}
			model.addAttribute(Constants.message,
					Constants.adminInvalidCredentials);
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: openAccount Description: This method will navigate the user
	 * to open account page Return Type: String
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
	public String openAccount(
			@RequestParam("openFormUserName") String customerName,
			@RequestParam("openFormUseremail") String email,
			@RequestParam("openFormUserAddress") String address,
			@RequestParam("openFormUserPan") String panCard,
			@RequestParam("openUserAccountType") String accountType,
			@RequestParam("userName") String userName, Model model) {
		try {
			long initialAccountId = Constants.initialAccountId;
			service.insertIntoCustomer(initialAccountId, customerName, email,
					address, panCard, accountType, userName);
			UserBean userFetched = service.fetchUserById(userName);
			long updatedAccountId = service.fetchAccountIdFromCustomer(userName);
			userFetched.setAccountId(updatedAccountId);
			service.updateAccountIdinUser(userFetched);
			service.insertIntoAccountMaster(updatedAccountId, accountType,
					Date.valueOf(LocalDate.now()), Constants.initBalance);
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
	public String changeRequest(
			@RequestParam("ft-newAddress") String newAddress,
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
	 * This method fetches transaction record for the payer and is called in fundtransfer method
	 * @param accountId
	 * @param amount
	 * @param transactionDescription
	 * @return
	 */
	private TransactionsBean getTransactionRecordForPayer(long accountId,
			double amount, String transactionDescription) {
		TransactionsBean transactionByActId = new TransactionsBean(
				transactionDescription, Date.valueOf(LocalDate.now()),
				Constants.transactionTypeForPayer, amount, accountId);
		return transactionByActId;
	}

	/**
	 * This method fetches transaction record for the payee and is called in fundtransfer method
	 * @param payeId
	 * @param amount
	 * @param transactionDescription
	 * @return
	 */
	private TransactionsBean getTransactionsRecordForPayee(long payeId,
			double amount, String transactionDescription) {
		TransactionsBean transactionByPayeeId = new TransactionsBean(
				transactionDescription, Date.valueOf(LocalDate.now()),
				Constants.transactionTypeForPayee, amount, payeId);
		return transactionByPayeeId;
	}

	/**
	 * This method fetches paytee table record for the payee and is called in fundtransfer method
	 * @param accountId
	 * @param payeeId
	 * @param nickName
	 * @return
	 */
	private PayeeBean getPayeeRecord(long accountId, long payeeId,
			String nickName) {
		PayeeBean payee = new PayeeBean(accountId, payeeId, nickName);
		return payee;
	}

	/**
	 * This method fetches fund transfer record for the payee and is called in fundtransfer method 
	 * 
	 * @param accountId
	 * @param payeeId
	 * @param amount
	 * @return
	 */
	private FundTransferBean getFundTransferRecord(long accountId,
			long payeeId, double amount) {
		FundTransferBean fundTransfer = new FundTransferBean(accountId,
				payeeId, Date.valueOf(LocalDate.now()), amount);
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
	public String fundTransfer(@RequestParam("ft-userid") long accId,
			@RequestParam("ft-payeeid") long payeeId,
			@RequestParam("ft-amount") double amount,
			@RequestParam("userName") String userName,
			@RequestParam("ft-describe") String transactionDescription,
			@RequestParam("ft-nickname") String nickName, Model map) {
		try {
			UserBean userByName = new UserBean();
			userByName = service.fetchUserById(userName);
			long payerId = userByName.getAccountId();
			if (payerId == accId) {
				CustomerBean customerByActId = new CustomerBean();
				customerByActId = service.fetchCustomerByAccountId(payeeId);
				if (customerByActId != null) {
					long payeId = customerByActId.getAccountId();
					AccountBean accountByActId = new AccountBean();
					accountByActId = service.fetchAccountByAccountId(accId);
					double balance = accountByActId.getAccountBalance();
					if (balance >= amount) {
						TransactionsBean transactionByActId = getTransactionRecordForPayer(
								accId, amount, transactionDescription);
						service.insertTransactionDetails(transactionByActId);
						TransactionsBean transactionByPayeeId = getTransactionsRecordForPayee(
								payeId, amount, transactionDescription);
						service.insertTransactionDetails(transactionByPayeeId);
						PayeeBean payee = getPayeeRecord(accId, payeeId,
								nickName);
						service.insertPayeeDetails(payee);
						FundTransferBean fundTransfer = getFundTransferRecord(
								accId, payeeId, amount);
						service.insertFundTransferDetails(fundTransfer);
						double updatedBalanceForPayer = balance - amount;
						accountByActId
								.setAccountBalance(updatedBalanceForPayer);
						service.updateBalance(accountByActId);
						AccountBean accountByPayeeId = service
								.fetchAccountByAccountId(payeeId);
						double updatedBalanceForPayee = accountByPayeeId
								.getAccountBalance() + amount;
						accountByPayeeId
								.setAccountBalance(updatedBalanceForPayee);
						service.updateBalanceForPayee(accountByPayeeId);
						map.addAttribute(Constants.message,
								Constants.transactionSuccessful);
						map.addAttribute(Constants.page,
								Constants.returnToLogin);
						return RequestPage.redirectToPage;
					}
					map.addAttribute(Constants.message,
							Constants.insufficientBalance);
					map.addAttribute(Constants.page, Constants.returnToLogin);
					return RequestPage.redirectToErrorPage;
				}
				map.addAttribute(Constants.message,
						Constants.PayeeNotRegistered);
				map.addAttribute(Constants.page, Constants.returnToLogin);
				return RequestPage.redirectToErrorPage;
			}
			map.addAttribute(Constants.message, Constants.invalidPayerId);
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
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/miniStatement.htm", method = RequestMethod.POST)
	public String viewMiniStatement(
			@RequestParam("miniAccountNumber") long accountId,
			@RequestParam("userName") String userName, Model map) {
		try {
			long accountIdForUser = service.fetchUserById(userName)
					.getAccountId();
			if (accountIdForUser == accountId) {
				List<TransactionsBean> miniStatement = service.viewMiniStatement(accountId);
				map.addAttribute(Constants.miniStatement, miniStatement);
				map.addAttribute(Constants.selectTypeOfStatement,
						Constants.checkforMiniStatement);
				map.addAttribute(Constants.noOfTransactions,
						miniStatement.size());
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
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/detailedStatement.htm", method = RequestMethod.POST)
	public String viewDetailedStatement(
			@RequestParam("miniAccountNumber") long accountId,
			@RequestParam("userName") String userName,
			@RequestParam("startDate") Date startDate,
			@RequestParam("endDate") Date endDate, Model map) throws ParseException {
		try {
			long accountIdForUser = service.fetchUserById(userName)
					.getAccountId();
			if (accountIdForUser == accountId) {
				List<TransactionsBean> detailStatement = service.viewDetailStatement(accountId, startDate, endDate);
				map.addAttribute(Constants.noOfTransactions,
						detailStatement.size());
				map.addAttribute(Constants.detailedStatement, detailStatement);
				map.addAttribute(Constants.selectTypeOfStatement,
						Constants.checkforDetailStatement);
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
	 * Method Name: adminViewTransactions Description: This method will navigate
	 * the bank administrator to view transactions page Return Type: String
	 * 
	 * @param accId
	 * @param map
	 */
	@RequestMapping(value = "/adminViewTransactions.htm", method = RequestMethod.POST)
	public String adminViewTransactions(
			@RequestParam("accountId") long accountId, Model map) {
		try {
			List<TransactionsBean> adminStatement = service
					.adminViewTransactions(accountId);
			String userName = service.fetchCustomerByAccountId(accountId)
					.getUserId();
			map.addAttribute(Constants.userName, userName);
			map.addAttribute(Constants.transactionsForAdmin, adminStatement);
			map.addAttribute(Constants.selectAdminStatement,
					Constants.checkforAdminStatement);
			map.addAttribute(Constants.noOfTransactions, adminStatement.size());
			map.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToListPage;
		} catch (BankingException be) {
			map.addAttribute(Constants.message, be.getMessage());
			map.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

	/**
	 * Method Name: changePassword Description: Method to change password for
	 * the particular user Return Type: String
	 * 
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
			UserBean user = service.fetchUserById(userName);
			String loginPass = user.getLoginPassword();
			if (loginPass.equals(oldpassword)) {
				user.setLoginPassword(newpassword);
				service.updateloginpassword(user);
				model.addAttribute(Constants.message,
						Constants.LoginPasswordChanged);
				model.addAttribute(Constants.page, Constants.returnToHome);
				return RequestPage.redirectToPage;
			}
			model.addAttribute(Constants.message,
					Constants.incorrectOldPassword);
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		} catch (BankingException be) {
			model.addAttribute(Constants.message, be.getMessage());
			model.addAttribute(Constants.page, Constants.returnToHome);
			return RequestPage.redirectToErrorPage;
		}
	}

}
