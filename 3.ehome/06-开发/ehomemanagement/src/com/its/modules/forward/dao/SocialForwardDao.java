/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.forward.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.forward.entity.SocialForward;

/**
 * 转发DAO接口
 * @author wmm
 * @version 2017-08-03
 */
@MyBatisDao
public interface SocialForwardDao extends CrudDao<SocialForward> {
	
	/**
	 * 通过发言ID查询转发列表
	 * @param speakid
	 * @return
	 */
	public List<SocialForward> findBySpeakId(SocialForward socialForward);
	
	public int changeDelFlag(SocialForward socialForward);
	
}