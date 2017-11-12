package com.cg.project.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Payeetable")
public class PayeeBean {
	@Id
    @Column(name="account_id")
	long accountId;
    @Column(name="payee_account_id")
	long payeeAccountId;
    @Column(name="nickname")
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
