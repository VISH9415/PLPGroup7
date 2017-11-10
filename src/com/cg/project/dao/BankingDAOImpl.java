package com.cg.project.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
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
		
/*		@Override
		public void updateAccountIdinUser(String accountId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public UserBean validateUser(String userId) {
			// TODO Auto-generated method stub
			return null;
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
		}*/


}