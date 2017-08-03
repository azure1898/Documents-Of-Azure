package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 优惠券管理Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-07-18
 */
public class CouponManage extends DataEntity<CouponManage> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 楼盘ID
	private String couponName;		// 优惠券名称
	private String couponType;		// 优惠券类型：0固定金额券  1折扣券
	private Double couponMoney;		// 优惠数量：金额或折扣数
	private Double upperLimitMoney;		// 优惠上限
	private String useRule;		// 使用条件：0无限制  1满额使用
	private Double fullUseMoney;		// 满额可用的金额
	private String grantType;		// 发放总量：0无限制  1限量发送
	private Integer limitedNum;		// 限量发送数量
	private Integer receiveNum;		// 领取总量
	private String useScope;		// 使用范围：0无限制  1服务品类专享  2商家专享
	private String useObject;		// 使用范围服务品类/服务商家
	private String shareFlag;		// 是否与其它优惠同享
	private String validityType;		// 有效期类别：0时间段  1有效天数
	private Date validityStartTime;		// 团购券有效期开始时间
	private Date validityEndTime;		// 团购券有效期结束时间
	private Integer validityDays;		// 有效天数
	private String receiveType;		// 领取方式：0买家领取  1下单赠送  2平台推送
	private String receiveRule;		// 买家领取规则：0无限制  1每人每日限领1张  2每人限领1张
	private String giveRule;		// 赠送规则：0下单即送  1满额送
	private Double fullGiveRule;		// 满额赠送标准
	private String pushObjType;		// 推送对象类型：0平台注册用户  1平台登录用户  2未下单用户  3下单用户  4自定义用户
	private String timeScope;		// 时间范围：0一个月内  1三个月内  2六个月内  3自定义
	private Date timeScopeStartTime;		// 时间范围开始时间
	private Date timeScopeEndTime;		// 时间范围结束时间
	private String orderType;		// 订单类型
	private String userDescribes;		// 用户描述
	private Date activeStartTime;		// 活动起始时间
	private Date activeEndTime;		// 活动结束时间
	private String activeState;		// 活动状态：0待开始  1活动中  2已结束  3已关闭
	
	public CouponManage() {
		super();
	}

	public CouponManage(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=64, message="优惠券名称长度必须介于 0 和 64 之间")
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	@Length(min=0, max=1, message="优惠券类型：0固定金额券  1折扣券长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=1, message="使用条件：0无限制  1满额使用长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=1, message="发放总量：0无限制  1限量发送长度必须介于 0 和 1 之间")
	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	
	public Integer getLimitedNum() {
		return limitedNum;
	}

	public void setLimitedNum(Integer limitedNum) {
		this.limitedNum = limitedNum;
	}
	
	public Integer getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(Integer receiveNum) {
		this.receiveNum = receiveNum;
	}
	
	@Length(min=0, max=1, message="使用范围：0无限制  1服务品类专享  2商家专享长度必须介于 0 和 1 之间")
	public String getUseScope() {
		return useScope;
	}

	public void setUseScope(String useScope) {
		this.useScope = useScope;
	}
	
	@Length(min=0, max=64, message="使用范围服务品类/服务商家长度必须介于 0 和 64 之间")
	public String getUseObject() {
		return useObject;
	}

	public void setUseObject(String useObject) {
		this.useObject = useObject;
	}
	
	@Length(min=0, max=1, message="是否与其它优惠同享长度必须介于 0 和 1 之间")
	public String getShareFlag() {
		return shareFlag;
	}

	public void setShareFlag(String shareFlag) {
		this.shareFlag = shareFlag;
	}
	
	@Length(min=0, max=1, message="有效期类别长度必须介于 0 和 1 之间")
	public String getValidityType() {
		return validityType;
	}

	public void setValidityType(String validityType) {
		this.validityType = validityType;
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
	
	public Integer getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Integer validityDays) {
		this.validityDays = validityDays;
	}
	
	@Length(min=0, max=1, message="领取方式：0买家领取  1下单赠送  2平台推送长度必须介于 0 和 1 之间")
	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	
	@Length(min=0, max=1, message="买家领取规则：0无限制  1每人每日限领1张  2每人限领1张长度必须介于 0 和 1 之间")
	public String getReceiveRule() {
		return receiveRule;
	}

	public void setReceiveRule(String receiveRule) {
		this.receiveRule = receiveRule;
	}
	
	@Length(min=0, max=1, message="赠送规则：0下单即送  1满额送长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=1, message="推送对象类型：0平台注册用户  1平台登录用户  2未下单用户  3下单用户  4自定义用户长度必须介于 0 和 1 之间")
	public String getPushObjType() {
		return pushObjType;
	}

	public void setPushObjType(String pushObjType) {
		this.pushObjType = pushObjType;
	}
	
	@Length(min=0, max=1, message="时间范围：0一个月内  1三个月内  2六个月内  3自定义长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=6400, message="订单类型长度必须介于 0 和 6400 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=4000, message="用户描述长度必须介于 0 和 4000 之间")
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
	
	@Length(min=0, max=1, message="活动状态：0待开始  1活动中  2已结束  3已关闭长度必须介于 0 和 1 之间")
	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}
}