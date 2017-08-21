/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.recharge.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.recharge.dao.RechargeManageDao;
import com.its.modules.recharge.entity.RechargeManage;
import com.its.modules.recharge.entity.RechargePlanDTO;
import com.its.modules.sys.utils.UserUtils;
import com.its.modules.village.entity.VillageInfo;

/**
 * 充值管理Service
 * 
 * @author ChenXiangyu
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class RechargeManageService extends CrudService<RechargeManageDao, RechargeManage> {

	@Autowired
	private RechargeManageDao rechargeManageDao;
	/** 状态：正常 */
	private static final String STATE_NOMAL = "0";
	/** 操作模式：编辑 */
	public static final String option_Mode_edit = "edit";
	/** 操作模式：删除 */
	public static final String option_Mode_delete = "delete";

	public RechargeManage get(String id) {
		return super.get(id);
	}

	public List<RechargeManage> findList(RechargeManage rechargeManage) {
		return super.findList(rechargeManage);
	}

	public Page<RechargeManage> findPage(Page<RechargeManage> page, RechargeManage rechargeManage) {
		return super.findPage(page, rechargeManage);
	}

	@Transactional(readOnly = false)
	public void save(RechargeManage rechargeManage) {
		super.save(rechargeManage);
	}

	/**
	 * 删除对应楼盘ID的数据
	 * 
	 * @param rechargeManage
	 *            包含楼盘ID
	 * @param optionMode
	 *            操作模式（编辑模式：删除 除发布时间外 数据；删除模式：删除对应楼盘ID的所有数据）
	 */
	@Transactional(readOnly = false)
	public void delete(RechargeManage rechargeManage, String optionMode) {
		if (option_Mode_edit.equals(optionMode)) {
			// 编辑模式时，删除 除发布时间外 数据
			rechargeManage.setRechargeMoney(RechargeManage.SIGN_PUBLISH_DATE);
		} else if (option_Mode_delete.equals(optionMode)) {
			// 删除模式时，删除对应楼盘ID的所有数据
			rechargeManage.setRechargeMoney(null);
		}
		super.delete(rechargeManage);
	}

	/**
	 * 获取当前用户权限下的楼盘充值计划
	 * 
	 * @param rechargeManage
	 *            查询条件
	 * @return 当前用户权限下的楼盘充值计划
	 */
	public List<RechargePlanDTO> findListForUser(RechargeManage rechargeManage) {
		// 获取当前用户权限下的楼盘ID
		String villageInfoIdsStr = UserUtils.getUser().getVillageInfoIds();
		List<String> villageInfoIdList = new ArrayList<String>();
		if (villageInfoIdsStr != null && !villageInfoIdsStr.isEmpty()) {
			String[] villageInfoIds = villageInfoIdsStr.split(",");
			for (String villageInfoId : villageInfoIds) {
				if (villageInfoId != null && !villageInfoId.isEmpty()) {
					villageInfoIdList.add(villageInfoId);
				}
			}
		}
		
		if (rechargeManage.getVillageInfo() == null) {
			rechargeManage.setVillageInfo(new VillageInfo());
		}
		rechargeManage.getVillageInfo().setUserVillageIds(villageInfoIdList);
		// 非冻结状态的楼盘
		rechargeManage.getVillageInfo().setState(STATE_NOMAL);
		return rechargeManageToRechargePlan(super.findList(rechargeManage));
	}
	
	/**
	 * 获取当前用户权限下的楼盘充值计划
	 * 
	 * @param rechargeManage
	 *            查询条件
	 * @return 当前用户权限下的楼盘充值计划
	 */
	public List<RechargePlanDTO> findPageForUser(Page<RechargePlanDTO> page, RechargeManage rechargeManage) {
		// 获取当前用户权限下的楼盘ID
		String villageInfoIdsStr = UserUtils.getUser().getVillageInfoIds();
		List<String> villageInfoIdList = new ArrayList<String>();
		if (villageInfoIdsStr != null && !villageInfoIdsStr.isEmpty()) {
			String[] villageInfoIds = villageInfoIdsStr.split(",");
			for (String villageInfoId : villageInfoIds) {
				if (villageInfoId != null && !villageInfoId.isEmpty()) {
					villageInfoIdList.add(villageInfoId);
				}
			}
		}
		
		if (rechargeManage.getVillageInfo() == null) {
			rechargeManage.setVillageInfo(new VillageInfo());
		}
		rechargeManage.getVillageInfo().setUserVillageIds(villageInfoIdList);
		// 非冻结状态的楼盘
		rechargeManage.getVillageInfo().setState(STATE_NOMAL);
		List<RechargePlanDTO> rechargePlanDTO = rechargeManageToRechargePlan(super.findList(rechargeManage));
		List<RechargePlanDTO> result = new ArrayList<RechargePlanDTO>();
		
		if(!rechargePlanDTO.isEmpty()){
			page.setCount(rechargePlanDTO.size());
			page.initialize();
			// 当前请求页第一条数据
			int currentIndex = ((page.getPageNo() - 1) * page.getPageSize());
			for (int i = currentIndex ; i < (currentIndex + page.getPageSize()) ; i++) {
				if (i < rechargePlanDTO.size() && rechargePlanDTO.get(i) != null) {
					result.add(rechargePlanDTO.get(i));
				}
			}
		}
		
		return result;
	}

	/**
	 * 根据楼盘ID取得充值计划记录条数
	 * 
	 * @param villageId
	 *            楼盘ID
	 * @return 充值计划记录条数
	 */
	public int getRechargeCountByVillage(String villageId) {
		Integer rechargeCount = rechargeManageDao.getRechargeCountByVillage(villageId);
		if (rechargeCount == null) {
			return 0;
		}
		return rechargeCount;
	}

	/**
	 * 添加充值计划
	 * 
	 * @param villageInfo
	 *            楼盘信息
	 * @param rechargeMoneyMap
	 *            充值面额
	 */
	@Transactional(readOnly = false)
	public void add(VillageInfo villageInfo, Map<Double, Double> rechargeMoneyMap) {
		RechargeManage rechargePublishDate = new RechargeManage();
		rechargePublishDate.setVillageInfoId(villageInfo.getId());
		rechargePublishDate.setRechargeMoney(RechargeManage.SIGN_PUBLISH_DATE);
		save(rechargePublishDate);
		for (Map.Entry<Double, Double> entry : rechargeMoneyMap.entrySet()) {
			RechargeManage rechargeManage = new RechargeManage();
			rechargeManage.setVillageInfoId(villageInfo.getId());
			rechargeManage.setRechargeMoney(entry.getKey());
			rechargeManage.setGiveMoney(entry.getValue());
			save(rechargeManage);
		}
	}

	/**
	 * 编辑充值计划
	 * 
	 * @param villageInfo
	 *            楼盘信息
	 * @param rechargeMoneyMap
	 *            充值面额
	 */
	@Transactional(readOnly = false)
	public void edit(VillageInfo villageInfo, Map<Double, Double> rechargeMoneyMap) {
		// 删除除发布时间之外的数据
		RechargeManage rechargeManageForDelete = new RechargeManage();
		rechargeManageForDelete.setVillageInfo(villageInfo);
		delete(rechargeManageForDelete, option_Mode_edit);

		// 重新添加除发布时间之外的数据
		for (Map.Entry<Double, Double> entry : rechargeMoneyMap.entrySet()) {
			RechargeManage rechargeManage = new RechargeManage();
			rechargeManage.setVillageInfoId(villageInfo.getId());
			rechargeManage.setRechargeMoney(entry.getKey());
			rechargeManage.setGiveMoney(entry.getValue());
			save(rechargeManage);
		}
	}

	/**
	 * 将RechargeManage列表转换为RechargePlan列表
	 * 
	 * @param rechargeManageList
	 *            从数据库中取出的充值计划列表
	 * @return 以楼盘区分的充值计划列表（按发布时间排序）
	 */
	private List<RechargePlanDTO> rechargeManageToRechargePlan(List<RechargeManage> rechargeManageList) {
		// 转换科学计数法为普通数字格式
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setGroupingUsed(false); 
		
		// 用于前台显示的充值计划
		List<RechargePlanDTO> rechargePlanList = new ArrayList<RechargePlanDTO>();
		// 取得所有充值计划中的楼盘
		Set<String> villageInfoIds = new HashSet<String>();
		for (RechargeManage recharge : rechargeManageList) {
			if (!villageInfoIds.contains(recharge.getVillageInfo().getId())) {
				villageInfoIds.add(recharge.getVillageInfo().getId());
				rechargePlanList.add(new RechargePlanDTO(recharge.getVillageInfo(), null));
			}
		}
		// 构造用于前台显示的充值计划
		// 充值管理信息
		List<RechargeManage> tempRechargeManageList;
		// 充值面额
		StringBuilder rechargeMoney;
		// 赠送金额列表
		List<String> giveMoney;
		for (RechargePlanDTO rechargePlan : rechargePlanList) {
			tempRechargeManageList = new ArrayList<RechargeManage>();
			rechargeMoney = new StringBuilder();
			giveMoney = new ArrayList<String>();
			for (RechargeManage recharge : rechargeManageList) {
				if (rechargePlan.getVillageInfo().getId().equals(recharge.getVillageInfo().getId())) {
					// 充值管理信息
					tempRechargeManageList.add(recharge);
					// 充值面额（前台页面展示用）
					if (rechargeMoney.length() > 0) {
						rechargeMoney.append("、");
					}
					// 转换科学计数法为普通数字格式
					rechargeMoney.append(numberFormat.format(recharge.getRechargeMoney()));
					rechargeMoney.append("元");
					// 赠送金额（前台页面展示用）
					if (recharge.getGiveMoney() != null && recharge.getGiveMoney().doubleValue() != 0) {
						StringBuilder tempGiveMoney = new StringBuilder();
						tempGiveMoney.append(numberFormat.format(recharge.getRechargeMoney()));
						tempGiveMoney.append("元——赠送");
						tempGiveMoney.append(numberFormat.format(recharge.getGiveMoney()));
						tempGiveMoney.append("元");
						giveMoney.add(tempGiveMoney.toString());
					}
				}
			}
			// 设置充值管理信息
			rechargePlan.setRechargeManageList(tempRechargeManageList);
			// 设置充值面额（前台页面展示用）
			rechargePlan.setRechargeMoney(rechargeMoney.toString());
			// 设置赠送金额（前台页面展示用）
			rechargePlan.setGiveMoney(giveMoney);
			// 设置发布时间
			RechargeManage rechargePublishDate = new RechargeManage();
			rechargePublishDate.setVillageInfoId(rechargePlan.getVillageInfo().getId());
			rechargePublishDate = rechargeManageDao.getPublishDate(rechargePublishDate);
			if (rechargePublishDate != null) {
				rechargePlan.setPublishTime(rechargePublishDate.getCreateDate());
			} else {
				rechargePlan.setPublishTime(null);
			}
		}
		// 按发布时间排序
		Collections.sort(rechargePlanList);
		return rechargePlanList;
	}
}