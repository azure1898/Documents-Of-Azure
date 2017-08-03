/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.balance.dao.PropertyBalanceDao;
import com.its.modules.balance.entity.PropertyBalance;
import com.its.modules.balance.entity.PropertyBalanceDetail;

/**
 * 物业结算信息Service
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class PropertyBalanceService extends CrudService<PropertyBalanceDao, PropertyBalance> {

	private PropertyBalanceDetailService propertyBalanceDetailService;

	public PropertyBalance get(String id) {
		return super.get(id);
	}

	public List<PropertyBalance> findList(PropertyBalance propertyBalance) {
		return super.findList(propertyBalance);
	}

	public Page<PropertyBalance> findPage(Page<PropertyBalance> page, PropertyBalance propertyBalance) {
		return super.findPage(page, propertyBalance);
	}

	@Transactional(readOnly = false)
	public void save(PropertyBalance propertyBalance) {
		super.save(propertyBalance);

		// 1. 根据物业缴费信息获得物业结算主表实体
		// 根据propertyBalance查找最新插入的记录
		propertyBalance = super.get(propertyBalance);
		String propertyBalanceId = propertyBalance.getId();
		logger.warn("物业结算信息表的id:" + propertyBalanceId);

		// 2.在获得每一条结算表实体的同时，生成结算子表的实体
		// 生成propertyBalanceDetail表记录
		PropertyBalanceDetail propertyBalanceDetail = new PropertyBalanceDetail();
		propertyBalanceDetail.setBalanceStartTime(propertyBalance.getBalanceStartTime());
		propertyBalanceDetail.setBalanceEndTime(propertyBalance.getBalanceEndTime());
		List<PropertyBalanceDetail> propertyBalanceDetailList = new ArrayList<PropertyBalanceDetail>();
		propertyBalanceDetailList = propertyBalanceDetailService.findListByBalanceCycle(propertyBalanceDetail);

		for (PropertyBalanceDetail pbd : propertyBalanceDetailList) {
			pbd.setPropertyBalanceId(propertyBalanceId);
			propertyBalanceDetailService.save(pbd);

			// 3.根据订单号(order_no)、订单表表名，修改订单-商品类的结算状态为已结算
			propertyBalanceDetailService.updateCheckState(pbd);
		}
	}

	@Transactional(readOnly = false)
	public void delete(PropertyBalance propertyBalance) {
		super.delete(propertyBalance);
	}

	public List<PropertyBalance> findListByPropertyBill() {
		return dao.findListByPropertyBill();
	}

	@Transactional(readOnly = false)
	public void batchBalance(PropertyBalance propertyBalance) {
		for (String id : propertyBalance.getIds().split(",")) {
			PropertyBalance tmp = new PropertyBalance();
			tmp.setId(id);
			dao.batchBalance(tmp);
		}
	}

	public List<PropertyBalance> findAllList(PropertyBalance propertyBalance) {
		return dao.findAllList(propertyBalance);
	}

	public List<PropertyBalance> findBalanceApply(PropertyBalance propertyBalance) {
		return dao.findBalanceApply(propertyBalance);
	}

	@Transactional(readOnly = false)
	public void check(PropertyBalance propertyBalance) {
		dao.check(propertyBalance);
		
	}

}