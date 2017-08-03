/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 优惠券管理Entity
 * 
 * @author liuqi
 * @version 2017-07-03
 */
public class CouponManage extends DataEntity<CouponManage> {

	/**
	 * 固定金额
	 */
	public final static String COUPON_TYPE_FIXMONEY = "0";
	/**
	 * 优惠折扣
	 */
	public final static String COUPON_TYPE_DISCOUNT = "1";
	/**
	 * 平台注册用户
	 */
	public final static String PUSH_OBJ_TYPE_PLATREGUSER = "0";
	/**
	 * 平台登录用户
	 */
	public final static String PUSH_OBJ_TYPE_PLATLOGUSER = "1";
	/**
	 * 未下单用户
	 */
	public final static String PUSH_OBJ_TYPE_UNORDERUSER = "2";
	/**
	 * 下单用户
	 */
	public final static String PUSH_OBJ_TYPE_ORDERUSER = "3";
	/**
	 * 自定义用户
	 */
	public final static String PUSH_OBJ_TYPE_CUSTOMUSER = "4";

	/**
	 * 活动状态：0待开始 1活动中 2已结束 3已关闭
	 */
	public final static String ACTIVE_STATE_TOBEGIN = "0";
	/**
	 * 活动状态：0待开始 1活动中 2已结束 3已关闭
	 */
	public final static String ACTIVE_STATE_ING = "1";
	/**
	 * 活动状态：0待开始 1活动中 2已结束 3已关闭
	 */
	public final static String ACTIVE_STATE_OVER = "2";
	/**
	 * 活动状态：0待开始 1活动中 2已结束 3已关闭
	 */
	public final static String ACTIVE_STATE_CLOSED = "3";

	private static final long serialVersionUID = 1L;
	private String villageInfoId; // 楼盘ID
	private String couponName; // 优惠券名称
	private String couponType; // 优惠券类型：0固定金额券 1折扣券
	private Double couponMoney; // 优惠数量
	private Double upperLimitMoney; // 优惠上限
	private String useRule; // 使用条件：0无限制 1满额使用
	private Double fullUseMoney; // 满额可用的金额
	private String grantType; // 发放总量：0无限制 1限量发送
	private int limitedNum; // 限量发送数量
	private int receiveNum; // 领取总量
	private String useScope; // 使用范围：0无限制 1服务品类专享 2商家专享
	private String useObject; // 使用范围服务品类/服务商家
	private String shareFlag; // 是否与其它优惠同享
	private String ValidityType; // 有效期类型 0起至有效期 1自领取后有效天数
	private Date validityStartTime; // 团购券有效期开始时间
	private Date validityEndTime; // 团购券有效期结束时间
	private int validityDays; // 有效天数
	private String receiveType; // 领取方式：0买家领取 1下单赠送 2平台推送
	private String receiveRule; // 买家领取规则：0无限制 1每人每日限领1张 2每人限领1张
	private String giveRule; // 赠送规则：0下单即送 1满额送
	private Double fullGiveRule; // 满额赠送标准
	private String pushObjType; // 推送对象类型：0平台注册用户 1平台登录用户 2未下单用户 3下单用户 4自定义用户
	private String timeScope; // 时间范围
	private Date timeScopeStartTime; // 时间范围开始时间
	private Date timeScopeEndTime; // 时间范围结束时间
	private String orderType; // 订单类型
	private String userDescribes; // 用户描述
	private Date activeStartTime; // 活动起始时间
	private Date activeEndTime; // 活动结束时间
	private String activeState; // 活动状态
	private String lastId;
	private int receiveCount; // 领取总量

	public CouponManage() {
		super();
	}

	public CouponManage(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	@Length(min = 0, max = 64, message = "优惠券名称长度必须介于 0 和 64 之间")
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	/* @Length(min=0, max=1, message="优惠券类型：0固定金额券  1折扣券长度必须介于 0 和 1 之间") */
	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Double getUpperLimitMoney() {
		return upperLimitMoney;
	}

	public void setUpperLimitMoney(Double upperLimitMoney) {
		this.upperLimitMoney = upperLimitMoney;
	}

	/* @Length(min=0, max=1, message="使用条件：0无限制  1满额使用长度必须介于 0 和 1 之间") */
	public String getUseRule() {
		return useRule;
	}

	public void setUseRule(String useRule) {
		this.useRule = useRule;
	}

	public Double getFullUseMoney() {
		return fullUseMoney;
	}

	public void setFullUseMoney(Double fullUseMoney) {
		this.fullUseMoney = fullUseMoney;
	}

	/* @Length(min=0, max=1, message="发放总量：0无限制  1限量发送长度必须介于 0 和 1 之间") */
	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public int getLimitedNum() {
		return limitedNum;
	}

	public void setLimitedNum(int limitedNum) {
		this.limitedNum = limitedNum;
	}

	public int getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(int receiveNum) {
		this.receiveNum = receiveNum;
	}

	/*
	 * @Length(min=0, max=1, message="使用范围：0无限制  1服务品类专享  2商家专享长度必须介于 0 和 1 之间")
	 */
	public String getUseScope() {
		return useScope;
	}

	public void setUseScope(String useScope) {
		this.useScope = useScope;
	}

	@Length(min = 0, max = 64, message = "使用范围服务品类/服务商家长度必须介于 0 和 64 之间")
	public String getUseObject() {
		return useObject;
	}

	public void setUseObject(String useObject) {
		this.useObject = useObject;
	}

	/* @Length(min=0, max=1, message="是否与其它优惠同享长度必须介于 0 和 1 之间") */
	public String getShareFlag() {
		return shareFlag;
	}

	public void setShareFlag(String shareFlag) {
		this.shareFlag = shareFlag;
	}

	public String getValidityType() {
		return ValidityType;
	}

	public void setValidityType(String validityType) {
		ValidityType = validityType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidityStartTime() {
		return validityStartTime;
	}

	public void setValidityStartTime(Date validityStartTime) {
		this.validityStartTime = validityStartTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidityEndTime() {
		return validityEndTime;
	}

	public void setValidityEndTime(Date validityEndTime) {
		this.validityEndTime = validityEndTime;
	}

	/* @Length(min=0, max=11, message="有效天数长度必须介于 0 和 11 之间") */
	public int getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(int validityDays) {
		this.validityDays = validityDays;
	}

	/*
	 * @Length(min=0, max=1, message="领取方式：0买家领取  1下单赠送  2平台推送长度必须介于 0 和 1 之间")
	 */
	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	/*
	 * @Length(min=0, max=1,
	 * message="买家领取规则：0无限制  1每人每日限领1张  2每人限领1张长度必须介于 0 和 1 之间")
	 */
	public String getReceiveRule() {
		return receiveRule;
	}

	public void setReceiveRule(String receiveRule) {
		this.receiveRule = receiveRule;
	}

	/* @Length(min=0, max=1, message="赠送规则：0下单即送  1满额送长度必须介于 0 和 1 之间") */
	public String getGiveRule() {
		return giveRule;
	}

	public void setGiveRule(String giveRule) {
		this.giveRule = giveRule;
	}

	public Double getFullGiveRule() {
		return fullGiveRule;
	}

	public void setFullGiveRule(Double fullGiveRule) {
		this.fullGiveRule = fullGiveRule;
	}

	/*
	 * @Length(min=0, max=1,
	 * message="推送对象类型：0平台注册用户  1平台登录用户  2未下单用户  3下单用户  4自定义用户长度必须介于 0 和 1 之间")
	 */
	public String getPushObjType() {
		return pushObjType;
	}

	public void setPushObjType(String pushObjType) {
		this.pushObjType = pushObjType;
	}

	/* @Length(min=0, max=1, message="时间范围长度必须介于 0 和 1 之间") */
	public String getTimeScope() {
		return timeScope;
	}

	public void setTimeScope(String timeScope) {
		this.timeScope = timeScope;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeScopeStartTime() {
		return timeScopeStartTime;
	}

	public void setTimeScopeStartTime(Date timeScopeStartTime) {
		this.timeScopeStartTime = timeScopeStartTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeScopeEndTime() {
		return timeScopeEndTime;
	}

	public void setTimeScopeEndTime(Date timeScopeEndTime) {
		this.timeScopeEndTime = timeScopeEndTime;
	}

	@Length(min = 0, max = 6400, message = "订单类型长度必须介于 0 和 6400 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Length(min = 0, max = 4000, message = "用户描述长度必须介于 0 和 4000 之间")
	public String getUserDescribes() {
		return userDescribes;
	}

	public void setUserDescribes(String userDescribes) {
		this.userDescribes = userDescribes;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActiveStartTime() {
		return activeStartTime;
	}

	public void setActiveStartTime(Date activeStartTime) {
		this.activeStartTime = activeStartTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActiveEndTime() {
		return activeEndTime;
	}

	public void setActiveEndTime(Date activeEndTime) {
		this.activeEndTime = activeEndTime;
	}

	/* @Length(min=0, max=1, message="活动状态长度必须介于 0 和 1 之间") */
	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
	
	public int getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(int receiveCount) {
		this.receiveCount = receiveCount;
	}

}