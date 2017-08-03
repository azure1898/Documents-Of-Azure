/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 服务管理Entity
 * @author sushipeng
 * @version 2017-07-26
 */
public class ServiceInfo extends DataEntity<ServiceInfo> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String serialNumbers;		// 服务编号
	private String name;		// 服务名称
	private String sortInfoId;		// 服务分类ID
	private String imgs;		// 轮播图片
	private Double basePrice;		// 原价
	private Double benefitPrice;		// 优惠价
	private Integer stock;		// 每日库存
	private Integer sellCount;		// 已售数量
	private String baseUnit;		// 服务单位
	private String isBase;		// 服务单位是否来自公共字典表:0否1是
	private String content;		// 服务介绍
	private String quota;		// 是否限购:0否1是
	private Integer quotaNum;		// 限购数量
	private String recommend;		// 是否推荐:0否1是
	private String state;		// 上下架状态 0下架1上架
	
	public ServiceInfo() {
		super();
	}

	public ServiceInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=11, message="服务编号长度必须介于 1 和 11 之间")
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
	
	@Length(min=1, max=64, message="服务分类ID长度必须介于 1 和 64 之间")
	public String getSortInfoId() {
		return sortInfoId;
	}

	public void setSortInfoId(String sortInfoId) {
		this.sortInfoId = sortInfoId;
	}
	
	@Length(min=0, max=512, message="轮播图片长度必须介于 0 和 512 之间")
	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
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
	
	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}
	
	@Length(min=0, max=64, message="服务单位长度必须介于 0 和 64 之间")
	public String getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}
	
	@Length(min=0, max=1, message="服务单位是否来自公共字典表:0否1是长度必须介于 0 和 1 之间")
	public String getIsBase() {
		return isBase;
	}

	public void setIsBase(String isBase) {
		this.isBase = isBase;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="是否限购:0否1是长度必须介于 0 和 1 之间")
	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}
	
	public Integer getQuotaNum() {
		return quotaNum;
	}

	public void setQuotaNum(Integer quotaNum) {
		this.quotaNum = quotaNum;
	}
	
	@Length(min=0, max=1, message="是否推荐:0否1是长度必须介于 0 和 1 之间")
	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	
	@Length(min=0, max=1, message="上下架状态 0下架1上架长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}