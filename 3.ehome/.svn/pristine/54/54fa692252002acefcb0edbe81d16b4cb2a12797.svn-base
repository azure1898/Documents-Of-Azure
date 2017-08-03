/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.recharge.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;
import com.its.common.utils.excel.annotation.ExcelField;
import com.its.modules.account.entity.Account;
import com.its.modules.sys.utils.DictUtils;

/**
 * 充值记录Entity
 * 
 * @author ChenXiangyu
 * @version 2017-07-05
 */
public class WalletDetail extends DataEntity<WalletDetail> {

	private static final long serialVersionUID = 1L;
	/** label：账号充值 */
	public static final String LABEL_ACCOUNT_RECHARGE = "充值";
	/** label：充值赠送 */
	public static final String LABEL_RECHARGE_GIFT = "充值赠送";
	/** label：订单消费 */
	public static final String LABEL_ORDER_CONSUME = "钱包支付";
	/** label：订单取消 */
	public static final String LABEL_ORDER_CANCEL = "退款";
	/** label备注：账号充值 */
	public static final String LABEL_REMARKS_ACCOUNT_RECHARGE = "账号充值";
	/** label备注：充值赠送 */
	public static final String LABEL_REMARKS_RECHARGE_GIFT = "充值赠送";
	/** label备注：订单消费 */
	public static final String LABEL_REMARKS_ORDER_CONSUME = "订单消费";
	/** label备注：订单取消 */
	public static final String LABEL_REMARKS_ORDER_CANCEL = "订单取消";
	/** label：充值余额 */
	public static final String LABEL_RECHARGE_BALANCE = "充值余额";
	/** 字典类型：交易方式 */
	public static final String DICT_TYPE_TRADETYPE = "trade_type";

	private String serialNumber; // 导出EXCEL用 序号
	private Account account; // 会员信息
	private String villageInfoId; // 楼盘ID
	private String tradeType; // 交易类型
	private Date tradeDate; // 交易时间
	private Double money; // 金额
	private String terminalSource; // 终端来源
	private String mobileUniqueCode; // 手机唯一码
	private String payType; // 支付方式
	private Date beginTradeDate; // 开始 交易时间
	private Date endTradeDate; // 结束 交易时间
	private String accountRecharge;// 账号充值
	private String rechargeGift;// 充值赠送
	private String orderConsume;// 订单消费
	private String orderCancel;// 订单取消
	private String rechargeBalance;// 充值余额

	public WalletDetail() {
		super();
	}

	public WalletDetail(String id) {
		super(id);
	}

	@ExcelField(title = "序号", type = 1, align = 2, sort = 1)
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * 获取用户名
	 * 
	 * @return 用户名
	 */
	@ExcelField(title = "用户名", type = 1, align = 2, sort = 2, width=4000)
	public String getAccountPhoneNum() {
		return this.account.getPhoneNum();
	}

	@Length(min = 0, max = 64, message = "楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}

	@Length(min = 0, max = 2, message = "交易类型长度必须介于 0 和 2 之间")
	@ExcelField(title = "交易方式", type = 1, align = 2, sort = 3)
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title = "交易时间", type = 1, align = 2, sort = 4 )
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public Double getMoney() {
		return money;
	}

	/**
	 * 获取带“+、-”符号的金额字符串
	 * 
	 * @return
	 */
	@ExcelField(title = "金额", type = 1, align = 2, sort = 5)
	public String getMoneyStr() {
		StringBuilder moneyStr = new StringBuilder();
		if(DictUtils.getDictValue(LABEL_ORDER_CONSUME, DICT_TYPE_TRADETYPE, "").equals(this.tradeType) 
				|| LABEL_REMARKS_ORDER_CONSUME.equals(this.tradeType)){
			moneyStr.append("-");
		} else {
			moneyStr.append("+");
		} 
		moneyStr.append(this.money);
		return moneyStr.toString();
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Length(min = 0, max = 1, message = "终端来源长度必须介于 0 和 1 之间")
	@ExcelField(title = "终端来源", type = 1, align = 2, sort = 6, dictType = "terminal_source")
	public String getTerminalSource() {
		return terminalSource;
	}

	public void setTerminalSource(String terminalSource) {
		this.terminalSource = terminalSource;
	}

	@Length(min = 0, max = 64, message = "手机唯一编码长度必须介于 0 和 64 之间")
	@ExcelField(title = "手机唯一编码", type = 1, align = 2, sort = 7, width=8000)
	public String getMobileUniqueCode() {
		return mobileUniqueCode;
	}

	public void setMobileUniqueCode(String mobileUniqueCode) {
		this.mobileUniqueCode = mobileUniqueCode;
	}

	@Length(min = 0, max = 1, message = "支付方式长度必须介于 0 和 1 之间")
	@ExcelField(title = "支付方式", type = 1, align = 2, sort = 8, dictType = "pay_type")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getBeginTradeDate() {
		return beginTradeDate;
	}

	public void setBeginTradeDate(Date beginTradeDate) {
		this.beginTradeDate = beginTradeDate;
	}

	public Date getEndTradeDate() {
		return endTradeDate;
	}

	public void setEndTradeDate(Date endTradeDate) {
		this.endTradeDate = endTradeDate;
	}

	public String getAccountRecharge() {
		return accountRecharge;
	}

	public void setAccountRecharge(String accountRecharge) {
		this.accountRecharge = accountRecharge;
	}

	public String getRechargeGift() {
		return rechargeGift;
	}

	public void setRechargeGift(String rechargeGift) {
		this.rechargeGift = rechargeGift;
	}

	public String getOrderConsume() {
		return orderConsume;
	}

	public void setOrderConsume(String orderConsume) {
		this.orderConsume = orderConsume;
	}

	public String getOrderCancel() {
		return orderCancel;
	}

	public void setOrderCancel(String orderCancel) {
		this.orderCancel = orderCancel;
	}

	public String getRechargeBalance() {
		return rechargeBalance;
	}

	public void setRechargeBalance(String rechargeBalance) {
		this.rechargeBalance = rechargeBalance;
	}

}