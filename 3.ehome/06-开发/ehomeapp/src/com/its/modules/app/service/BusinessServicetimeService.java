package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;

import com.its.modules.app.entity.BusinessServicetime;
import com.its.modules.app.dao.BusinessServicetimeDao;

/**
 * 商户服务时段Service
 * 
 * @author sushipeng
 * 
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class BusinessServicetimeService extends CrudService<BusinessServicetimeDao, BusinessServicetime> {

	public BusinessServicetime get(String id) {
		return super.get(id);
	}

	public List<BusinessServicetime> findList(BusinessServicetime businessServicetime) {
		return super.findList(businessServicetime);
	}

	public Page<BusinessServicetime> findPage(Page<BusinessServicetime> page, BusinessServicetime businessServicetime) {
		return super.findPage(page, businessServicetime);
	}

	@Transactional(readOnly = false)
	public void save(BusinessServicetime businessServicetime) {
		super.save(businessServicetime);
	}

	@Transactional(readOnly = false)
	public void delete(BusinessServicetime businessServicetime) {
		super.delete(businessServicetime);
	}

	/**
	 * 获取某商家某时段类型下的商家服务时段
	 * 
	 * @param businessInfoId
	 *            商家ID
	 * @param timeType
	 *            时段类型
	 * @return List<BusinessServicetime>
	 */
	public List<BusinessServicetime> getByBusinessInfoID(String businessInfoId, String timeType) {
		return dao.getByBusinessInfoID(businessInfoId, timeType);
	}
}