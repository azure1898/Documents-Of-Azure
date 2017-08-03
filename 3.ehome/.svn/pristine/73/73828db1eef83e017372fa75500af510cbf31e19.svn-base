package com.its.modules.app.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.modules.app.entity.GroupPurchase;

public class GroupPurchaseBean extends GroupPurchase {
	private static final long serialVersionUID = 2274512988299579731L;
	// 团购子表ID
	private String groupPurchaseTimeId;
	// 团购开始时间
	private Date startTime;
	// 团购结束时间
	private Date endTime;
	// 库存量
	private Integer stockNum;
	// 已出售数量
	private Integer saleNum;

	public String getGroupPurchaseTimeId() {
		return groupPurchaseTimeId;
	}

	public void setGroupPurchaseTimeId(String groupPurchaseTimeId) {
		this.groupPurchaseTimeId = groupPurchaseTimeId;
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