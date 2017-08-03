package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 订单-团购类子表-团购券清单Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-07-13
 */
public class OrderGroupPurcList extends DataEntity<OrderGroupPurcList> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String groupPurchaseId;		// 团购ID
	private String orderGroupPurcId;		// 团购订单ID
	private String orderNo;		// 团购订单号
	private String name;		// 团购名称
	private String imgs;		// 团购图片
	private Double basePrice;		// 市场价
	private Double groupPurcPrice;		// 团购价
	private Date startTime;		// 团购券有效开始时间
	private Date endTime;		// 团购券有效结束时间
	private String content;		// 团购详情
	private String useTime;		// 使用时间
	private String useContent;		// 使用规则
	private Double paySumMoney;		// 小计金额
	private String groupPurcNumber;		// 团购券号
	private String groupPurcState;		// 团购券状态:0未消费1已消费2退款处理中3已退款
	private Date consumeTime;		// 消费时间
	
	public OrderGroupPurcList() {
		super();
	}

	public OrderGroupPurcList(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=64, message="团购ID长度必须介于 1 和 64 之间")
	public String getGroupPurchaseId() {
		return groupPurchaseId;
	}

	public void setGroupPurchaseId(String groupPurchaseId) {
		this.groupPurchaseId = groupPurchaseId;
	}
	
	@Length(min=1, max=64, message="团购订单ID长度必须介于 1 和 64 之间")
	public String getOrderGroupPurcId() {
		return orderGroupPurcId;
	}

	public void setOrderGroupPurcId(String orderGroupPurcId) {
		this.orderGroupPurcId = orderGroupPurcId;
	}
	
	@Length(min=0, max=64, message="团购订单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="团购名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=512, message="团购图片长度必须介于 0 和 512 之间")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	
	@Length(min=0, max=255, message="使用时间长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=64, message="团购券号长度必须介于 0 和 64 之间")
	public String getGroupPurcNumber() {
		return groupPurcNumber;
	}

	public void setGroupPurcNumber(String groupPurcNumber) {
		this.groupPurcNumber = groupPurcNumber;
	}
	
	@Length(min=0, max=1, message="团购券状态:0未消费1已消费2退款处理中3已退款长度必须介于 0 和 1 之间")
	public String getGroupPurcState() {
		return groupPurcState;
	}

	public void setGroupPurcState(String groupPurcState) {
		this.groupPurcState = groupPurcState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}	
}