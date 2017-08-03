/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.balance.entity.PropertyBalanceDetail;

/**
 * 物业结算明细信息DAO接口
 * @author LiuQi
 * @version 2017-07-17
 */
@MyBatisDao
public interface PropertyBalanceDetailDao extends CrudDao<PropertyBalanceDetail> {

	public List<PropertyBalanceDetail> findListByBalanceCycle(PropertyBalanceDetail propertyBalanceDetail);

	public void updateCheckState(PropertyBalanceDetail pbd);

	public List<PropertyBalanceDetail> findExportList(PropertyBalanceDetail propertyBalanceDetail);
	
}