/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.VillageLineRecomBusiTypeDetail;
import com.its.modules.app.dao.VillageLineRecomBusiTypeDetailDao;

/**
 * 楼盘产品线推荐商家模式Service
 * @author sushipeng
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class VillageLineRecomBusiTypeDetailService extends CrudService<VillageLineRecomBusiTypeDetailDao, VillageLineRecomBusiTypeDetail> {

	public VillageLineRecomBusiTypeDetail get(String id) {
		return super.get(id);
	}
	
	public List<VillageLineRecomBusiTypeDetail> findList(VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail) {
		return super.findList(villageLineRecomBusiTypeDetail);
	}
	
	public Page<VillageLineRecomBusiTypeDetail> findPage(Page<VillageLineRecomBusiTypeDetail> page, VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail) {
		return super.findPage(page, villageLineRecomBusiTypeDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail) {
		super.save(villageLineRecomBusiTypeDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(VillageLineRecomBusiTypeDetail villageLineRecomBusiTypeDetail) {
		super.delete(villageLineRecomBusiTypeDetail);
	}
	
}