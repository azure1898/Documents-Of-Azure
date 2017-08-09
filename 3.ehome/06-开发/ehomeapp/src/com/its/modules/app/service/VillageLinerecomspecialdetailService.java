package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.VillageLinerecomspecialdetail;
import com.its.modules.app.dao.VillageLinerecomspecialdetailDao;

/**
 * 楼盘产品线专题推荐明细Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-07
 */
@Service
@Transactional(readOnly = true)
public class VillageLinerecomspecialdetailService extends CrudService<VillageLinerecomspecialdetailDao, VillageLinerecomspecialdetail> {
	public VillageLinerecomspecialdetail get(String id) {
		return super.get(id);
	}

	public List<VillageLinerecomspecialdetail> findList(VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
		return super.findList(villageLinerecomspecialdetail);
	}

	public Page<VillageLinerecomspecialdetail> findPage(Page<VillageLinerecomspecialdetail> page, VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
		return super.findPage(page, villageLinerecomspecialdetail);
	}

	@Transactional(readOnly = false)
	public void save(VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
		super.save(villageLinerecomspecialdetail);
	}

	@Transactional(readOnly = false)
	public void delete(VillageLinerecomspecialdetail villageLinerecomspecialdetail) {
		super.delete(villageLinerecomspecialdetail);
	}

	/**
	 * 获取某专题推荐的明细列表
	 * 
	 * @param specialId
	 *            专题推荐ID
	 * @return List<VillageLinerecomspecialdetail>
	 */
	public List<VillageLinerecomspecialdetail> getVillageLinerecomspecialdetailList(String specialId) {
		return dao.getVillageLinerecomspecialdetailList(specialId);
	}
}