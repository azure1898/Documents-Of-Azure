/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.forward.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.forward.entity.SocialForward;
import com.its.modules.forward.dao.SocialForwardDao;

/**
 * 转发Service
 * @author wmm
 * @version 2017-08-03
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
	
	@Transactional(readOnly = false)
	public List<SocialForward> findBySpeakId(SocialForward socialForward) {
		return socialForwardDao.findBySpeakId(socialForward);
	}
	
	@Transactional(readOnly = false)
	public int changeDelFlag(SocialForward socialForward) {
		return socialForwardDao.changeDelFlag(socialForward);
	}
	
}