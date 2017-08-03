/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.edition.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.edition.dao.EditionManageDao;
import com.its.modules.edition.entity.EditionManage;

/**
 * 版本管理Service
 * @author ChenXiangyu
 * @version 2017-06-29
 */
@Service
@Transactional(readOnly = true)
public class EditionManageService extends CrudService<EditionManageDao, EditionManage> {

	public EditionManage get(String id) {
		return super.get(id);
	}
	
	public List<EditionManage> findList(EditionManage editionManage) {
		return super.findList(editionManage);
	}
	
	public Page<EditionManage> findPage(Page<EditionManage> page, EditionManage editionManage) {
		return super.findPage(page, editionManage);
	}
	
	@Transactional(readOnly = false)
	public void save(EditionManage editionManage) {
		super.save(editionManage);
	}
	
	@Transactional(readOnly = false)
	public void delete(EditionManage editionManage) {
		super.delete(editionManage);
	}
	
}