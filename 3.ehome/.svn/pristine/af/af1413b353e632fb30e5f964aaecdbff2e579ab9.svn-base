/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 场地预约子表_分段编辑临时表Entity
 * @author xzc
 * @version 2017-07-03
 */
public class FieldInfoPrice extends DataEntity<FieldInfoPrice> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String fieldInfoId;		// 场地id
	private String monday="0";		// 星期一是否营业:0否1是
	private String tuesday="0";		// 星期二是否营业:0否1是
	private String wednesday="0";		// 星期三是否营业:0否1是
	private String thursday="0";		// 星期四是否营业:0否1是
	private String friday="0";		// 星期五是否营业:0否1是
	private String saturday="0";		// 星期六是否营业:0否1是
	private String sunday="0";		// 星期日是否营业:0否1是
	private String startOpenTime;		// 开放预约起始时段
	private String endOpenTime;		// 开放预约结束时段
	private Double basePrice;		// 时段价格


	public FieldInfoPrice() {
		super();
	}

	public FieldInfoPrice(String id){
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
	
	@Length(min=0, max=1, message="星期一是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getMonday() {
		return monday;
	}

	public void setMonday(String monday) {
		this.monday = monday!=null?monday:"0";
	}
	
	@Length(min=0, max=1, message="星期二是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getTuesday() {
		return tuesday;
	}

	public void setTuesday(String tuesday) {
		this.tuesday = tuesday!=null?tuesday:"0";
	}
	
	@Length(min=0, max=1, message="星期三是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getWednesday() {
		return wednesday;
	}

	public void setWednesday(String wednesday) {
		this.wednesday = wednesday!=null?wednesday:"0";
	}
	
	@Length(min=0, max=1, message="星期四是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getThursday() {
		return thursday;
	}

	public void setThursday(String thursday) {
		this.thursday = thursday!=null?thursday:"0";
	}
	
	@Length(min=0, max=1, message="星期五是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getFriday() {
		return friday;
	}

	public void setFriday(String friday) {
		this.friday =  friday!=null?friday:"0";
	}
	
	@Length(min=0, max=1, message="星期六是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday =  saturday!=null?saturday:"0";
	}
	
	@Length(min=0, max=1, message="星期日是否营业:0否1是长度必须介于 0 和 1 之间")
	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday =  sunday!=null?sunday:"0";
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

}