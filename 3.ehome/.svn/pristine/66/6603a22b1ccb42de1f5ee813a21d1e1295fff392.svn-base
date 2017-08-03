package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.VillageLineRecomBusiTypeBean;
import com.its.modules.app.entity.VillageLineRecomBusiType;

/**
 * 楼盘产品线推荐商家模式DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-31
 */
@MyBatisDao
public interface VillageLineRecomBusiTypeDao extends CrudDao<VillageLineRecomBusiType> {

	/**
	 * 获取某楼盘下某推荐位置的商家列表
	 * 
	 * @param recommendPosition
	 *            推荐位置：00 首页推荐 10 社区推荐 20 生活商家推荐2
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<Map<String, Object>>
	 */
	public List<VillageLineRecomBusiTypeBean> getRecommendBusinessList(@Param("recommendPosition") String recommendPosition, @Param("villageInfoId") String villageInfoId);
}