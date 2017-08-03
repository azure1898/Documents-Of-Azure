/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.balance.entity.PropertyBalance;

/**
 * 物业结算信息DAO接口
 * @author LiuQi
 * @version 2017-07-17
 */
@MyBatisDao
public interface PropertyBalanceDao extends CrudDao<PropertyBalance> {

	public List<PropertyBalance> findListByPropertyBill();

	public void batchBalance(PropertyBalance propertyBalance);

	public List<PropertyBalance> findBalanceApply(PropertyBalance propertyBalance);

	public void check(PropertyBalance propertyBalance);
	
}