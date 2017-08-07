package com.its.modules.app.bean;

import java.util.List;

import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderGoods;
import com.its.modules.app.entity.OrderGoodsList;

public class OrderGoodsBean extends OrderGoods {
	private static final long serialVersionUID = 361683180963250557L;
	// 商家信息
	private BusinessInfo businessInfo;
	// 订单清单
	private List<OrderGoodsList> orderGoodsLists;

	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

	public List<OrderGoodsList> getOrderGoodsLists() {
		return orderGoodsLists;
	}

	public void setOrderGoodsLists(List<OrderGoodsList> orderGoodsLists) {
		this.orderGoodsLists = orderGoodsLists;
	}
}