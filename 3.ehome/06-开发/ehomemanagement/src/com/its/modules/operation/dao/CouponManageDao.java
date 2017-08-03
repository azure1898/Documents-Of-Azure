/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.operation.entity.CouponManage;

/**
 * 优惠券管理DAO接口
 * 
 * @author liuqi
 * @version 2017-07-03
 */
@MyBatisDao
public interface CouponManageDao extends CrudDao<CouponManage> {
	public int close(CouponManage couponManage);
}