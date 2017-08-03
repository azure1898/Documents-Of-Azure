/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.OrderServiceList;

/**
 * 订单-服务类子表-服务清单DAO接口
 * @author sushipeng
 * @version 2017-07-07
 */
@MyBatisDao
public interface OrderServiceListDao extends CrudDao<OrderServiceList> {
	
}