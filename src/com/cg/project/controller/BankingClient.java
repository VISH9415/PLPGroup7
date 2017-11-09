package com.cg.project.controller;
/*package com.cg.project.client;

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
//java doc comments...
public class BankingClient {

	public static void main(String[] args) throws BankingException {

		CustomerBean customer = new CustomerBean();
		AccountBean account = new AccountBean();
		TransactionsBean trans = new TransactionsBean();
		IBankingService service = new BankingServiceImpl();
		PayeeBean payeeBean = new PayeeBean();
		UserBean user = new UserBean();
		ServiceTrackerBean serviceTracker = new ServiceTrackerBean();
		Scanner sc = new Scanner(System.in);

		// user register for particular bank ... before opening account
		System.out.println("Welcome to VIVAHAKA Bank");
		int home = 1;
		
		while (home > 0) {
			System.out
					.println("Please select: \n1.New User\n2.ExistingUser\n3.BankAdmin");

			String use = sc.nextLine();

			switch (use) {
			*//**
			 * 
			 * Case 1 starts
			 *//*
			case "1":
				System.out.println("Please signup for our services .. ");

				System.out.println("Enter the username: ");
				String uid = sc.nextLine();
				boolean p = false;
				while (p == false) {
					System.out.println("Enter your password");
					String pass = sc.nextLine();

					System.out.println("Please confirm password");
					String passc = sc.nextLine();
					// p=true;

					if (passc.equals(pass) == false) {
						// System.out.println("again enter the password..");
						continue;
					} else {
						// home=0;
						boolean r = true;
						while (r == true) {
							System.out
									.println("Enter a transaction password that should be different from account password..");
							String trpass = sc.nextLine();
							if (trpass.equals(pass)) {
								System.out
										.println("enter valid transaction password..");
								continue;
							} else {

								System.out.println("your transaction pass is: "
										+ trpass);
								r = false;

								user.setAccountId(0); // while registering
														// account id is not
														// there...
								user.setLoginPassword(pass);
								user.setTransactionPassword(trpass);
								user.setUserId(uid);

								int userRegStatus = service.registerUser(user); // insert
																				// user
								// account id will be updated as account is
								// opened ...
								System.out.println(userRegStatus
										+ " user registered");

							}
						}
						p = true;
					}
				}

				break;

			*//**
			 * 
			 * Case 1 ends
			 *//*

			*//**
			 * 
			 * Case 2 starts
			 *//*

			case "2":
				// existing user in bank...
				boolean c2 = true;
				while (c2) {
					System.out.println("Please Login: ");

					System.out.println("Enter username"); // check for case
															// sensitivity also
															// ...
					uid = sc.nextLine();
					HashMap<String, UserBean> userMap = service
							.fetchUserById(uid); // no of users for this uid

					// if uid exists userMap.containskey(key)...
					if (userMap.get(uid).getUserId() == null) {
						System.out
								.println("Please enter the correct username..");
						System.out.println("");
						continue;
					} else {
						System.out.println("Enter the password");// for login
						String lpass = sc.nextLine();
						// fetching password from user details and matching...

						if (lpass.equals(userMap.get(uid).getLoginPassword())) {
							System.out
									.println("Now you can proceed to avail services: ");
						} else {
							System.out.println("wrong password...");
							continue;
							// System.exit(0);
						}
						if (service.getPreviousUser(uid) == 0) {
							System.out
									.println("Operations to be done : \n1.OpenAccount\n2.Transactions\n"
											+ "3.FundTransfer\n4.ChangeRequest\n5.ChequeBookServiceTracking");
						} else {
							System.out
									.println("Hello "
											+ uid
											+ ""
											+ " Your account is already opened with accountId: "
											+ service.getPreviousUser(uid));
							System.out
									.println("Operations to be done \n: 2.Transactions\n"
											+ "3.FundTransfer\n4.ChangeRequest\n5.ChequeBookServiceTracking");
						}
						String choice = sc.nextLine();

						switch (choice) {
						case "1":
							// open account..
							boolean op = true;
							while (op) {
								System.out
										.println("Dear customer enter the name: ");
								String name = sc.nextLine();
								// if(name.matches("^[A-Z]{1}[a-z]{2,}$"))
								System.out.println("Enter emailId");
								String email = sc.nextLine();
								if (email
										.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
								// if(email.matches("^[a-z]{2,}[0-9._%+-]+@[0-9.-]+\\.[a-z]{2,6}$"))
								{
									op = false;
									System.out.println("Enter address");
									String address = sc.nextLine();
									boolean p1 = true;
									while (p1) {
										System.out
												.println("Enter PAN card no.");
										String pan = sc.nextLine();

										if (pan.matches("^[A-Z]{5}[0-9]{4}[A-Z]{1}$")) {
											op = false;
											System.out
													.println("Enter the type of account you prefer to open: \n1.Savings\n2.Current");
											int actchoice = sc.nextInt();

											// String cdate = "06-11-2017";
											LocalDate cdate = LocalDate.now();
											// DateTimeFormatter frmt =
											// DateTimeFormatter.ofPattern("dd-MM-yyyy");
											// LocalDate opDate =
											// LocalDate.parse(cdate,frmt);
											Date openDate = Date.valueOf(cdate);
											System.out
													.println("account open date is: "
															+ cdate);
											System.out
													.println("chequebook request date is: "
															+ cdate);

											String actType = "";
											if (actchoice == 1) {
												actType = "Savings";
											} else {
												actType = "Current";
											}

											customer.setCustomerName(name);
											customer.setAddress(address);
											customer.setEmail(email);
											customer.setPancard(pan);
											customer.setAccountType(actType);
											long actid;

											actid = service
													.openAccount(customer); // insert
																			// into
																			// customer
																			// table

											account.setAccountId(actid);
											account.setOpenDate(openDate);
											account.setAccountType(actType);
											account.setAccountBalance(0);

											int status = service
													.insertAccount(account); // insert
																				// into
																				// accountmaster
																				// table...
											// account initial details inserted
											// ...
											if (status != 0) {
												System.out
														.println("Thanks "
																+ name
																+ " your account with account id "
																+ actid
																+ " is "
																+ "opened with zero initial Balance");

												user.setAccountId(actid);
												user.setLoginPassword(lpass);

												service.updateUserDetails(user,
														uid, actid); // update
																		// details
																		// for
																		// particular
																		// user...
												p1 = false;
												c2 = false;
												home = 0;

											}
											// op = false;
										} else {
											System.out
													.println("Enter PAN in format: \n ABCDE1234A");
											continue;
										}
									}

								} else {
									System.out
											.println("Enter emailid in format:\n abcd@gmail.com");
									continue;
								}
							}
							break;

						*//**
						 * Start of transaction
						 *//*

						case "2":
							// Transactions....
							boolean tr = true;
							while (tr) {
								System.out
										.println("Enter your choice\n1.Deposit\n2.Withdrawl\n3.View Balance");
								String transChoice = sc.nextLine();

								switch (transChoice) {
								case "1":
									String transtype = "Deposit";
									String transdesp = null;// transaction
															// description
									boolean dep = true;
									while (dep) {
										System.out
												.println("Enter your account Id please:");
										long accId = sc.nextLong();
										// validate id for this user also ...
										int count1 = service
												.fetchUserByActId(accId);
										if (count1 == 0) {
											System.out
													.println("incorrect account id for this user...");
											continue;
										} else {
											dep = false;
											customer = service
													.validateId(accId); // validate
																		// account
																		// id
																		// from
																		// customer...
											if (customer == null) {
												System.out
														.println("Sorry account doesn't exists...");
											} else if (customer != null) {
												double initAmount = service
														.fetchAmount(accId); // account
																				// balance
																				// before
																				// transaction
												System.out
														.println("initial balance: "
																+ initAmount);

												System.out
														.println("Enter the amount to be deposited");
												double depositamount = sc
														.nextDouble();

												transdesp = service
														.depositUpdate(accId,
																depositamount); // insert
																				// transaction
												System.out.println(transdesp);
												if (transdesp != null) {
													trans.setTransactionType(transtype);
													trans.setTransDescription(transdesp);
													trans.setTransactionAmount(depositamount);
													service.transactionUpdate(
															accId, trans); // insert
																			// into
																			// transactions
																			// table....

													c2 = false;
													home = 0;
												}
											}
										}
									}
									System.out
											.println("Do you want to continue :\n1.Yes \n2.No");
									String choice1 = sc.next();
									if (choice1.equals("1")) {
										tr = true;
									} else {
										System.out
												.println("Thanks Visit Again");
										tr = false;
									}

									break;

								case "2":
									// withdraw....
									transtype = "Withdraw";
									transdesp = null;// transaction description
									boolean with = true;
									while (with) {
										System.out
												.println("Enter your account Id please:");
										long accId = sc.nextLong();
										int count1 = service
												.fetchUserByActId(accId);
										if (count1 == 0) {
											System.out
													.println("Incorrect account id for this user");
											continue;
										} else {
											System.out.println("correct id...");
											customer = service
													.validateId(accId); // validate
																		// account

											if (customer == null) {
												System.out
														.println("Sorry account doesn't exists...");
											}

											else if (customer != null) {
												boolean am = true;
												while (am) {
													double initAmount = service
															.fetchAmount(accId);
													System.out
															.println("initial balance: "
																	+ initAmount);
													System.out
															.println("Enter amount to be withdrawn");
													double withdrawamount = sc
															.nextDouble();
													if (withdrawamount <= 0) {
														System.out
																.println("Invalid amount entered");
														break;
													} else {

														if (initAmount > withdrawamount) {
															transdesp = service
																	.withdraw(
																			accId,
																			withdrawamount);
															System.out
																	.println(transdesp);
															if (transdesp != null) {

																trans.setTransactionType(transtype);
																trans.setTransDescription(transdesp);
																trans.setTransactionAmount(withdrawamount);
																service.transactionUpdate(
																		accId,
																		trans); // insert
																				// into
																				// transactions
																				// table....
																am = false;
															}
														} else {
															System.out
																	.println("Insufficient Balance");
															continue;
														}
													}
												}
											}
										}
										with = false;
										tr = false;
										c2 = false;
										home = 0;
									}
									System.out
											.println("Do you want to continue :\n1.Yes \n2.No");
									choice1 = sc.next();
									if (choice1.equals("1")) {
										tr = true;
									} else {
										System.out
												.println("Thanks Visit Again");
										tr = false;
									}
									break;

								case "3":
									// view balance ....
									// also ministatement and detailedstatement
									// to be made ....
									boolean stmt = true;
									while (stmt) {
										System.out
												.println("please enter your account id: ");
										long actid = sc.nextLong();
										int count = service
												.fetchUserByActId(actid); // validate
																			// actid
																			// for
																			// this
																			// user..
										if (count == 0) {
											System.out
													.println("wrong account id entered...");
											continue;
										} else if (count != 0) {
											customer = service
													.validateId(actid);
											if (customer != null) {
												System.out
														.println("Actions:\n1.Mini Statement\n2.Detailed Statement");
												String ch = sc.next();
												switch (ch) {

												case "1":
													HashMap<Long, List<TransactionsBean>> miniMap = new HashMap<>();
													miniMap = service
															.viewMiniStatement(actid);
													// list of descriptions ...
													List<TransactionsBean> list = new ArrayList<TransactionsBean>();
													list = miniMap.get(actid);
													Iterator<TransactionsBean> itr = list
															.iterator();
													System.out
															.println("Mini Statement for account Id: "
																	+ actid
																	+ " is: ");
													while (itr.hasNext()) {
														System.out
																.println(itr
																		.next()
																		.getTransDescription());
													}
													stmt = false;
													break;

												case "2":
													HashMap<Long, List<TransactionsBean>> detMap = new HashMap<>();
													System.out
															.println("Enter date 1 in format dd-mm-yyyy");
													String init = sc.next();
													DateTimeFormatter frmt1 = DateTimeFormatter
															.ofPattern("dd-MM-yyyy");
													LocalDate IDate = LocalDate
															.parse(init, frmt1);
													Date idate = Date
															.valueOf(IDate);

													System.out
															.println("Enter date 2 in format dd-mm-yyyy");
													String second = sc.next();
													DateTimeFormatter frmt2 = DateTimeFormatter
															.ofPattern("dd-MM-yyyy");
													// String text =
													// .format(frmt);
													LocalDate LDate = LocalDate
															.parse(second,
																	frmt2);
													Date ldate = Date
															.valueOf(LDate);

													detMap = service
															.viewDetailedStatement(
																	actid,
																	idate,
																	ldate);
													// System.out.println("detMap is: "+detMap);

													List<TransactionsBean> dlist = new ArrayList<TransactionsBean>();
													dlist = detMap.get(actid);
													// System.out.println("dlist is: "+dlist);

													Iterator<TransactionsBean> ditr = dlist
															.iterator();
													System.out
															.println("Detailed Statement for account Id: "
																	+ actid
																	+ " is: ");

													while (ditr.hasNext()) {
														System.out
																.println(ditr
																		.next()
																		.getTransDescription());
													}
													stmt = false;
													break;
												}
											}
										} else {
											System.out
													.println("Account doesn't exists...");
										}
										with = false;
										tr = false;
										c2 = false;
										home = 0;
									}
									System.out
											.println("Do you want to continue :\n1.Yes \n2.No");
									choice1 = sc.next();
									if (choice1.equals("1")) {
										tr = true;
									} else {
										System.out
												.println("Thanks Visit Again");

									}
								}
							}
							break;
						*//**
						 * End of transaction
						 *//*
						case "3":
							// fund transfer ...
							boolean fund = true;
							while (fund) {
								System.out
										.println("Welcome to fund transfer services");
								System.out.println("Enter your account id: ");
								long actId = sc.nextLong();

								int noOfUser = 0;
								int count = 0;
								// validate actid
								noOfUser = service.fetchUserByActId(actId);
								if (noOfUser == 0) {
									System.out
											.println("user's incorrect account id entered...");
									continue;
								} else if (noOfUser == 1) {
									customer = service.validateId(actId);
									// validate actid for this user..
									if (customer == null) {
										System.out
												.println("Invalid account id entered...");
									} else if (customer != null) {
										boolean paye = true;
										while (paye) {
											System.out
													.println("Enter payee's account id: ");// assuming
																							// payee
																							// is
																							// one
																							// of
																							// the
																							// user..
											long pactId = sc.nextLong();

											noOfUser = service
													.fetchUserByActId(pactId);
											if (noOfUser == 0) {
												System.out
														.println("payee's incorrect account id entered...");
												continue;
											} else if (noOfUser == 1) {
												customer = service
														.validateId(pactId);
												System.out
														.println("Enter Nick Name for payee");
												String nickName = sc.next();
												if (customer == null) {
													System.out
															.println("invalid payee id: ");
												} else if (customer != null) {
													System.out
															.println("Enter the amount to be transfered..");
													double tamt = sc
															.nextDouble();

													double intAmtSender = service
															.fetchAmount(actId); // fetch
																					// from
																					// accountmaster..

													if (intAmtSender >= tamt) {
														service.sendfund(tamt,
																actId, pactId);

														payeeBean
																.setAccountId(actId);
														payeeBean
																.setPayeeAccountId(pactId);
														payeeBean
																.setNickName(nickName);
														service.insertPayee(payeeBean);

														TransactionsBean transBean = new TransactionsBean();
														transBean
																.setTransactionAmount(tamt);
														transBean
																.setTransactionType("fundsent");
														transBean
																.setTransDescription(tamt
																		+ " sent");
														service.transactionUpdate(
																actId,
																transBean);

														// for reciever ...

														transBean
																.setTransactionAmount(tamt);
														transBean
																.setTransactionType("fundrecieved");
														transBean
																.setTransDescription(tamt
																		+ " recieved");
														service.transactionUpdate(
																pactId,
																transBean);
														paye = false;

														// System.out.println("net balance remaining in ur account is: ");
													} else {
														System.out
																.println("insufficient funds...");
														continue;
													}
												}
											}

										}
									}
								}
								fund = false;
								tr = false;
								c2 = false;
								home = 0;
								op = false;
								System.out
										.println("Do you want to continue :\n1.Yes \n2.No");
								String choice1 = sc.next();
								if (choice1.equals("1")) {
									fund = true;
								} else {
									System.out.println("Thanks Visit Again");

								}
							}

							break;

						case "4":
							// Change Request....
							boolean req = true;
							while (req) {
								System.out
										.println("Enter the change request you like to perform: 1.ChangeLoginPassword\n2.ChangeAddress\n");
								String changechoice = sc.next();

								System.out.println("your user name is: " + uid);

								switch (changechoice) {
								case "1":
									// password change
									boolean pre = true;
									while (pre) {
										System.out
												.println("Please enter your previous password");
										// for(int i=0;i<3;i++){

										String pwd1 = sc.next();

										if (pwd1.equals(service
												.validatePassword(uid))) {
											// validate pass using act id from
											// user table ...
											System.out
													.println("Enter new password");
											String pwdNew = sc.next();
											System.out
													.println("Please reenter your new password..");
											String pwdNewRe = sc.next();
											if (pwdNewRe.equals(pwdNew)) {
												user.setLoginPassword(pwdNew);
												// user.setAccountId(id);
												// //entered now this time ...
												// System.out.println("ur account id is: "+id);
												long actId = service
														.fetchUserById(uid)
														.get(uid)
														.getAccountId();
												System.out
														.println("Your account id: "
																+ actId);

												service.updateUserDetails(user,
														uid, actId); // to
																		// update
																		// password
																		// in
																		// usertable
																		// ...
												pre = false;
											} else {
												System.out
														.println("password isn't matching...");
												continue;
											}
										} else {
											System.out
													.println("Sorry your password doesn't match with previous password");
											continue;
										}

									}

									tr = false;
									c2 = false;
									home = 0;
									req = false;
									op = false;
									System.out
											.println("Do you want to continue :\n1.Yes \n2.No");
									String choice1 = sc.next();
									if (choice1.equals("1")) {

										req = true;
										continue;
									} else {
										System.out
												.println("Thanks Visit Again");

									}

									break;

								case "2":
									// address change

									long actId = service.fetchUserById(uid)
											.get(uid).getAccountId();
									System.out.println("Your account id: "
											+ actId);

									System.out
											.println("Please enter new address");
									String newAdd = sc.nextLine();
									int temp = service.updateCustomerAddress(
											actId, newAdd); // update customer
															// address
									System.out
											.println(temp
													+ " address updated in customer table ..");
									break;
								}
								req = false;
								tr = false;
								c2 = false;
								home = 0;
								op = false;
								System.out
										.println("Do you want to continue :\n1.Yes \n2.No");
								String choice1 = sc.next();
								if (choice1.equals("1")) {

									req = true;
									continue;
								} else {
									System.out.println("Thanks Visit Again");

								}
							}
							break;

						case "5":
							// service tracking ... for chequebook request
							boolean ser = true;
							while (ser) {
								System.out.println("Enter your accountid: ");
								long actId = sc.nextLong();
								int count = service.fetchUserByActId(actId);
								if (count == 0) {
									System.out
											.println("incorrect act id for this user..");
									continue;
								}

								else if (count != 0) {
									account = service.fetchAccounts(actId);

									// count=1;
									// System.out.println("accounts for this act id: "+count);
									if (account != null) {
										// service.fetchAccounts(actId);
										System.out
												.println("Dear customer "
														+ service
																.validateId(
																		actId)
																.getCustomerName()
														+ " you are a registered customer of our bank");
										// service raised on sysdate...
										Date opdate = service
												.fetchOpenDate(actId);
										LocalDate opeDate = opdate
												.toLocalDate();

										// System.out.println("chequebook request date is: "+ate);

										String raisedate = "12-11-2017";
										DateTimeFormatter frmt1 = DateTimeFormatter
												.ofPattern("dd-MM-yyyy");
										LocalDate rdate = LocalDate.parse(
												raisedate, frmt1);
										Date redate = Date.valueOf(rdate);

										int days = opeDate.getDayOfMonth()
												- rdate.getDayOfMonth();
										System.out.println("days " + days);

										// insert into service tracker..
										serviceTracker.setAccountId(actId);
										serviceTracker
												.setServiceDescription("Request for chequeBook");
										serviceTracker
												.setServiceRaisedDate(redate);
										// Date todayDate = null; //date of
										// tracking request made
										int servicePeriod = 10;// in days set
																// for get it
																// released...
										if (days >= 0 && days <= 10) {
											System.out
													.println(servicePeriod
															- days
															+ " are remaining to get your chequebook processed...");
											int var = servicePeriod - days;
											serviceTracker
													.setServiceStatus("To be dispatched after "
															+ var + " days");
										} else if (days > 10) {
											serviceTracker
													.setServiceStatus("Already dispatched..");
											System.out
													.println("Already dispatched and you shall recieve it shortly..");
										}
										service.insertService(serviceTracker);
									}
									// break;

								}
								// break;
							}
							ser = false;
							tr = false;
							c2 = false;
							home = 0;
							op = false;
							System.out
									.println("Do you want to continue :\n1.Yes \n2.No");
							String choice1 = sc.next();
							if (choice1.equals("1")) {
								ser = true;
							} else {
								System.out.println("Thanks Visit Again");

							}
							break;

						}
					}
				}
				break;
			*//**
			 * 
			 * Case 2 ends
			 *//*
			*//**
			 * 
			 * Case 3 starts
			 *//*
			case "3":
				int x = 1;
				System.out.println("Hello Admin");
				while (x > 0) {
					System.out.println("Enter your userid:");
					String adminId = sc.next();
					System.out.println("Enter your password: ");
					String adminPass = sc.next();
					if (adminId.equals("ViVaHaKa") && adminPass.equals("6769")) {
						System.out
								.println("Operations you want to perform \n1.View Account holders"
										+ "\n2.View Transactions");
						x = 0;
						String adminChoice = sc.next();
						switch (adminChoice) {
						case "1":
							List<UserBean> userList = new ArrayList<>();
							userList = service.viewAccountHolders();
							System.out.println("Account holders :\n");
							for (UserBean u : userList) {
								System.out.println(u);
							}
							x = 0;
							break;

						case "2":
							List<TransactionsBean> transList = new ArrayList<>();
							transList = service.viewTransactionsDetails();
							System.out
									.println("Transactions Data of Account Holders :\n");
							for (TransactionsBean t : transList) {
								System.out.println(t);
							}
							x = 0;
							break;

						default:
							System.out.println("Invalid Choice");
							break;

						}
					} else {
						System.out.println("Invalid credentials");
					}
					System.out
							.println("Do you want to continue :\n1.Yes \n2.No");
					String choice1 = sc.next();
					if (choice1.equals("1")) {
						x = 1;
						continue;
					} else {
						System.out.println("Thanks Visit Again");

					}
				}

				c2 = false;
				home = 0;

				break;

			}
		}
	}

}// correct upto here

*/