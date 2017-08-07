package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.AdverManage;

/**
 * 广告管理DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-28
 */
@MyBatisDao
public interface AdverManageDao extends CrudDao<AdverManage> {
	/**
	 * 获取某楼盘产品线某个位置下投放中的广告列表
	 * 
	 * @param adverPositionId
	 *            广告位置ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param showCount
	 *            展示数量
	 * @return List<AdverManage>
	 */
	public List<AdverManage> getAdverManageList(@Param("adverPositionId") String adverPositionId, @Param("villageInfoId") String villageInfoId, @Param("showCount") int showCount);
}