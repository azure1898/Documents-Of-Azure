/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.its.common.persistence.DataEntity;

/**
 * 楼盘信息Entity
 * 
 * @author zhujiao
 * @version 2017-07-03
 */
public class VillageInfo extends DataEntity<VillageInfo> {

	private static final long serialVersionUID = 1L;
	private String villageName; // 小区名称
	private String addrPro; // 地址_省
	private String addrCity; // 地址_市
	private String addrCityName; // 城市名
	private String addrArea; // 地址_区
	private String companyInfoId; // 所属公司ID
	private String companyInfoName; // 所属公司名称
	private String propertyCompanyId; // 所属物业公司ID
	private String propertyCompanyName; // 物业公司名称
	private String phoneNum; // 楼盘电话
	private String state; // 状态
	private List<String> userVillageIds;// 当前用户楼盘权限（楼盘信息ID）
	private String villageIds;//当前用户所管束的楼盘；

	private List<VillageInfo> children=new ArrayList<>(); // 子级别数据
	private List<VillageLine> villageLine = new ArrayList<VillageLine>();// 关联的产品线
	private String villageLineId;  //查询时候用楼盘线的Id
	
	public VillageInfo() {
		super();
	}

	public VillageInfo(String id) {
		super(id);
	}

	@Length(min = 0, max = 128, message = "小区名称长度必须介于 0 和 128 之间")
	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	@Length(min = 0, max = 64, message = "地址_省长度必须介于 0 和 64 之间")
	public String getAddrPro() {
		return addrPro;
	}

	public void setAddrPro(String addrPro) {
		this.addrPro = addrPro;
	}

	@Length(min = 0, max = 64, message = "地址_市长度必须介于 0 和 64 之间")
	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrCityName() {
		return addrCityName;
	}

	public void setAddrCityName(String addrCityName) {
		this.addrCityName = addrCityName;
	}

	@Length(min = 0, max = 64, message = "地址_区长度必须介于 0 和 64 之间")
	public String getAddrArea() {
		return addrArea;
	}

	public void setAddrArea(String addrArea) {
		this.addrArea = addrArea;
	}

	@Length(min = 0, max = 64, message = "所属公司ID长度必须介于 0 和 64 之间")
	public String getCompanyInfoId() {
		return companyInfoId;
	}

	public void setCompanyInfoId(String companyInfoId) {
		this.companyInfoId = companyInfoId;
	}

	public String getCompanyInfoName() {
		return companyInfoName;
	}

	public void setCompanyInfoName(String companyInfoName) {
		this.companyInfoName = companyInfoName;
	}

	@Length(min = 0, max = 64, message = "所属物业公司ID长度必须介于 0 和 64 之间")
	public String getPropertyCompanyId() {
		return propertyCompanyId;
	}

	public void setPropertyCompanyId(String propertyCompanyId) {
		this.propertyCompanyId = propertyCompanyId;
	}

	@Length(min = 0, max = 128, message = "物业公司长度必须介于 0 和 128 之间")
	public String getPropertyCompanyName() {
		return propertyCompanyName;
	}

	public void setPropertyCompanyName(String propertyCompanyName) {
		this.propertyCompanyName = propertyCompanyName;
	}

	@NotBlank(message = "请输入楼盘电话")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<VillageInfo> getChildren() {
		return children;
	}

	public void setChildren(List<VillageInfo> children) {
		this.children = children;
	}

	public List<String> getUserVillageIds() {
		return userVillageIds;
	}

	public void setUserVillageIds(List<String> userVillageIds) {
		this.userVillageIds = userVillageIds;
	}

	public List<VillageLine> getVillageLine() {
		return villageLine;
	}

	public void setVillageLine(List<VillageLine> villageLine) {
		this.villageLine = villageLine;
	}

	public String getVillageLineId() {
		return villageLineId;
	}

	public void setVillageLineId(String villageLineId) {
		this.villageLineId = villageLineId;
	}

	public String getVillageIds() {
		return villageIds;
	}

	public void setVillageIds(String villageIds) {
		this.villageIds = villageIds;
	}
}