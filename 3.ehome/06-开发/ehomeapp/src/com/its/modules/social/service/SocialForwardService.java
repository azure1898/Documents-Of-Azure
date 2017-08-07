/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.social.bean.SocialForwardBean;
import com.its.modules.social.dao.SocialForwardDao;
import com.its.modules.social.entity.SocialForward;

/**
 * 转发Service
 * @author wmm
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = true)
public class SocialForwardService extends CrudService<SocialForwardDao, SocialForward> {
	
	@Autowired
	private SocialForwardDao socialForwardDao;

	public SocialForward get(String id) {
		return super.get(id);
	}
	
	public List<SocialForward> findList(SocialForward socialForward) {
		return super.findList(socialForward);
	}
	
	public Page<SocialForward> findPage(Page<SocialForward> page, SocialForward socialForward) {
		return super.findPage(page, socialForward);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialForward socialForward) {
		super.save(socialForward);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialForward socialForward) {
		super.delete(socialForward);
	}
	
	/**
	 * @Description：根据发言ID获取转发bean集合
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param socialForward 查询参数
	 * @param pageIndex 分页页码(从0开始为起始页)
	 * @param pageSize
	 * @return
	 */
	public List<SocialForwardBean> findForwardBeanList(SocialForward socialForward, int pageIndex, int pageSize){
		return socialForwardDao.findForwardBeanList(socialForward, pageIndex, pageSize);
	}
	
}