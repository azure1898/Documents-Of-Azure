/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.balance.entity.BusinessBalance;

/**
 * 商家结算信息DAO接口
 * @author LiuQi
 * @version 2017-07-17
 */
@MyBatisDao
public interface BusinessBalanceDao extends CrudDao<BusinessBalance> {
	public int insertByOrderGoods(BusinessBalance businessBalance);
	
	public List<BusinessBalance> findListByOrderGoods();
	public List<BusinessBalance> findListByOrderService();
	public List<BusinessBalance> findListByOrderLesson();
	public List<BusinessBalance> findListByOrderField();
	public List<BusinessBalance> findListByOrderGroupPurc();

	public void batchBalance(BusinessBalance businessBalance);

	public List<BusinessBalance> findBalanceApply(BusinessBalance businessBalance);

	public void check(BusinessBalance businessBalance);
}