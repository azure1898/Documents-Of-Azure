package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 钱包明细Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-08-16
 */
public class WalletDetail extends DataEntity<WalletDetail> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageInfoId;		// 楼盘ID
	private String tradeType;		// 交易类型：0-充值；1-充值赠送；2-钱包支付；3-手环支付；4-刷脸支付；5-退款
	private String orderId;		// 订单ID
	private Double walletPrincipal;		// 本金金额
	private Double walletPresent;		// 赠送金额
	private Date tradeDate;		// 交易时间
	private String terminalSource;		// 终端来源：0-Android；1-IOS
	private String mobileUniqueCode;		// 手机唯一码
	private String payType;		// 支付方式：0-余额支付；1-微信支付；2-支付宝支付
	
	public WalletDetail() {
		super();
	}

	public WalletDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=2, message="交易类型：0-充值；1-充值赠送；2-钱包支付；3-手环支付；4-刷脸支付；5-退款长度必须介于 0 和 2 之间")
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	@Length(min=0, max=64, message="订单ID长度必须介于 0 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public Double getWalletPrincipal() {
		return walletPrincipal;
	}

	public void setWalletPrincipal(Double walletPrincipal) {
		this.walletPrincipal = walletPrincipal;
	}
	
	public Double getWalletPresent() {
		return walletPresent;
	}

	public void setWalletPresent(Double walletPresent) {
		this.walletPresent = walletPresent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	@Length(min=0, max=1, message="终端来源：0-Android；1-IOS长度必须介于 0 和 1 之间")
	public String getTerminalSource() {
		return terminalSource;
	}

	public void setTerminalSource(String terminalSource) {
		this.terminalSource = terminalSource;
	}
	
	@Length(min=0, max=64, message="手机唯一码长度必须介于 0 和 64 之间")
	public String getMobileUniqueCode() {
		return mobileUniqueCode;
	}

	public void setMobileUniqueCode(String mobileUniqueCode) {
		this.mobileUniqueCode = mobileUniqueCode;
	}
	
	@Length(min=0, max=1, message="支付方式：0-余额支付；1-微信支付；2-支付宝支付长度必须介于 0 和 1 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
}