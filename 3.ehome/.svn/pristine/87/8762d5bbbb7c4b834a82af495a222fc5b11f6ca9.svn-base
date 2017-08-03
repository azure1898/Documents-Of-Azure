package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 收货地址Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-07-06
 */
public class Address extends DataEntity<Address> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageInfoId;		// 楼盘信息ID
	private String contact;		// 联系人
	private String phoneNum;		// 联系电话
	private String addressType;		// 地址类型：0按楼栋选择地址；1手动输入地址
	private String address;		// 详细地址
	private String building;		// 楼栋
	private String houseNumber;		// 门牌号
	private String isDefault;		// 是否默认：0-否；1-是
	
	public Address() {
		super();
	}

	public Address(String id){
		super(id);
	}

	@Length(min=0, max=64, message="会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="楼盘信息ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=1, message="地址类型：0按楼栋选择地址；1手动输入地址长度必须介于 0 和 1 之间")
	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	@Length(min=0, max=255, message="详细地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="楼栋长度必须介于 0 和 64 之间")
	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}
	
	@Length(min=0, max=64, message="门牌号长度必须介于 0 和 64 之间")
	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	@Length(min=0, max=1, message="是否默认：0-否；1-是长度必须介于 0 和 1 之间")
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
}