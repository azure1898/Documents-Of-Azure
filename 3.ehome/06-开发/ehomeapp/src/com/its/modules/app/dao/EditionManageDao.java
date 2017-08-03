/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.EditionManage;

/**
 * 版本管理DAO接口
 * @author like
 * @version 2017-07-17
 */
@MyBatisDao
public interface EditionManageDao extends CrudDao<EditionManage> {
	/**
	 * 获取最新的版本信息
	 * @return
	 */
	public EditionManage getLatestEdition();
}