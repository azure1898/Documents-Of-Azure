package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 订单-课程培训类子表-课程培训清单Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-07-12
 */
public class OrderLessonList extends DataEntity<OrderLessonList> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String lessonInfoId;		// 课程培训ID
	private String orderLessonId;		// 课程培训订单ID
	private String orderNo;		// 课程培训订单号
	private String name;		// 课程名称
	private String imgs;		// 轮播图片
	private Integer peopleLimit;		// 人数限制
	private Integer lessonCount;		// 课次
	private Date startTime;		// 上课开始时间
	private Date endTime;		// 上课结束时间
	private String address;		// 上课地点
	private String content;		// 课程介绍
	private Double basePrice;		// 原价
	private Double benefitPrice;		// 优惠价
	private Double paySumMoney;		// 小计金额
	
	public OrderLessonList() {
		super();
	}

	public OrderLessonList(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=64, message="课程培训ID长度必须介于 1 和 64 之间")
	public String getLessonInfoId() {
		return lessonInfoId;
	}

	public void setLessonInfoId(String lessonInfoId) {
		this.lessonInfoId = lessonInfoId;
	}
	
	@Length(min=1, max=64, message="课程培训订单ID长度必须介于 1 和 64 之间")
	public String getOrderLessonId() {
		return orderLessonId;
	}

	public void setOrderLessonId(String orderLessonId) {
		this.orderLessonId = orderLessonId;
	}
	
	@Length(min=0, max=64, message="课程培训订单号长度必须介于 0 和 64 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=0, max=64, message="课程名称长度必须介于 0 和 64 之间")
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
	
	public Integer getPeopleLimit() {
		return peopleLimit;
	}

	public void setPeopleLimit(Integer peopleLimit) {
		this.peopleLimit = peopleLimit;
	}
	
	public Integer getLessonCount() {
		return lessonCount;
	}

	public void setLessonCount(Integer lessonCount) {
		this.lessonCount = lessonCount;
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
	
	@Length(min=0, max=255, message="上课地点长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
}