/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 订单团购券清单Entity
 * @author lq
 * @created on 2017年7月27日
 */
public class OrderGroupPurcList extends DataEntity<OrderGroupPurcList> {

	private static final long serialVersionUID = 1L;
	private String businessInfoId;// 商家id
	private String groupPurchaseId;// 团购ID
	private String orderGroupPurcId;// 团购订单ID
	private String orderNo;// 团购订单号
	private String name;// 团购名称
	private String imgs;// 团购图片
	private Double basePrice;// 市场价
	private Double groupPurcPrice;// 团购价
	private Date startTime;// 团购券有效开始时间
	private Date endTime;// 团购券有效结束时间
	private String content;// 团购详情
	private String useTime;// 使用时间
	private String useContent;// 使用规则
	private Double paySumMoney;// 小计金额
	private String groupPurcNumber;// 团购券号
	private String groupPurcState;// 团购券状态:0未消费1已消费2退款处理中3已退款
	private Date consumeTime;// 消费时间

	private String searchFlg;// 查询标识

	public OrderGroupPurcList() {
		super();
	}

	public OrderGroupPurcList(String id) {
		super(id);
	}

	@Length(min = 1, max = 64, message = "商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	public String getGroupPurchaseId() {
		return groupPurchaseId;
	}

	public void setGroupPurchaseId(String groupPurchaseId) {
		this.groupPurchaseId = groupPurchaseId;
	}

	public String getOrderGroupPurcId() {
		return orderGroupPurcId;
	}

	public void setOrderGroupPurcId(String orderGroupPurcId) {
		this.orderGroupPurcId = orderGroupPurcId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Double getGroupPurcPrice() {
		return groupPurcPrice;
	}

	public void setGroupPurcPrice(Double groupPurcPrice) {
		this.groupPurcPrice = groupPurcPrice;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getUseContent() {
		return useContent;
	}

	public void setUseContent(String useContent) {
		this.useContent = useContent;
	}

	public Double getPaySumMoney() {
		return paySumMoney;
	}

	public void setPaySumMoney(Double paySumMoney) {
		this.paySumMoney = paySumMoney;
	}

	public String getGroupPurcNumber() {
		return groupPurcNumber;
	}

	public void setGroupPurcNumber(String groupPurcNumber) {
		this.groupPurcNumber = groupPurcNumber;
	}

	public String getGroupPurcState() {
		return groupPurcState;
	}

	public void setGroupPurcState(String groupPurcState) {
		this.groupPurcState = groupPurcState;
	}

	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}

	public String getSearchFlg() {
		return searchFlg;
	}

	public void setSearchFlg(String searchFlg) {
		this.searchFlg = searchFlg;
	}
}
