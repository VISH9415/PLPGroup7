package com.cg.project.dao;

import java.sql.Date;
import java.util.List;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.FundTransferBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.exception.BankingException;

public interface IBankingDAO {

	UserBean registerUser(UserBean userBean) throws BankingException;

	UserBean fetchUserById(String userName) throws BankingException;

	int validateAdmin(String adminId, String adminPassword) throws BankingException;

	void updateUserDetails(String uid, long actId) throws BankingException;

	AccountBean insertIntoAccountMaster(AccountBean accountBean) throws BankingException;

	void updateCustomerAddress(CustomerBean customer) throws BankingException;

	UserBean validateUser(String userId) throws BankingException;

	CustomerBean viewCustomer(long accId) throws BankingException;

	UserBean viewAccountId(String userId) throws BankingException;

	AccountBean viewAccount(long accId) throws BankingException;

	AccountBean updateBalance(AccountBean account) throws BankingException;

	UserBean getUserName(long accId) throws BankingException;

	List<TransactionsBean> viewMiniStatement(long accId) throws BankingException;

	List<TransactionsBean> viewDetailStatement(long accId, Date initDate, Date finDate) throws BankingException;

	List<TransactionsBean> adminViewTransactions(long accId) throws BankingException;

	TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException;

	CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException;

	long fetchAccountIdFromCustomer(String userName) throws BankingException;

	AccountBean fetchAccountByAccountId(long accountId) throws BankingException;

	PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException;

	void insertFundTransferDetails(FundTransferBean fundTransfer) throws BankingException;

	AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException;

	void updateloginpassword(UserBean userBean) throws BankingException;

	CustomerBean insertIntoCustomer(CustomerBean customer) throws BankingException;

	UserBean updateAccountIdinUser(UserBean userBean) throws BankingException;

	CustomerBean fetchCustomerByUserId(String username);

	List<TransactionsBean> fetchTransactionsByAccountId(long accountId) throws BankingException;

	CustomerBean test(CustomerBean customer);

}