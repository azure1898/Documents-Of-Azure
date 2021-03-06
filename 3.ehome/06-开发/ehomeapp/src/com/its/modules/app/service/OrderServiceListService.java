package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.OrderServiceListDao;
import com.its.modules.app.entity.OrderServiceList;

/**
 * 订单-服务类子表-服务清单Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-07
 */
@Service
@Transactional(readOnly = true)
public class OrderServiceListService extends CrudService<OrderServiceListDao, OrderServiceList> {
	public OrderServiceList get(String id) {
		return super.get(id);
	}

	public List<OrderServiceList> findList(OrderServiceList orderServiceList) {
		return super.findList(orderServiceList);
	}

	public Page<OrderServiceList> findPage(Page<OrderServiceList> page, OrderServiceList orderServiceList) {
		return super.findPage(page, orderServiceList);
	}

	@Transactional(readOnly = false)
	public void save(OrderServiceList orderServiceList) {
		super.save(orderServiceList);
	}

	@Transactional(readOnly = false)
	public void delete(OrderServiceList orderServiceList) {
		super.delete(orderServiceList);
	}

	/**
	 * 获取某服务订单的订单清单
	 * 
	 * @param orderServiceId
	 *            服务ID
	 * @return OrderServiceList
	 */
	public OrderServiceList getOrderServiceList(String orderServiceId) {
		return dao.getOrderServiceList(orderServiceId);
	}
}