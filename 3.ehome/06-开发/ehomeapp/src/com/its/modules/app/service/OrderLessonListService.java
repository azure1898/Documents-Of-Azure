package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.OrderLessonList;
import com.its.modules.app.dao.OrderLessonListDao;

/**
 * 订单-课程培训类子表-课程培训清单Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-12
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