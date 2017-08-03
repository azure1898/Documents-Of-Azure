/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.OrderTrack;

/**
 * 订单跟踪DAO接口
 * @author sushipeng
 * @version 2017-07-19
 */
@MyBatisDao
public interface OrderTrackDao extends CrudDao<OrderTrack> {
	
}