package com.cg.project.bean;

import java.sql.Date;

public class ServiceTrackerBean {
private long serviceId;
private String serviceDescription;
private long accountId;
private Date serviceRaisedDate;
private String serviceStatus;


public ServiceTrackerBean() {
	super();
}
public ServiceTrackerBean(long serviceId, String serviceDescription,
		long accountId, Date serviceRaisedDate, String serviceStatus) {
	super();
	this.serviceId = serviceId;
	this.serviceDescription = serviceDescription;
	this.accountId = accountId;
	this.serviceRaisedDate = serviceRaisedDate;
	this.serviceStatus = serviceStatus;
}
public long getServiceId() {
	return serviceId;
}
public void setServiceId(long serviceId) {
	this.serviceId = serviceId;
}
public String getServiceDescription() {
	return serviceDescription;
}
public void setServiceDescription(String serviceDescription) {
	this.serviceDescription = serviceDescription;
}
public long getAccountId() {
	return accountId;
}
public void setAccountId(long accountId) {
	this.accountId = accountId;
}
public Date getServiceRaisedDate() {
	return serviceRaisedDate;
}
public void setServiceRaisedDate(Date serviceRaisedDate) {
	this.serviceRaisedDate = serviceRaisedDate;
}
public String getServiceStatus() {
	return serviceStatus;
}
public void setServiceStatus(String serviceStatus) {
	this.serviceStatus = serviceStatus;
}
}
