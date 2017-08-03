package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.OrderGroupPurcList;
import com.its.modules.app.dao.OrderGroupPurcListDao;

/**
 * 订单-团购类子表-团购券清单Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-13
 */
@Service
@Transactional(readOnly = true)
public class OrderGroupPurcListService extends CrudService<OrderGroupPurcListDao, OrderGroupPurcList> {

	public OrderGroupPurcList get(String id) {
		return super.get(id);
	}

	public List<OrderGroupPurcList> findList(OrderGroupPurcList orderGroupPurcList) {
		return super.findList(orderGroupPurcList);
	}

	public Page<OrderGroupPurcList> findPage(Page<OrderGroupPurcList> page, OrderGroupPurcList orderGroupPurcList) {
		return super.findPage(page, orderGroupPurcList);
	}

	@Transactional(readOnly = false)
	public void save(OrderGroupPurcList orderGroupPurcList) {
		super.save(orderGroupPurcList);
	}

	@Transactional(readOnly = false)
	public void delete(OrderGroupPurcList orderGroupPurcList) {
		super.delete(orderGroupPurcList);
	}
}