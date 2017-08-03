/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 团购管理Entity
 * @author caojing
 * @version 2017-06-28
 */
public class GroupPurchasetime extends DataEntity<GroupPurchasetime> {
	
	private static final long serialVersionUID = 1L;
	private String groupPurchaseId;		// 模块ID
	private Date startTime;		// 团购开始时间
	private Date endTime;		// 团购结束时间
	private String stockNum;		// 库存量
	private String saleNum;		// 已出售数量
	private String groupState;   //团购状态
	
	public GroupPurchasetime() {
		super();
	}

	public GroupPurchasetime(String id){
		super(id);
	}

	@Length(min=0, max=64, message="模块ID长度必须介于 0 和 64 之间")
	public String getGroupPurchaseId() {
		return groupPurchaseId;
	}

	public void setGroupPurchaseId(String groupPurchaseId) {
		this.groupPurchaseId = groupPurchaseId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=11, message="库存量长度必须介于 0 和 11 之间")
	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}
	
	@Length(min=0, max=11, message="已出售数量长度必须介于 0 和 11 之间")
	public String getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}

	public String getGroupState() {
		return groupState;
	}

	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}
	
}