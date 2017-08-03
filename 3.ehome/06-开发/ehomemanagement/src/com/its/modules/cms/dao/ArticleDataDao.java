/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.cms.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.cms.entity.ArticleData;

/**
 * 文章DAO接口
 * @author Jetty
 * @version 2013-8-23
 */
@MyBatisDao
public interface ArticleDataDao extends CrudDao<ArticleData> {
	
}
