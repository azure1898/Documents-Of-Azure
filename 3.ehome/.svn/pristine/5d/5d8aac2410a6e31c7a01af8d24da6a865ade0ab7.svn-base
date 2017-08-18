package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.entity.AccountApplication;

/**
 * 会员的应用DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@MyBatisDao
public interface AccountApplicationDao extends CrudDao<AccountApplication> {

	/**
	 * 获取某用户在某楼盘下的常用应用
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<String>
	 */
	public List<String> getAccountApplicationList(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);

	/**
	 * 获取某用户在某楼盘下的某常用应用
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param moduleManageId
	 *            模块管理ID
	 * @return AccountApplication
	 */
	public AccountApplication getAccountApplication(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("moduleManageId") String moduleManageId);
}