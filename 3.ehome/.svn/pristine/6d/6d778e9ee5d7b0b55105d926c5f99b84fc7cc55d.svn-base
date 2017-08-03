/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.FamilyInfo;

/**
 * 家属成员信息DAO接口
 * @author like
 * @version 2017-07-21
 */
@MyBatisDao
public interface FamilyInfoDao extends CrudDao<FamilyInfo> {
	/**
	 * 获取房间的家属集合
	 * @param roomId
	 * @return
	 */
	public List<FamilyInfo> getRoomFamilies(String roomId);
}