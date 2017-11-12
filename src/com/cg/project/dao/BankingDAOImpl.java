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
			entityManager.persist(userBean);
			entityManager.flush();
	}
	
	/**
	 * Method to fetch user by userName from usertable
	 */
	@Override
	public UserBean fetchUserById(String userName) throws BankingException
	{
		UserBean userFound= new UserBean();
		userFound = entityManager.find(UserBean.class, userName);
		return userFound;	
	}
	
	/**
	 * Method to validate bank administrator during login 
	 */
	@Override
	public int validateAdmin(String adminId, String adminPassword) throws BankingException {
		
		int count = Constants.adminInitialCount; 
		//System.out.println("in dao ... validate admin");
	if(adminId.equals(Constants.adminUserName)&&adminPassword.equals(Constants.adminLoginPassword))
	{
		count++;
	}
	return count;
	}
	
	@Override
	public void updateloginpassword(UserBean userBean) throws BankingException
	{
		entityManager.merge(userBean);
		entityManager.flush();
	}
	
	@Override
	public UserBean updateAccountIdinUser(UserBean userBean) throws BankingException
	{
		UserBean user = entityManager.merge(userBean);
		return user;
	}
	
	@Override
	public void insertIntoCustomer(CustomerBean customer) throws BankingException
	{
		entityManager.persist(customer);
		entityManager.flush();
    }
	
/*	public CustomerBean fetchCustomerByAccountId(long accountId)
	{
		CustomerBean customerFound = new CustomerBean();
		customerFound = entityManager.find(CustomerBean.class, accountId);
		return customerFound;	
	}*/
	
	    /**
	     * Method to update user details in usertable
	     */
		@Override
		public void updateUserDetails(String uid,long actId) throws BankingException
		{
			UserBean userFound= new UserBean();
			userFound = entityManager.find(UserBean.class, actId);
			
			entityManager.merge(userFound);
			
			//String sql = "UPDATE USERTABLE SET ACCOUNT_ID=?,LOGIN_PASSWORD=? WHERE USER_ID=?";	
		}

		/**
		 * Method to insert account record into accountmaster table
		 */
		@Override
		public void insertIntoAccountMaster(AccountBean accountBean) throws BankingException {
			// TODO Auto-generated method stub
			entityManager.persist(accountBean);
			entityManager.flush();
		}
        
		/**
		 * Method to update Customer address in customer table 
		 */
		@Override
		public void updateCustomerAddress(CustomerBean customer) throws BankingException {
			// TODO Auto-generated method stub
		    entityManager.merge(customer);
		    entityManager.flush();
		}
       
		/**
		 * Method to validate user against the userId provided
		 */
		@Override
		public UserBean validateUser(String userId) throws BankingException {
	    //String qry = "Select user from UserBean user where userId=:puserId";
		TypedQuery<UserBean> query = entityManager.createQuery(QueryMapper.validateUserQuery, UserBean.class);
		query.setParameter("puserId", userId);
		UserBean user = query.getSingleResult();
			return user;
		}
        /**
         * Method to obtain customer details against the accountId provided
         */
		@Override
		public CustomerBean viewCustomer(long accId) throws BankingException {
			CustomerBean customer = entityManager.find(CustomerBean.class, accId);
			return customer;
		}
		
        /**
         * Method to view AccountId against the userId provided by Customer
         */
		@Override
		public UserBean viewAccountId(String userId) throws BankingException {
			UserBean user = entityManager.find(UserBean.class, userId);
			return user;
		}
		
        /**
         * Method to view Account details of customer for the accountId provided 
         */
		@Override
		public AccountBean viewAccount(long accId) throws BankingException {
			AccountBean account = entityManager.find(AccountBean.class, accId);
			return account;
		}
       
		/**
		 * Method to update Account balance of customer in account-master table
		 */
		@Override
		public AccountBean updateBalance(AccountBean account) throws BankingException {
			AccountBean accountBean = entityManager.merge(account);
			//String qry = "Update AccountBean account set account.accountBalance = account.accountBalance-:amount";		
			return accountBean;
		}
        /**
         * Method to obtain userName of customer by accountId
         */
		@Override
		public UserBean getUserName(long accId) throws BankingException {
			UserBean user = entityManager.find(UserBean.class, accId);
			return user;
		}

		/**
		 * Method to obtain last 10 transactions details of customer with given accountId
		 */
		@Override
		public List<TransactionsBean> viewMiniStatement(long accId) throws BankingException {
			//String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(QueryMapper.viewMiniStatementQuery, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> miniStatement = query.setMaxResults(10).getResultList();
			return miniStatement;
		}
        
/*		*//**
		 * Method to obtain all transactions performed by the customer with given accountId
		 *//*
		@Override
		public TransactionsBean viewDetailsOfTransactions(long accId) {
			//String qry = "Select transaction from TransactionsBean transaction where transaction.accountNumber=:paccNo";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(QueryMapper.TransactionDetailsQuery, TransactionsBean.class);
			TransactionsBean transaction = query.getSingleResult();
			return transaction;
		}*/
		
        /**
         * Method to obtain periodic transactions details of customer with given accountId
         */
		@Override
		public List<TransactionsBean> viewDetailStatement(long accId,Date initDate,Date finDate) throws BankingException {
			//String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId and transaction.dateOfTransaction between :pdate1 and :pdate2 order by transaction.dateOfTransaction";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(QueryMapper.viewDetailedStatementQuery,TransactionsBean.class);
			query.setParameter("paccountId", accId);
			query.setParameter("pdate1", initDate);
			query.setParameter("pdate2",finDate);
			List<TransactionsBean> detailStatement = query.getResultList();
			return detailStatement;
		}
		
        /**
         * Method to fetch all transactions details for the customer
         */
		@Override
		public List<TransactionsBean> adminViewTransactions(long accId) throws BankingException {
			//String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(QueryMapper.adminViewTransactionsQuery, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> adminStatement = query.getResultList();
			return adminStatement;
		}

		
/*		@Override
		public UserBean openAccount(UserBean userBean) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CustomerBean changeAddress(CustomerBean customer) {
			// TODO Auto-generated method stub
			return null;
		}*/
		
		/**
		 * Method to insert transaction record into transactions table in database
		 */
		@Override
		public TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException
		{
			entityManager.persist(transaction);
			return transaction;
		}

		/**
		 * Method to fetch customer by accountId given
		 */
		@Override
		public CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException {
			// TODO Auto-generated method stub
			CustomerBean customerFound = new CustomerBean();
			customerFound = entityManager.find(CustomerBean.class, accountId);
			return customerFound;
		}

		/**
		 * Method to fetch account Id for a user from customer table
		 */
		@Override
		public long fetchAccountIdFromCustomer(String userName) throws BankingException{
			// TODO Auto-generated method stub 
			//String qry = "Select customer from CustomerBean customer where customer.userId=:puId";
			TypedQuery<CustomerBean> query = entityManager.createQuery(QueryMapper.selectCustomerQuery, CustomerBean.class);
			query.setParameter("puId", userName);
			CustomerBean customer= query.getSingleResult();
			return customer.getAccountId();
		}

		/**
		 * Method to fetch Account details for the accountId provided
		 */
		@Override
		public AccountBean fetchAccountByAccountId(long accountId) throws BankingException{
			// TODO Auto-generated method stub
			AccountBean accountFound = new AccountBean();
			accountFound = entityManager.find(AccountBean.class, accountId);
			return accountFound;
		}

		/**
		 * Method to insert payee record in payee table
		 */
		@Override
		public PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException{
			// TODO Auto-generated method stub
			entityManager.persist(payee);
			return payee;
		}
		
		/**
		 * Method to insert fund transfer details for every transaction.
		 */
		@Override
		public void insertFundTransferDetails(
				FundTransferBean fundTransfer) throws BankingException
		{
			entityManager.persist(fundTransfer);
			entityManager.flush();
		}
        
		/**
		 * Method to update account balance for payee
		 */
		@Override
		public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException {
			AccountBean accountOfPayee =  entityManager.merge(accountByPayeeId);
		  return accountOfPayee;
		}
		
		}
