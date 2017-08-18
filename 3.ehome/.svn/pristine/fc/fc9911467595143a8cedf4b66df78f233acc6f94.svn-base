package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;

import com.its.modules.app.dao.VillageLineRecomSpecialDao;
import com.its.modules.app.entity.VillageLineRecomSpecial;

/**
 * 楼盘产品线专题推荐Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class VillageLineRecomSpecialService extends CrudService<VillageLineRecomSpecialDao, VillageLineRecomSpecial> {

	public VillageLineRecomSpecial get(String id) {
		return super.get(id);
	}

	public List<VillageLineRecomSpecial> findList(VillageLineRecomSpecial villageLineRecomSpecial) {
		return super.findList(villageLineRecomSpecial);
	}

	public Page<VillageLineRecomSpecial> findPage(Page<VillageLineRecomSpecial> page, VillageLineRecomSpecial villageLineRecomSpecial) {
		return super.findPage(page, villageLineRecomSpecial);
	}

	@Transactional(readOnly = false)
	public void save(VillageLineRecomSpecial villageLineRecomSpecial) {
		super.save(villageLineRecomSpecial);
	}

	@Transactional(readOnly = false)
	public void delete(VillageLineRecomSpecial villageLineRecomSpecial) {
		super.delete(villageLineRecomSpecial);
	}

	/**
	 * 获取某楼盘APP产品线下某位置的专题推荐
	 * 
	 * @param recommendPosition
	 *            推荐位置：00 首页专题推荐 10 社区推荐 20 生活推荐
	 * @param villageInfoId
	 *            楼盘ID
	 * @return VillageLineRecomSpecial
	 */
	public VillageLineRecomSpecial getVillageLineRecomSpecial(String recommendPosition, String villageInfoId) {
		return dao.getVillageLineRecomSpecial(recommendPosition, villageInfoId);
	}
}