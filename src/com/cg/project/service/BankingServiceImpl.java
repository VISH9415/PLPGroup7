package com.cg.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.project.bean.CustomerBean;
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

	@Override
	public CustomerBean insertIntoCustomer(CustomerBean customer) {
		// TODO Auto-generated method stub
		return dao.insertIntoCustomer(customer);
	}

	/*@Override
	public void updateAccountIdinUser(String accountId) {
		// TODO Auto-generated method stub
		dao.updateAccountIdinUser(accountId);
	}*/
/*	public BankingServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AccountBean fetchAccounts(long actId) throws BankingException {
		return dao.fetchAccounts(actId);
	}

	@Override
	public double fetchAmount(long actId) throws BankingException {
		return dao.fetchAmount(actId);
	}

	@Override
	public long openAccount(CustomerBean customerBean) throws BankingException {
		// TODO Auto-generated method stub
		return dao.openAccount(customerBean);
	}

	@Override
	public CustomerBean validateId(long actId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.validateId(actId);
	}

	@Override
	public String deposit(long actId, double amount, AccountBean accountBean)
			throws BankingException {
		// TODO Auto-generated method stub
		return dao.deposit(actId, amount, accountBean);
	}

	@Override
	public String depositUpdate(long actId, double amount)
			throws BankingException {
		// TODO Auto-generated method stub
		return dao.depositUpdate(actId, amount);
	}

	@Override
	public void transactionUpdate(long accId, TransactionsBean transBean)
			throws BankingException {
			dao.transactionUpdate(accId, transBean);
	}

	@Override
	public String withdraw(long accId, double amount) throws BankingException {
		// TODO Auto-generated method stub
		return dao.withdraw(accId, amount);
	}

	@Override
	public void sendfund(double amt, long actId, long pactId)
			throws BankingException {
		// TODO Auto-generated method stub
			dao.sendfund(amt, actId, pactId);
	}

	@Override
	public String validatePassword(String uId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.validatePassword(uId);
	}

	@Override
	public int registerUser(UserBean userBean) throws BankingException {
		// TODO Auto-generated method stub
		return dao.registerUser(userBean);
	}
*/

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
