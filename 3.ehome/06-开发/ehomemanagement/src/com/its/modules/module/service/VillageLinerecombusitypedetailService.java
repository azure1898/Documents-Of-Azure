/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.module.entity.VillageLinerecombusitypedetail;
import com.its.modules.module.dao.VillageLinerecombusitypedetailDao;

/**
 * 楼盘产品线推荐商家模式Service
 * @author zhujiao
 * @version 2017-07-27
 */
@Service
@Transactional(readOnly = true)
public class VillageLinerecombusitypedetailService extends CrudService<VillageLinerecombusitypedetailDao, VillageLinerecombusitypedetail> {

	public VillageLinerecombusitypedetail get(String id) {
		return super.get(id);
	}
	
	public List<VillageLinerecombusitypedetail> findList(VillageLinerecombusitypedetail villageLinerecombusitypedetail) {
		return super.findList(villageLinerecombusitypedetail);
	}
	
	public Page<VillageLinerecombusitypedetail> findPage(Page<VillageLinerecombusitypedetail> page, VillageLinerecombusitypedetail villageLinerecombusitypedetail) {
		return super.findPage(page, villageLinerecombusitypedetail);
	}
	
	@Transactional(readOnly = false)
	public void save(VillageLinerecombusitypedetail villageLinerecombusitypedetail) {
		super.save(villageLinerecombusitypedetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(VillageLinerecombusitypedetail villageLinerecombusitypedetail) {
		super.delete(villageLinerecombusitypedetail);
	}
	
}