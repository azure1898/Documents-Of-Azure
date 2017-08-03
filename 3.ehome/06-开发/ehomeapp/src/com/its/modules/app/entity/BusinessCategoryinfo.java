/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商户分类Entity
 * @author like
 * @version 2017-07-05
 */
public class BusinessCategoryinfo extends DataEntity<BusinessCategoryinfo> {
	
	private static final long serialVersionUID = 1L;
	private String businessinfoId;		// 商户基本信息ID
	private String businessCategoryDictId;		// 商户分类ID
	
	public BusinessCategoryinfo() {
		super();
	}

	public BusinessCategoryinfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商户基本信息ID长度必须介于 1 和 64 之间")
	public String getBusinessinfoId() {
		return businessinfoId;
	}

	public void setBusinessinfoId(String businessinfoId) {
		this.businessinfoId = businessinfoId;
	}
	
	@Length(min=1, max=64, message="商户分类ID长度必须介于 1 和 64 之间")
	public String getBusinessCategoryDictId() {
		return businessCategoryDictId;
	}

	public void setBusinessCategoryDictId(String businessCategoryDictId) {
		this.businessCategoryDictId = businessCategoryDictId;
	}
	
}