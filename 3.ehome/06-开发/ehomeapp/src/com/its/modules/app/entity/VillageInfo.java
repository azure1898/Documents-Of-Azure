/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 楼盘信息Entity
 * @author like
 * @version 2017-07-24
 */
public class VillageInfo extends DataEntity<VillageInfo> {
	
	private static final long serialVersionUID = 1L;
	private String villageName;		// 小区名称
	private String addrPro;		// 地址_省
	private String addrCity;		// 地址_市
	private String addrArea;		// 地址_区
	private String companyInfoId;		// 所属公司ID
	private String propertyCompanyId;		// 所属物业公司ID
	private String propertyCompanyName;		// 物业公司
	private String state;		// 状态
	private String phoneNum;		// 楼盘电话
	
	public VillageInfo() {
		super();
	}

	public VillageInfo(String id){
		super(id);
	}

	@Length(min=0, max=128, message="小区名称长度必须介于 0 和 128 之间")
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	@Length(min=0, max=64, message="地址_省长度必须介于 0 和 64 之间")
	public String getAddrPro() {
		return addrPro;
	}

	public void setAddrPro(String addrPro) {
		this.addrPro = addrPro;
	}
	
	@Length(min=0, max=64, message="地址_市长度必须介于 0 和 64 之间")
	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	
	@Length(min=0, max=64, message="地址_区长度必须介于 0 和 64 之间")
	public String getAddrArea() {
		return addrArea;
	}

	public void setAddrArea(String addrArea) {
		this.addrArea = addrArea;
	}
	
	@Length(min=0, max=64, message="所属公司ID长度必须介于 0 和 64 之间")
	public String getCompanyInfoId() {
		return companyInfoId;
	}

	public void setCompanyInfoId(String companyInfoId) {
		this.companyInfoId = companyInfoId;
	}
	
	@Length(min=0, max=64, message="所属物业公司ID长度必须介于 0 和 64 之间")
	public String getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(String propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}
	
	@Length(min=0, max=128, message="物业公司长度必须介于 0 和 128 之间")
	public String getPropertyCompanyName() {
		return propertyCompanyName;
	}

	public void setPropertyCompanyName(String propertyCompanyName) {
		this.propertyCompanyName = propertyCompanyName;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=64, message="楼盘电话长度必须介于 0 和 64 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}