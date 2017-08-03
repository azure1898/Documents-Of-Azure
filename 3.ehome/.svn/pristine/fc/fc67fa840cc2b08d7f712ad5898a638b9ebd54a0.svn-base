/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.entity;

import org.hibernate.validator.constraints.Length;
import java.util.List;
import com.google.common.collect.Lists;

import com.its.common.persistence.DataEntity;

/**
 * 商品规格名称Entity
 * @author liuhl
 * @version 2017-07-04
 */
public class SkuKey extends DataEntity<SkuKey> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String name;		// 规格名称
	private List<SkuValue> skuValueList = Lists.newArrayList();		// 子表列表
	
	public SkuKey() {
		super();
	}

	public SkuKey(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=0, max=64, message="规格名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<SkuValue> getSkuValueList() {
		return skuValueList;
	}

	public void setSkuValueList(List<SkuValue> skuValueList) {
		this.skuValueList = skuValueList;
	}
}