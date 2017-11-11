package com.cg.project.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class CustomerBean {

	@Id
	@SequenceGenerator(name="seq1",sequenceName="acctid_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq1")
	@Column(name = "Account_Id")
	private long accountId;
	@Column(name = "Customer_Name")
	private String customerName;
	@Column(name = "Email")
	private String email;
	@Column(name =  "Address")
	private String address;
	@Column(name  = "Pancard")
	private String pancard;
	@Column(name="account_type")
	private String accountType;
    @Column(name="user_id")
    private String userId;
    
	public CustomerBean() {
		super();
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CustomerBean [accountId=" + accountId + ", customerName="
				+ customerName + ", email=" + email + ", address=" + address
				+ ", pancard=" + pancard + ", accountType=" + accountType
				+ ", userId=" + userId + "]";
	}
	
	
}