package com.its.modules.app.bean;

import java.util.Date;

import com.its.common.persistence.DataEntity;



public class OrderGroupPurcRCBean extends DataEntity<OrderGroupPurcRCBean> {

	private static final long serialVersionUID = 1L;

	private String businessinfoId;		// 商家id
	private String orderNo;		// 订单号
	private String name;		// 团购名称
	private String accountId;		// 买家ID
	private String accountName;		// 买家名称
	private String groupPurcName;		// 团购名称
	private Date validityStartTime;		// 团购券有效期开始时间
	private Date validityEndTime;		// 团购券有效期结束时间
	
	public String getBusinessinfoId() {
		return businessinfoId;
	}
	public void setBusinessinfoId(String businessInfoId) {
		this.businessinfoId = businessInfoId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getGroupPurcName() {
		return groupPurcName;
	}
	public void setGroupPurcName(String groupPurcName) {
		this.groupPurcName = groupPurcName;
	}
	public Date getValidityStartTime() {
		return validityStartTime;
	}
	public void setValidityStartTime(Date validityStartTime) {
		this.validityStartTime = validityStartTime;
	}
	public Date getValidityEndTime() {
		return validityEndTime;
	}
	public void setValidityEndTime(Date validityEndTime) {
		this.validityEndTime = validityEndTime;
	}
}