package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;

import com.its.modules.app.entity.VisitorInvite;

/**
 * 访客邀请DAO接口
 * 
 * @author sushipeng
 * 
 * @version 2017-08-07
 */
@MyBatisDao
public interface VisitorInviteDao extends CrudDao<VisitorInvite> {

	/**
	 * 获取某用户某楼盘下的访客列表（正常）
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<VisitorInvite>
	 */
	public List<VisitorInvite> getNormalVisitorInviteList(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);

	/**
	 * 获取某用户某楼盘下的访客列表（失效）
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param count
	 *            数量
	 * @return List<VisitorInvite>
	 */
	public List<VisitorInvite> getInvalidVisitorInviteList(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("count") int count);

	/**
	 * 判断某用户某楼盘下是否存在某访客邀请
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param visitorInviteId
	 *            访客邀请ID
	 * @return VisitorInvite
	 */
	public VisitorInvite judgeVisitorInvite(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId, @Param("visitorInviteId") String visitorInviteId);

}