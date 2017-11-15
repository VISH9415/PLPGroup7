package com.cg.project.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.resource.spi.AdministeredObject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.FundTransferBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.exception.BankingException;
import com.cg.project.utils.Constants;
import com.cg.project.utils.QueryMapper;

@Repository
@Transactional
public class BankingDAOImpl implements IBankingDAO {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Register the user in bank
	 * 
	 * This Method recieves the user record after signup and inserts it into
	 * usertable in database.
	 * 
	 * @param userBean
	 *            - user record that is inserted into usertable
	 * @return userBean - inserted user record is returned
	 * @throws BankingException
	 */
	@Override
	public UserBean registerUser(UserBean userBean) throws BankingException {
		try {
			entityManager.persist(userBean);
			entityManager.flush();
			return userBean;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception in inserting user record");
		}

	}

	/**
	 * 
	 * Fetch user by user name
	 * 
	 * User record is fetched from user table based on user name provided
	 * 
	 * @param userName
	 *            - user name used to compare the table column user_id
	 * @return userBean - user record that matches for user name provided
	 * @throws BankingException
	 */
	@Override
	public UserBean fetchUserById(String userName) throws BankingException {
		try {
			UserBean userFound = new UserBean();
			userFound = entityManager.find(UserBean.class, userName);
			return userFound;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in fetching user.");
		}

	}

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
	 * @throws BankingException
	 */
	@Override
	public int validateAdmin(String adminId, String adminPassword)
			throws BankingException {

		try {
			int count = Constants.adminInitialCount;
			if (adminId.equals(Constants.adminUserName)
					&& adminPassword.equals(Constants.adminLoginPassword)) {
				count++;
			}
			return count;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in validating admin.");
		}
	}

	/**
	 * Update login password for user
	 * 
	 * This method updates the login password for user that matches with user
	 * name provided
	 * 
	 * @param userBean
	 *            - user for which login password is required to be updated
	 * @throws BankingException
	 */
	@Override
	public void updateloginpassword(UserBean userBean) throws BankingException {
		try {
			entityManager.merge(userBean);
			entityManager.flush();
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in updating user login password");
		}
	}

	/**
	 * Update accountId for user
	 * 
	 * AccountId is updated in user records for the user provided
	 * 
	 * @param userBean
	 *            - user record that is to be updated in user table
	 * @return userBean - updated user record is returned
	 * @throws BankingException
	 */
	@Override
	public UserBean updateAccountIdinUser(UserBean userBean)
			throws BankingException {
		try {
			UserBean user = entityManager.merge(userBean);
			return user;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in updating accountId in usertable.");
		}
	}

	/**
	 * Insert customer record customer table
	 * 
	 * Customer record is inserted into Customer table after opening the account
	 * in Bank
	 * 
	 * @param - customer record to be inserted
	 * @return - customer record
	 * @throws BankingException
	 */
	@Override
	public CustomerBean insertIntoCustomer(CustomerBean customer)
			throws BankingException {
		try {
			entityManager.persist(customer);
			entityManager.flush();
			return customer;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in inserting customer record.");
		}
	}

	/**
	 * Method to insert account record into account master table
	 * 
	 * @param accountBean
	 *            - account record to be inserted into account master
	 * @return - inserted account record is returned
	 * @throws BankingException
	 */
	@Override
	public AccountBean insertIntoAccountMaster(AccountBean accountBean)
			throws BankingException {
		try {
			entityManager.persist(accountBean);
			entityManager.flush();
			return accountBean;
		} catch (Exception e) {
			throw new BankingException(
					e.getMessage()
							+ " Exception occured in inserting account record into AccountMaster.");
		}
	}

	/**
	 * Update Customer address in customer table
	 * 
	 * Customer address is updated in customer records upon the request by
	 * Customer
	 * 
	 * @param customer
	 *            - customer record in which customer address is to be updated
	 * @throws BankingException
	 */
	@Override
	public void updateCustomerAddress(CustomerBean customer)
			throws BankingException {
		try {
			entityManager.merge(customer);
			entityManager.flush();
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in updating customer address");
		}
	}

	/**
	 * Update Account balance of customer
	 * 
	 * Account balance for the payer is updated in accounts table after fund
	 * transfer operation
	 * 
	 * @param account - 
	 *            Account record updated
	 * @return accountBean - Updated account record for payer is returned.
	 * @throws BankingException
	 */
	@Override
	public AccountBean updateBalance(AccountBean account)
			throws BankingException {
		try {
			AccountBean accountBean = entityManager.merge(account);
			return accountBean;
		} catch (Exception e) {
			throw new BankingException(
					e.getMessage()
							+ " Exception occured in updating balance in accountmaster ");
		}
	}

	/**
	 * MiniStatement for the customer Last 10 transactions details of customer
	 * are fetched for the customer with given accountId
	 * 
	 * @param AccountId
	 *            Account Id of the user for which transactions records are
	 *            fetched
	 * @return miniStatement - List of transactions
	 * @throws BankingException
	 */
	@Override
	public List<TransactionsBean> viewMiniStatement(long accId)
			throws BankingException {
		try {
			TypedQuery<TransactionsBean> query = entityManager.createQuery(
					QueryMapper.viewMiniStatementQuery, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> miniStatement = query.setMaxResults(10)
					.getResultList();
			return miniStatement;
		} catch (Exception e) {
			throw new BankingException(
					e.getMessage()
							+ " Exception occured in fetching transaction details for mini statement");
		}
	}

	/**
	 * DetailedStatement for the customer Transactions details of customer are
	 * fetched for the customer between 2 dates input by customer.
	 * 
	 * @param accId - 
	 *            Account Id of the user for which transactions records are
	 *            fetched
	 * @param initDate
	 *            - Initial date of transaction
	 * @param finDate
	 *            - final date of transaction
	 * @return detailStatement - List of transactions
	 */
	@Override
	public List<TransactionsBean> viewDetailStatement(long accId,
			Date initDate, Date finDate) throws BankingException {
		try {
			TypedQuery<TransactionsBean> query = entityManager.createQuery(
					QueryMapper.viewDetailedStatementQuery,
					TransactionsBean.class);
			query.setParameter("paccountId", accId);
			query.setParameter("pdate1", initDate);
			query.setParameter("pdate2", finDate);
			List<TransactionsBean> detailStatement = query.getResultList();
			return detailStatement;
		} catch (Exception e) {
			throw new BankingException(
					e.getMessage()
							+ " Exception occured in fetching transaction details for detailed statement");
		}
	}

	/**
	 * Transactions details of customer are fetched by bank admin by inputing
	 * account id of customer
	 * 
	 * @param accId - 
	 *            Account Id of the user for which transactions records are
	 *            fetched
	 * @return detailStatement - List of transactions
	 * @throws BankingException
	 */
	@Override
	public List<TransactionsBean> adminViewTransactions(long accId)
			throws BankingException {
		try {
			TypedQuery<TransactionsBean> query = entityManager.createQuery(
					QueryMapper.adminViewTransactionsQuery,
					TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> adminStatement = query.getResultList();
			return adminStatement;
		} catch (Exception e) {
			throw new BankingException(
					e.getMessage()
							+ " Exception occured in fetching transaction records by the admin");
		}
	}

	/**
	 * Insert transaction record into transactions table
	 * 
	 * Transaction record is inserted into Transactions table against the
	 * account Id provided
	 * 
	 * @param transaction - 
	 *            Transaction record inserted
	 * @return transaction - Inserted transaction record is returned
	 * @throws BankingException
	 */
	@Override
	public TransactionsBean insertTransactionDetails(
			TransactionsBean transaction) throws BankingException {
		try {
			entityManager.persist(transaction);
			return transaction;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in inserting transaction record");
		}
	}

	/**
	 * Fetch customer from accounts table
	 * 
	 * Customer record is fetched from accounts table for which account Id is
	 * provided
	 * 
	 * @param accountId - 
	 *            AccountId for the customer
	 * @return customerFound - Customer record from account master that matches
	 *         with account id
	 * @throws BankingException
	 */
	@Override
	public CustomerBean fetchCustomerByAccountId(long accountId)
			throws BankingException {
		try {
			CustomerBean customerFound = new CustomerBean();
			customerFound = entityManager.find(CustomerBean.class, accountId);
			return customerFound;
		} catch (Exception e) {
			throw new BankingException(
					e.getMessage()
							+ " Exception occured in fetching customer from customer table");
		}
	}

	/**
	 * Fetch account Id for a user from customer table
	 * 
	 * Account Id is fetched from customer records for the user with user name
	 * provided
	 * 
	 * @param userName 
	 *            - user name for which accountId is fetched
	 * @return accountId - accountId fetched for user
	 * @throws BankingException
	 */
	@Override
	public long fetchAccountIdFromCustomer(String userName)
			throws BankingException {
		try {
			TypedQuery<CustomerBean> query = entityManager.createQuery(
					QueryMapper.selectCustomerQuery, CustomerBean.class);
			query.setParameter("puId", userName);
			CustomerBean customer = query.getSingleResult();
			return customer.getAccountId();
		} catch (Exception e) {
			throw new BankingException(
					e.getMessage()
							+ " Exception occured in fetching accountId from customer table");
		}
	}

	/**
	 * Fetch Account details for the accountId provided
	 * 
	 * @param accountId
	 *            - Account Id for customer corresponding to which account
	 *            record is fetched
	 * @return accountFound - account record fetch is returned
	 * @throws BankingException
	 */
	@Override
	public AccountBean fetchAccountByAccountId(long accountId)
			throws BankingException {
		try {
			AccountBean accountFound = new AccountBean();
			accountFound = entityManager.find(AccountBean.class, accountId);
			return accountFound;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in fetching account record");
		}
	}

	/**
	 * Insert payee record in payee table
	 * 
	 * Payee record is inserted for the payee after a fund transfer operation is
	 * performed
	 * 
	 * @param payee
	 *            Payee record inserted into payee table
	 * @return payee - Inserted payee record is returned
	 * @throws BankingException
	 */
	@Override
	public PayeeBean insertPayeeDetails(PayeeBean payee)
			throws BankingException {
		try {
			entityManager.persist(payee);
			return payee;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in inserting payee record");
		}
	}

	/**
	 * 
	 * Insert fund transfer record
	 * 
	 * Fund transfer record is inserted into fund transfer table after fund
	 * transfer operation is successfully completed
	 * 
	 * @param fundTransfer - 
	 *            Fund transfer record
	 * @throws BankingException
	 */
	@Override
	public void insertFundTransferDetails(FundTransferBean fundTransfer)
			throws BankingException {
		try {
			entityManager.persist(fundTransfer);
			entityManager.flush();
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in inserting fund transfer record");
		}
	}

	/**
	 * Update Account balance of customer
	 * 
	 * Account balance for the payee is updated in accounts table after fund
	 * transfer operation
	 * 
	 * @param account - 
	 *            Account record updated
	 * @return accountBean - Updated account record for payee is returned.
	 * @throws BankingException
	 */
	@Override
	public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId)
			throws BankingException {
		try {
			AccountBean accountOfPayee = entityManager.merge(accountByPayeeId);
			return accountOfPayee;
		} catch (Exception e) {
			throw new BankingException(e.getMessage()
					+ " Exception occured in updating balance for payee");
		}
	}

}
