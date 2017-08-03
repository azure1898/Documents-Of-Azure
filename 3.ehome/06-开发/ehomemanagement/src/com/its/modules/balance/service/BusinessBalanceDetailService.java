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
import com.its.modules.balance.dao.BusinessBalanceDetailDao;
import com.its.modules.balance.entity.BusinessBalanceDetail;

/**
 * 商家结算明细信息Service
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class BusinessBalanceDetailService extends CrudService<BusinessBalanceDetailDao, BusinessBalanceDetail> {

	public BusinessBalanceDetail get(String id) {
		return super.get(id);
	}

	public List<BusinessBalanceDetail> findList(BusinessBalanceDetail businessBalanceDetail) {
		return super.findList(businessBalanceDetail);
	}

	public Page<BusinessBalanceDetail> findPage(Page<BusinessBalanceDetail> page,
			BusinessBalanceDetail businessBalanceDetail) {
		return super.findPage(page, businessBalanceDetail);
	}

	@Transactional(readOnly = false)
	public void save(BusinessBalanceDetail businessBalanceDetail) {
		super.save(businessBalanceDetail);
	}

	@Transactional(readOnly = false)
	public void delete(BusinessBalanceDetail businessBalanceDetail) {
		super.delete(businessBalanceDetail);
	}

	/**
	 * 根据商品订单、商家信息，以及商家结算的结算周期获取商家结算子表信息
	 * 
	 * @param businessBalanceDetail
	 * @return
	 */
	public List<BusinessBalanceDetail> findListByOrderType(BusinessBalanceDetail businessBalanceDetail, int orderType) {
		List<BusinessBalanceDetail> businessBalanceDetailList = new ArrayList<BusinessBalanceDetail>();
		switch (orderType) {
		case 0:
			businessBalanceDetailList = dao.findListByOrderGoods(businessBalanceDetail);
			break;
		case 1:
			businessBalanceDetailList = dao.findListByOrderService(businessBalanceDetail);
			break;
		case 2:
			businessBalanceDetailList = dao.findListByOrderLesson(businessBalanceDetail);
			break;
		case 3:
			businessBalanceDetailList = dao.findListByOrderField(businessBalanceDetail);
			break;
		case 4:
			businessBalanceDetailList = dao.findListByOrderGroupPurc(businessBalanceDetail);
			break;
		default:

		}

		return businessBalanceDetailList;
	}

	@Transactional(readOnly = false)
	public void updateCheckStateByOrderType(BusinessBalanceDetail businessBalanceDetail, int orderType) {
		switch (orderType) {
		case 0:
			dao.updateOrderGoods(businessBalanceDetail);
			break;
		case 1:
			dao.updateOrderService(businessBalanceDetail);
			break;
		case 2:
			dao.updateOrderLesson(businessBalanceDetail);
			break;
		case 3:
			dao.updateOrderField(businessBalanceDetail);
			break;
		case 4:
			dao.updateOrderGroupPurc(businessBalanceDetail);
			break;
		default:
			
		}
	}

	public List<BusinessBalanceDetail> findAllList(BusinessBalanceDetail businessBalanceDetail) {
		return dao.findAllList(businessBalanceDetail);
	}

	public List<BusinessBalanceDetail> findExportList(BusinessBalanceDetail businessBalanceDetail) {
		return dao.findExportList(businessBalanceDetail);
	}

}