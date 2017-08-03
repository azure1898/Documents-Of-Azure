/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 房间认证Entity
 * @author like
 * @version 2017-08-02
 */
public class RoomCertify extends DataEntity<RoomCertify> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 楼盘ID
	private String buildingId;		// 楼栋
	private String buildingName;		// 楼栋名称
	private String floorCode;		// 楼层ID
	private String roomName;		// 房间号
	private String customerType;		// 客户类型：0-业主；1-家属；2-租客
	private String customerName;		// 客户姓名
	private String phoneNum;		// 账号/手机号
	private String customerId;		// 客户ID
	private Date customerUpdateTime;		// 客户更新时间
	private String accountId;		// 会员ID
	private Date roomUpdateTime;		// 房间更新时间
	
	public RoomCertify() {
		super();
	}

	public RoomCertify(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=64, message="楼栋长度必须介于 0 和 64 之间")
	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
	@Length(min=0, max=64, message="楼栋名称长度必须介于 0 和 64 之间")
	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	@Length(min=0, max=64, message="楼层ID长度必须介于 0 和 64 之间")
	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}
	
	@Length(min=0, max=64, message="房间号长度必须介于 0 和 64 之间")
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	@Length(min=0, max=1, message="客户类型：0-业主；1-家属；2-租客长度必须介于 0 和 1 之间")
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	@Length(min=0, max=64, message="客户姓名长度必须介于 0 和 64 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=64, message="账号/手机号长度必须介于 0 和 64 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=64, message="客户ID长度必须介于 0 和 64 之间")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCustomerUpdateTime() {
		return customerUpdateTime;
	}

	public void setCustomerUpdateTime(Date customerUpdateTime) {
		this.customerUpdateTime = customerUpdateTime;
	}
	
	@Length(min=0, max=64, message="会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRoomUpdateTime() {
		return roomUpdateTime;
	}

	public void setRoomUpdateTime(Date roomUpdateTime) {
		this.roomUpdateTime = roomUpdateTime;
	}
	
}