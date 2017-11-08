package com.cg.project.bean;

public class UserBean {
private long accountId;
private String userId;
private String loginPassword;
private String transactionPassword;
private String lockStatus;


public UserBean(long accountId, String userId, String loginPassword,
		 String transactionPassword, String lockStatus) {
	super();
	this.accountId = accountId;
	this.userId = userId;
	this.loginPassword = loginPassword;
	this.transactionPassword = transactionPassword;
	this.lockStatus = lockStatus;
}
public UserBean() {
	super();
}
public long getAccountId() {
	return accountId;
}
public void setAccountId(long accountId) {
	this.accountId = accountId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getLoginPassword() {
	return loginPassword;
}
public void setLoginPassword(String loginPassword) {
	this.loginPassword = loginPassword;
}
public String getTransactionPassword() {
	return transactionPassword;
}
public void setTransactionPassword(String transactionPassword) {
	this.transactionPassword = transactionPassword;
}
public String getLockStatus() {
	return lockStatus;
}
public void setLockStatus(String lockStatus) {
	this.lockStatus = lockStatus;
}
@Override
public String toString() {
	return "UserBean [accountId=" + accountId + ", userId=" + userId + ", loginPassword=" + loginPassword
			+ ", transactionPassword=" + transactionPassword + ", lockStatus="
			+ lockStatus + "]";
}


}
