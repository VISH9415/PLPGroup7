package com.cg.project.service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.FundTransferBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.dao.IBankingDAO;
import com.cg.project.exception.BankingException;
import com.cg.project.utils.Constants;

@Service
public class BankingServiceImpl implements IBankingService {

	@Autowired
	IBankingDAO dao;

	/**
	 * 
	 * Fetch user by user name 
	 * 
	 * User record is fetched from user table based on user name provided
	 * 
	 * @param userName- user name used to compare the table column user_id
	 * @return userBean - user record that matches for user name provided
	 */
	@Override
	public UserBean fetchUserById(String userName) throws BankingException {
		return dao.fetchUserById(userName);
	}

	/**
	 * Validate bank administrator  
	 *
	 *Bank Administrator is validated against userId and password provided
	 *
	 *@param adminId - user id for bank administrator
	 *@param adminPassword - login password for bank administrator
	 *@return count - status if administrator is valid or not 
	 */
	@Override
	public int validateAdmin(String adminId, String adminPassword) throws BankingException {
		return dao.validateAdmin(adminId, adminPassword);

	}

	/**
	 * Update login password for user
	 * 
	 * This method updates the login password for user that matches with user name provided
	 * @param userBean - user for which login password is required to be updated
	 * 
	 */
	@Override
	public void updateloginpassword(UserBean userBean) throws BankingException {
		dao.updateloginpassword(userBean);
	}

	/**
	 * Insert customer record customer table
	 * 
	 * Customer record is inserted into Customer table after opening the account in Bank
	 * 
	 * @param - customer record to be inserted
	 * @return - customer record
	 */
	@Override
	public CustomerBean insertIntoCustomer(long initialAccountId, String customerName, String email, String address,
			String panCard, String accountType, String userName) throws BankingException {
		CustomerBean customer = new CustomerBean(initialAccountId, customerName, email, address, panCard, accountType,
				userName);
		return dao.insertIntoCustomer(customer);
	}

	/**
	 * Insert transaction record into transactions table 
	 * 
	 * Transaction record is inserted into Transactions table against the account Id provided
	 * 
	 * @param transaction Transaction record inserted 
	 * @return transaction - Inserted transaction record is returned
	 */
	@Override
	public TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException {
		return dao.insertTransactionDetails(transaction);
	}

	/** 
	 * Register the user in bank
	 * 
	 * This Method recieves the user record after signup and inserts it into usertable in database.
	 *  
	 * @param userBean - user record that is inserted into usertable 
	 * @return userBean - user record inserted
	 * @throws BankingException
	 */
	@Override
	public UserBean registerUser(long initialAccountId,String userName,String password,String transactionPassword,String lockStatus,String secretQuestion,String secretAnswer) throws BankingException {
		UserBean user = new UserBean(initialAccountId,userName,password,transactionPassword,lockStatus,secretQuestion,secretAnswer);
		return dao.registerUser(user);
	}

	   /**
	    * Update accountId for user 
	    * 
	    * AccountId is updated in user records for the user provided
	    * 
	    * @param userBean - user record that is to be updated in user table 
	    * @return userBean - updated user record is returned
	    */
	@Override
	public UserBean updateAccountIdinUser(UserBean userBean) throws BankingException {
		// TODO Auto-generated method stub
		return dao.updateAccountIdinUser(userBean);
	}

	/*
	 * @Override public UserBean validateUser(String userId) throws BankingException
	 * { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public CustomerBean changeAddress(CustomerBean customer) throws
	 * BankingException { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public CustomerBean viewCustomer(long accId) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public UserBean viewAccountId(String userId) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public AccountBean viewAccount(long accId) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * @Override public UserBean getUserName(long accId) { // TODO Auto-generated
	 * method stub return null; }
	 */

	/**
	 * DetailedStatement for the customer
	 * Transactions details of customer are fetched for the customer between 2 dates input by customer.
	 * 
	 * @param accId Account Id of the user for which transactions records are fetched
	 * @param initDate - Initial date of transaction
	 * @param finDate - final date of transaction
	 * @return detailStatement - List of transactions 
	 */
	@Override
	public List<TransactionsBean> viewDetailStatement(long accId, Date initDate, Date finDate) throws BankingException {
		// TODO Auto-generated method stub
		return dao.viewDetailStatement(accId, initDate, finDate);
	}

	/**
	 * Transactions details of customer are fetched by bank admin by inputing account id of customer
	 * 
	 * @param accId Account Id of the user for which transactions records are fetched
	 * @return detailStatement - List of transactions 
	 */
	@Override
	public List<TransactionsBean> adminViewTransactions(long accId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.adminViewTransactions(accId);
	}

	/**
	 * Method to insert account record into account master table
	 * 
	 * @param accountBean - account record to be inserted into account master
	 * @return - inserted account record is returned
	 */
	@Override
	public AccountBean insertIntoAccountMaster(long updatedAccountId,String accountType,Date opendate,double initialBalance) throws BankingException {
       AccountBean newAccount = new AccountBean(updatedAccountId,accountType,opendate,initialBalance);
		return dao.insertIntoAccountMaster(newAccount);
	}


	/**
	 * Update Customer address in customer table
	 * 
	 * Customer address is updated in customer records upon the request by Customer
	 *  
	 *  @param customer - customer record in which customer address is to be updated
	 */
	@Override
	public void updateCustomerAddress(CustomerBean customer) throws BankingException {
		// TODO Auto-generated method stub
		dao.updateCustomerAddress(customer);
	}

	/**
	 * Fetch customer from accounts table
	 * 
	 * Customer record is fetched from accounts table for which account Id is provided
	 * 
	 *  @param accountId - accountId for the customer 
	 *  @return customerFound - customer record from account master that matches with account id 
	 */
	@Override
	public CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchCustomerByAccountId(accountId);
	}

	/**
	 * Fetch account Id for a user from customer table
	 * 
	 * Account Id is fetched from customer records for the user with user name provided
	 * @param userName - user name for which accountId is fetched 
	 * @return accountId - accountId fetched for user
	 */
	@Override
	public long fetchAccountIdFromCustomer(String userName) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchAccountIdFromCustomer(userName);
	}

	/**
	 * Fetch Account details for the accountId provided
	 * 
	 * @param accountId - Account Id for customer corresponding to which account record is fetched
	 * @return accountFound - account record fetch is returned
	 */
	@Override
	public AccountBean fetchAccountByAccountId(long accountId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchAccountByAccountId(accountId);
	}

	/**
	 * MiniStatement for the customer
	 * Method to obtain last 10 transactions details of customer are fetched for the customer with given accountId
	 * 
	 * @param AccountId Account Id of the user for which transactions records are fetched
	 * @return miniStatement - List of transactions 
	 */
	@Override
	public List<TransactionsBean> viewMiniStatement(long accId) throws BankingException {
		return dao.viewMiniStatement(accId);
	}

	/**
	 * Insert payee record in payee table
	 * 
	 * Payee record is inserted for the payee after a fund transfer operation is performed
	 * 
	 * @param payee Payee record inserted into payee table 
	 * @return payee - Inserted payee record is returned
	 */
	@Override
	public PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException {
		return dao.insertPayeeDetails(payee);
	}

	/**
	 * 
	 * Insert fund transfer record
	 * 
	 * Fund transfer record is inserted into fund transfer table after fund transfer operation is 
	 * successfully completed
	 * 
	 * @param fundTransfer Fund transfer record 
	 * 
	 */
	@Override
	public void insertFundTransferDetails(FundTransferBean fundTransfer) throws BankingException {
		dao.insertFundTransferDetails(fundTransfer);
	}

	/**
	 * Update Account balance of customer 
	 * 
	 * Account balance for the payer is updated in accounts table after fund transfer operation
	 * 
	 * @param account Account record updated 
	 * @return accountBean - Updated account record for payer is returned.
	 */
	@Override
	public AccountBean updateBalance(AccountBean account) throws BankingException {
		return dao.updateBalance(account);
	}

	/**
	 * Update Account balance of customer 
	 * 
	 * Account balance for the payer is updated in accounts table after fund transfer operation
	 * 
	 * @param account Account record updated 
	 * @return accountBean - Updated account record for payer is returned.
	 */
	@Override
	public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException {
		return dao.updateBalanceForPayee(accountByPayeeId);
	}

	/**
	 * Method to generate random password for user case user requests for forget password
	 * @return newPassword - new password generated that is to be used by user for further login
	 */
	@Override
	public String randPassword() {
		String uuid = UUID.randomUUID().toString();
		String newPassword = uuid.substring(0, 8).replace("-", "");
		return newPassword;
	}


}
