/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.edition.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.edition.entity.EditionManage;

/**
 * 版本管理DAO接口
 * @author ChenXiangyu
 * @version 2017-06-29
 */
@MyBatisDao
public interface EditionManageDao extends CrudDao<EditionManage> {
	
}