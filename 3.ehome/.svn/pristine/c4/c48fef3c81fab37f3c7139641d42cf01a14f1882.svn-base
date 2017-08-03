package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.SortInfo;
import com.its.modules.app.dao.SortInfoDao;

/**
 * 商品分类信息Service
 * 
 * @author sushipeng
 * @version 2017-07-24
 */
@Service
@Transactional(readOnly = true)
public class SortInfoService extends CrudService<SortInfoDao, SortInfo> {

	public SortInfo get(String id) {
		return super.get(id);
	}

	public List<SortInfo> findList(SortInfo sortInfo) {
		return super.findList(sortInfo);
	}

	public Page<SortInfo> findPage(Page<SortInfo> page, SortInfo sortInfo) {
		return super.findPage(page, sortInfo);
	}

	@Transactional(readOnly = false)
	public void save(SortInfo sortInfo) {
		super.save(sortInfo);
	}

	@Transactional(readOnly = false)
	public void delete(SortInfo sortInfo) {
		super.delete(sortInfo);
	}

	/**
	 * 获取某商家的商品或服务分类信息
	 * 
	 * @param businessInfoId
	 *            商家ID
	 * @param type
	 *            分类类型（0商品1服务）
	 * @return List<SortInfo>
	 */
	public List<SortInfo> getSortInfoListOfBusiness(String businessInfoId, String type) {
		return dao.getSortInfoListOfBusiness(businessInfoId, type);
	}
}