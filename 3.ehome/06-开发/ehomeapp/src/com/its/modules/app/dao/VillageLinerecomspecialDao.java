package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.VillageLineRecomSpecial;

/**
 * 楼盘产品线专题推荐DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@MyBatisDao
public interface VillageLineRecomSpecialDao extends CrudDao<VillageLineRecomSpecial> {

	/**
	 * 获取某楼盘APP产品线下某位置的专题推荐列表
	 * 
	 * @param recommendPosition
	 *            推荐位置：00 首页专题推荐 10 社区推荐 20 生活推荐
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<VillageLineRecomSpecial>
	 */
	public List<VillageLineRecomSpecial> getVillageLineRecomSpecialList(@Param("recommendPosition") String recommendPosition, @Param("villageInfoId") String villageInfoId);
}