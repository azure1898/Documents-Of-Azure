/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.village.entity.BuildingInfo;
import com.its.modules.village.dao.BuildingInfoDao;

/**
 * 查询楼栋信息接口Service
 * @author caojing
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class BuildingInfoService extends CrudService<BuildingInfoDao, BuildingInfo> {

	@Autowired
	private BuildingInfoDao buildingInfoDao;
	
	public BuildingInfo get(String id) {
		return super.get(id);
	}
	
	public List<BuildingInfo> findList(BuildingInfo buildingInfo) {
		return super.findList(buildingInfo);
	}
	
	public Page<BuildingInfo> findPage(Page<BuildingInfo> page, BuildingInfo buildingInfo) {
		return super.findPage(page, buildingInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(BuildingInfo buildingInfo) {
		super.save(buildingInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BuildingInfo buildingInfo) {
		super.delete(buildingInfo);
	}
	
	/**
	 * 依据楼栋id，查询楼栋信息
	 * @param buildingList
	 * @return
	 */
	public BuildingInfo getListById(BuildingInfo buildingInfo){
		return buildingInfoDao.getListById(buildingInfo);
	}
	/**
	 * 删除楼栋信息
	 * @param groupPurchase
	 */
	@Transactional(readOnly = false)
	public void deleteBulding(String villageInfoId){
		buildingInfoDao.deleteBulding(villageInfoId);
	}
	
	/**
	 * 批量插入楼栋信息
	 * @param buildingList
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insertBatch(List<BuildingInfo> buildingList){
		return buildingInfoDao.insertBatch(buildingList);
	}
}