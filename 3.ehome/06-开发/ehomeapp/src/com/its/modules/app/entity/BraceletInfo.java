/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 手环信息管理Entity
 * @author yinxiaoyin
 * @version 2017-08-14
 */
public class BraceletInfo extends DataEntity<BraceletInfo> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 用户ID
	private String villageinfoId;		// 楼盘ID
	private Integer model;		// 手环类型
	private String modelName;		// 手环类型名称
	private String serialNumber;		// 序列号
	private Integer pairType;		// 手环配对类型
	private String name;		// 名称
	private String version;		// 版本号
	private Integer targetStep;		// 目标步数
	private String mac;		// 物理地址
	
	public BraceletInfo() {
		super();
	}

	public BraceletInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用户ID长度必须介于 1 和 64 之间")
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
	
	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}
	
	@Length(min=0, max=64, message="手环类型名称长度必须介于 0 和 64 之间")
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	@Length(min=0, max=64, message="序列号长度必须介于 0 和 64 之间")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Integer getPairType() {
		return pairType;
	}

	public void setPairType(Integer pairType) {
		this.pairType = pairType;
	}
	
	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="版本号长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=64, message="物理地址长度必须介于 0 和 64 之间")
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
}