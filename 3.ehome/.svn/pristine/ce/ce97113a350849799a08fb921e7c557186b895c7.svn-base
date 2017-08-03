/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 优惠券导入的用户Entity
 * 
 * @author liuqi
 * @version 2017-07-05
 */
public class CouponManageUsers extends DataEntity<CouponManageUsers> {

	private static final long serialVersionUID = 1L;
	private String couponManageId; // 优惠券ID
	private String account; // 用户账号(手机号)
	private Date registerTime; // 注册时间
	private String existFlag; // 是否存在此用户
	private String accountId; // 会员ID
	private String lastId; // 记录上传后，待保存前临时生成的couponManageId

	public CouponManageUsers() {
		super();
	}

	public CouponManageUsers(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "优惠券ID长度必须介于 0 和 64 之间")
	public String getCouponManageId() {
		return couponManageId;
	}

	public void setCouponManageId(String couponManageId) {
		this.couponManageId = couponManageId;
	}

	@Length(min = 0, max = 16, message = "用户账号(手机号)长度必须介于 0 和 16 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	/* @Length(min=0, max=1, message="是否存在此用户长度必须介于 0 和 1 之间") */
	public String getExistFlag() {
		return existFlag;
	}

	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}

	@Length(min = 0, max = 64, message = "会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}
}