/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.module.entity.VillageLinerecombusitype;
import com.its.modules.module.dao.VillageLinerecombusitypeDao;

/**
 * 楼盘产品线推荐商家模式Service
 * @author zhujiao
 * @version 2017-07-27
 */
@Service
@Transactional(readOnly = true)
public class VillageLinerecombusitypeService extends CrudService<VillageLinerecombusitypeDao, VillageLinerecombusitype> {

	public VillageLinerecombusitype get(String id) {
		return super.get(id);
	}
	
	public List<VillageLinerecombusitype> findList(VillageLinerecombusitype villageLinerecombusitype) {
		return super.findList(villageLinerecombusitype);
	}
	
	public Page<VillageLinerecombusitype> findPage(Page<VillageLinerecombusitype> page, VillageLinerecombusitype villageLinerecombusitype) {
		return super.findPage(page, villageLinerecombusitype);
	}
	
	@Transactional(readOnly = false)
	public void save(VillageLinerecombusitype villageLinerecombusitype) {
		super.save(villageLinerecombusitype);
	}
	
	@Transactional(readOnly = false)
	public void delete(VillageLinerecombusitype villageLinerecombusitype) {
		super.delete(villageLinerecombusitype);
	}
	
}