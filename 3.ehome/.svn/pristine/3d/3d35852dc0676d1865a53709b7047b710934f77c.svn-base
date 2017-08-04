/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.entity.NoticeManage;

/**
 * 公告管理DAO接口
 * 
 * @author like
 * @version 2017-08-03
 */
@MyBatisDao
public interface NoticeManageDao extends CrudDao<NoticeManage> {
	/**
	 * 获取最新一条公告
	 * 
	 * @param villageInfoId
	 *            楼盘ID
	 * @return
	 */
	public NoticeManage getLatestNotice(@Param("villageInfoId") String villageInfoId);

	/**
	 * 公告列表
	 * 
	 * @param villageInfoId
	 * @return
	 */
	public List<NoticeManage> getNoticeList(@Param("villageInfoId") String villageInfoId);
}