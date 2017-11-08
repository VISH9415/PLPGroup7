package com.cg.project.bean;

public class PayeeBean {

	long accountId;
	long payeeAccountId;
	String nickName;
	
	public PayeeBean() {
		super();
	}
	public PayeeBean(long accountId, long payeeAccountId, String nickName) {
		super();
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.nickName = nickName;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getPayeeAccountId() {
		return payeeAccountId;
	}
	public void setPayeeAccountId(long payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
