/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.lesson.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;
import com.its.modules.service.entity.ServiceInfoPic;

/**
 * 课程培训Entity
 * @author sushipeng
 * @version 2017-07-19
 */
public class LessonInfo extends DataEntity<LessonInfo> {
	
	private static final long serialVersionUID = 1L;
	private String businessInfoId;		// 商家id
	private String serialNumbers;		// 课程编号
	private String name;		// 课程名称
	private String imgs;		// 轮播图片
	private Double basePrice;		// 原价
	private Double benefitPrice;		// 优惠价
	private Integer peopleLimit;		// 人数限制（库存）
	private Integer lessonCount;		// 课次
	private Integer sellCount;		// 已售数量
	private Date startTime;		// 上课开始时间
	private Date endTime;		// 上课结束时间
	private String address;		// 上课地点
	private String content;		// 课程介绍
	private String lessonQuota;		// 是否限购:0否1是
	private Integer quotaNum;		// 限购数量
	private String recommend;		// 是否推荐:0否1是
	private String state;		// 上下架状态 0下架1上架
	private String sortItem; // 排序项目
	private String sort;  // 排序顺
	private List<String> imageUrls; //一览用图片路径
	private List<ServiceInfoPic> picList; //图片LIST
	private String delImageName; //服务编辑时删除的图片名称
	private String isfull; // 是否约满（商家首页查询用）

	private String stock;		// 用于条件查询,约满

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public LessonInfo() {
		super();
	}

	public LessonInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="商家id长度必须介于 1 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}
	
	@Length(min=1, max=11, message="课程编号长度必须介于 1 和 11 之间")
	public String getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
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
	
	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
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
	
	@Length(min=0, max=1, message="是否限购:0否1是长度必须介于 0 和 1 之间")
	public String getLessonQuota() {
		return lessonQuota;
	}

	public void setLessonQuota(String lessonQuota) {
		this.lessonQuota = lessonQuota;
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

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<ServiceInfoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<ServiceInfoPic> picList) {
		this.picList = picList;
	}

	public String getDelImageName() {
		return delImageName;
	}

	public void setDelImageName(String delImageName) {
		this.delImageName = delImageName;
	}

	public String getIsfull() {
		return isfull;
	}

	public void setIsfull(String isfull) {
		this.isfull = isfull;
	}
	
}