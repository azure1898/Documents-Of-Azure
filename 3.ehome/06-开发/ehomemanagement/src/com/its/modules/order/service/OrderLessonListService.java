/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.OrderLessonList;
import com.its.modules.order.dao.OrderLessonListDao;

/**
 * 订单-课程培训类子表-课程培训清单Service
 * @author liuhl
 * @version 2017-07-19
 */
@Service
@Transactional(readOnly = true)
public class OrderLessonListService extends CrudService<OrderLessonListDao, OrderLessonList> {

	public OrderLessonList get(String id) {
		return super.get(id);
	}
	
	public List<OrderLessonList> findList(OrderLessonList orderLessonList) {
		return super.findList(orderLessonList);
	}
	
	public Page<OrderLessonList> findPage(Page<OrderLessonList> page, OrderLessonList orderLessonList) {
		return super.findPage(page, orderLessonList);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderLessonList orderLessonList) {
		super.save(orderLessonList);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderLessonList orderLessonList) {
		super.delete(orderLessonList);
	}
	
}