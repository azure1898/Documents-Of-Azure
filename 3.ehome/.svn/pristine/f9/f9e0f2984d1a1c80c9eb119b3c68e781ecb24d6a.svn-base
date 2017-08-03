/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.recharge.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.recharge.entity.RechargeManage;

/**
 * 充值管理DAO接口
 * @author ChenXiangyu
 * @version 2017-07-05
 */
@MyBatisDao
public interface RechargeManageDao extends CrudDao<RechargeManage> {

	public Integer getRechargeCountByVillage(String villageId);
	
	public RechargeManage getPublishDate(RechargeManage rechargeManage);
}