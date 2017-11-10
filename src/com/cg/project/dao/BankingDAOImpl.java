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
		entityManager.persist(userBean);
		entityManager.flush();
	}
	
	@Override
	public UserBean fetchUserById(String uid) 
	{
		//HashMap<String,UserBean> userMap = new HashMap<String,UserBean>();
		UserBean userFound= new UserBean();
		userFound = entityManager.find(UserBean.class, uid);
		//userMap.put(uid,userFound);
		return userFound;	
	}
	
	@Override
	public int validateAdmin(String adminId, String adminPassword) {
		// TODO Auto-generated method stub
		int count = 0;
	if(adminId.equals("ViVaHaKa")&&adminPassword.equals("ADMIN1234"))
	{
		count++;
	}
	return count;
	}
/*

	@Override
	public UserBean registerUser(UserBean userBean) {
		entityManager.persist(userBean);
		entityManager.flush();
		return userBean;
	}

	@Override
	public HashMap<String, UserBean> fetchUserById(String uid) {
		HashMap<String,UserBean> userMap = new HashMap<String,UserBean>();
		UserBean userFound= new UserBean();
		userFound = entityManager.find(UserBean.class, uid);
		userMap.put(uid,userFound);
		return userMap;	
	}

	@Override
	public UserBean validateUser(String userId) {
		String qry = "SELECT user from UserBean user where userId =:puserId";
		TypedQuery<UserBean> query = entityManager.createQuery(qry, UserBean.class);
		query.setParameter("puserId", userId);
		user = query.getSingleResult();
		return user;
	}

	@Override
	public UserBean openAccount(UserBean userBean) {
		entityManager.persist(userBean);
		entityManager.flush();
		return userBean;
	}
	public CustomerBean selectCustomer(CustomerBean customerBean){
		String qry = "SELECT customer from CustomerBean customer where accountId=:paccountId";
		TypedQuery<CustomerBean> query = entityManager.createQuery(qry, CustomerBean.class);
		query.setParameter("paccountId", customerBean.getAccountId());
		customerBean = query.getSingleResult();
		return customerBean;
	}*/
	

	

	
	public CustomerBean insertIntoCustomer(CustomerBean customer)
	{
		entityManager.persist(customer);
		entityManager.flush();
		return customer;
	}
	
	public void updateAccountIdinUser(long accountId)
	{
		entityManager.merge(accountId);
		
	}
	//updating address and password based on request..
		@Override
		public void updateUserDetails(String uid,long actId) 
		{
			UserBean userFound= new UserBean();
			userFound = entityManager.find(UserBean.class, uid);
			
			entityManager.merge(userFound);
			
			//String sql = "UPDATE USERTABLE SET ACCOUNT_ID=?,LOGIN_PASSWORD=? WHERE USER_ID=?";	
		}
	
		
		
		/*@Override
		public int insertAccount(AccountBean account) throws BankingException {
			Connection conn = null;
			PreparedStatement pst = null;
			try{
				conn = DBUtil.createConnection();
				String sql2 = "INSERT INTO ACCOUNTMASTER VALUES(?,?,?,?)";
				int count = 0;
				pst = conn.prepareStatement(sql2);
				pst.setLong(1,account.getAccountId());
				pst.setString(2,account.getAccountType());
				pst.setDouble(3,account.getAccountBalance());
				pst.setDate(4,account.getOpenDate());
				count = pst.executeUpdate();
				//logger.info("");

				System.out.println(count+"accounts inserted...");
				return count;
			}catch(SQLException se){
				//logger.error("");
				throw new BankingException("Error in insertion into accountmaster");
			}
		}*/
/*	@Override
	public AccountBean fetchAccounts(long actId) throws BankingException
	{
		AccountBean account = new AccountBean();
		account = entityManager.find(AccountBean.class, actId);
		return account;
		//logger.info("Account records fetched for given account id");
		
		//logger.error("Error in fetching records..");
		//throw new BankingException("Error in fetching records..");
	}

	//show balance
	@Override  
	public double fetchAmount(long actId) throws BankingException
	{
		AccountBean account = new AccountBean();
		account = entityManager.find(AccountBean.class, actId);
		return account.getAccountBalance();
	}

    @Override
	public void openAccount(CustomerBean customerBean) throws BankingException
	{
	entityManager.persist(customerBean);
	//acc_seq for acct id ...
	}
	
	@Override
	public CustomerBean validateId(long actId) throws BankingException
	{
		CustomerBean customerBean= new CustomerBean();
		customerBean= entityManager.find(CustomerBean.class, actId);
		return customerBean;
	}

		public int validateAccountByUserId(long userId) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pst = null;

		conn = DBUtil.createConnection();
		String sql = "Select  from  where account_Id="
		pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while(rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	@Override
	public void deposit(long actId,double amount,AccountBean accountBean) throws BankingException
	{
		AccountBean account = new AccountBean();
		account = entityManager.find(AccountBean.class, actId);
		String accountType=  account.getAccountType();
		System.out.println("act type is: "+accountType);
       
		//both setting need to be done in client side..
		accountBean.setAccountType(accountType);
		accountBean.setAccountId(actId);
		accountBean.setAccountBalance(amount);
		entityManager.persist(accountBean);//check for date also...
	}

	
	
	
	
	@Override
	public AccountBean depositUpdate(long actId,double Amount,AccountBean accountBean) throws BankingException {
		
		AccountBean account = new AccountBean();
		account = entityManager.find(AccountBean.class, actId);
		
	  AccountBean depositedAccount= entityManager.merge(account);
		//String sql1 = "UPDATE ACCOUNTMASTER SET ACCOUNT_BALANCE = ACCOUNT_BALANCE + ? WHERE ACCOUNT_ID = ?";
        return depositedAccount;
	}
   
	
	
	@Override
	public void transactionUpdate(long accId,TransactionsBean transBean ) throws BankingException
	{
	
	    entityManager.persist(transBean);
		//String sql1 = "INSERT INTO TRANSACTIONS VALUES(tran_seq.nextval,?,?,?,?,?)";
	}

	
	@Override
	public AccountBean withdraw(long accId,double amount) throws BankingException
	{
		AccountBean account = new AccountBean();
		account = entityManager.find(AccountBean.class, accId);
		AccountBean withdrawnAccount = entityManager.merge(account);
		return withdrawnAccount;
	}

	@Override
	public void sendfund(double amt, long actId, long pactId,AccountBean accountBean) throws BankingException
	{
		//for sender...
		AccountBean senderAccount = new AccountBean();
		senderAccount = entityManager.find(AccountBean.class, actId);
		
		AccountBean account1 = entityManager.merge(senderAccount);
		
		//count records with account_id as actId...
	
		AccountBean recieverAccount = new AccountBean();
		recieverAccount = entityManager.find(AccountBean.class, pactId);
		
		AccountBean account2 = entityManager.merge(recieverAccount);
		
		
		entityManager.persist(account1);
		entityManager.persist(account2);
		
        FundTransferBean fundTransfer = new FundTransferBean();
        entityManager.persist(fundTransfer);
	}
	
	@Override
	public String validatePassword(String uId) throws BankingException{
        UserBean userBean = new UserBean();
        userBean = entityManager.find(UserBean.class, uId);
	//		String sql = "Select login_password from usertable where user_id=?";
	return userBean.getLoginPassword();
	}

	@Override
	public int registerUser(UserBean userBean) throws BankingException
	{
    //UserBean userBean = new UserBean();
    entityManager.persist(userBean);
	}*/

		@Override
		public void updateAccountIdinUser(String accountId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UserBean validateUser(String userId) {
		String qry = "Select user from UserBean user where userId=:puserId";
		TypedQuery<UserBean> query = entityManager.createQuery(qry, UserBean.class);
		query.setParameter("puserId", userId);
		UserBean user = query.getSingleResult();
			return user;
		}

		@Override
		public UserBean openAccount(UserBean userBean) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int insertAccount(AccountBean account) {
			// TODO Auto-generated method stub
			return 0;
		}

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