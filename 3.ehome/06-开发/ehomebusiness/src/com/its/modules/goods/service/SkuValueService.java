/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.goods.entity.SkuValue;
import com.its.modules.goods.dao.SkuValueDao;

/**
 * 商品规格信息Service
 * @author liuhl
 * @version 2017-07-04
 */
@Service
@Transactional(readOnly = true)
public class SkuValueService extends CrudService<SkuValueDao, SkuValue> {

	public SkuValue get(String id) {
		return super.get(id);
	}
	
	public List<SkuValue> findList(SkuValue skuValue) {
		return super.findList(skuValue);
	}
	
	public Page<SkuValue> findPage(Page<SkuValue> page, SkuValue skuValue) {
		return super.findPage(page, skuValue);
	}
	
	@Transactional(readOnly = false)
	public void save(SkuValue skuValue) {
		super.save(skuValue);
	}
	
	@Transactional(readOnly = false)
	public void delete(SkuValue skuValue) {
		super.delete(skuValue);
	}
	
}