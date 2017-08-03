/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.bean.FieldInfoBean;
import com.its.modules.app.bean.FieldPartitionBean;
import com.its.modules.app.dao.FieldPartitionPriceDao;
import com.its.modules.app.entity.FieldPartitionPrice;

/**
 * 场地分段预约表Service
 * 
 * @author like
 * @version 2017-07-11
 */
@Service
@Transactional(readOnly = true)
public class FieldPartitionPriceService extends CrudService<FieldPartitionPriceDao, FieldPartitionPrice> {

	public FieldPartitionPrice get(String id) {
		return super.get(id);
	}

	public List<FieldPartitionPrice> findList(FieldPartitionPrice fieldPartitionPrice) {
		return super.findList(fieldPartitionPrice);
	}

	public Page<FieldPartitionPrice> findPage(Page<FieldPartitionPrice> page, FieldPartitionPrice fieldPartitionPrice) {
		return super.findPage(page, fieldPartitionPrice);
	}

	@Transactional(readOnly = false)
	public void save(FieldPartitionPrice fieldPartitionPrice) {
		super.save(fieldPartitionPrice);
	}

	@Transactional(readOnly = false)
	public void delete(FieldPartitionPrice fieldPartitionPrice) {
		super.delete(fieldPartitionPrice);
	}

	/**
	 * 返回商家可预约日期集合
	 * 
	 * @param today
	 *            起始日期（格式：2017-07-11 00:00:00）
	 * @param businessInfoID
	 *            商家ID
	 * @return
	 */
	public List<String> findAppointmentDateList(String today, String businessInfoID) {
		return dao.findAppointmentDateList(today, businessInfoID);
	}

	/**
	 * 返回商家可预约时间内的可预约场地集合
	 * 
	 * @param today
	 *            起始日期（格式：2017-07-11 00:00:00）
	 * @param businessInfoID
	 * @return
	 */
	public List<FieldInfoBean> findAppointmentSiteList(String today, String businessInfoID) {
		return dao.findAppointmentSiteList(today, businessInfoID);
	}
	/**
	 * 返回某一商家某一天的某个场地的时段预约情况
	 * 
	 * @param dateString
	 * @param businessInfoID
	 * @return
	 */
	public List<FieldPartitionPrice> findFieldPartition(String businessInfoID,
			 String dateString, String siteID){
		return dao.findFieldPartition(businessInfoID, dateString, siteID);
	}
	/**
	 * 根据用户选择的时段ID查询相关数据（多个ID以逗号（半角）分隔）
	 * @param ids
	 * @return
	 */
	public List<FieldPartitionBean> findSelectedFieldPartition(String ids){
		String[] id = ids.split(",");
		return dao.findSelectedFieldPartition(id);
	}
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
	public int updateFieldPartitionState(String id,String oldState,String newState){
		return dao.updateFieldPartitionState(id, oldState, newState);
	}
}