package com.its.modules.app.bean;

import java.util.List;

import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderField;
import com.its.modules.app.entity.OrderFieldList;
import com.its.modules.app.entity.OrderTrack;

public class OrderFieldBean extends OrderField {
	private static final long serialVersionUID = -5515208522931292773L;
	// 商家信息
	private BusinessInfo businessInfo;
	// 订单清单
	private List<OrderFieldList> orderFieldLists;
	// 订单跟踪
	private List<OrderTrack> orderTracks;

	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

	public List<OrderFieldList> getOrderFieldLists() {
		return orderFieldLists;
	}

	public void setOrderFieldLists(List<OrderFieldList> orderFieldLists) {
		this.orderFieldLists = orderFieldLists;
	}

	public List<OrderTrack> getOrderTracks() {
		return orderTracks;
	}

	public void setOrderTracks(List<OrderTrack> orderTracks) {
		this.orderTracks = orderTracks;
	}
}