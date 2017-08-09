/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.rong.dao;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.rong.entity.ActToken;

/**
 * rongDAO接口
 * @author 刘浩浩
 * @version 2017-08-09
 */
@MyBatisDao
public interface ActTokenDao extends CrudDao<ActToken> {
	
	/**
	 * @Description：根据用户ID查询该用户的token对象
	 * @Author：刘浩浩
	 * @Date：2017年8月9日
	 * @param actToken
	 * @return
	 */
	public ActToken getActToken(@Param("accountId") String accountId);
}