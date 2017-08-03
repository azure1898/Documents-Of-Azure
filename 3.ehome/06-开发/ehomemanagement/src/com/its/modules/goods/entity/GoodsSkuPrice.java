/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.entity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 商品规格价格Entity
 * @author liuhl
 * @version 2017-07-04
 */
public class GoodsSkuPrice extends DataEntity<GoodsSkuPrice> {
	
	private static final long serialVersionUID = 1L;
	private GoodsInfo goodsInfoId;		// 商品ID
	private String skuKeyId;		// 规格名称id
	private String skuValueId;		// 规格列表id
	private String sortOrder;		// 排序
	private Double basePrice;		// 基本价格
	private Double benefitPrice;		// 优惠价
	private Integer stock;		// 库存
	private String skuValueName;    // 规格列表名称
	
	public GoodsSkuPrice() {
		super();
	}

	public GoodsSkuPrice(GoodsInfo goodsInfoId){
		this.goodsInfoId = goodsInfoId;
	}
	
	public GoodsSkuPrice(String id){
		super(id);
	}

	public GoodsInfo getGoodsInfoId() {
		return goodsInfoId;
	}

	public void setGoodsInfoId(GoodsInfo goodsInfoId) {
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
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@DecimalMin(value="0.01",message="规格价格必须大于0.01")
	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	@DecimalMin(value="0.01",message="规格优惠价格必须大于0.01")
	public Double getBenefitPrice() {
		return benefitPrice;
	}

	public void setBenefitPrice(Double benefitPrice) {
		this.benefitPrice = benefitPrice;
	}
	
	@Min(value=0,message="规格库存必须大于0")
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getSkuValueName() {
		return skuValueName;
	}

	public void setSkuValueName(String skuValueName) {
		this.skuValueName = skuValueName;
	}
	
}