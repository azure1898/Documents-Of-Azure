/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.List;

import com.its.modules.field.dao.FieldPartitionPriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.entity.OrderFieldList;
import com.its.modules.order.dao.OrderFieldListDao;

/**
 * 场地预约订单表Service
 * @author xzc
 * @version 2017-07-07
 */
@Service
@Transactional(readOnly = true)
public class OrderFieldListService extends CrudService<OrderFieldListDao, OrderFieldList> {

	@Autowired
	private FieldPartitionPriceDao fieldPartitionPriceDao;
	@Autowired
	private OrderTrackService orderTrackService;
	public OrderFieldList get(String id) {
		return super.get(id);
	}

	/**
	 * 查询列表
	 * @param orderFieldList
	 * @return
	 */
	public List<OrderFieldList> findList(OrderFieldList orderFieldList) {
		return super.findList(orderFieldList);
	}

	/**
	 * 查询分页列表
	 * @param page 分页对象
	 * @param orderFieldList
	 * @return
	 */
	public Page<OrderFieldList> findPage(Page<OrderFieldList> page, OrderFieldList orderFieldList) {
		return super.findPage(page, orderFieldList);
	}

	/**
	 * 保存或者更新
	 * @param orderFieldList
	 */
	@Transactional(readOnly = false)
	public void save(OrderFieldList orderFieldList) {
		super.save(orderFieldList);
	}

	/**
	 * 删除
	 * @param orderFieldList
	 */
	@Transactional(readOnly = false)
	public void delete(OrderFieldList orderFieldList) {
		super.delete(orderFieldList);
	}

	/**
	 * 取消预约
	 * @param id 场地分段信息ID
	 * @param orderNo 订单号
	 * @return
	 */
	@Transactional(readOnly = false)
	public void cancelOrderFieldList(String orderNo,String id) {
		fieldPartitionPriceDao.cancelOrderFieldList(id);
		dao.cancelOrderFieldList(id);
		orderTrackService.saveOrdMsg(orderNo,"取消预约成功","取消场地预约成功（商家后台预约）");
	}
}