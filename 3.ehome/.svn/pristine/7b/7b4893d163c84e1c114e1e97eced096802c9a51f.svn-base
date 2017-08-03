package com.its.modules.app.bean;

import java.util.Date;

/**
 * 我的订单通用类
 * 
 * @author sushipeng
 * 
 * @version 2017-07-28
 */
public class MyOrderBean implements Comparable<MyOrderBean> {
	private String orderId;			// 订单ID
	private String name; 			// 名称：商家名称或团购活动名称
	private String businessImage; 	// 商家图片
	private int orderType; 			// 订单类型：1->商品购买订单2->服务预约订单3->课程购买订单4->场地预约订单5->精品团购订单
	private String timeLabel; 		// 时间标签：配送时间、预约时间、上课时间、预约时间、下单时间
	private String time; 			// 时间：不同订单类型有不同的展示方式
	private double orderMoney; 		// 订单金额
	private String orderStatus; 	// 订单状态
	private Date createDate; 		// 下单时间

	/**
	 * 按照下单时间进行降序排序
	 */
	public int compareTo(MyOrderBean myOrderBean) {
		if (myOrderBean.getCreateDate() == null) {
			myOrderBean.setCreateDate(new Date());
		}
		if (this.createDate.getTime() > myOrderBean.getCreateDate().getTime()) {
			return -1;
		} else if (this.createDate.getTime() == myOrderBean.getCreateDate().getTime()) {
			return 0;
		} else {
			return 1;
		}
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBusinessImage() {
		return businessImage;
	}

	public void setBusinessImage(String businessImage) {
		this.businessImage = businessImage;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(String timeLabel) {
		this.timeLabel = timeLabel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(double orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}