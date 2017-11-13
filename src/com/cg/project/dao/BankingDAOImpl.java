package com.cg.project.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
public class BankingDAOImpl implements IBankingDAO{

	//private static Logger logger = Logger.getLogger(com.cg.project.dao.BankingDAOImpl.class);
	//open account 

	@PersistenceContext
	private EntityManager entityManager;


	/** 
	 * Method to register user after signup into usertable
	 * com.cg.project.dao.IBankingDAO#registerUser(com.cg.project.bean.UserBean)
	 */
	@Override
	public void registerUser(UserBean userBean) throws BankingException{
		try {
			entityManager.persist(userBean);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception in inserting user record");			}
	}

	/**
	 * Method to fetch user by userName from usertable
	 */
	@Override
	public UserBean fetchUserById(String userName) throws BankingException
	{
		try {
			UserBean userFound = new UserBean();
			userFound = entityManager.find(UserBean.class, userName);
			return userFound;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching user.");
		}

	}

	/**
	 * Method to validate bank administrator during login 
	 */
	@Override
	public int validateAdmin(String adminId, String adminPassword) throws BankingException {

		try {
			int count = Constants.adminInitialCount; 
			//System.out.println("in dao ... validate admin");
			if(adminId.equals(Constants.adminUserName)&&adminPassword.equals(Constants.adminLoginPassword))
			{
				count++;
			}
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in validating admin.");
		}
	}
 
	/**
	 * Method to update login password in user table
	 */
	@Override
	public void updateloginpassword(UserBean userBean) throws BankingException
	{
		try {
			entityManager.merge(userBean);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in updating user login password");
		}
	}
	
   /**
    * Method to update accountId for user in user table
    */
	@Override
	public UserBean updateAccountIdinUser(UserBean userBean) throws BankingException
	{
		try {
			UserBean user = entityManager.merge(userBean);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in updating accountId in usertable.");
		}
	}
	/**
	 * Method to insert customer record in customer table
	 */
	@Override
	public void insertIntoCustomer(CustomerBean customer) throws BankingException
	{
		try {
			entityManager.persist(customer);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in inserting customer record.");
		}
	}
	/**
	 * Method to update user details in user table
	 */
	@Override
	public void updateUserDetails(String uid,long actId) throws BankingException
	{
		try {
			UserBean userFound= new UserBean();
			userFound = entityManager.find(UserBean.class, actId);

			entityManager.merge(userFound);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured updating user record.");
		}

		//String sql = "UPDATE USERTABLE SET ACCOUNT_ID=?,LOGIN_PASSWORD=? WHERE USER_ID=?";	
	}

	/**
	 * Method to insert account record into account master table
	 */
	@Override
	public void insertIntoAccountMaster(AccountBean accountBean) throws BankingException {
		// TODO Auto-generated method stub
		try {
			entityManager.persist(accountBean);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in inserting account record into AccountMaster.");
		}
	}

	/**
	 * Method to update Customer address in customer table 
	 */
	@Override
	public void updateCustomerAddress(CustomerBean customer) throws BankingException {
		// TODO Auto-generated method stub
		try {
			entityManager.merge(customer);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in updating customer address");
		}
	}

	/**
	 * Method to validate user against the userId provided
	 */
	@Override
	public UserBean validateUser(String userId) throws BankingException {
		//String qry = "Select user from UserBean user where userId=:puserId";
		try {
			TypedQuery<UserBean> query = entityManager.createQuery(QueryMapper.validateUserQuery, UserBean.class);
			query.setParameter("puserId", userId);
			UserBean user = query.getSingleResult();
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in validating user.");
		}
	}

	/**
	 * Method to obtain customer details against the accountId provided
	 */
	@Override
	public CustomerBean viewCustomer(long accId) throws BankingException {
		try {
			CustomerBean customer = entityManager.find(CustomerBean.class, accId);
			return customer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching Customer.");
		}
	}

	/**
	 * Method to view AccountId against the userId provided by Customer
	 */
	@Override
	public UserBean viewAccountId(String userId) throws BankingException {
		try {
			UserBean user = entityManager.find(UserBean.class, userId);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching accountId from usertable");
		}
	}

	/**
	 * Method to view Account details of customer for the accountId provided 
	 */
	@Override
	public AccountBean viewAccount(long accId) throws BankingException {
		try {
			AccountBean account = entityManager.find(AccountBean.class, accId);
			return account;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching account record.");
		}
	}

	/**
	 * Method to update Account balance of customer in account-master table
	 */
	@Override
	public AccountBean updateBalance(AccountBean account) throws BankingException {
		try {
			AccountBean accountBean = entityManager.merge(account);
			//String qry = "Update AccountBean account set account.accountBalance = account.accountBalance-:amount";		
			return accountBean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in updating balance in accountmaster ");
		}
	}
	/**
	 * Method to obtain userName of customer by accountId
	 */
	@Override
	public UserBean getUserName(long accId) throws BankingException {
		try {
			UserBean user = entityManager.find(UserBean.class, accId);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching username.");
		}
	}

	/**
	 * Method to obtain last 10 transactions details of customer with given accountId
	 */
	@Override
	public List<TransactionsBean> viewMiniStatement(long accId) throws BankingException {
		//String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
		try {
			TypedQuery<TransactionsBean> query = entityManager.createQuery(QueryMapper.viewMiniStatementQuery, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> miniStatement = query.setMaxResults(10).getResultList();
			return miniStatement;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching transaction details for mini statement");
		}
	}

	/**
	 * Method to obtain periodic transactions details of customer with given accountId
	 */
	@Override
	public List<TransactionsBean> viewDetailStatement(long accId,Date initDate,Date finDate) throws BankingException {
		//String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId and transaction.dateOfTransaction between :pdate1 and :pdate2 order by transaction.dateOfTransaction";
		try {
			TypedQuery<TransactionsBean> query = entityManager.createQuery(QueryMapper.viewDetailedStatementQuery,TransactionsBean.class);
			query.setParameter("paccountId", accId);
			query.setParameter("pdate1", initDate);
			query.setParameter("pdate2",finDate);
			List<TransactionsBean> detailStatement = query.getResultList();
			return detailStatement;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching transaction details for detailed statement");
		}
	}

	/**
	 * Method to fetch all transactions details for the customer
	 */
	@Override
	public List<TransactionsBean> adminViewTransactions(long accId) throws BankingException {
		//String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
		try {
			TypedQuery<TransactionsBean> query = entityManager.createQuery(QueryMapper.adminViewTransactionsQuery, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> adminStatement = query.getResultList();
			return adminStatement;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching transaction records by the admin");
		}
	}


	/**
	 * Method to insert transaction record into transactions table in database
	 */
	@Override
	public TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException
	{
		try {
			entityManager.persist(transaction);
			return transaction;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in inserting transaction record");
		}
	}

	/**
	 * Method to fetch customer by accountId given
	 */
	@Override
	public CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException {
		// TODO Auto-generated method stub
		try {
			CustomerBean customerFound = new CustomerBean();
			customerFound = entityManager.find(CustomerBean.class, accountId);
			return customerFound;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching customer from customer table");
		}
	}

	/**
	 * Method to fetch account Id for a user from customer table
	 */
	@Override
	public long fetchAccountIdFromCustomer(String userName) throws BankingException{
		// TODO Auto-generated method stub 
		//String qry = "Select customer from CustomerBean customer where customer.userId=:puId";
		try {
			TypedQuery<CustomerBean> query = entityManager.createQuery(QueryMapper.selectCustomerQuery, CustomerBean.class);
			query.setParameter("puId", userName);
			CustomerBean customer= query.getSingleResult();
			return customer.getAccountId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching accountId from customer table");
		}
	}

	/**
	 * Method to fetch Account details for the accountId provided
	 */
	@Override
	public AccountBean fetchAccountByAccountId(long accountId) throws BankingException{
		// TODO Auto-generated method stub
		try {
			AccountBean accountFound = new AccountBean();
			accountFound = entityManager.find(AccountBean.class, accountId);
			return accountFound;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in fetching account record");
		}
	}

	/**
	 * Method to insert payee record in payee table
	 */
	@Override
	public PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException{
		// TODO Auto-generated method stub
		try {
			entityManager.persist(payee);
			return payee;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in inserting payee record");
		}
	}

	/**
	 * Method to insert fund transfer details for every transaction.
	 */
	@Override
	public void insertFundTransferDetails(
			FundTransferBean fundTransfer) throws BankingException
	{
		try {
			entityManager.persist(fundTransfer);
			entityManager.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in inserting fund transfer record");
		}
	}

	/**
	 * Method to update account balance for payee
	 */
	@Override
	public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException {
		try {
			AccountBean accountOfPayee =  entityManager.merge(accountByPayeeId);
			return accountOfPayee;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException("Exception occured in updating balance for payee");
		}
	}

}
