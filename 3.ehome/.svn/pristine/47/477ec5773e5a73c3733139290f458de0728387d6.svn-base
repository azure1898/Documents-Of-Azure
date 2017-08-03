/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.MyCollectBean;
import com.its.modules.app.entity.MyCollect;

/**
 * 我的收藏DAO接口
 * 
 * @author like
 * @version 2017-07-04
 */
@MyBatisDao
public interface MyCollectDao extends CrudDao<MyCollect> {

	/**
	 * 取消收藏
	 * 
	 * @param userID
	 * @param buildingID
	 * @param businessID
	 */
	public void cancelCollection(String accountId, String villageInfoId, String businessInfoId);

	/**
	 * 获取用户的收藏集合
	 * 
	 * @param accountId
	 * @param villageInfoId
	 */
	public List<MyCollectBean> findMyCollectOfAccount(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);
	/**
	 * 判断用户是否收藏商家
	 * @param accountId
	 * @param villageInfoId
	 * @param businessInfoId
	 * @return
	 */
	public int isCollect(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("businessInfoId") String businessInfoId);
}