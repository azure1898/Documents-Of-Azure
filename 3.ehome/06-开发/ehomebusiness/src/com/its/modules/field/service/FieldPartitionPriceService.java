/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.dao.FieldPartitionPriceDao;

/**
 * 场地预约子表-场地分段信息Service
 * @author xzc
 * @version 2017-07-03
 */
@Service
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
	 * 查询预约时间的场地预约分段信息
	 * @param fieldPartitionPrice 场地预约信息
	 * @return
	 */
	public List<FieldPartitionPrice> findListByAppointmentTime(FieldPartitionPrice fieldPartitionPrice) {
		return dao.findListByAppointmentTime(fieldPartitionPrice);
	}
	
	@Transactional(readOnly = false)
	public void deleteAll(String findInfoId){
		this.dao.deleteAll(findInfoId);
	}
	@Transactional(readOnly = false)
	public void taskUpdateState(){
		this.dao.taskUpdateState();
	}
	
}