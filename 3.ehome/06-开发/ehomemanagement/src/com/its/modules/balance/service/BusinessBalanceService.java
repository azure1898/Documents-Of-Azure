/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.balance.dao.BusinessBalanceDao;
import com.its.modules.balance.entity.BusinessBalance;
import com.its.modules.balance.entity.BusinessBalanceDetail;

/**
 * 商家结算信息Service
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class BusinessBalanceService extends CrudService<BusinessBalanceDao, BusinessBalance> {

	@Autowired
	private BusinessBalanceDetailService businessBalanceDetailService;

	public BusinessBalance get(String id) {
		return super.get(id);
	}

	public List<BusinessBalance> findList(BusinessBalance businessBalance) {
		return super.findList(businessBalance);
	}

	public Page<BusinessBalance> findPage(Page<BusinessBalance> page, BusinessBalance businessBalance) {
		return super.findPage(page, businessBalance);
	}

	@Transactional(readOnly = false)
	public void save(BusinessBalance businessBalance, int orderType) {
		super.save(businessBalance);

		// 1. 根据订单-商品类信息获得商家结算主表实体
		// 根据businessBalance查找最新插入的记录
		businessBalance = super.get(businessBalance);
		String businessBalanceId = businessBalance.getId();
		logger.warn("商家结算信息表的id:" + businessBalanceId);

		// 2.在获得每一条商家结算表实体的同时，生成商家结算子表的实体
		// 生成businessBalanceDetail表记录
		BusinessBalanceDetail businessBalanceDetail = new BusinessBalanceDetail();
		businessBalanceDetail.setBalanceStartTime(businessBalance.getBalanceStartTime());
		businessBalanceDetail.setBalanceEndTime(businessBalance.getBalanceEndTime());
		List<BusinessBalanceDetail> businessBalanceDetailList = new ArrayList<BusinessBalanceDetail>();
		businessBalanceDetailList = businessBalanceDetailService.findListByOrderType(businessBalanceDetail, orderType);

		for (BusinessBalanceDetail bbd : businessBalanceDetailList) {
			bbd.setBusinessBalanceId(businessBalanceId);
			logger.warn("2.在获得每一条商家结算表实体的同时，生成商家结算子表的实体");
			businessBalanceDetailService.save(bbd);

			// 3.根据订单号(order_no)、订单表表名，修改订单-商品类的结算状态为已结算
			logger.warn("商家结算子表对象：" + bbd.toString());
			businessBalanceDetailService.updateCheckStateByOrderType(bbd, orderType);
		}

	}

	@Transactional(readOnly = false)
	public void delete(BusinessBalance businessBalance) {
		super.delete(businessBalance);
	}

	public List<BusinessBalance> findListByOrderGoods() {
		return dao.findListByOrderGoods();
	}

	public List<BusinessBalance> findListByOrderService() {
		return dao.findListByOrderService();
	}

	public List<BusinessBalance> findListByOrderLesson() {
		return dao.findListByOrderLesson();
	}

	public List<BusinessBalance> findListByOrderField() {
		return dao.findListByOrderField();
	}

	public List<BusinessBalance> findListByOrderGroupPurc() {
		return dao.findListByOrderGroupPurc();
	}

	public List<BusinessBalance> findAllList(BusinessBalance businessBalance) {
		return dao.findAllList(businessBalance);
	}

	/**
	 * 批量修改商家结算表的结算状态
	 * 
	 * @param businessBalance
	 */
	@Transactional(readOnly = false)
	public void batchBalance(BusinessBalance businessBalance) {
		for (String id : businessBalance.getIds().split(",")) {
			BusinessBalance tmpBB = new BusinessBalance();
			tmpBB.setId(id);
			dao.batchBalance(tmpBB);
		}
	}

	public List<BusinessBalance> findBalanceApply(BusinessBalance businessBalance) {
		return dao.findBalanceApply(businessBalance);
	}

	/**
	 * 核对结算单
	 * 
	 * @param businessBalance
	 */
	@Transactional(readOnly = false)
	public void check(BusinessBalance businessBalance) {
		dao.check(businessBalance);
		
	}

}