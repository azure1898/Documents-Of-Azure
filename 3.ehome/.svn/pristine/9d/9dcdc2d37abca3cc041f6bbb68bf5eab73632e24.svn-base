/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.SysArea;
import com.its.modules.app.entity.VillageInfo;

/**
 * 楼盘信息DAO接口
 * @author like
 * @version 2017-07-03
 */
@MyBatisDao
public interface VillageInfoDao extends CrudDao<VillageInfo> {
	
	/**
	 * 查询所有有楼盘的城市列表
	 * @return
	 */
	public List<SysArea> findCities();
	
	/**
	 * 查询某城市下的楼盘信息
	 * @return
	 */
	public List<VillageInfo> findCityVillageList(String addrCity);
	
	/**
	 * 获取用户最后一次选择楼盘所在的城市
	 * @return
	 */
	public String getAccountCityID(String accountID);
}