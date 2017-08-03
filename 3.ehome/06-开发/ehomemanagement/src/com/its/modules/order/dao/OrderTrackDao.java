/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderTrack;

/**
 * 订单跟踪表DAO接口
 * @author xzc
 * @version 2017-07-11
 */
@MyBatisDao
public interface OrderTrackDao extends CrudDao<OrderTrack> {
	
}