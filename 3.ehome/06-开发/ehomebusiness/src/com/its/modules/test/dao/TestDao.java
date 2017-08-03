/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.test.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.test.entity.Test;

/**
 * 测试DAO接口
 * @author Jetty
 * @version 2013-10-17
 */
@MyBatisDao
public interface TestDao extends CrudDao<Test> {
	
}
