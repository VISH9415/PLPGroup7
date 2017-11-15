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

public interface IBankingService {

	/**
	 * 
	 * Fetch user by user name
	 * 
	 * User record is fetched from user table based on user name provided
	 * 
	 * @param userName
	 *            - user name used to compare the table column user_id
	 * @return userBean - user record that matches for user name provided
	 */
	UserBean fetchUserById(String userName) throws BankingException;

	/**
	 * Validate bank administrator
	 *
	 * Bank Administrator is validated against userId and password provided
	 *
	 * @param adminId
	 *            - user id for bank administrator
	 * @param adminPassword
	 *            - login password for bank administrator
	 * @return count - status if administrator is valid or not
	 */
	int validateAdmin(String adminId, String adminPassword)
			throws BankingException;

	/**
	 * DetailedStatement for the customer
	 * Transactions details of customer are fetched for the customer between 2 dates input by customer.
	 * 
	 * @param accId Account Id of the user for which transactions records are fetched
	 * @param initDate - Initial date of transaction
	 * @param finDate - final date of transaction
	 * @return detailStatement - List of transactions 
	 */
	List<TransactionsBean> viewDetailStatement(long accId, Date initDate,
			Date finDate) throws BankingException;

	/**
	 * Transactions details of customer are fetched by bank admin by inputing account id of customer
	 * 
	 * @param accId Account Id of the user for which transactions records are fetched
	 * @return detailStatement - List of transactions 
	 */
	List<TransactionsBean> adminViewTransactions(long accId)
			throws BankingException;

	/**
	 * Update Customer address in customer table
	 * 
	 * Customer address is updated in customer records upon the request by
	 * Customer
	 * 
	 * @param customer
	 *            - customer record in which customer address is to be updated
	 */
	void updateCustomerAddress(CustomerBean customer) throws BankingException;

	/**
	 * Fetch customer from accounts table
	 * 
	 * Customer record is fetched from accounts table for which account Id is
	 * provided
	 * 
	 * @param accountId
	 *            - accountId for the customer
	 * @return customerFound - customer record from account master that matches
	 *         with account id
	 */
	CustomerBean fetchCustomerByAccountId(long accountId)
			throws BankingException;

	/**
	 * Fetch account Id for a user from customer table
	 * 
	 * Account Id is fetched from customer records for the user with user name
	 * provided
	 * 
	 * @param userName
	 *            - user name for which accountId is fetched
	 * @return accountId - accountId fetched for user
	 */
	long fetchAccountIdFromCustomer(String userName) throws BankingException;

	/**
	 * Fetch Account details for the accountId provided
	 * 
	 * @param accountId
	 *            - Account Id for customer corresponding to which account
	 *            record is fetched
	 * @return accountFound - account record fetch is returned
	 */
	AccountBean fetchAccountByAccountId(long accountId) throws BankingException;

	/**
	 * MiniStatement for the customer
	 * Method to obtain last 10 transactions details of customer are fetched for the customer with given accountId
	 * 
	 * @param AccountId Account Id of the user for which transactions records are fetched
	 * @return miniStatement - List of transactions 
	 */
	List<TransactionsBean> viewMiniStatement(long accId)
			throws BankingException;

	/**
	 * Insert payee record in payee table
	 * 
	 * Payee record is inserted for the payee after a fund transfer operation is
	 * performed
	 * 
	 * @param payee
	 *            Payee record inserted into payee table
	 * @return payee - Inserted payee record is returned
	 */
	PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException;

	/**
	 * 
	 * Insert fund transfer record
	 * 
	 * Fund transfer record is inserted into fund transfer table after fund
	 * transfer operation is successfully completed
	 * 
	 * @param fundTransfer
	 *            Fund transfer record
	 * 
	 */
	void insertFundTransferDetails(FundTransferBean fundTransfer)
			throws BankingException;

	/**
	 * Update Account balance of customer
	 * 
	 * Account balance for the payer is updated in accounts table after fund
	 * transfer operation
	 * 
	 * @param account
	 *            Account record updated
	 * @return accountBean - Updated account record for payer is returned.
	 */
	AccountBean updateBalance(AccountBean account) throws BankingException;

	/**
	 * Update Account balance of customer
	 * 
	 * Account balance for the payer is updated in accounts table after fund
	 * transfer operation
	 * 
	 * @param account
	 *            Account record updated
	 * @return accountBean - Updated account record for payer is returned.
	 */
	AccountBean updateBalanceForPayee(AccountBean accountByPayeeId)
			throws BankingException;

	String randPassword();

	/**
	 * Update login password for user
	 * 
	 * This method updates the login password for user that matches with user
	 * name provided
	 * 
	 * @param userBean
	 *            - user for which login password is required to be updated
	 * 
	 */
	void updateloginpassword(UserBean userBean) throws BankingException;

	/**
	 * Update accountId for user
	 * 
	 * AccountId is updated in user records for the user provided
	 * 
	 * @param userBean
	 *            - user record that is to be updated in user table
	 * @return userBean - updated user record is returned
	 */
	UserBean updateAccountIdinUser(UserBean userBean) throws BankingException;

	/**
	 * Insert transaction record into transactions table
	 * 
	 * Transaction record is inserted into Transactions table against the
	 * account Id provided
	 * 
	 * @param transaction
	 *            Transaction record inserted
	 * @return transaction - Inserted transaction record is returned
	 */
	TransactionsBean insertTransactionDetails(TransactionsBean transaction)
			throws BankingException;

	/**
	 * Insert customer record customer table
	 * 
	 * Customer record is inserted into Customer table after opening the account
	 * in Bank
	 * 
	 * @param - customer record to be inserted
	 * @return - customer record
	 */
	CustomerBean insertIntoCustomer(long initialAccountId, String customerName,
			String email, String address, String panCard, String accountType,
			String userName) throws BankingException;

	/**
	 * Method to insert account record into account master table
	 * 
	 * @param accountBean
	 *            - account record to be inserted into account master
	 * @return - inserted account record is returned
	 */
	AccountBean insertIntoAccountMaster(long updatedAccountId,
			String accountType, Date opendate, double initialBalance)
			throws BankingException;

	/**
	 * Register the user in bank
	 * 
	 * This Method recieves the user record after signup and inserts it into
	 * usertable in database.
	 * 
	 * @param userBean
	 *            - user record that is inserted into usertable
	 * @return userBean - user record inserted
	 * @throws BankingException
	 */
	UserBean registerUser(long initialaccountid, String userName,
			String password, String transactionPassword, String lockstatus,
			String secretQuestion, String secretAnswer) throws BankingException;

}
