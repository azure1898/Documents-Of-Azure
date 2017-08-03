package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.SortInfo;

/**
 * 商品分类信息DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-07-24
 */
@MyBatisDao
public interface SortInfoDao extends CrudDao<SortInfo> {

	/**
	 * 获取某商家的商品或服务分类信息
	 * 
	 * @param businessInfoId
	 *            商家ID
	 * @param type
	 *            分类类型（0商品1服务）
	 * @return List<SortInfo>
	 */
	public List<SortInfo> getSortInfoListOfBusiness(@Param("businessInfoId") String businessInfoId, @Param("type") String type);
}