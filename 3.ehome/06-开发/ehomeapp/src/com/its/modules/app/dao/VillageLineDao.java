package com.its.modules.app.dao;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.entity.VillageLine;

/**
 * 楼盘产品线及产品线设置管理DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-28
 */
@MyBatisDao
public interface VillageLineDao extends CrudDao<VillageLine> {

	/**
	 * 获取某楼盘下已设置的业主APP产品线信息
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return VillageLine
	 */
	public VillageLine getByVillageInfoId(String villageInfoId);

	/**
	 * 根据TYPE和VALUE查询字典表
	 * 
	 * @return 标签LABEL
	 */
	public String findSysDict(@Param("type") String type, @Param("value") String value);
}