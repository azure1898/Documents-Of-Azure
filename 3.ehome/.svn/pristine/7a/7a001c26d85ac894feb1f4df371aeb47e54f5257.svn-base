/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.business.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.business.entity.BusinessServicescope;
import com.its.modules.business.dao.BusinessServicescopeDao;

/**
 * 商户服务范围Service
 * @author liuqi
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class BusinessServicescopeService extends CrudService<BusinessServicescopeDao, BusinessServicescope> {

	public BusinessServicescope get(String id) {
		return super.get(id);
	}
	
	public List<BusinessServicescope> findList(BusinessServicescope businessServicescope) {
		return super.findList(businessServicescope);
	}
	
	public Page<BusinessServicescope> findPage(Page<BusinessServicescope> page, BusinessServicescope businessServicescope) {
		return super.findPage(page, businessServicescope);
	}
	
	@Transactional(readOnly = false)
	public void save(BusinessServicescope businessServicescope) {
		super.save(businessServicescope);
	}
	
	@Transactional(readOnly = false)
	public void delete(BusinessServicescope businessServicescope) {
		super.delete(businessServicescope);
	}
	
}