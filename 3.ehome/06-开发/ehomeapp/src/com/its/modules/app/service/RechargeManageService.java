/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.RechargeManage;
import com.its.modules.app.dao.RechargeManageDao;

/**
 * 充值管理Service
 * @author like
 * @version 2017-07-18
 */
@Service
@Transactional(readOnly = true)
public class RechargeManageService extends CrudService<RechargeManageDao, RechargeManage> {

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
	
	@Transactional(readOnly = false)
	public void delete(RechargeManage rechargeManage) {
		super.delete(rechargeManage);
	}
	
	/**
	 * 获取楼盘下的充值计划集合
	 * @param villageInfoId 楼盘ID
	 * @return
	 */
	public List<RechargeManage> getVillageRechargeList(String villageInfoId){
		return dao.getVillageRechargeList(villageInfoId);
	}
}