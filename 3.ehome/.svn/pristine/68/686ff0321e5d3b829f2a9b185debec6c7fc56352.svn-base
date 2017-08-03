package com.its.modules.app.dao;

import java.util.List;

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
	 * 获取某个位置下投放中的广告列表
	 * 
	 * @param adverPositionId
	 *            广告位置ID
	 * @return List<AdverManage>
	 */
	public List<AdverManage> getAdverManageByPositionId(String adverPositionId);
}