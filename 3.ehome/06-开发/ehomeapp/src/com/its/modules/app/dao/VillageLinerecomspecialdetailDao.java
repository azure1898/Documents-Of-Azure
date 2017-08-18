package com.its.modules.app.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.entity.VillageLineRecomSpecialDetail;

/**
 * 楼盘产品线专题推荐明细DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@MyBatisDao
public interface VillageLineRecomSpecialDetailDao extends CrudDao<VillageLineRecomSpecialDetail> {

	/**
	 * 获取某专题推荐的明细列表
	 * 
	 * @param specialId
	 *            专题推荐ID
	 * @return List<VillageLineRecomSpecialDetail>
	 */
	public List<VillageLineRecomSpecialDetail> getRecomSpecialDetailList(String specialId);
}