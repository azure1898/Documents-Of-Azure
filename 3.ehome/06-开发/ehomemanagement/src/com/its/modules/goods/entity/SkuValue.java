/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商品规格名称Entity
 * @author liuhl
 * @version 2017-07-04
 */
public class SkuValue extends DataEntity<SkuValue> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 规格列表值名称
	private SkuKey skuKeyId;		// 规格名称ID 父类
	private String sortOrder;		// 排序
	
	public SkuValue() {
		super();
	}

	public SkuValue(String id){
		super(id);
	}

	public SkuValue(SkuKey skuKeyId){
		this.skuKeyId = skuKeyId;
	}

	@Length(min=0, max=64, message="规格列表值名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="规格名称ID长度必须介于 0 和 64 之间")
	public SkuKey getSkuKeyId() {
		return skuKeyId;
	}

	public void setSkuKeyId(SkuKey skuKeyId) {
		this.skuKeyId = skuKeyId;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}