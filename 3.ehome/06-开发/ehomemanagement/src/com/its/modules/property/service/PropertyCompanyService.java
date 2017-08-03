/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.property.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.property.entity.PropertyCompany;
import com.its.modules.property.dao.PropertyCompanyDao;

/**
 * 物业公司信息Service
 * @author ChenXiangyu
 * @version 2017-07-11
 */
@Service
@Transactional(readOnly = true)
public class PropertyCompanyService extends CrudService<PropertyCompanyDao, PropertyCompany> {

	public PropertyCompany get(String id) {
		return super.get(id);
	}
	
	public List<PropertyCompany> findList(PropertyCompany propertyCompany) {
		return super.findList(propertyCompany);
	}
	
	public Page<PropertyCompany> findPage(Page<PropertyCompany> page, PropertyCompany propertyCompany) {
		return super.findPage(page, propertyCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(PropertyCompany propertyCompany) {
		super.save(propertyCompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(PropertyCompany propertyCompany) {
		super.delete(propertyCompany);
	}
	
}