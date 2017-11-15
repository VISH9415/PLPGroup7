package com.cg.project.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.UserBean;
import com.cg.project.dao.IBankingDAO;
import com.cg.project.service.BankingServiceImpl;
import com.cg.project.service.IBankingService;

@RunWith(MockitoJUnitRunner.class)
public class BankingTest {
	@Mock
	private IBankingDAO bankingDao;
	
	@InjectMocks
	private IBankingService bankingService = new BankingServiceImpl();
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testValidateAdmin() throws Exception{
		String adminId = "sam";
		String adminPass = "ADMIN1234";
		int count = 1;
		stub(bankingDao.validateAdmin(adminId, adminPass)).toReturn(count);
		assertEquals(count,bankingService.validateAdmin(adminId, adminPass));
		verify(bankingDao).validateAdmin(adminId, adminPass);
	}
	
	@Test
	public void testFetchUserById() throws Exception{
		String userName = "sam";
		UserBean user = new UserBean(10012002,"sam","sam04@12","0214@1114","nope","What is your favorite color?","Black");
		stub(bankingDao.fetchUserById(userName)).toReturn(user);
		assertEquals(user,bankingService.fetchUserById(userName));
		verify(bankingDao).fetchUserById(userName);
	}
	
	@Test
	public void testUpdateAccountIdinUser() throws Exception{
		UserBean userBean = new UserBean();
		UserBean user = new UserBean(10012002,"sam","sam04@12","0214@1114","nope","What is your favorite color?","Black");
		stub(bankingDao.updateAccountIdinUser(userBean)).toReturn(user);
		assertEquals(user,bankingService.updateAccountIdinUser(userBean));
		verify(bankingDao).updateAccountIdinUser(userBean);
	}
	
	@Test
	public void testUpdateBalance() throws Exception{
		AccountBean accountBean = new AccountBean();
		Date openDate = Date.valueOf(LocalDate.now());
		AccountBean account = new AccountBean(10012002,"Savings",openDate,5000.00);
		stub(bankingDao.updateBalance(accountBean)).toReturn(account);
		assertEquals(account,bankingService.updateBalance(accountBean));
		verify(bankingDao).updateBalance(accountBean);
	}	
}