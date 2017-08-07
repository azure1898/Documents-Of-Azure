/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.social.entity.SocialSubRelation;

/**
 * 发言和话题关联DAO接口
 * @author wmm
 * @version 2017-08-02
 */
@MyBatisDao
public interface SocialSubRelationDao extends CrudDao<SocialSubRelation> {
	
}