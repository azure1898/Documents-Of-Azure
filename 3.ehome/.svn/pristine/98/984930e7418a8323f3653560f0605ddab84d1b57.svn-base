/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.balance.entity.BusinessBalanceDetail;

/**
 * 商家结算明细信息DAO接口
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@MyBatisDao
public interface BusinessBalanceDetailDao extends CrudDao<BusinessBalanceDetail> {
	/**
	 * 根据商品订单、商家信息，以及商家结算的结算周期获取商家结算子表信息
	 * 
	 * @param businessBalanceDetail
	 * @return
	 */
	public List<BusinessBalanceDetail> findListByOrderGoods(BusinessBalanceDetail businessBalanceDetail);
	public List<BusinessBalanceDetail> findListByOrderService(BusinessBalanceDetail businessBalanceDetail);
	public List<BusinessBalanceDetail> findListByOrderLesson(BusinessBalanceDetail businessBalanceDetail);
	public List<BusinessBalanceDetail> findListByOrderField(BusinessBalanceDetail businessBalanceDetail);
	public List<BusinessBalanceDetail> findListByOrderGroupPurc(BusinessBalanceDetail businessBalanceDetail);

	public void updateOrderGoods(BusinessBalanceDetail businessBalanceDetail);
	public void updateOrderService(BusinessBalanceDetail businessBalanceDetail);
	public void updateOrderLesson(BusinessBalanceDetail businessBalanceDetail);
	public void updateOrderField(BusinessBalanceDetail businessBalanceDetail);
	public void updateOrderGroupPurc(BusinessBalanceDetail businessBalanceDetail);
	public List<BusinessBalanceDetail> findExportList(BusinessBalanceDetail businessBalanceDetail);
}