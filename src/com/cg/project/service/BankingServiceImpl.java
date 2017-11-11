package com.cg.project.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.FundTransferBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.dao.IBankingDAO;
import com.cg.project.exception.BankingException;
@Service
public class BankingServiceImpl implements IBankingService {
   
	@Autowired
	IBankingDAO dao;
	
	@Override
	public UserBean fetchUserById(String userName)
			throws BankingException {
		return dao.fetchUserById(userName);
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
	public void insertIntoCustomer(CustomerBean customer) {
		// TODO Auto-generated method stub
		dao.insertIntoCustomer(customer);
	}
	
	public TransactionsBean insertTransactionDetails(TransactionsBean transaction)
	{
		return dao.insertTransactionDetails(transaction);
	}


	@Override
	public void updateUserDetails(String uid, long actId) {
		// TODO Auto-generated method stub
		dao.updateUserDetails(uid, actId);
	}

	@Override
	public void registerUser(UserBean userBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserBean updateAccountIdinUser(UserBean userBean) {
		// TODO Auto-generated method stub
		return dao.updateAccountIdinUser(userBean);
	}

	@Override
	public UserBean validateUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerBean changeAddress(CustomerBean customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerBean viewCustomer(long accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean viewAccountId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountBean viewAccount(long accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean getUserName(long accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionsBean> viewDetailStatement(long accId,
			Date initDate, Date finDate) {
		// TODO Auto-generated method stub
		return dao.viewDetailStatement(accId, initDate, finDate);
	}

	@Override
	public List<TransactionsBean> adminViewTransactions(long accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertIntoAccountMaster(AccountBean accountBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomerAddress(CustomerBean customer) {
		// TODO Auto-generated method stub
		dao.updateCustomerAddress(customer);  
	}

	@Override
	public CustomerBean fetchCustomerByAccountId(long accountId) {
		// TODO Auto-generated method stub
		return dao.fetchCustomerByAccountId(accountId);
	}

	@Override
	public long fetchAccountIdFromCustomer(String userName) {
		// TODO Auto-generated method stub
		return dao.fetchAccountIdFromCustomer(userName);
	}

	@Override
	public AccountBean fetchAccountByAccountId(long accountId) {
		// TODO Auto-generated method stub
		return dao.fetchAccountByAccountId(accountId);
	}

	@Override
	public List<TransactionsBean> viewMiniStatement(long accId) {
		// TODO Auto-generated method stub
		return dao.viewMiniStatement(accId);
	}

	@Override
	public PayeeBean insertPayeeDetails(PayeeBean payee) {
		// TODO Auto-generated method stub
		return dao.insertPayeeDetails(payee);
	}

	@Override
	public void insertFundTransferDetails(FundTransferBean fundTransfer) {
		// TODO Auto-generated method stub
       dao.insertFundTransferDetails(fundTransfer);}

}
