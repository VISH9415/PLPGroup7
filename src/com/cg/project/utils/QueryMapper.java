package com.cg.project.utils;

public class QueryMapper {

	public static final String validateUserQuery = "Select user from UserBean user where userId=:puserId";
	public static final String viewMiniStatementQuery = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
    public static final String viewDetailedStatementQuery = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId and transaction.dateOfTransaction between :pdate1 and :pdate2 order by transaction.dateOfTransaction";
    public static final String TransactionDetailsQuery ="Select transaction from TransactionsBean transaction where transaction.accountNumber=:paccNo";
    public static final String adminViewTransactionsQuery = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
    public static final String selectCustomerQuery = "Select customer from CustomerBean customer where customer.userId=:puId";
}

