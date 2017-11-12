package com.cg.project.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.project.bean.UserBean;
import com.cg.project.dao.BankingDAOImpl;
import com.cg.project.dao.IBankingDAO;
import com.cg.project.exception.BankingException;

public class BankingDAOImplTest {

	private IBankingDAO dao = new BankingDAOImpl();
	
	@Test
	public final void testRegisterUser() {
		UserBean user = new UserBean();
		try {
		//UserBean userBean = new UserBean(1200L,"Pratike","pratikpass","sahooPass","nope","City?","Pune");
		user.setAccountId(1200L);
		dao.registerUser(user);
		}
		catch(BankingException e)
		{
			e.printStackTrace();
		}
		
	}

/*	@Test
	public final void testChangeCustomerAddress() throws BankingException {
		CustomerBean customerBean = new CustomerBean(1100L,"Harshit Bhat","hbhat@gmail.com","Bangalore","SDFGH5687K","Current","habhat");
		dao.updateCustomerAddress(customerBean);
		}*/
/*	@Test
	public final void testViewMiniStatement() throws BankingException {
		long actId = 457889L;
		dao.viewMiniStatement(actId);
	}*/

}
