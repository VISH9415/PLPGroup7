package com.cg.project.service;

import java.sql.Date;
import java.util.List;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.FundTransferBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.exception.BankingException;

public interface IBankingService
{

	UserBean fetchUserById(String userName) throws BankingException;

	int validateAdmin(String adminId, String adminPassword) throws BankingException;

	void updateUserDetails(String uid, long actId) throws BankingException;

	List<TransactionsBean> viewDetailStatement(long accId, Date initDate, Date finDate) throws BankingException;

	List<TransactionsBean> adminViewTransactions(long accId) throws BankingException;


	void updateCustomerAddress(CustomerBean customer) throws BankingException;

	CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException;

	long fetchAccountIdFromCustomer(String userName) throws BankingException;

	AccountBean fetchAccountByAccountId(long accountId) throws BankingException;

	List<TransactionsBean> viewMiniStatement(long accId) throws BankingException;

	PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException;

	void insertFundTransferDetails(FundTransferBean fundTransfer) throws BankingException;

	AccountBean updateBalance(AccountBean account) throws BankingException;

	AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException;

	String randPassword();

	void updateloginpassword(UserBean userBean) throws BankingException;

	UserBean updateAccountIdinUser(UserBean userBean) throws BankingException;

	TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException;

	CustomerBean fetchCustomerByUserId(String username) throws BankingException;

	List<TransactionsBean> fetchTransactionsByAccountId(long accountId) throws BankingException;

	CustomerBean test(long initialaccountid, String customerName, String email, String address, String panCard,
			String accountType, String userName);

	CustomerBean insertIntoCustomer(long initialAccountId, String customerName, String email, String address,
			String panCard, String accountType, String userName) throws BankingException;

	AccountBean insertIntoAccountMaster(long updatedAccountId, String accountType, Date opendate, double initialBalance)
			throws BankingException;

	UserBean registerUser(long initialaccountid, String userName, String password,
			String transactionPassword, String lockstatus,
			String secretQuestion, String secretAnswer) throws BankingException;


}
