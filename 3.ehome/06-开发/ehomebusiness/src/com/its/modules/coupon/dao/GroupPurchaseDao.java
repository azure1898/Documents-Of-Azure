/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.coupon.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.coupon.entity.GroupPurchase;

/**
 * 优惠验券管理DAO接口
 * @author caojing
 * @version 2017-07-25
 */
@MyBatisDao
public interface GroupPurchaseDao extends CrudDao<GroupPurchase> {
	/**
	 * 团购管理中，依据团购ID，获取团购信息详情
	 * @return
	 */
	public GroupPurchase getDetail(GroupPurchase groupPurchase);
}