/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商户服务范围Entity
 * @author like
 * @version 2017-07-05
 */
public class BusinessServicescope extends DataEntity<BusinessServicescope> {
	
	private static final long serialVersionUID = 1L;
	private String businessinfoId;		// 商户基本信息ID
	private String villageInfoId;		// 楼盘信息ID
	
	public BusinessServicescope() {
		super();
	}

	public BusinessServicescope(String id){
		super(id);
	}

	@Length(min=0, max=64, message="商户基本信息ID长度必须介于 0 和 64 之间")
	public String getBusinessinfoId() {
		return businessinfoId;
	}

	public void setBusinessinfoId(String businessinfoId) {
		this.businessinfoId = businessinfoId;
	}
	
	@Length(min=0, max=64, message="楼盘信息ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
}