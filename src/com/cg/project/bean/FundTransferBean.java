package com.cg.project.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="fund_transfer")
public class FundTransferBean {

	@Id
	@Column(name="fundtransfer_id")
	@SequenceGenerator(name="seq1",sequenceName="fundid_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq1")
	private String fundTransferId;
	@Column(name="account_id")
	private long accountId;
	@Column(name="payee_account_id")
	private long payeeAccountId;
	@Column(name="date_of_transfer")
	private Date dateOfTransfer;
	@Column(name="transfer_amount")
	private double transferAmount;
	
	public String getFundTransferId() {
		return fundTransferId;
	}
	public void setFundTransferId(String fundTransferId) {
		this.fundTransferId = fundTransferId;
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
	public Date getDateOfTransfer() {
		return dateOfTransfer;
	}
	public void setDateOfTransfer(Date dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	@Override
	public String toString() {
		return "FundTransferBean [fundTransferId=" + fundTransferId
				+ ", accountId=" + accountId + ", payeeAccountId="
				+ payeeAccountId + ", dateOfTransfer=" + dateOfTransfer
				+ ", transferAmount=" + transferAmount + "]";
	}
}
