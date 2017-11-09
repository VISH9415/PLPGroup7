package com.cg.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.UserBean;
import com.cg.project.exception.BankingException;

public class BankingDAOImpl implements IBankingDAO{

	//private static Logger logger = Logger.getLogger(com.cg.project.dao.BankingDAOImpl.class);
	//open account 
	
	@PersistenceContext
	EntityManager entityManager;
	UserBean user;
	public BankDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserBean registerUser(UserBean userBean) {
		entityManager.persist(userBean);
		entityManager.flush();
		return userBean;
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
	
	@Override
	public UserBean fetchUserById(String uid) throws BankingException
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
		public void updateUserDetails(String uid,long actId) throws BankingException
		{
			UserBean userFound= new UserBean();
			userFound = entityManager.find(UserBean.class, uid);
			
			entityManager.merge(userFound);
			
			//String sql = "UPDATE USERTABLE SET ACCOUNT_ID=?,LOGIN_PASSWORD=? WHERE USER_ID=?";	
		}
	
		
		
		@Override
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
		}
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

	/*
   


	@Override
	public int fetchUserByActId(long acid) throws BankingException
	{
      //use list and get the size...
	  //String sql = "Select count(*) from usertable where account_id=?";	
	}

	@Override
	public int updateCustomerAddress(long actId,String address) throws BankingException
	{
		CustomerBean customerFound= new CustomerBean();
		customerFound = entityManager.find(CustomerBean.class,actId);
		
		    entityManager.merge(customerFound);
			//String sql = "UPDATE CUSTOMER SET ADDRESS=? WHERE ACCOUNT_ID=?";	
	} 

	@Override
	public HashMap<Long, List<TransactionsBean>> viewMiniStatement(long accId) throws BankingException
	{
		Date dateOfTrans = null;
		HashMap<Long, List<TransactionsBean>> miniMap = new HashMap<>();
		Connection conn = null;
		PreparedStatement pst = null;
		try{
			conn = DBUtil.createConnection();
			String sql = "select * from (select * from TRANSACTIONS order by DATE_OF_TRANSACTION desc) where rownum<=10 and account_no ="+accId;
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<TransactionsBean> list = new ArrayList<TransactionsBean>();
			while(rs.next()){
				TransactionsBean transactionsBean = new TransactionsBean(rs.getLong(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getDouble(5),rs.getLong(6));
				list.add(transactionsBean);
			}
	}

	@Override
	public HashMap<Long, List<TransactionsBean>> viewDetailedStatement(long accId,Date date1,Date date2) throws BankingException 
	{
		HashMap<Long, List<TransactionsBean>> detMap = new HashMap<>();
		Connection conn = null;
		PreparedStatement pst = null;
		try{
			conn = DBUtil.createConnection();
			String sql = "SELECT * FROM TRANSACTIONS WHERE ACCOUNT_NO=? AND DATE_OF_TRANSACTION BETWEEN ? AND ?";
			pst = conn.prepareStatement(sql);
			pst.setLong(1,accId);
			pst.setDate(2, date1);
			pst.setDate(3, date2);
			ResultSet rs = pst.executeQuery();
			List<TransactionsBean> list = new ArrayList<TransactionsBean>();
			while(rs.next()){
				TransactionsBean transactionsBean = new TransactionsBean(rs.getLong(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getDouble(5),rs.getLong(6));
				list.add(transactionsBean);
			}
			detMap.put(accId,list);
			logger.info("");

		}catch(SQLException se){
			logger.error("");
			throw new BankingException("Error in fetching detailed statement");
		}
		return detMap;
	}
	
	@Override
	public int serviceTracking(ServiceTrackerBean service,long accId) throws BankingException
	{
		int status=0;
		Connection conn=null;
		PreparedStatement pst = null;
		String sql = "INSERT INTO SERVICETRACKER VALUES(SERV_SEQ.NEXTVAL,?,?,?,?)";
		try {
			conn = DBUtil.createConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1,service.getServiceDescription());
			pst.setLong(2,accId);
			pst.setDate(3,service.getServiceRaisedDate());
			pst.setString(4,service.getServiceStatus());
			status = pst.executeUpdate();
			logger.info("");

		} catch (SQLException e) {
			logger.error("");
			throw new BankingException("Error in insertion into servicetracker");
		}
		System.out.println(status +" Service registered...");
		return status;	
	}

	@Override
	public Date fetchOpenDate(long accId) throws BankingException
	{
		Connection conn = null;
		PreparedStatement pst = null;
		Date opdate = null;
		try{
			conn = DBUtil.createConnection();
			String sql = "Select open_date from accountmaster where account_Id="+accId;
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				opdate = rs.getDate(1);
			}
			logger.info("");

		}catch(SQLException se){
			logger.error("");
			throw new BankingException("Error in fetching opendate");
		}
		return opdate;
	}
	

	
	@Override
	public long getPreviousUser(String uId) throws BankingException
	{
		Connection conn = null;
		PreparedStatement pst = null;
		UserBean userBean = new UserBean();
		try{
			conn = DBUtil.createConnection();
			String sql = "Select account_id from usertable where USER_ID= ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1,uId);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				userBean.setAccountId(rs.getLong(1));
			}
			logger.info("");

		}catch(SQLException se){
			logger.error("");
			throw new BankingException("Error in fetching account_id");
		}
		return userBean.getAccountId() ;
	}

	@Override
	public int insertService(ServiceTrackerBean serviceTracker) throws BankingException
	{
		int count = 0;
		Connection conn =null;
		PreparedStatement pst= null;
		String sql = new String("INSERT INTO SERVICETRACKER VALUES(serv_seq.nextval,?,?,?,?)");
		try{
			conn = DBUtil.createConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1,serviceTracker.getServiceDescription());
			pst.setLong(2,serviceTracker.getAccountId());
			pst.setDate(3,serviceTracker.getServiceRaisedDate());
			pst.setString(4,serviceTracker.getServiceStatus());
			count = pst.executeUpdate();
			logger.info("");

		}catch(SQLException se){
			logger.error("");
			throw new BankingException("Record not inserted into servicetracker");
		}
		finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se){
				logger.error("");
				throw new BankingException("Problems in closing connection");
			}
			System.out.println(count+" service results affected");
		}
		return count;
	}
	@Override
	public int insertPayee(PayeeBean payeeBean) throws BankingException {
		int count = 0;
		Connection conn =null;
		PreparedStatement pst= null;
		String sql = new String("INSERT INTO PAYEETABLE VALUES(?,?,?)");
		try{
			conn = DBUtil.createConnection();
			pst = conn.prepareStatement(sql);
			pst.setLong(1,payeeBean.getAccountId());
			pst.setLong(2,payeeBean.getPayeeAccountId());
			pst.setString(3,payeeBean.getNickName());
			count = pst.executeUpdate();
			logger.info("");
		}catch(SQLException se){
			logger.error("");
			throw new BankingException("Record not inserted into payeeTable ");
		}
		finally
		{
			try
			{
				DBUtil.closeConnection();
			}catch(SQLException se){
				logger.error("");
				throw new BankingException("Problems in closing connection");
			}
			System.out.println(count+" Payee results affected");
		}
		return count;
	}
	@Override
	public List<UserBean> viewAccountHolders() throws BankingException {
		Connection conn = null;
		PreparedStatement pst = null;
		//UserBean userBean = new UserBean();
		List<UserBean> userList = new ArrayList<>();
		try{
			conn = DBUtil.createConnection();
			String sql = "Select * from usertable where account_id!=0";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
			UserBean userBean = new UserBean(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			userList.add(userBean);
			}
			logger.info("");

		}catch(SQLException se){
			logger.error("");
			throw new BankingException("Error in fetching user records");
		}
		
		return userList;
	}
	@Override
	public List<TransactionsBean> viewTransactionsDetails()	throws BankingException {
		Connection conn = null;
		PreparedStatement pst = null;
		//UserBean userBean = new UserBean();
		List<TransactionsBean> transList = new ArrayList<>();
		try{
			conn = DBUtil.createConnection();
			String sql = "Select * from transactions";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
			TransactionsBean transactionsBean= new TransactionsBean(rs.getLong(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getDouble(5),rs.getLong(6));
			transList.add(transactionsBean);
			}
			logger.info("");

		}catch(SQLException se){
			logger.error("");
			throw new BankingException("Error in fetching transaction details");
		}
		
		return transList;
	}*/
}