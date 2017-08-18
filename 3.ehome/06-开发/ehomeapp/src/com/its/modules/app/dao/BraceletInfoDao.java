/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.BraceletInfo;

/**
 * 手环信息DAO接口
 * 
 * @author like
 * @version 2017-07-20
 */
@MyBatisDao
public interface BraceletInfoDao extends CrudDao<BraceletInfo> {

	/**
	 * 获取用户绑定的某个手环信息
	 * 
	 * @param accountId
	 * @param villageInfoId
	 * @return
	 */
	public BraceletInfo getAccountBraceletSpe(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId,@Param("bandMac")String bandMac);
	
	/**
	 * 获取用户绑定的所有手环
	 * 
	 * @param accountId
	 * @param villageInfoId
	 * @return
	 */
	public List<BraceletInfo> getAccountBracelets(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);

	/**
	 * 修改手环名称
	 * 
	 * @param id
	 * @param name
	 */
	public void updateName(@Param("id") String id, @Param("name") String name);

	/**
	 * 修改目标步数
	 * @param accountId
	 * @param villageInfoId
	 * @param targetStep
	 */
	public void updateTarget(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("targetStep") int targetStep);
}