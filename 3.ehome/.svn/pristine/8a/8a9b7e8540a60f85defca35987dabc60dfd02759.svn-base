/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.order.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.order.dao.OrderGroupPurcDao;
import com.its.modules.order.dao.OrderGroupPurcListDao;
import com.its.modules.order.entity.OrderGroupPurc;
import com.its.modules.order.entity.OrderGroupPurcList;

/**
 * 订单团购券清单Service
 * 
 * @author lq
 * @created on 2017年7月27日
 */
@Service
@Transactional(readOnly = true)
public class OrderGroupPurcListService extends CrudService<OrderGroupPurcListDao, OrderGroupPurcList> {
	
	@Autowired
	private OrderGroupPurcListDao orderGroupPurcListDao;
	@Autowired
	private OrderGroupPurcDao orderGroupPurcDao;

	public OrderGroupPurcList get(String id) {
		return super.get(id);
	}
	
	public List<OrderGroupPurcList> findList(OrderGroupPurcList OrderGroupPurcList) {
		return super.findList(OrderGroupPurcList);
	}
	
	public Page<OrderGroupPurcList> findPage(Page<OrderGroupPurcList> page, OrderGroupPurcList OrderGroupPurcList) {
		return super.findPage(page, OrderGroupPurcList);
	}
	
	/**
	 * 优惠/验券管理：验券的团购券消费列表信息的查询 
	 * @param orderGroupPurcList
	 * @return
	 */
	public List<OrderGroupPurcList> getNumberList(OrderGroupPurcList orderGroupPurcList) {
		
		//依据团购券号查找订单号
		List<OrderGroupPurcList> orderList=
				orderGroupPurcListDao.getOrderListByNumber(orderGroupPurcList);
		
		//订单号存在
		if(orderList !=null && orderList.size() > 0){
			String order = orderList.get(0).getOrderNo();			
			if(StringUtils.isNotBlank(order)){
				//设置订单号
				OrderGroupPurcList orderGroupPurc = new OrderGroupPurcList();
				orderGroupPurc.setOrderNo(order);
				//依据订单号，验券的团购券消费列表信息的查询 
				List<OrderGroupPurcList> orderGroupList = 
						orderGroupPurcListDao.getNumberList(orderGroupPurc);
				return orderGroupList;				
			}
		}
		
		return null;
	}
	
	/**
	 * 优惠/验券管理：依据团购券清单Id，查询团购券清单信息
	 * @param orderGroupPurcList
	 * @return
	 */
	public List<OrderGroupPurcList> getNumberListById(String id) {
		
		OrderGroupPurcList orderGroupPurcList = new OrderGroupPurcList();
		orderGroupPurcList.setId(id);
		//优惠/验券管理（验券）： 依据团购券清单表ID查找订单号
		OrderGroupPurcList orderInfo = 
				orderGroupPurcListDao.getOrderInfoById(orderGroupPurcList);
		
		//订单号存在
		if(orderInfo !=null){
			String order = orderInfo.getOrderNo();			
			if(StringUtils.isNotBlank(order)){
				//设置订单号
				OrderGroupPurcList orderGroupPurc = new OrderGroupPurcList();
				orderGroupPurc.setOrderNo(order);
				//依据订单号，验券的团购券消费列表信息的查询 
				List<OrderGroupPurcList> orderGroupList = 
						orderGroupPurcListDao.getNumberList(orderGroupPurc);
				return orderGroupList;				
			}
		}
		
		return null;
	}
	
	/**
	 * 优惠/验券管理：更新团购券消费信息
	 * @param orderGroupPurcList
	 * @return
	 */
	@Transactional(readOnly = false)
	public void updateGroupNumber(String checkedNumberId){
		//页面选中的团购券号
		String[] groupNumberIds = checkedNumberId.split(",");
		//依据团购券号，更新团购券状态、消费时间
		for(int i = 0; i < groupNumberIds.length; i++){
			OrderGroupPurcList orderGroupPurcList = new OrderGroupPurcList();
			orderGroupPurcList.setId(groupNumberIds[i]);
			//团购券状态：已消费
			orderGroupPurcList.setGroupPurcState("1");
			//消费时间
			orderGroupPurcList.setConsumeTime(new Date());
			orderGroupPurcList.preUpdate();
			//依据团购券号，更新团购券状态、消费时间
			orderGroupPurcListDao.updateGroupNumber(orderGroupPurcList);
		}
		
		if(groupNumberIds.length > 0){
			OrderGroupPurcList orderGroupPurcList = new OrderGroupPurcList();
			orderGroupPurcList.setId(groupNumberIds[0]);
			//优惠/验券管理（验券）： 依据团购券清单表ID查找订单号
			OrderGroupPurcList orderInfo = orderGroupPurcListDao.getOrderInfoById(orderGroupPurcList);
			
			if(orderInfo !=null && StringUtils.isNotBlank(orderInfo.getOrderNo())){
								
				OrderGroupPurcList orderDetail = new OrderGroupPurcList();
				//订单号
				orderDetail.setOrderNo(orderInfo.getOrderNo());
				//消费状态：未消费
				orderDetail.setGroupPurcState("0");
				//优惠/验券管理（验券）： 查找同订单号下面未消费的记录
				int notSpendingCount = orderGroupPurcListDao.countNotSpending(orderDetail);
				//未消费的记录不存在，全部被消费掉，更新订单团购主表的订单状态字段为：已消费
				if(notSpendingCount == 0){
					OrderGroupPurc orderGroup= new OrderGroupPurc();
					//订单状态：已消费
					orderGroup.setOrderState("1");
					orderGroup.preUpdate();
					orderGroup.setOrderNo(orderInfo.getOrderNo());
					orderGroupPurcDao.updateOrderState(orderGroup);
			    }
			}
		}
	}
}