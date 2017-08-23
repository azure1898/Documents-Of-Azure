/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.FieldInfo;
import com.its.modules.app.dao.FieldInfoDao;

/**
 * 场地预约Service
 * @author sushipeng
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class FieldInfoService extends CrudService<FieldInfoDao, FieldInfo> {

	public FieldInfo get(String id) {
		return super.get(id);
	}
	
	public List<FieldInfo> findList(FieldInfo fieldInfo) {
		return super.findList(fieldInfo);
	}
	
	public Page<FieldInfo> findPage(Page<FieldInfo> page, FieldInfo fieldInfo) {
		return super.findPage(page, fieldInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(FieldInfo fieldInfo) {
		super.save(fieldInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(FieldInfo fieldInfo) {
		super.delete(fieldInfo);
	}
	
}