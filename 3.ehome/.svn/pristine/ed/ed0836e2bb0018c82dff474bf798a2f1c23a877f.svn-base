/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.MemberNews;

/**
 * 提醒消息DAO接口
 * 
 * @author like
 * @version 2017-07-19
 */
@MyBatisDao
public interface MemberNewsDao extends CrudDao<MemberNews> {
	/**
	 * 获取每种消息的最新消息
	 * 
	 * @param accountId
	 * @return
	 */
	public List<MemberNews> selectLatestNewsEveryType(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);
	/**
	 * 
	 * @param newsType 0业主关怀；1订单提醒；2系统消息
	 * @param accountId
	 * @param villageInfoId
	 * @return
	 */
	public List<MemberNews> selectNewsByType(@Param("newsType") String newsType, @Param("accountId") String accountId,
			@Param("villageInfoId") String villageInfoId);
}