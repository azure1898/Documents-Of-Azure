package com.struts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 用户地址实体类
 *
 */
@Entity
@Table(name = "user_address")
public class UserAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int addressId;
	// @Column(name = "user_id")
	// private int userId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserAccount userAccount;

	@Column(name = "address_type")
	private String addressType;
	@Column(name = "address_desc")
	private String addressDesc;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

}
