package com.cg.project.client;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cg.project.bean.AccountBean;
import com.cg.project.bean.CustomerBean;
import com.cg.project.bean.PayeeBean;
import com.cg.project.bean.ServiceTrackerBean;
import com.cg.project.bean.TransactionsBean;
import com.cg.project.bean.UserBean;
import com.cg.project.exception.BankingException;
import com.cg.project.service.BankingServiceImpl;
import com.cg.project.service.IBankingService;

public class Client {
	
	static Scanner sc = new Scanner(System.in);

	static CustomerBean customerBean = null;
	static AccountBean accountBean = null;
	static TransactionsBean transactionBean = null; 
	static IBankingService service =  new BankingServiceImpl();
	static PayeeBean payeeBean = null;
	static UserBean userBean = null;
	static ServiceTrackerBean serviceTrackerBean = null;
	
	public static void main(String[] args) throws BankingException { 	
		
		System.out.println("Welcome to VIVAHAKA Bank");
		boolean homeScreen = true;
		while(homeScreen) {
			System.out.println("Kindly enter your choice: \n1 for New User\n2 for Existing User\n3 for Bank Admin\n4 for exit");
			String user = sc.nextLine();
			switch(user) {
				case "1":
					newUser();
					break;
				case "2":
					existingUser();
					break;
				case "3":
					bankAdmin();
					break;
				case "4":
					homeScreen = false;
					break;
				default:
					System.out.println("Kindly enter correct choice");
					break;
			} // End of switch case
		}// End of while case for displaying home screen
	}// End of main
	
	public static void newUser() throws BankingException {
		
		System.out.println("Kindly signup for our services .. ");
		
		System.out.println("Enter the username:");
		String userName = sc.nextLine();
		
		System.out.println("Enter your password");
		String userPassword = sc.nextLine();
		
		System.out.println("Kindly re enter your password");
		String repeatPassword = sc.nextLine();
		
		boolean passwordMatches = false;
		while(passwordMatches == false) {
			if(userPassword.equals(repeatPassword)) {
				passwordMatches = true;
			} else {
				System.out.println("Passwords do not match");
				System.out.println("Kindly re enter your password to confirm");
				repeatPassword = sc.nextLine();
			}
		}// End of password match checker
		
		System.out.println("Enter a transaction password different from the account password..");
		String transactionPassword = sc.nextLine();
		
		boolean transactionPasswordchecker = false;
		while(transactionPasswordchecker == false) {
			if(transactionPassword.equals(userPassword)) {
				System.out.println("Trasanction password should be different from account password");
				System.out.println("Kindly re enter accounts password");
				transactionPassword = sc.nextLine();
			} else {
				transactionPasswordchecker = true;
			}
		} // End of transaction password Checker
		
		System.out.println("Your transaction password is: " + transactionPassword);
		
		// initialising client
		userBean = new UserBean();
		
		userBean.setAccountId(0);
		userBean.setUserId(userName);
		userBean.setLoginPassword(userPassword);
		userBean.setTransactionPassword(transactionPassword);
		
		int insertionStatus = service.registerUser(userBean);
		System.out.println(insertionStatus + " user inserted");
	
	}// End of new user function
	
	
	public static void bankAdmin() throws BankingException {
		
		System.out.println("Welcome Admin");
		
		System.out.println("Enter your username");
		String adminUserName = sc.nextLine();
		System.out.println("Enter your password");
		String adminUserPassword =  sc.nextLine();
		
		boolean isAdminValid = true;
		while(isAdminValid) {	
			if(adminUserName.equals("ViVaHaKa") && adminUserPassword.equals("6769")) {
				System.out.println("Select your operation:\n\t1.View Account Holders\n\t2.View Transactions");
				String adminChoice = sc.nextLine();
				switch(adminChoice) {
					case "1":
						List<UserBean> userList = new ArrayList<>();
						userList = service.viewAccountHolders();
						System.out.println("List of Account holders :\n");
						for(UserBean u:userList){
							System.out.println("\t"+ u);
						}
						break;
					case "2":
						List<TransactionsBean> transList = new ArrayList<>();
						transList = service.viewTransactionsDetails();
						System.out.println("Transactions Data of Account Holders :\n");
						for(TransactionsBean t:transList){
							System.out.println("\t" + t);
						}
						break;
				} // End of switch case
				System.out.println("Do you want to continue?");
				System.out.println("Enter\n\t1 for yes\n\t2 for no");
				String userChoice = sc.nextLine();
				if(userChoice.equals("2")) {
					System.out.println("Thanks for your visit.");
					isAdminValid = false;
				} 
			} // admin functions
			else {
				System.out.println("Invalid Credentials");
				System.out.println("Do you want to continue?");
				System.out.println("Enter\n\t1 for yes\n\t2 for no");
				String userChoice = sc.nextLine();
				if(userChoice.equals("2")) {
					System.out.println("Thanks for your visit.");
					isAdminValid = false;
				} else {
					System.out.println("Enter your username");
					adminUserName = sc.nextLine();
					System.out.println("Enter your password");
					adminUserPassword =  sc.nextLine();
				}
			} // end of else
		} // End of user interaction
	} // End of BankAdmin function
	
	public static void existingUser
	() throws BankingException {
		
	}
	
}