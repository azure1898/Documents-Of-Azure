/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.recharge.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;
import com.its.modules.village.entity.VillageInfo;

/**
 * 充值管理Entity
 * @author ChenXiangyu
 * @version 2017-07-05
 */
public class RechargeManage extends DataEntity<RechargeManage> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 楼盘ID
	private List<String> villageInfoIdList;		// 楼盘IDList（查询用）
	private Double rechargeMoney;		// 充值金额
	private Double giveMoney;		// 赠送金额
	private String addrPro;		// 地址_省ID
	private String addrCity;		// 地址_市ID
	private VillageInfo villageInfo;   // 楼盘信息
	/** 标记：发布时间 */
	public static final Double SIGN_PUBLISH_DATE = new Double(-1);	
	
	public RechargeManage() {
		super();
	}

	public RechargeManage(String id){
		super(id);
	}

	@Length(min=1, max=64, message="楼盘ID长度必须介于 1 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	public List<String> getVillageInfoIdList() {
		return villageInfoIdList;
	}

	public void setVillageInfoIdList(List<String> villageInfoIdList) {
		this.villageInfoIdList = villageInfoIdList;
	}

	public Double getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(Double rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	
	public Double getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(Double giveMoney) {
		this.giveMoney = giveMoney;
	}

	public String getAddrPro() {
		return addrPro;
	}

	public void setAddrPro(String addrPro) {
		this.addrPro = addrPro;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public VillageInfo getVillageInfo() {
		return villageInfo;
	}

	public void setVillageInfo(VillageInfo villageInfo) {
		this.villageInfo = villageInfo;
	}
	
}