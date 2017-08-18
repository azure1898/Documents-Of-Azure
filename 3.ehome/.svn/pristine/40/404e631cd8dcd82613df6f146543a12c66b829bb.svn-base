package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.VillageLineRecomSpecialDetailDao;
import com.its.modules.app.entity.VillageLineRecomSpecialDetail;

/**
 * 楼盘产品线专题推荐明细Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class VillageLineRecomSpecialDetailService extends CrudService<VillageLineRecomSpecialDetailDao, VillageLineRecomSpecialDetail> {
	public VillageLineRecomSpecialDetail get(String id) {
		return super.get(id);
	}

	public List<VillageLineRecomSpecialDetail> findList(VillageLineRecomSpecialDetail villageLineRecomSpecialDetail) {
		return super.findList(villageLineRecomSpecialDetail);
	}

	public Page<VillageLineRecomSpecialDetail> findPage(Page<VillageLineRecomSpecialDetail> page, VillageLineRecomSpecialDetail villageLineRecomSpecialDetail) {
		return super.findPage(page, villageLineRecomSpecialDetail);
	}

	@Transactional(readOnly = false)
	public void save(VillageLineRecomSpecialDetail villageLineRecomSpecialDetail) {
		super.save(villageLineRecomSpecialDetail);
	}

	@Transactional(readOnly = false)
	public void delete(VillageLineRecomSpecialDetail villageLineRecomSpecialDetail) {
		super.delete(villageLineRecomSpecialDetail);
	}

	/**
	 * 获取某专题推荐的明细列表
	 * 
	 * @param specialId
	 *            专题推荐ID
	 * @return List<VillageLineRecomSpecialDetail>
	 */
	public List<VillageLineRecomSpecialDetail> getRecomSpecialDetailList(String specialId) {
		return dao.getRecomSpecialDetailList(specialId);
	}
}