/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.order.entity.OrderLessonList;

/**
 * 订单-课程培训类子表-课程培训清单DAO接口
 * @author liuhl
 * @version 2017-07-19
 */
@MyBatisDao
public interface OrderLessonListDao extends CrudDao<OrderLessonList> {
	
}