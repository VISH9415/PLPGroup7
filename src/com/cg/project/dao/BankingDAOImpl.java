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

@Repository
@Transactional
public class BankingDAOImpl implements IBankingDAO{

	//private static Logger logger = Logger.getLogger(com.cg.project.dao.BankingDAOImpl.class);
	//open account 
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void registerUser(UserBean userBean) {
		System.out.println("userBean is: "+userBean);
		System.out.println("in dao..register");
		entityManager.persist(userBean);
		entityManager.flush();
	}
	
	@Override
	public UserBean fetchUserById(String userName) 
	{
		UserBean userFound= new UserBean();
		userFound = entityManager.find(UserBean.class, userName);
		return userFound;	
	}
	
	@Override
	public int validateAdmin(String adminId, String adminPassword) {
		// TODO Auto-generated method stub
		int count = 0;
		System.out.println("in dao ... validate admin");
	if(adminId.equals("ViVaHaKa")&&adminPassword.equals("ADMIN1234"))
	{
		count++;
	}
	return count;
	}
	
	public void updateloginpassword(UserBean userBean)
	{
		entityManager.merge(userBean);
		entityManager.flush();
	}

	public UserBean updateAccountIdinUser(UserBean userBean)
	{
		UserBean user = entityManager.merge(userBean);
		return user;
	}
	
	public void insertIntoCustomer(CustomerBean customer)
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
	
	//updating address and password based on request..
		@Override
		public void updateUserDetails(String uid,long actId) 
		{
			UserBean userFound= new UserBean();
			userFound = entityManager.find(UserBean.class, actId);
			
			entityManager.merge(userFound);
			
			//String sql = "UPDATE USERTABLE SET ACCOUNT_ID=?,LOGIN_PASSWORD=? WHERE USER_ID=?";	
		}

		@Override
		public void insertIntoAccountMaster(AccountBean accountBean) {
			// TODO Auto-generated method stub
			entityManager.persist(accountBean);
			entityManager.flush();
		}

		@Override
		public void updateCustomerAddress(CustomerBean customer) {
			// TODO Auto-generated method stub
		    entityManager.merge(customer);
		    entityManager.flush();
		}
		
/*		@Override
=======
		public UserBean openAccount(UserBean userBean) {
			// TODO Auto-generated method stub
			return null;
		}

	/*	@Override
		public AccountBean viewAccount(long accId) {
			// TODO Auto-generated method stub
			return null;
		}*/

	/*	@Override
		public AccountBean updateBalance(long accId, AccountBean account) {
			// TODO Auto-generated method stub
			return null;
		}*/

	/*	@Override
		public UserBean getUserName(long accId) {
			// TODO Auto-generated method stub
			return null;
		}*/

		/*@Override
		public AccountBean updateBalance(long accId, double amount) {
			// TODO Auto-generated method stub
			return null;
		}
*/
			
	/*	@Override
>>>>>>> bf6b94265a72433b0d72482278ea3b597142a30a
		public void updateAccountIdinUser(String accountId) {
			// TODO Auto-generated method stub
			
		}*/

		@Override
		public UserBean validateUser(String userId) {
		String qry = "Select user from UserBean user where userId=:puserId";
		TypedQuery<UserBean> query = entityManager.createQuery(qry, UserBean.class);
		query.setParameter("puserId", userId);
		UserBean user = query.getSingleResult();
			return user;
		}

	/*	@Override
		public int insertAccount(AccountBean account) {
			// TODO Auto-generated method stub
			return 0;
		}
*/

		@Override
		public CustomerBean viewCustomer(long accId) {
			CustomerBean customer = entityManager.find(CustomerBean.class, accId);
			return customer;
		}

		@Override
		public UserBean viewAccountId(String userId) {
			UserBean user = entityManager.find(UserBean.class, userId);
			return user;
		}

		@Override
		public AccountBean viewAccount(long accId) {
			AccountBean account = entityManager.find(AccountBean.class, accId);
			return account;
		}

		@Override
		public AccountBean updateBalance(long accId,double amount) {
//			entityManager.merge(account);
			String qry = "Update AccountBean account set account.accountBalance = account.accountBalance-:amount";		
			return null;
		}

		@Override
		public UserBean getUserName(long accId) {
			UserBean user = entityManager.find(UserBean.class, accId);
			return user;
		}

		@Override
		public AccountBean updateBalance(long accId, AccountBean account) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<TransactionsBean> viewMiniStatement(long accId) {
			String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(qry, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> miniStatement = query.setMaxResults(2).getResultList();
			return miniStatement;
		}

		@Override
		public TransactionsBean viewDetailsOfTransactions(long accId) {
			String qry = "Select transaction from TransactionsBean transaction where transaction.accountNumber=:paccNo";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(qry, TransactionsBean.class);
			TransactionsBean transaction = query.getSingleResult();
			return transaction;
		}

		@Override
		public List<TransactionsBean> viewDetailStatement(long accId,Date initDate,Date finDate) {
			String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId and transaction.dateOfTransaction between :pdate1 and :pdate2 order by transaction.dateOfTransaction";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(qry, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			query.setParameter("pdate1", initDate);
			query.setParameter("pdate2",finDate);
			List<TransactionsBean> detailStatement = query.getResultList();
			return detailStatement;
		}

		@Override
		public List<TransactionsBean> adminViewTransactions(long accId) {
			String qry = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
			TypedQuery<TransactionsBean> query = entityManager.createQuery(qry, TransactionsBean.class);
			query.setParameter("paccountId", accId);
			List<TransactionsBean> adminStatement = query.getResultList();
			return adminStatement;
		}

		@Override
		public UserBean openAccount(UserBean userBean) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CustomerBean changeAddress(CustomerBean customer) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public TransactionsBean insertTransactionDetails(TransactionsBean transaction)
		{
			entityManager.persist(transaction);
			return transaction;
		}

		@Override
		public CustomerBean fetchCustomerByAccountId(long accountId) {
			// TODO Auto-generated method stub
			CustomerBean customerFound = new CustomerBean();
			customerFound = entityManager.find(CustomerBean.class, accountId);
			return customerFound;
		}

		@Override
		public long fetchAccountIdFromCustomer(String userName) {
			// TODO Auto-generated method stub 
			String qry = "Select customer from CustomerBean customer where customer.userId=:puId";
			TypedQuery<CustomerBean> query = entityManager.createQuery(qry, CustomerBean.class);
			query.setParameter("puId", userName);
			CustomerBean customer= query.getSingleResult();
			return customer.getAccountId();
		}

		@Override
		public AccountBean fetchAccountByAccountId(long accountId) {
			// TODO Auto-generated method stub
			AccountBean accountFound = new AccountBean();
			accountFound = entityManager.find(AccountBean.class, accountId);
			return accountFound;
		}

		@Override
		public PayeeBean insertPayeeDetails(PayeeBean payee) {
			// TODO Auto-generated method stub
			entityManager.persist(payee);
			return payee;
		}
		
		@Override
		public void insertFundTransferDetails(
				FundTransferBean fundTransfer)
		{
			entityManager.persist(fundTransfer);
		}
		
		}
