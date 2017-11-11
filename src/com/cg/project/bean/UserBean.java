package com.cg.project.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserTable")
public class UserBean {
	
	@Column(name = "Account_Id")
	private long accountId;
	@Id
	@Column(name = "User_Id")
	private String userId;
	@Column(name = "login_Password")
	private String loginPassword;
	@Column(name = "transaction_Password")
	private String transactionPassword;
	@Column(name = "lock_Status")
	private String lockStatus;
	@Column(name="secret_question")
	private String secretQuestion;
	@Column(name="secret_answer")
    private String secretAnswer;
	

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
public String getSecretAnswer() {
	return secretAnswer;
}
public void setSecretAnswer(String secretAnswer) {
	this.secretAnswer = secretAnswer;
}
public String getSecretQuestion() {
	return secretQuestion;
}
public void setSecretQuestion(String secretQuestion) {
	this.secretQuestion = secretQuestion;
}
public String getLockStatus() {
	return lockStatus;
}
public void setLockStatus(String lockStatus) {
	this.lockStatus = lockStatus;
}

@Override
public String toString() {
	return "UserBean [accountId=" + accountId + ", userId=" + userId
			+ ", loginPassword=" + loginPassword + ", transactionPassword="
			+ transactionPassword + ", lockStatus=" + lockStatus
			+ ", secretQuestion=" + secretQuestion + ", secretAnswer="
			+ secretAnswer + "]";
}


}