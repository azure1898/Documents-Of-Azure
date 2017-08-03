/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 订单商品明细表Entity
 * @author liuhl
 * @version 2017-07-12
 */
public class OrderGoodsList extends DataEntity<OrderGoodsList> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String goodsInfoId;		// 商品基本信息ID
	private String orderGoodsId;		// 商品类订单ID
	private String orderNo;		// 商品类订单号
	private String serialNumbers;		// 商品编号
	private String name;		// 商品名称
	private String imgs;		// 轮播图片
	private String content;		// 图文详情
	private String skuContent;		// 商品规格
	private String skuKeyId;		// 规格名称ID
	private String skuValueId;		// 规格项ID
	private Double basePrice;		// 原价
	private Double benefitPrice;		// 优惠价
	private Integer goodsSum;		// 商品数量
	private Double paySumMoney;		// 小计金额
	private String imageUrl;  //图片URL
	
	public OrderGoodsList() {
		super();
	}

	public OrderGoodsList(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=64, message="商品基本信息ID长度必须介于 1 和 64 之间")
	public String getGoodsInfoId() {
		return goodsInfoId;
	}

	public void setGoodsInfoId(String goodsInfoId) {
		this.goodsInfoId = goodsInfoId;
	}
	
	@Length(min=1, max=64, message="商品类订单ID长度必须介于 1 和 64 之间")
	public String getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(String orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	
	@Length(min=0, max=64, message="商品类订单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=11, message="商品编号长度必须介于 0 和 11 之间")
	public String getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	
	@Length(min=0, max=64, message="商品名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=512, message="轮播图片长度必须介于 0 和 512 之间")
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=255, message="商品规格长度必须介于 1 和 255 之间")
	public String getSkuContent() {
		return skuContent;
	}

	public void setSkuContent(String skuContent) {
		this.skuContent = skuContent;
	}
	
	@Length(min=1, max=64, message="规格名称ID长度必须介于 1 和 64 之间")
	public String getSkuKeyId() {
		return skuKeyId;
	}

	public void setSkuKeyId(String skuKeyId) {
		this.skuKeyId = skuKeyId;
	}
	
	@Length(min=1, max=64, message="规格项ID长度必须介于 1 和 64 之间")
	public String getSkuValueId() {
		return skuValueId;
	}

	public void setSkuValueId(String skuValueId) {
		this.skuValueId = skuValueId;
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
	
	public Integer getGoodsSum() {
		return goodsSum;
	}

	public void setGoodsSum(Integer goodsSum) {
		this.goodsSum = goodsSum;
	}
	
	public Double getPaySumMoney() {
		return paySumMoney;
	}

	public void setPaySumMoney(Double paySumMoney) {
		this.paySumMoney = paySumMoney;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}