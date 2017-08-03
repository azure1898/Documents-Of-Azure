/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.property.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 物业公司信息Entity
 * @author ChenXiangyu
 * @version 2017-07-11
 */
public class PropertyCompany extends DataEntity<PropertyCompany> {
	
	private static final long serialVersionUID = 1L;
	private String companyName;		// 公司名称
	private String addrPro;		// 地址_省
	private String addrCity;		// 地址_市
	private String addrArea;		// 地址_区
	private String addrDetail;		// 详址
	private String contactPerson;		// 联系人
	private String phoneNum;		// 联系电话
	private String balanceModel;		// 结算模式
	private Double collectFees;		// 收取费用
	private String balanceCycle;		// 结算周期
	
	public PropertyCompany() {
		super();
	}

	public PropertyCompany(String id){
		super(id);
	}

	@Length(min=0, max=128, message="公司名称长度必须介于 0 和 128 之间")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	
	@Length(min=0, max=200, message="详址长度必须介于 0 和 200 之间")
	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=1, message="结算模式长度必须介于 0 和 1 之间")
	public String getBalanceModel() {
		return balanceModel;
	}

	public void setBalanceModel(String balanceModel) {
		this.balanceModel = balanceModel;
	}
	
	public Double getCollectFees() {
		return collectFees;
	}

	public void setCollectFees(Double collectFees) {
		this.collectFees = collectFees;
	}
	
	@Length(min=0, max=1, message="结算周期长度必须介于 0 和 1 之间")
	public String getBalanceCycle() {
		return balanceCycle;
	}

	public void setBalanceCycle(String balanceCycle) {
		this.balanceCycle = balanceCycle;
	}
	
}