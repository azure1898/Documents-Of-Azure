/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.company.entity.CompanyInfo;
import com.its.modules.company.dao.CompanyInfoDao;
import com.its.modules.module.entity.ModuleManage;

/**
 * 开发商公司信息Service
 * @author ChenXiangyu
 * @version 2017-07-11
 */
@Service
@Transactional(readOnly = true)
public class CompanyInfoService extends CrudService<CompanyInfoDao, CompanyInfo> {
	
	@Autowired
	private CompanyInfoDao companyInfoDao;

	public CompanyInfo get(String id) {
		return super.get(id);
	}
	
	public List<CompanyInfo> findList(CompanyInfo companyInfo) {
		return super.findList(companyInfo);
	}
	
	public Page<CompanyInfo> findPage(Page<CompanyInfo> page, CompanyInfo companyInfo) {
		return super.findPage(page, companyInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(CompanyInfo companyInfo) {
		super.save(companyInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CompanyInfo companyInfo) {
		super.delete(companyInfo);
	}
	
	 /**
     * 查询全部数据
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午7:39:39
     * @return List<AdverPosition>
     */
    public List<CompanyInfo> findAllList() {
        return companyInfoDao.findAllList(new CompanyInfo());
    }
    
}