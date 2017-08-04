package com.its.modules.app.bean;

import java.io.Serializable;
import java.util.List;

import com.its.modules.app.entity.OrderTrack;

public class OrderTrackViewBean implements Serializable {
	private static final long serialVersionUID = 6873325619162549377L;
	// 订单ID
	private String mainOrderId;
	// 订单号
	private String mainOrderNo;
	// 商家ID
	private String businessInfoId;
	// 商家电话
	private String phoneNum;
	// 订单追踪列表
	private List<OrderTrack> orderTracks;

	public String getMainOrderId() {
		return mainOrderId;
	}

	public void setMainOrderId(String mainOrderId) {
		this.mainOrderId = mainOrderId;
	}

	public String getMainOrderNo() {
		return mainOrderNo;
	}

	public void setMainOrderNo(String mainOrderNo) {
		this.mainOrderNo = mainOrderNo;
	}

	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public List<OrderTrack> getOrderTracks() {
		return orderTracks;
	}

	public void setOrderTracks(List<OrderTrack> orderTracks) {
		this.orderTracks = orderTracks;
	}
}