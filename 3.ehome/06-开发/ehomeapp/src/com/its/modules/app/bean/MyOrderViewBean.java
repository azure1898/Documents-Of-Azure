package com.its.modules.app.bean;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MyOrderViewBean implements Serializable {
	private static final long serialVersionUID = 7117599948028988249L;
	private String orderId; 			// 订单ID
	private String orderNo; 			// 订单号
	private Double payMoney; 			// 订单金额
	private String villageInfoId; 		// 楼盘ID
	private String accountId; 			// 用户ID
	private String moduleManageId; 		// 模块ID
	private Integer orderType; 			// 订单类型：1->商品购买订单2->服务预约订单3->课程购买订单4->场地预约订单5->精品团购订单
	private String timeLabel; 			// 时间标签：配送时间、预约时间、上课时间、预约时间、下单时间
	private String orderTime; 			// 时间：不同订单类型有不同的展示方式
	private Date createDate; 			// 下单时间
	private String showName; 			// 名称：商家名称或团购活动名称
	private String businessPic; 		// 商家图片

	@Length(min = 1, max = 64, message = "orderId长度必须介于 1 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 64, message = "order_no长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	@Length(min = 0, max = 64, message = "village_info_id长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	@Length(min = 0, max = 64, message = "account_id长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Length(min = 0, max = 64, message = "module_manage_id长度必须介于 0 和 64 之间")
	public String getModuleManageId() {
		return moduleManageId;
	}

	public void setModuleManageId(String moduleManageId) {
		this.moduleManageId = moduleManageId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	@Length(min = 1, max = 4, message = "time_label长度必须介于 1 和 4 之间")
	public String getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(String timeLabel) {
		this.timeLabel = timeLabel;
	}

	@Length(min = 0, max = 131, message = "order_time长度必须介于 0 和 131 之间")
	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Length(min = 0, max = 64, message = "show_name长度必须介于 0 和 64 之间")
	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	@Length(min = 0, max = 64, message = "business_pic长度必须介于 0 和 64 之间")
	public String getBusinessPic() {
		return businessPic;
	}

	public void setBusinessPic(String businessPic) {
		this.businessPic = businessPic;
	}
}