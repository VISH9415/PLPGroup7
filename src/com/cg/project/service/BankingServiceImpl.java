package com.cg.project.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.dao.IBankingDAO;
import com.cg.project.exception.BankingException;
@Service
public class BankingServiceImpl implements IBankingService {
   
	@Autowired
	IBankingDAO dao;
	
	@Override
	public UserBean fetchUserById(String uid)
			throws BankingException {
		return dao.fetchUserById(uid);
	}
	
	@Override
	public int validateAdmin(String adminId, String adminPassword) {
		return dao.validateAdmin(adminId, adminPassword);
		
	}
	
	public void updateloginpassword(UserBean userBean)
	{
	    dao.updateloginpassword(userBean);
	}

	@Override
	public CustomerBean insertIntoCustomer(CustomerBean customer) {
		// TODO Auto-generated method stub
		return dao.insertIntoCustomer(customer);
	}

	@Override
	public UserBean updateAccountIdinUser(long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerUser(UserBean userBean) {
		// TODO Auto-generated method stub
		dao.registerUser(userBean);
	}

	@Override
	public UserBean validateUser(String userId) {
		return dao.validateUser(userId);
	}

	@Override
	public CustomerBean changeAddress(CustomerBean customer) {
		return dao.changeAddress(customer);
	}

	@Override
	public CustomerBean viewCustomer(long accId) {
		return dao.viewCustomer(accId);
	}

	@Override
	public UserBean viewAccountId(String userId) {
		return dao.viewAccountId(userId);
	}

	@Override
	public AccountBean viewAccount(long accId) {
		return dao.viewAccount(accId);
	}

	@Override
	public UserBean getUserName(long accId) {
		return dao.getUserName(accId);
	}

	@Override
	public List<TransactionsBean> vewMiniStatement(long accId) {
		return dao.vewMiniStatement(accId);
	}

	@Override
	public List<TransactionsBean> viewDetailStatement(long accId,Date initDate,Date finDate) {
		return dao.viewDetailStatement(accId,initDate,finDate);
	}
	public UserBean updateAccountIdinUser(UserBean userBean) {
		// TODO Auto-generated method stub
		return dao.updateAccountIdinUser(userBean);
	}

	@Override
	public List<TransactionsBean> adminViewTransactions(long accId) {
		return dao.adminViewTransactions(accId);
	}

	@Override
	public void insertIntoAccountMaster(AccountBean accountBean) {
		// TODO Auto-generated method stub
		dao.insertIntoAccountMaster(accountBean);
	}

/*	@Override
	public void updateAccountIdinUser(long accountId) {
		// TODO Auto-generated method stub
		dao.updateAccountIdinUser(accountId);
	}*/

	@Override
	public void updateUserDetails(String uid, long actId) {
		// TODO Auto-generated method stub
		dao.updateUserDetails(uid, actId);
	}
	
	/*@Override
	public int updateUserDetails(UserBean userBean, String uid, long actId)
			throws BankingException {
		// TODO Auto-generated method stub
		return dao.updateUserDetails(userBean, uid, actId);
	}

	@Override
	public int fetchUserByActId(long acid) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchUserByActId(acid);
	}

	@Override
	public int updateCustomerAddress(long actId, String address)
			throws BankingException {
		// TODO Auto-generated method stub
		return dao.updateCustomerAddress(actId, address);
	}

	@Override
	public HashMap<Long, List<TransactionsBean>> viewMiniStatement(long accId)
			throws BankingException {
		// TODO Auto-generated method stub
		return dao.viewMiniStatement(accId);
	}

	@Override
	public HashMap<Long, List<TransactionsBean>> viewDetailedStatement(
			long accId, Date date1, Date date2) throws BankingException {
		// TODO Auto-generated method stub
		return dao.viewDetailedStatement(accId, date1, date2);
	}

	@Override
	public int serviceTracking(ServiceTrackerBean service, long accId)
			throws BankingException {
		// TODO Auto-generated method stub
		return dao.serviceTracking(service, accId);
	}

	@Override
	public Date fetchOpenDate(long accId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchOpenDate(accId);
	}

	@Override
	public int insertAccount(AccountBean account) throws BankingException {
		// TODO Auto-generated method stub
		return dao.insertAccount(account);
	}

	@Override
	public long getPreviousUser(String uId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.getPreviousUser(uId);
	}

	@Override
	public int insertService(ServiceTrackerBean serviceTracker)
			throws BankingException {
		// TODO Auto-generated method stub
		return dao.insertService(serviceTracker);
	}

	@Override
	public int insertPayee(PayeeBean payeeBean) throws BankingException {
			return dao.insertPayee(payeeBean);
	}

	@Override
	public List<UserBean> viewAccountHolders() throws BankingException {
		return dao.viewAccountHolders();
	}

	@Override
	public List<TransactionsBean> viewTransactionsDetails()
			throws BankingException {
		return dao.viewTransactionsDetails();
	}*/

}
