package com.cg.project.dao;

import java.sql.Date;
import java.util.List;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.FundTransferBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;

public interface IBankingDAO 
{
	public void registerUser(UserBean userBean);
	
	public UserBean fetchUserById(String uid);
	
	public int validateAdmin(String adminId, String adminPassword);
	
	public void updateloginpassword(UserBean userBean);
	
	public UserBean updateAccountIdinUser(UserBean userBean);
	
	public void insertIntoCustomer(CustomerBean customer);
	
	public void updateUserDetails(String uid,long actId);
	
	public void insertIntoAccountMaster(AccountBean accountBean);
		
	public UserBean openAccount(UserBean userBean);
	
	public CustomerBean changeAddress(CustomerBean customer);
	
	public CustomerBean viewCustomer(long accId);
	
	public UserBean viewAccountId(String userId);
	
	public AccountBean viewAccount(long accId);
	
	public AccountBean updateBalance(long accId,AccountBean account);
	
	public UserBean getUserName(long accId);

	public AccountBean updateBalance(long accId,double amount);
	
	public TransactionsBean viewDetailsOfTransactions(long accId);
	
	public List<TransactionsBean> viewDetailStatement(long accId,Date initDate,Date finDate);
	
	public AccountBean fetchAccountByAccountId(long accountId);
	
	public void updateCustomerAddress(CustomerBean customer);
	
	public CustomerBean fetchCustomerByAccountId(long accountId);
	

	public List<TransactionsBean> adminViewTransactions(long accId);
	
	public UserBean validateUser(String userId);

	public long fetchAccountIdFromCustomer(String userName);

	List<TransactionsBean> viewMiniStatement(long accId);

	TransactionsBean insertTransactionDetails(TransactionsBean transaction);
	
	public PayeeBean insertPayeeDetails(PayeeBean payee);

	public void insertFundTransferDetails(FundTransferBean fundTransfer);

	/* public AccountBean fetchAccounts(long actId) throws BankingException;
	 public double fetchAmount(long actId) throws BankingException;
	 public void openAccount(CustomerBean customerBean) throws BankingException;
	 public CustomerBean validateId(long actId) throws BankingException;
	 public String deposit(long actId,double amount,AccountBean accountBean) throws BankingException;
	 public String depositUpdate(long actId,double amount) throws BankingException;
	 public void transactionUpdate(long accId,TransactionsBean transBean ) throws BankingException;
	 public String withdraw(long accId,double amount) throws BankingException;
	 public void sendfund(double amt, long actId, long pactId) throws BankingException;
	 public String validatePassword(String uId) throws BankingException;
	 public int registerUser(UserBean userBean) throws BankingException;
	 public int updateUserDetails(UserBean userBean,String uid,long actId) throws BankingException;
	 public int fetchUserByActId(long acid) throws BankingException;
	 public int updateCustomerAddress(long actId,String address) throws BankingException;
	 public HashMap<Long, List<TransactionsBean>> viewMiniStatement(long accId) throws BankingException;
	 public HashMap<Long, List<TransactionsBean>> viewDetailedStatement(long accId,Date date1,Date date2) throws BankingException;
	 public int serviceTracking(ServiceTrackerBean service,long accId) throws BankingException;
	 public Date fetchOpenDate(long accId) throws BankingException;
	 public int insertAccount(AccountBean account) throws BankingException;
	 public long getPreviousUser(String uId) throws BankingException;
	 public int insertService(ServiceTrackerBean serviceTracker) throws BankingException;
	 public int insertPayee(PayeeBean payeeBean) throws BankingException;
	 public List<UserBean> viewAccountHolders() throws BankingException;
	 public List<TransactionsBean> viewTransactionsDetails() throws BankingException;*/
}