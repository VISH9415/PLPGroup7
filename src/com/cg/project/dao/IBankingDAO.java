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

public interface IBankingDAO 
{

	public void registerUser(UserBean userBean) throws BankingException;

	public UserBean fetchUserById(String userName) throws BankingException;

	public int validateAdmin(String adminId, String adminPassword) throws BankingException;

	public void updateUserDetails(String uid, long actId) throws BankingException;

	public void insertIntoAccountMaster(AccountBean accountBean) throws BankingException;

	public void updateCustomerAddress(CustomerBean customer) throws BankingException;

	public UserBean validateUser(String userId) throws BankingException;

	public CustomerBean viewCustomer(long accId) throws BankingException;

	public UserBean viewAccountId(String userId) throws BankingException;

	public AccountBean viewAccount(long accId) throws BankingException;

	public AccountBean updateBalance(AccountBean account) throws BankingException;

	public UserBean getUserName(long accId) throws BankingException;

	public List<TransactionsBean> viewMiniStatement(long accId) throws BankingException;

	public List<TransactionsBean> viewDetailStatement(long accId, Date initDate, Date finDate) throws BankingException;

	public List<TransactionsBean> adminViewTransactions(long accId) throws BankingException;

	public TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException;

	public CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException;

	public long fetchAccountIdFromCustomer(String userName) throws BankingException;

	public AccountBean fetchAccountByAccountId(long accountId) throws BankingException;

	public PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException;

	public void insertFundTransferDetails(FundTransferBean fundTransfer) throws BankingException;

	public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException;

	public void updateloginpassword(UserBean userBean) throws BankingException;

	public void insertIntoCustomer(CustomerBean customer) throws BankingException;

	public UserBean updateAccountIdinUser(UserBean userBean) throws BankingException;

}