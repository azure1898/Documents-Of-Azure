package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.AdverManage;
import com.its.modules.app.dao.AdverManageDao;

/**
 * 广告管理Service
 * 
 * @author like
 * 
 * @version 2017-07-28
 */
@Service
@Transactional(readOnly = true)
public class AdverManageService extends CrudService<AdverManageDao, AdverManage> {
	public AdverManage get(String id) {
		return super.get(id);
	}

	public List<AdverManage> findList(AdverManage adverManage) {
		return super.findList(adverManage);
	}

	public Page<AdverManage> findPage(Page<AdverManage> page, AdverManage adverManage) {
		return super.findPage(page, adverManage);
	}

	@Transactional(readOnly = false)
	public void save(AdverManage adverManage) {
		super.save(adverManage);
	}

	@Transactional(readOnly = false)
	public void delete(AdverManage adverManage) {
		super.delete(adverManage);
	}

	/**
	 * 获取某个位置下投放中的广告列表
	 * 
	 * @param adverPositionId
	 *            广告位置ID
	 * @return List<AdverManage>
	 */
	public List<AdverManage> getAdverManageByPositionId(String adverPositionId) {
		return dao.getAdverManageByPositionId(adverPositionId);
	}
}