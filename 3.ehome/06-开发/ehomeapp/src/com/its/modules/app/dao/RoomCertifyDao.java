package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.RoomCertify;

/**
 * 房间认证DAO接口
 * 
 * @author like
 * 
 * @version 2017-07-21
 */
@MyBatisDao
public interface RoomCertifyDao extends CrudDao<RoomCertify> {

	/**
	 * 查询用户当前楼盘下认证的房间
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<RoomCertify>
	 */
	public List<RoomCertify> getAccountRoomCertify(@Param("accountId") String accountId, @Param("villageInfoId") String villageInfoId);
}