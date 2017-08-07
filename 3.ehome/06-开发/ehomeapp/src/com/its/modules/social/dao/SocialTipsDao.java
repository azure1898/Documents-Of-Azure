/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.social.entity.SocialTips;

/**
 * 消息提醒DAO接口
 * @author 刘浩浩
 * @version 2017-08-07
 */
@MyBatisDao
public interface SocialTipsDao extends CrudDao<SocialTips> {
	
}