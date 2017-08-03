package com.its.modules.app.bean;

import java.util.List;

import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderGroupPurc;
import com.its.modules.app.entity.OrderGroupPurcList;

public class OrderGroupPurcBean extends OrderGroupPurc {
	private static final long serialVersionUID = 4735052107191075179L;
	// 商家信息
	private BusinessInfo businessInfo;
	// 订单清单
	private List<OrderGroupPurcList> orderGroupPurcLists;

	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

	public List<OrderGroupPurcList> getOrderGroupPurcLists() {
		return orderGroupPurcLists;
	}

	public void setOrderGroupPurcLists(List<OrderGroupPurcList> orderGroupPurcLists) {
		this.orderGroupPurcLists = orderGroupPurcLists;
	}
}