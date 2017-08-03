/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 家属成员信息Entity
 * @author like
 * @version 2017-07-21
 */
public class FamilyInfo extends DataEntity<FamilyInfo> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageInfoId;		// 楼盘ID
	private String roomCertifyId;		// 房间认证ID
	private String name;		// 姓名
	private String identity;		// 身份：0-家属；1-租客
	private String phoneNum;		// 手机号
	private String certifyState;		// 认证状体：0-未认证；1-已认证
	
	public FamilyInfo() {
		super();
	}

	public FamilyInfo(String id){
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
	
	@Length(min=0, max=64, message="房间认证ID长度必须介于 0 和 64 之间")
	public String getRoomCertifyId() {
		return roomCertifyId;
	}

	public void setRoomCertifyId(String roomCertifyId) {
		this.roomCertifyId = roomCertifyId;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="身份：0-家属；1-租客长度必须介于 0 和 1 之间")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	@Length(min=0, max=64, message="手机号长度必须介于 0 和 64 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=1, message="认证状体：0-未认证；1-已认证长度必须介于 0 和 1 之间")
	public String getCertifyState() {
		return certifyState;
	}

	public void setCertifyState(String certifyState) {
		this.certifyState = certifyState;
	}
	
}