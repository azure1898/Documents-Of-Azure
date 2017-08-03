/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.test.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.test.entity.TestDataChild;

/**
 * 主子表生成DAO接口
 * @author Jetty
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {
	
}