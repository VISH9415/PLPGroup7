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

	public UserBean fetchUserById(String userName) throws BankingException;

	public int validateAdmin(String adminId, String adminPassword) throws BankingException;

	public void updateUserDetails(String uid, long actId) throws BankingException;

	public void registerUser(UserBean userBean) throws BankingException;

	public List<TransactionsBean> viewDetailStatement(long accId, Date initDate, Date finDate) throws BankingException;

	public List<TransactionsBean> adminViewTransactions(long accId) throws BankingException;

	public void insertIntoAccountMaster(AccountBean accountBean) throws BankingException;

	public void updateCustomerAddress(CustomerBean customer) throws BankingException;

	public CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException;

	public long fetchAccountIdFromCustomer(String userName) throws BankingException;

	public AccountBean fetchAccountByAccountId(long accountId) throws BankingException;

	public List<TransactionsBean> viewMiniStatement(long accId) throws BankingException;

	public PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException;

	public void insertFundTransferDetails(FundTransferBean fundTransfer) throws BankingException;

	public AccountBean updateBalance(AccountBean account) throws BankingException;

	public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException;

	public String randPassword();

	public void updateloginpassword(UserBean userBean) throws BankingException;

	public void insertIntoCustomer(CustomerBean customer) throws BankingException;

	public UserBean updateAccountIdinUser(UserBean userBean) throws BankingException;

	public TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException;

	/*UserBean updateAccountIdinUser(UserBean userBean) throws BankingException;*/

	/*void insertIntoCustomer(CustomerBean customer) throws BankingException;*/
	
	
	
/*	public void registerUser(UserBean userBean) throws BankingException;
	
	public UserBean fetchUserById(String uid) throws BankingException;
	
	public int validateAdmin(String adminId, String adminPassword);
	
	public void updateloginpassword(UserBean userBean);
	
	public UserBean updateAccountIdinUser(UserBean userBean);
	
	public void insertIntoCustomer(CustomerBean customer);

	public UserBean validateUser(String userId);
	
	public CustomerBean changeAddress(CustomerBean customer);
	
	public CustomerBean viewCustomer(long accId);
	
	public UserBean viewAccountId(String userId);
	
	public AccountBean viewAccount(long accId);
	
	public List<TransactionsBean> viewMiniStatement(long accId);
	
	public UserBean getUserName(long accId);
	
	public List<TransactionsBean> viewDetailStatement(long accId,Date initDate, Date finDate);

	public List<TransactionsBean> adminViewTransactions(long accId);
	
	public void updateUserDetails(String uid,long actId);
	
	public void insertIntoAccountMaster(AccountBean accountBean);
	

	public void updateCustomerAddress(CustomerBean customer);

	public CustomerBean fetchCustomerByAccountId(long accountId);

	public long fetchAccountIdFromCustomer(String userName);

	public AccountBean fetchAccountByAccountId(long accountId);

	public TransactionsBean insertTransactionDetails(TransactionsBean transaction);
	
	public PayeeBean insertPayeeDetails(PayeeBean payee);

	public void insertFundTransferDetails(FundTransferBean fundTransfer);

	public AccountBean updateBalance(AccountBean account);

	public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId);

	public String randPassword();*/
	


}
