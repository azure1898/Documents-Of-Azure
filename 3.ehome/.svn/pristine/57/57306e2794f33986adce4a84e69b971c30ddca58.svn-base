/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.account.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.account.entity.RoomCertify;

/**
 * 房间认证DAO接口
 * @author ChenXiangyu
 * @version 2017-07-04
 */
@MyBatisDao
public interface RoomCertifyDao extends CrudDao<RoomCertify> {
	/**
	 * 依据房间id，查询房间信息是否存在
	 * @param roomCertify
	 * @return
	 */
	public int countRoom(RoomCertify roomCertify);
	
	/**
	 * 在本系统房间信息存在，把物管的房间编号，楼层Id更新过来
	 * @param roomCertify
	 * @return
	 */
	public int updateRoom(RoomCertify roomCertify);
}