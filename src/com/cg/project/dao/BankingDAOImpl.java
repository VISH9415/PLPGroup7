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
	public UserBean fetchUserById(String uid) 
	{
		UserBean userFound= new UserBean();
		userFound = entityManager.find(UserBean.class, uid);
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
	
	public CustomerBean insertIntoCustomer(CustomerBean customer)
	{
		entityManager.persist(customer);
		entityManager.flush();
		return customer;
	}
	
/*	public void updateAccountIdinUser(long accountId)
	{
		entityManager.merge(accountId);
		
	}*/
	
	//updating address and password based on request..
		@Override
		public void updateUserDetails(String uid,long actId) 
		{
			UserBean userFound= new UserBean();
			userFound = entityManager.find(UserBean.class, uid);
			
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
		public CustomerBean changeAddress(CustomerBean customer) {
			entityManager.merge(customer);
			return customer;
		}

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
		public List<TransactionsBean> vewMiniStatement(long accId) {
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
		}
