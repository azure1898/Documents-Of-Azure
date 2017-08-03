/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.speak.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.speak.entity.SocialSpeak;

/**
 * 发言管理DAO接口
 * @author wmm
 * @version 2017-08-01
 */
@MyBatisDao
public interface SocialSpeakDao extends CrudDao<SocialSpeak> {
	
	/**
	 * 修改状态
	 * @param socialSpeak
	 * @return
	 */
	public int changeDelFlag(SocialSpeak socialSpeak);
	
	/**
	 * 修改置顶状态
	 * @param socialSpeak
	 * @return
	 */
	public int changeTop(SocialSpeak socialSpeak);
}