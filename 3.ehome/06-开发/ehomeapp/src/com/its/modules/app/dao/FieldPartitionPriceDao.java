/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.app.bean.FieldInfoBean;
import com.its.modules.app.bean.FieldPartitionBean;
import com.its.modules.app.entity.FieldPartitionPrice;

/**
 * 场地分段预约表DAO接口
 * 
 * @author like
 * @version 2017-07-11
 */
@MyBatisDao
public interface FieldPartitionPriceDao extends CrudDao<FieldPartitionPrice> {

	/**
	 * 返回商家可预约日期集合
	 * 
	 * @param today
	 *            起始日期（格式：2017-07-11 00:00:00）
	 * @param businessInfoID
	 *            商家ID
	 * @return
	 */
	public List<String> findAppointmentDateList(@Param("today") String today,
			@Param("businessInfoID") String businessInfoID);

	/**
	 * 返回商家可预约时间内的可预约场地集合
	 * 
	 * @param today
	 * @param businessInfoID
	 * @return
	 */
	public List<FieldInfoBean> findAppointmentSiteList(@Param("today") String today,
			@Param("businessInfoID") String businessInfoID);

	/**
	 * 返回某一商家某一天的某个场地的时段预约情况
	 * 
	 * @param dateString
	 * @param businessInfoID
	 * @return
	 */
	public List<FieldPartitionPrice> findFieldPartition(@Param("businessInfoID") String businessInfoID,
			@Param("dateString") String dateString, @Param("siteID") String siteID);

	/**
	 * 根据用户选择的时段ID查询相关数据
	 * 
	 * @param ids
	 * @return
	 */
	public List<FieldPartitionBean> findSelectedFieldPartition(@Param("ids") String[] ids);

	/**
	 * 更改场地时段预约状态
	 * 
	 * @param id
	 *            时段ID
	 * @param oldState
	 *            原状态（0可预约1已预约2已消费）
	 * @param newState
	 *            现状态（0可预约1已预约2已消费）
	 * @return 修改记录数
	 */
	public int updateFieldPartitionState(@Param("id") String id, @Param("oldState") String oldState,
			@Param("newState") String newState);
}