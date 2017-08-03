/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.balance.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.balance.entity.BusinessBalanceDetail;
import com.its.modules.balance.entity.PropertyBalanceDetail;
import com.its.modules.balance.dao.PropertyBalanceDetailDao;

/**
 * 物业结算明细信息Service
 * 
 * @author LiuQi
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class PropertyBalanceDetailService extends CrudService<PropertyBalanceDetailDao, PropertyBalanceDetail> {

	public PropertyBalanceDetail get(String id) {
		return super.get(id);
	}

	public List<PropertyBalanceDetail> findList(PropertyBalanceDetail propertyBalanceDetail) {
		return super.findList(propertyBalanceDetail);
	}

	public Page<PropertyBalanceDetail> findPage(Page<PropertyBalanceDetail> page,
			PropertyBalanceDetail propertyBalanceDetail) {
		return super.findPage(page, propertyBalanceDetail);
	}

	@Transactional(readOnly = false)
	public void save(PropertyBalanceDetail propertyBalanceDetail) {
		super.save(propertyBalanceDetail);
	}

	@Transactional(readOnly = false)
	public void delete(PropertyBalanceDetail propertyBalanceDetail) {
		super.delete(propertyBalanceDetail);
	}
	
	@Transactional(readOnly = false)
	public void updateCheckState(PropertyBalanceDetail pbd) {
		dao.updateCheckState(pbd);

	}

	public List<PropertyBalanceDetail> findListByBalanceCycle(PropertyBalanceDetail propertyBalanceDetail) {
		List<PropertyBalanceDetail> propertyBalanceDetailList = new ArrayList<PropertyBalanceDetail>();

		propertyBalanceDetailList = dao.findListByBalanceCycle(propertyBalanceDetail);

		return propertyBalanceDetailList;
	}

	public List<PropertyBalanceDetail> findAllList(PropertyBalanceDetail propertyBalanceDetail) {
		return dao.findAllList(propertyBalanceDetail);
	}

	public List<PropertyBalanceDetail> findExportList(PropertyBalanceDetail propertyBalanceDetail) {
		return dao.findExportList(propertyBalanceDetail);
	}

}