/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.its.common.persistence.DataEntity;

/**
 * 充值管理Entity
 * @author like
 * @version 2017-07-18
 */
public class RechargeManage extends DataEntity<RechargeManage> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 楼盘ID
	private Double rechargeMoney;		// 充值金额
	private Double giveMoney;		// 赠送金额
	
	public RechargeManage() {
		super();
	}

	public RechargeManage(String id){
		super(id);
	}

	@Length(min=1, max=64, message="楼盘ID长度必须介于 1 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@NotNull(message="充值金额不能为空")
	public Double getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(Double rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	
	public Double getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(Double giveMoney) {
		this.giveMoney = giveMoney;
	}
	
}