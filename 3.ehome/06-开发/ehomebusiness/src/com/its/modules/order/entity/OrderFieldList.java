/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 场地预约订单表Entity
 * @author xzc
 * @version 2017-07-06
 */
public class OrderFieldList extends DataEntity<OrderFieldList> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String fieldInfoId;		// 场地预约ID
	private String fieldPartitionPriceId;		// 场地预约分段信息ID
	private OrderField orderField;		// 场地预约类订单ID 父类
	private String orderNo;		// 场地预约类订单号
	private String name;		// 场地名称
	private Date appointmentTime;		// 预约日期
	private Date startTime;		// 起始时段
	private Date endTime;		// 结束时段
	private Double basePrice;		// 原价(每小时)
	private Double benefitPrice;		// 优惠价(每小时)
	private Double sumMoney;		// 总价格
	private String orderState;//订单状态:0待预约、1预约成功、2已取消'
	
	private String orderFieldId;
	
	public OrderFieldList() {
		super();
	}

	public OrderFieldList(String id){
		super(id);
	}

	public OrderFieldList(OrderField orderField){
		this.orderField = orderField;
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=64, message="场地预约ID长度必须介于 1 和 64 之间")
	public String getFieldInfoId() {
		return fieldInfoId;
	}

	public void setFieldInfoId(String fieldInfoId) {
		this.fieldInfoId = fieldInfoId;
	}
	
	@Length(min=1, max=64, message="场地预约类订单ID长度必须介于 1 和 64 之间")
	public OrderField getOrderField() {
		return orderField;
	}

	public void setOrderField(OrderField orderField) {
		this.orderField = orderField;
	}

	public String getFieldPartitionPriceId() {
		return fieldPartitionPriceId;
	}

	public void setFieldPartitionPriceId(String fieldPartitionPriceId) {
		this.fieldPartitionPriceId = fieldPartitionPriceId;
	}

	@Length(min=0, max=64, message="场地预约类订单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="场地名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
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
	
	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	public Double getBenefitPrice() {
		return benefitPrice;
	}

	public void setBenefitPrice(Double benefitPrice) {
		this.benefitPrice = benefitPrice;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getOrderFieldId() {
		return orderFieldId;
	}

	public void setOrderFieldId(String orderFieldId) {
		this.orderFieldId = orderFieldId;
	}
	
	
}