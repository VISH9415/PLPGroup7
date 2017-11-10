package com.cg.project.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Transactions")
public class TransactionsBean {
private long transactionId;
private String transDescription;
private Date dateOfTransaction;
private String transactionType;
private double transactionAmount;
private long accountNumber;

public TransactionsBean() {
	super();
}

public TransactionsBean(long transactionId, String transDescription,
		Date dateOfTransaction, String transactionType,
		double transactionAmount, long accountNumber) {
	super();
	this.transactionId = transactionId;
	this.transDescription = transDescription;
	this.dateOfTransaction = dateOfTransaction;
	this.transactionType = transactionType;
	this.transactionAmount = transactionAmount;
	this.accountNumber = accountNumber;
}



public long getTransactionId() {
	return transactionId;
}
public void setTransactionId(long transactionId) {
	this.transactionId = transactionId;
}
public String getTransDescription() {
	return transDescription;
}
public void setTransDescription(String transDescription) {
	this.transDescription = transDescription;
}
public Date getDateOfTransaction() {
	return dateOfTransaction;
}
public void setDateOfTransaction(Date dateOfTransaction) {
	this.dateOfTransaction = dateOfTransaction;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public Double getTransactionAmount() {
	return transactionAmount;
}
public void setTransactionAmount(Double transactionAmount) {
	this.transactionAmount = transactionAmount;
}
public long getAccountNumber() {
	return accountNumber;
}
public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
}

@Override
public String toString() {
	return "TransactionsBean [transactionId=" + transactionId
			+ ", transDescription=" + transDescription + ", dateOfTransaction="
			+ dateOfTransaction + ", transactionType=" + transactionType
			+ ", transactionAmount=" + transactionAmount + ", accountNumber="
			+ accountNumber + "]";
}

}
