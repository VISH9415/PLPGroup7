package com.cg.project.utils;

public interface QueryMapper {

	String validateUserQuery = "Select user from UserBean user where userId=:puserId";
	String viewMiniStatementQuery = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
    String viewDetailedStatementQuery = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId and transaction.dateOfTransaction between :pdate1 and :pdate2 order by transaction.dateOfTransaction";
    String TransactionDetailsQuery ="Select transaction from TransactionsBean transaction where transaction.accountNumber=:paccNo";
    String adminViewTransactionsQuery = "SELECT transaction from TransactionsBean transaction where transaction.accountNumber=:paccountId order by transaction.dateOfTransaction";
    String selectCustomerQuery = "Select customer from CustomerBean customer where customer.userId=:puId";
}

