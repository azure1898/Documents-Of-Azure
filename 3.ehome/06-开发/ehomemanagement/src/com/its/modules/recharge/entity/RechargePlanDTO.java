package com.its.modules.recharge.entity;

import java.util.Date;
import java.util.List;

import com.its.modules.village.entity.VillageInfo;

/**
 * 以楼盘区分的充值计划
 * @author ChenXiangyu
 */
public class RechargePlanDTO implements Comparable<RechargePlanDTO>{

	private VillageInfo villageInfo;  // 楼盘信息
	private List<RechargeManage> rechargeManageList;  // 充值管理信息
	private String rechargeMoney;		// 充值面额（前台页面展示用）
	private List<String> giveMoney;		// 赠送金额（前台页面展示用）
	private Date publishTime;		// 发布时间（前台页面展示用）
	
	public RechargePlanDTO() {
		super();
	}
	
	public RechargePlanDTO(VillageInfo villageInfo, List<RechargeManage> rechargeManageList) {
		super();
		this.villageInfo = villageInfo;
		this.rechargeManageList = rechargeManageList;
	}

	public VillageInfo getVillageInfo() {
		return villageInfo;
	}
	
	public void setVillageInfo(VillageInfo villageInfo) {
		this.villageInfo = villageInfo;
	}
	
	public List<RechargeManage> getRechargeManageList() {
		return rechargeManageList;
	}
	
	public void setRechargeManageList(List<RechargeManage> rechargeManageList) {
		this.rechargeManageList = rechargeManageList;
	}

	public String getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(String rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public List<String> getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(List<String> giveMoney) {
		this.giveMoney = giveMoney;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * 根据发布时间排序比较
	 * @return 1：当前发布时间在参数发布时间之前；-1：当前发布时间在参数发布时间之后；0：当前发布时间与参数发布时间相等；
	 */
	@Override
	public int compareTo(RechargePlanDTO rechargePlan) {
		if (rechargePlan == null || this.publishTime == null || rechargePlan.getPublishTime() == null) {
			// 默认当前发布时间在参数发布时间之前
			return 1;
		}
		int result = this.publishTime.compareTo(rechargePlan.publishTime);
		if (result == 0) {
			return 0;
		} else if (result < 0) {
			return 1;
		} else {
			return -1;
		}
	}
}
