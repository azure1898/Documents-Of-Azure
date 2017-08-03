/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 手环信息Entity
 * @author like
 * @version 2017-07-24
 */
public class BraceletInfo extends DataEntity<BraceletInfo> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// account_id
	private String villageinfoId;		// 楼盘ID
	private String model;		// model
	private String serialNumber;		// serial_number
	private String name;		// name
	private String version;		// version
	private Integer targetStep;		// target_step
	private String mac;		// MAC地址
	
	public BraceletInfo() {
		super();
	}

	public BraceletInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="account_id长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageinfoId() {
		return villageinfoId;
	}

	public void setVillageinfoId(String villageinfoId) {
		this.villageinfoId = villageinfoId;
	}
	
	@Length(min=0, max=64, message="model长度必须介于 0 和 64 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=64, message="serial_number长度必须介于 0 和 64 之间")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@Length(min=0, max=64, message="name长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="version长度必须介于 0 和 64 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public Integer getTargetStep() {
		return targetStep;
	}

	public void setTargetStep(Integer targetStep) {
		this.targetStep = targetStep;
	}
	
	@Length(min=0, max=64, message="MAC地址长度必须介于 0 和 64 之间")
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
}