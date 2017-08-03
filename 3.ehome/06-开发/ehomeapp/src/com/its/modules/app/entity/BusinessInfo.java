package com.its.modules.app.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.common.persistence.DataEntity;

/**
 * 商家信息管理Entity
 * 
 * @author like
 * 
 * @version 2017-07-07
 */
public class BusinessInfo extends DataEntity<BusinessInfo> {
	
	private static final long serialVersionUID = 1L;
	private String businessName;		// 商户名称
	private String businessPic;		// 商家图片
	private String contactPerson;		// 联系人
	private String phoneNum;		// 联系电话
	private String addrPro;		// 地址_省
	private String addrCity;		// 地址_市
	private String addrArea;		// 地址_区
	private String addrDetail;		// 详址
	private String longitude;		// 经度
	private String latitude;		// 纬度
	private String businessLabel;		// 商家标签
	private String businessHours;		// 营业时间
	private String recomFlag;		// 是否推荐：0否  1是
	private String onlineFlag;		// 是否在线交易
	private String groupPurchaseFlag;		// 是否支持团购
	private String balanceModel;		// 结算模式：0 单笔订单比例抽成  1 单笔订单固定金额抽成
	private Double collectFees;		// 收取费用
	private String balanceCycle;		// 结算周期：0 按周结算  1按月结算  2按天结算
	private String businessIntroduce;		// 商家介绍描述
	private String businessState;		// 营业状态：0停业  1正常营业
	private Date stopBusinessBeginTime;		// 暂停营业起始时间
	private Date stopBusinessEndTime;		// 暂停营业结束时间
	private String serviceModel;		// 预约服务方式：0到店  1上门
	private Integer serviceTimeInterval;		// 服务时间间隔
	private Double serviceCharge;		// 上门服务费用
	private Integer shortestTime;		// 最短上门时间
	private String distributeModel;		// 商品配送方式：0商家配送  1第三方配送
	private Integer distributeTimeInterval;		// 时间片显示区间(配送时间间隔)
	private Integer shortestArriveTime;		// 最短送达时间
	private Double distributeCharge;		// 配送费用
	private String fullDistributeFlag;		// 是否满额起配
	private Double fullDistributeMoney;		// 满额起配金额
	private String freeDistributeFlag;		// 是否满额免运费
	private Double freeDistributeMoney;		// 满额免运费的金额
	private String depositBank;		// 开户银行
	private String accountName;		// 开户名称
	private String bankAccount;		// 银行账号
	private String promotionFlag;		// 促销设置（启用满减活动）
	private String useState;		// 商家状态：1正常 0冻结
	
	public BusinessInfo() {
		super();
	}

	public BusinessInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="商户名称长度必须介于 0 和 64 之间")
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	@Length(min=1, max=64, message="商家图片长度必须介于 1 和 64 之间")
	public String getBusinessPic() {
		return businessPic;
	}

	public void setBusinessPic(String businessPic) {
		this.businessPic = businessPic;
	}
	
	@Length(min=1, max=64, message="联系人长度必须介于 1 和 64 之间")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@Length(min=1, max=64, message="联系电话长度必须介于 1 和 64 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=1, max=64, message="地址_省长度必须介于 1 和 64 之间")
	public String getAddrPro() {
		return addrPro;
	}

	public void setAddrPro(String addrPro) {
		this.addrPro = addrPro;
	}
	
	@Length(min=1, max=64, message="地址_市长度必须介于 1 和 64 之间")
	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	
	@Length(min=1, max=64, message="地址_区长度必须介于 1 和 64 之间")
	public String getAddrArea() {
		return addrArea;
	}

	public void setAddrArea(String addrArea) {
		this.addrArea = addrArea;
	}
	
	@Length(min=1, max=200, message="详址长度必须介于 1 和 200 之间")
	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Length(min=1, max=128, message="商家标签长度必须介于 1 和 128 之间")
	public String getBusinessLabel() {
		return businessLabel;
	}

	public void setBusinessLabel(String businessLabel) {
		this.businessLabel = businessLabel;
	}
	
	@Length(min=1, max=256, message="营业时间长度必须介于 1 和 256 之间")
	public String getBusinessHours() {
		return businessHours;
	}

	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}
	
	@Length(min=1, max=1, message="是否推荐：0否  1是长度必须介于 1 和 1 之间")
	public String getRecomFlag() {
		return recomFlag;
	}

	public void setRecomFlag(String recomFlag) {
		this.recomFlag = recomFlag;
	}
	
	@Length(min=1, max=1, message="是否在线交易长度必须介于 1 和 1 之间")
	public String getOnlineFlag() {
		return onlineFlag;
	}

	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	
	@Length(min=1, max=1, message="是否支持团购长度必须介于 1 和 1 之间")
	public String getGroupPurchaseFlag() {
		return groupPurchaseFlag;
	}

	public void setGroupPurchaseFlag(String groupPurchaseFlag) {
		this.groupPurchaseFlag = groupPurchaseFlag;
	}
	
	@Length(min=1, max=1, message="结算模式：0 单笔订单比例抽成  1 单笔订单固定金额抽成长度必须介于 1 和 1 之间")
	public String getBalanceModel() {
		return balanceModel;
	}

	public void setBalanceModel(String balanceModel) {
		this.balanceModel = balanceModel;
	}
	
	@NotNull(message="收取费用不能为空")
	public Double getCollectFees() {
		return collectFees;
	}

	public void setCollectFees(Double collectFees) {
		this.collectFees = collectFees;
	}
	
	@Length(min=1, max=1, message="结算周期：0 按周结算  1按月结算  2按天结算长度必须介于 1 和 1 之间")
	public String getBalanceCycle() {
		return balanceCycle;
	}

	public void setBalanceCycle(String balanceCycle) {
		this.balanceCycle = balanceCycle;
	}
	
	@Length(min=1, max=2000, message="商家介绍描述长度必须介于 1 和 2000 之间")
	public String getBusinessIntroduce() {
		return businessIntroduce;
	}

	public void setBusinessIntroduce(String businessIntroduce) {
		this.businessIntroduce = businessIntroduce;
	}
	
	@Length(min=1, max=1, message="营业状态：0停业  1正常营业长度必须介于 1 和 1 之间")
	public String getBusinessState() {
		return businessState;
	}

	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="暂停营业起始时间不能为空")
	public Date getStopBusinessBeginTime() {
		return stopBusinessBeginTime;
	}

	public void setStopBusinessBeginTime(Date stopBusinessBeginTime) {
		this.stopBusinessBeginTime = stopBusinessBeginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="暂停营业结束时间不能为空")
	public Date getStopBusinessEndTime() {
		return stopBusinessEndTime;
	}

	public void setStopBusinessEndTime(Date stopBusinessEndTime) {
		this.stopBusinessEndTime = stopBusinessEndTime;
	}
	
	@Length(min=1, max=1, message="预约服务方式：0到店  1上门长度必须介于 1 和 1 之间")
	public String getServiceModel() {
		return serviceModel;
	}

	public void setServiceModel(String serviceModel) {
		this.serviceModel = serviceModel;
	}
	
	@NotNull(message="服务时间间隔不能为空")
	public Integer getServiceTimeInterval() {
		return serviceTimeInterval;
	}

	public void setServiceTimeInterval(Integer serviceTimeInterval) {
		this.serviceTimeInterval = serviceTimeInterval;
	}
	
	@NotNull(message="上门服务费用不能为空")
	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	
	@NotNull(message="最短上门时间不能为空")
	public Integer getShortestTime() {
		return shortestTime;
	}

	public void setShortestTime(Integer shortestTime) {
		this.shortestTime = shortestTime;
	}
	
	@Length(min=1, max=1, message="商品配送方式：0商家配送  1第三方配送长度必须介于 1 和 1 之间")
	public String getDistributeModel() {
		return distributeModel;
	}

	public void setDistributeModel(String distributeModel) {
		this.distributeModel = distributeModel;
	}
	
	@NotNull(message="时间片显示区间(配送时间间隔)不能为空")
	public Integer getDistributeTimeInterval() {
		return distributeTimeInterval;
	}

	public void setDistributeTimeInterval(Integer distributeTimeInterval) {
		this.distributeTimeInterval = distributeTimeInterval;
	}
	
	@NotNull(message="最短送达时间不能为空")
	public Integer getShortestArriveTime() {
		return shortestArriveTime;
	}

	public void setShortestArriveTime(Integer shortestArriveTime) {
		this.shortestArriveTime = shortestArriveTime;
	}
	
	@NotNull(message="配送费用不能为空")
	public Double getDistributeCharge() {
		return distributeCharge;
	}

	public void setDistributeCharge(Double distributeCharge) {
		this.distributeCharge = distributeCharge;
	}
	
	@Length(min=1, max=1, message="是否满额起配长度必须介于 1 和 1 之间")
	public String getFullDistributeFlag() {
		return fullDistributeFlag;
	}

	public void setFullDistributeFlag(String fullDistributeFlag) {
		this.fullDistributeFlag = fullDistributeFlag;
	}
	
	@NotNull(message="满额起配金额不能为空")
	public Double getFullDistributeMoney() {
		return fullDistributeMoney;
	}

	public void setFullDistributeMoney(Double fullDistributeMoney) {
		this.fullDistributeMoney = fullDistributeMoney;
	}
	
	@Length(min=1, max=1, message="是否满额免运费长度必须介于 1 和 1 之间")
	public String getFreeDistributeFlag() {
		return freeDistributeFlag;
	}

	public void setFreeDistributeFlag(String freeDistributeFlag) {
		this.freeDistributeFlag = freeDistributeFlag;
	}
	
	@NotNull(message="满额免运费的金额不能为空")
	public Double getFreeDistributeMoney() {
		return freeDistributeMoney;
	}

	public void setFreeDistributeMoney(Double freeDistributeMoney) {
		this.freeDistributeMoney = freeDistributeMoney;
	}
	
	@Length(min=1, max=64, message="开户银行长度必须介于 1 和 64 之间")
	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}
	
	@Length(min=1, max=64, message="开户名称长度必须介于 1 和 64 之间")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Length(min=1, max=32, message="银行账号长度必须介于 1 和 32 之间")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Length(min=1, max=1, message="促销设置（启用满减活动）长度必须介于 1 和 1 之间")
	public String getPromotionFlag() {
		return promotionFlag;
	}

	public void setPromotionFlag(String promotionFlag) {
		this.promotionFlag = promotionFlag;
	}
	
	@Length(min=0, max=1, message="商家状态：1正常 0冻结长度必须介于 0 和 1 之间")
	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}
}