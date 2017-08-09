package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;

import com.its.modules.app.dao.VillageLinerecomspecialDao;
import com.its.modules.app.entity.VillageLinerecomspecial;

/**
 * 楼盘产品线专题推荐Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-07
 */
@Service
@Transactional(readOnly = true)
public class VillageLinerecomspecialService extends CrudService<VillageLinerecomspecialDao, VillageLinerecomspecial> {

	public VillageLinerecomspecial get(String id) {
		return super.get(id);
	}

	public List<VillageLinerecomspecial> findList(VillageLinerecomspecial villageLinerecomspecial) {
		return super.findList(villageLinerecomspecial);
	}

	public Page<VillageLinerecomspecial> findPage(Page<VillageLinerecomspecial> page, VillageLinerecomspecial villageLinerecomspecial) {
		return super.findPage(page, villageLinerecomspecial);
	}

	@Transactional(readOnly = false)
	public void save(VillageLinerecomspecial villageLinerecomspecial) {
		super.save(villageLinerecomspecial);
	}

	@Transactional(readOnly = false)
	public void delete(VillageLinerecomspecial villageLinerecomspecial) {
		super.delete(villageLinerecomspecial);
	}

	/**
	 * 获取某楼盘APP产品线下某位置的专题推荐
	 * 
	 * @param recommendPosition
	 *            推荐位置：00 首页专题推荐 10 社区推荐 20 生活推荐
	 * @param villageInfoId
	 *            楼盘ID
	 * @return VillageLinerecomspecial
	 */
	public VillageLinerecomspecial getVillageLinerecomspecial(String recommendPosition, String villageInfoId) {
		return dao.getVillageLinerecomspecial(recommendPosition, villageInfoId);
	}
}