/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 购物车Entity
 * @author like
 * @version 2017-07-06
 */
public class ShoppingCart extends DataEntity<ShoppingCart> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageInfoId;		// 楼盘ID
	private String businessInfoId;		// 商家ID
	private String goodsInfoId;		// 商品ID
	private String goodsSkuPriceId;		// 商品规格价格表ID
	private Integer number;		// 数量
	private Date addDate;		// 加入时间
	private Double price;		// 价格
	private String orderStatus;		// 是否已生成订单
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(String id){
		super(id);
	}

	@Length(min=0, max=64, message="会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=64, message="商家ID长度必须介于 0 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=0, max=64, message="商品ID长度必须介于 0 和 64 之间")
	public String getGoodsInfoId() {
		return goodsInfoId;
	}

	public void setGoodsInfoId(String goodsInfoId) {
		this.goodsInfoId = goodsInfoId;
	}
	
	@Length(min=0, max=64, message="商品规格价格表ID长度必须介于 0 和 64 之间")
	public String getGoodsSkuPriceId() {
		return goodsSkuPriceId;
	}

	public void setGoodsSkuPriceId(String goodsSkuPriceId) {
		this.goodsSkuPriceId = goodsSkuPriceId;
	}
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Length(min=0, max=1, message="是否已生成订单长度必须介于 0 和 1 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}