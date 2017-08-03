/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 广告投放的楼盘Entity
 * @author like
 * @version 2017-07-28
 */
public class AdverBuilding extends DataEntity<AdverBuilding> {
	
	private static final long serialVersionUID = 1L;
	private String advId;		// 广告ID
	private String villageLineId;		// 楼盘产品线ID
	
	public AdverBuilding() {
		super();
	}

	public AdverBuilding(String id){
		super(id);
	}

	@Length(min=1, max=64, message="广告ID长度必须介于 1 和 64 之间")
	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}
	
	@Length(min=1, max=64, message="楼盘产品线ID长度必须介于 1 和 64 之间")
	public String getVillageLineId() {
		return villageLineId;
	}

	public void setVillageLineId(String villageLineId) {
		this.villageLineId = villageLineId;
	}
	
}