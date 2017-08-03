/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.its.common.persistence.DataEntity;

/**
 * 商品分类信息Entity
 * @author liuhl
 * @version 2017-07-03
 */
public class SortInfo extends DataEntity<SortInfo> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String type;		// 分类类型（0商品1服务）
	private String name;		// 分类名称
	private String sortOrder;		// 排序

	public SortInfo() {
		super();
	}

	public SortInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=1, message="分类类型（0商品1服务）长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=64, message="分类名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Range(min=0, max=999, message="排序是0-999之间的自然数")
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

}