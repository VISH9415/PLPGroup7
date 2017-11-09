package com.cg.project.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accountmaster")
public class AccountBean {
	@Id
	@Column(name = "account_Id")
	private long accountId;
	@Column(name = "account_Type")
	private String accountType;
	@Column(name = "open_Date")
	private Date openDate;
	@Column(name = "Account_Balance")
	private double accountBalance;

public AccountBean() {
	super();
}
public long getAccountId() {
	return accountId;
}
public void setAccountId(long accountId) {
	this.accountId = accountId;
}
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
public Date getOpenDate() {
	return openDate;
}
public void setOpenDate(Date openDate) {
	this.openDate = openDate;
}
public double getAccountBalance() {
	return accountBalance;
}
public void setAccountBalance(double accountBalance) {
	this.accountBalance = accountBalance;
}
}
