package com.cg.project.dao;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.UserBean;

public interface IBankingDAO 
{
	public UserBean fetchUserById(String uid);

	public int validateAdmin(String adminId, String adminPassword);

	void updateUserDetails(String uid, long actId);

	int insertAccount(AccountBean account);
	
	public UserBean registerUser(UserBean userBean);

	public CustomerBean insertIntoCustomer(CustomerBean customer);

	public void updateAccountIdinUser(String accountId);
	
	public UserBean validateUser(String userId);
	
	public UserBean openAccount(UserBean userBean);

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