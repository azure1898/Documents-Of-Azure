/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.oa.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.oa.entity.OaNotify;

/**
 * 通知通告DAO接口
 * @author Jetty
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify);
	
}