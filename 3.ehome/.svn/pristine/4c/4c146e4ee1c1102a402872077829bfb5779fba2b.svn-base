/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 场地预约子表-场地分段信息Entity
 * @author xzc
 * @version 2017-07-03
 */
public class FieldPartitionPrice extends DataEntity<FieldPartitionPrice> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String fieldInfoId;		// 场地id
	private Date appointmentTime;		// 预约日期
	private Date startTime;		// 起始时段
	private Date endTime;		// 结束时段
	private Double basePrice;		// 场地价格(每小时)
	private Double sumMoney;		// 总价格
	private String state;		// 场地状态： 0可预约1已预约2已消费


	private boolean outTimeState;//结束时段 是否超过当前日期
	
	public FieldPartitionPrice() {
		super();
	}

	public FieldPartitionPrice(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=64, message="场地id长度必须介于 1 和 64 之间")
	public String getFieldInfoId() {
		return fieldInfoId;
	}

	public void setFieldInfoId(String fieldInfoId) {
		this.fieldInfoId = fieldInfoId;
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
	
	@Length(min=0, max=1, message="场地状态： 0可预约1已预约2已消费长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public boolean isOutTimeState() {
		return outTimeState;
	}

	public void setOutTimeState(boolean outTimeState) {
		this.outTimeState = outTimeState;
	}

	public Double getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(Double sumMoney) {
		this.sumMoney = sumMoney;
	}
}