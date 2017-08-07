package com.its.modules.app.bean;

import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.OrderLesson;
import com.its.modules.app.entity.OrderLessonList;

public class OrderLessonBean extends OrderLesson {
	private static final long serialVersionUID = -5122724794413028514L;
	// 商家信息
	private BusinessInfo businessInfo;
	// 订单清单
	private OrderLessonList orderLessonList;

	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

	public OrderLessonList getOrderLessonList() {
		return orderLessonList;
	}

	public void setOrderLessonList(OrderLessonList orderLessonList) {
		this.orderLessonList = orderLessonList;
	}
}