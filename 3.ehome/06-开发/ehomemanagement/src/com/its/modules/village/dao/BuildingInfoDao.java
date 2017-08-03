/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.village.entity.BuildingInfo;

/**
 * 查询楼栋信息接口DAO接口
 * @author caojing
 * @version 2017-07-19
 */
@MyBatisDao
public interface BuildingInfoDao extends CrudDao<BuildingInfo> {
	/**
	 * 删除楼栋信息
	 * @param groupPurchase
	 */
	public void deleteBulding(String villageInfoId);
	
	/**
	 * 批量插入楼栋信息
	 * @param buildingList
	 * @return
	 */
	public int insertBatch(List<BuildingInfo> buildingList);
	
	/**
	 * 依据楼栋id，查询楼栋信息
	 * @param buildingList
	 * @return
	 */
	public BuildingInfo getListById(BuildingInfo buildingInfo);

}