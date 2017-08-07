/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.praise.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.praise.entity.SocialPraise;

/**
 * 点赞DAO接口
 * @author wmm
 * @version 2017-08-03
 */
@MyBatisDao
public interface SocialPraiseDao extends CrudDao<SocialPraise> {
	
	/**
	 * 通过发言ID查找点赞列表
	 * @param speakid
	 * @return
	 */
	public List<SocialPraise> findBySpeakId(SocialPraise socialPraise);
	
}