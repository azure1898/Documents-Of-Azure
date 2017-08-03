/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderServiceList;

/**
 * 订单-服务类子表-服务清单DAO接口
 * @author liuhl
 * @version 2017-07-17
 */
@MyBatisDao
public interface OrderServiceListDao extends CrudDao<OrderServiceList> {
	
}