/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.dao;

import com.its.common.persistence.TreeDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author Jetty
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
