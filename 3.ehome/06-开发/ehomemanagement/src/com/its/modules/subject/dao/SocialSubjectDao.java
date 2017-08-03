/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.subject.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.subject.entity.SocialSubject;

/**
 * 话题管理DAO接口
 * @author wmm
 * @version 2017-07-31
 */
@MyBatisDao
public interface SocialSubjectDao extends CrudDao<SocialSubject> {
	
	public int updateSortNum(SocialSubject socialSubject);
	
	public int updateRecommend(SocialSubject socialSubject);
	
	public List<SocialSubject> findAll();
	
}