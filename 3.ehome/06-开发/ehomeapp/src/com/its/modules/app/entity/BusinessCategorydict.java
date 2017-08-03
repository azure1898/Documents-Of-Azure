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
public class BusinessCategorydict extends DataEntity<BusinessCategorydict> {
	
	private static final long serialVersionUID = 1L;
	private String categoryName;		// 分类名称
	private String prodType;		// 产品模式：0商品购买  1服务预约  2课程购买  3场地预约
	private String categoryIntroduce;		// 分类介绍
	
	public BusinessCategorydict() {
		super();
	}

	public BusinessCategorydict(String id){
		super(id);
	}

	@Length(min=0, max=32, message="分类名称长度必须介于 0 和 32 之间")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Length(min=0, max=1, message="产品模式：0商品购买  1服务预约  2课程购买  3场地预约长度必须介于 0 和 1 之间")
	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	@Length(min=0, max=2000, message="分类介绍长度必须介于 0 和 2000 之间")
	public String getCategoryIntroduce() {
		return categoryIntroduce;
	}

	public void setCategoryIntroduce(String categoryIntroduce) {
		this.categoryIntroduce = categoryIntroduce;
	}
	
}