package com.cg.project.service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

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
	public int validateAdmin(String adminId, String adminPassword) throws BankingException  {
		return dao.validateAdmin(adminId, adminPassword);
		
	}
	
	@Override
	public void updateloginpassword(UserBean userBean) throws BankingException 
	{
	    dao.updateloginpassword(userBean);
	}

	@Override
	public void insertIntoCustomer(CustomerBean customer) throws BankingException {
		// TODO Auto-generated method stub
		dao.insertIntoCustomer(customer); }
	
	
	@Override
	public TransactionsBean insertTransactionDetails(TransactionsBean transaction) throws BankingException 
	{
		return dao.insertTransactionDetails(transaction);
	}


	@Override
	public void updateUserDetails(String uid, long actId) throws BankingException  {
		// TODO Auto-generated method stub
		dao.updateUserDetails(uid, actId);
	}

	@Override
	public void registerUser(UserBean userBean) throws BankingException{
		// TODO Auto-generated method stub
		dao.registerUser(userBean);
	}

	@Override
	public UserBean updateAccountIdinUser(UserBean userBean) throws BankingException {
		// TODO Auto-generated method stub
		return dao.updateAccountIdinUser(userBean);
	}

/*	@Override
	public UserBean validateUser(String userId) throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerBean changeAddress(CustomerBean customer) throws BankingException  {
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
	}*/

	@Override
	public List<TransactionsBean> viewDetailStatement(long accId,
			Date initDate, Date finDate) throws BankingException {
		// TODO Auto-generated method stub
		return dao.viewDetailStatement(accId, initDate, finDate);
	}

	@Override
	public List<TransactionsBean> adminViewTransactions(long accId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.adminViewTransactions(accId);
	}

	@Override
	public void insertIntoAccountMaster(AccountBean accountBean) throws BankingException {
		// TODO Auto-generated method stub
		dao.insertIntoAccountMaster(accountBean);
	}

	@Override
	public void updateCustomerAddress(CustomerBean customer) throws BankingException {
		// TODO Auto-generated method stub
		dao.updateCustomerAddress(customer);  
	}

	@Override
	public CustomerBean fetchCustomerByAccountId(long accountId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchCustomerByAccountId(accountId);
	}

	@Override
	public long fetchAccountIdFromCustomer(String userName) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchAccountIdFromCustomer(userName);
	}

	@Override
	public AccountBean fetchAccountByAccountId(long accountId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchAccountByAccountId(accountId);
	}

	@Override
	public List<TransactionsBean> viewMiniStatement(long accId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.viewMiniStatement(accId);
	}

	@Override
	public PayeeBean insertPayeeDetails(PayeeBean payee) throws BankingException {
		// TODO Auto-generated method stub
		return dao.insertPayeeDetails(payee);
	}

	@Override
	public void insertFundTransferDetails(FundTransferBean fundTransfer) throws BankingException {
		// TODO Auto-generated method stub
       dao.insertFundTransferDetails(fundTransfer);}

	@Override
	public AccountBean updateBalance(AccountBean account) throws BankingException  {
		// TODO Auto-generated method stub
		return dao.updateBalance(account);
	}

	@Override
	public AccountBean updateBalanceForPayee(AccountBean accountByPayeeId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.updateBalanceForPayee(accountByPayeeId);
	}

	@Override
	public String randPassword() {
		// TODO Auto-generated method stub
        String uuid = UUID.randomUUID().toString();
        String newPassword = uuid.substring(0,8).replace("-", "");
        return newPassword;
	}

	@Override
	public CustomerBean fetchCustomerByUserId(String username)
			throws BankingException {
		return dao.fetchCustomerByUserId(username);
	}

	@Override
	public List<TransactionsBean> fetchTransactionsByAccountId(long accountId) throws BankingException {
		// TODO Auto-generated method stub
		return dao.fetchTransactionsByAccountId(accountId);
	}

}
