/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;

/**
 * 商家结算信息Entity
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
public class BusinessBalance extends DataEntity<BusinessBalance> {

	private static final long serialVersionUID = 1L;
	private String businessInfoId; // 商户ID
	private String balanceCycle; // 结算周期：0 按周结算 1按月结算 2按天结算
	private Date balanceStartTime; // 结算周期开始时间
	private Date balanceEndTime; // 结算周期结束时间
	private String balanceModel; // 结算模式：0 单笔订单比例抽成 1 单笔订单固定金额抽成
	private String balanceState; // 结算状态：0未结算 1已结算
	private Double orderMoney; // 订单金额
	private Double couponMoney; // 平台优惠金额
	private Double deductionMoney; // 扣点金额
	private Double payMoney; // 应付金额
	private String checkState; // 核对状态
	private String businessName; // 商户名称
	private String cityName; // 所在城市
	private String ids; // 页面的checkbox复选结果
	
	// 打印和导出用到的结算单成员
	private String  serialNum; // 序号
	private String accountName; // 开户名称
	private String depositBank; // 开户银行
	private String bankAccount; // 银行账号
	private String remark; // 备注
	private String addrPro; // 地址_省
	private String addrCity; // 地址_市
	private String villageInfoId; // 楼盘
	
	public BusinessBalance() {
		super();
	}

	public BusinessBalance(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "商户ID长度必须介于 0 和 64 之间")
	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	@Length(min = 0, max = 1, message = "结算周期：0 按周结算  1按月结算  2按天结算长度必须介于 0 和 1 之间")
	public String getBalanceCycle() {
		return balanceCycle;
	}

	public void setBalanceCycle(String balanceCycle) {
		this.balanceCycle = balanceCycle;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBalanceStartTime() {
		return balanceStartTime;
	}

	public void setBalanceStartTime(Date balanceStartTime) {
		this.balanceStartTime = balanceStartTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBalanceEndTime() {
		return balanceEndTime;
	}

	public void setBalanceEndTime(Date balanceEndTime) {
		this.balanceEndTime = balanceEndTime;
	}

	@Length(min = 0, max = 1, message = "结算模式：0 单笔订单比例抽成  1 单笔订单固定金额抽成长度必须介于 0 和 1 之间")
	public String getBalanceModel() {
		return balanceModel;
	}

	public void setBalanceModel(String balanceModel) {
		this.balanceModel = balanceModel;
	}

	@Length(min = 0, max = 1, message = "结算状态：0未结算  1已结算长度必须介于 0 和 1 之间")
	public String getBalanceState() {
		return balanceState;
	}

	public void setBalanceState(String balanceState) {
		this.balanceState = balanceState;
	}

	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Double getDeductionMoney() {
		return deductionMoney;
	}

	public void setDeductionMoney(Double deductionMoney) {
		this.deductionMoney = deductionMoney;
	}

	@ExcelField(title = "应付金额", type = 1, align = 2, sort = 7)
	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	@Length(min = 0, max = 1, message = "核对状态长度必须介于 0 和 1 之间")
	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	@ExcelField(title = "商家名称", type = 1, align = 2, sort = 2)
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@ExcelField(title = "所在城市", type = 1, align = 2, sort = 6)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@ExcelField(title = "序号", type = 1, align = 2, sort = 1)
	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	@ExcelField(title = "开户名称", type = 1, align = 2, sort = 3)
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@ExcelField(title = "开户银行", type = 1, align = 2, sort = 4)
	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	@ExcelField(title = "银行账号", type = 1, align = 2, sort = 5)
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@ExcelField(title = "备注", type = 1, align = 2, sort = 8)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddrPro() {
		return addrPro;
	}

	public void setAddrPro(String addrPro) {
		this.addrPro = addrPro;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	@Override
	public String toString() {
		return "BusinessBalance [businessInfoId=" + businessInfoId + ", balanceCycle=" + balanceCycle
				+ ", balanceStartTime=" + balanceStartTime + ", balanceEndTime=" + balanceEndTime + ", balanceModel="
				+ balanceModel + ", balanceState=" + balanceState + ", orderMoney=" + orderMoney + ", couponMoney="
				+ couponMoney + ", deductionMoney=" + deductionMoney + ", payMoney=" + payMoney + ", checkState="
				+ checkState + "]";
	}

}