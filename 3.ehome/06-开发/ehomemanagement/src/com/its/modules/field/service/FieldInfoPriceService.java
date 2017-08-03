/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.field.entity.FieldInfoPrice;
import com.its.modules.field.dao.FieldInfoPriceDao;

import javax.annotation.Resource;

/**
 * 场地预约子表_分段编辑临时表Service
 * @author xzc
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class FieldInfoPriceService extends CrudService<FieldInfoPriceDao, FieldInfoPrice> {

	@Autowired
	private FieldInfoPriceDao fieldInfoPriceDao;

	public FieldInfoPrice get(String id) {
		return super.get(id);
	}
	
	public List<FieldInfoPrice> findList(FieldInfoPrice fieldInfoPrice) {
		return super.findList(fieldInfoPrice);
	}
	
	public Page<FieldInfoPrice> findPage(Page<FieldInfoPrice> page, FieldInfoPrice fieldInfoPrice) {
		return super.findPage(page, fieldInfoPrice);
	}
	
	@Transactional(readOnly = false)
	public void save(FieldInfoPrice fieldInfoPrice) {
		super.save(fieldInfoPrice);
	}
	
	@Transactional(readOnly = false)
	public void delete(FieldInfoPrice fieldInfoPrice) {
		super.delete(fieldInfoPrice);
	}
	@Transactional(readOnly = false)
	public void deleteAll(FieldInfoPrice fieldInfoPrice,boolean isAll) {
		if (!isAll){
			super.delete(fieldInfoPrice);
		}else {
			fieldInfoPriceDao.deleteAll(fieldInfoPrice);
		}
	}
	
}