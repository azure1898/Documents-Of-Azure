/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.notice.dao;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.notice.entity.NoticeManage;

/**
 * 社区公告DAO接口
 * @author ChenXiangyu
 * @version 2017-07-18
 */
@MyBatisDao
public interface NoticeManageDao extends CrudDao<NoticeManage> {
	
}