/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 场地预约Entity
 * @author sushipeng
 * @version 2017-08-22
 */
public class FieldInfo extends DataEntity<FieldInfo> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String serialNumbers;		// 场地编号
	private String name;		// 场地名称
	private String monday;		// 星期一是否营业:0否1是
	private String tuesday;		// 星期二是否营业:0否1是
	private String wednesday;		// 星期三是否营业:0否1是
	private String thursday;		// 星期四是否营业:0否1是
	private String friday;		// 星期五是否营业:0否1是
	private String saturday;		// 星期六是否营业:0否1是
	private String sunday;		// 星期日是否营业:0否1是
	private Integer shortTime;		// 场地最短预约时间
	private String startOpenTime;		// 开放预约起始时段
	private String endOpenTime;		// 开放预约结束时段
	private Double basePrice;		// 场地基础价格
	private String isChild;		// 是否分段预约:0否1是
	private String state;		// 是否暂停 0否1是
	private String createState;		// 场地是否生成
	private Integer sellCount;		// 已售数量
	
	public FieldInfo() {
		super();
	}

	public FieldInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=11, message="场地编号长度必须介于 1 和 11 之间")
	public String getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	
	@Length(min=0, max=64, message="场地名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="星期一是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday;
	}
	
	@Length(min=0, max=1, message="星期二是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	
	@Length(min=0, max=1, message="星期三是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	
	@Length(min=0, max=1, message="星期四是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	
	@Length(min=0, max=1, message="星期五是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday = friday;
	}
	
	@Length(min=0, max=1, message="星期六是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	
	@Length(min=0, max=1, message="星期日是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	
	public Integer getShortTime() {
		return shortTime;
	}

	public void setShortTime(Integer shortTime) {
		this.shortTime = shortTime;
	}
	
	@Length(min=0, max=64, message="开放预约起始时段长度必须介于 0 和 64 之间")
	public String getStartOpenTime() {
		return startOpenTime;
	}

	public void setStartOpenTime(String startOpenTime) {
		this.startOpenTime = startOpenTime;
	}
	
	@Length(min=0, max=64, message="开放预约结束时段长度必须介于 0 和 64 之间")
	public String getEndOpenTime() {
		return endOpenTime;
	}

	public void setEndOpenTime(String endOpenTime) {
		this.endOpenTime = endOpenTime;
	}
	
	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	@Length(min=0, max=1, message="是否分段预约:0否1是长度必须介于 0 和 1 之间")
	public String getIsChild() {
		return isChild;
	}

	public void setIsChild(String isChild) {
		this.isChild = isChild;
	}
	
	@Length(min=0, max=1, message="是否暂停 0否1是长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=1, message="场地是否生成长度必须介于 0 和 1 之间")
	public String getCreateState() {
		return createState;
	}

	public void setCreateState(String createState) {
		this.createState = createState;
	}
	
	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}
	
}