/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.operation.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.operation.entity.CouponManageUsers;

/**
 * 优惠券导入的用户DAO接口
 * 
 * @author liuqi
 * @version 2017-07-05
 */
@MyBatisDao
public interface CouponManageUsersDao extends CrudDao<CouponManageUsers> {

	public void updateCouponManageId(CouponManageUsers couponManageUsers);
}