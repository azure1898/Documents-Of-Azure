/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 团购管理子表－团购时间Entity
 * @author sushipeng
 * @version 2017-07-24
 */
public class GroupPurchasetime extends DataEntity<GroupPurchasetime> {
	
	private static final long serialVersionUID = 1L;
	private String groupPurchaseId;		// 模块ID
	private Date startTime;		// 团购开始时间
	private Date endTime;		// 团购结束时间
	private Integer stockNum;		// 库存量
	private Integer saleNum;		// 已出售数量
	
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
	
	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	
	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}
	
}