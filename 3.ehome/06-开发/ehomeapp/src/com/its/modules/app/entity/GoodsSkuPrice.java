/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商品规格价格表Entity
 * @author like
 * @version 2017-07-24
 */
public class GoodsSkuPrice extends DataEntity<GoodsSkuPrice> {
	
	private static final long serialVersionUID = 1L;
	private String goodsInfoId;		// 商品ID
	private String skuKeyId;		// 规格名称id
	private String skuValueId;		// 规格列表id
	private Integer sortOrder;		// 排序
	private Double basePrice;		// 基本价格
	private Double benefitPrice;		// 优惠价
	private Integer stock;		// 库存
	
	public GoodsSkuPrice() {
		super();
	}

	public GoodsSkuPrice(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商品ID长度必须介于 1 和 64 之间")
	public String getGoodsInfoId() {
		return goodsInfoId;
	}

	public void setGoodsInfoId(String goodsInfoId) {
		this.goodsInfoId = goodsInfoId;
	}
	
	@Length(min=1, max=64, message="规格名称id长度必须介于 1 和 64 之间")
	public String getSkuKeyId() {
		return skuKeyId;
	}

	public void setSkuKeyId(String skuKeyId) {
		this.skuKeyId = skuKeyId;
	}
	
	@Length(min=1, max=64, message="规格列表id长度必须介于 1 和 64 之间")
	public String getSkuValueId() {
		return skuValueId;
	}

	public void setSkuValueId(String skuValueId) {
		this.skuValueId = skuValueId;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	public Double getBenefitPrice() {
		return benefitPrice;
	}

	public void setBenefitPrice(Double benefitPrice) {
		this.benefitPrice = benefitPrice;
	}
	
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}