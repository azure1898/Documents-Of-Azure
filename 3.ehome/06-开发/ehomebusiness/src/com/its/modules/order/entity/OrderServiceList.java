/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 订单-服务类子表-服务清单Entity
 * @author liuhl
 * @version 2017-07-17
 */
public class OrderServiceList extends DataEntity<OrderServiceList> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String serviceInfoId;		// 服务ID
	private String orderServiceId;		// 服务类订单ID
	private String orderNo;		// 服务类订单编号
	private String serialNumbers;		// 服务编号
	private String name;		// 服务名称
	private String imgs;		// 轮播图片
	private Integer payCount;		// 服务数量
	private String content;		// 服务介绍
	private Double basePrice;		// 原价
	private Double benefitPrice;		// 优惠价
	private Double paySumMoney;		// 小计金额
	private String imageUrl;  //图片URL
	
	public OrderServiceList() {
		super();
	}

	public OrderServiceList(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=64, message="服务ID长度必须介于 1 和 64 之间")
	public String getServiceInfoId() {
		return serviceInfoId;
	}

	public void setServiceInfoId(String serviceInfoId) {
		this.serviceInfoId = serviceInfoId;
	}
	
	@Length(min=1, max=64, message="服务类订单ID长度必须介于 1 和 64 之间")
	public String getOrderServiceId() {
		return orderServiceId;
	}

	public void setOrderServiceId(String orderServiceId) {
		this.orderServiceId = orderServiceId;
	}
	
	@Length(min=0, max=64, message="服务类订单编号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=11, message="服务编号长度必须介于 0 和 11 之间")
	public String getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	
	@Length(min=0, max=64, message="服务名称长度必须介于 0 和 64 之间")
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
	
	public Integer getPayCount() {
		return payCount;
	}

	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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