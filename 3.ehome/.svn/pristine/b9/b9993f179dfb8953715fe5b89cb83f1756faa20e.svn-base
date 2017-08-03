/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.Feedback;
import com.its.modules.app.dao.FeedbackDao;

/**
 * 意见反馈Service
 * @author like
 * @version 2017-07-17
 */
@Service
@Transactional(readOnly = true)
public class FeedbackService extends CrudService<FeedbackDao, Feedback> {

	public Feedback get(String id) {
		return super.get(id);
	}
	
	public List<Feedback> findList(Feedback feedback) {
		return super.findList(feedback);
	}
	
	public Page<Feedback> findPage(Page<Feedback> page, Feedback feedback) {
		return super.findPage(page, feedback);
	}
	
	@Transactional(readOnly = false)
	public void save(Feedback feedback) {
		super.save(feedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(Feedback feedback) {
		super.delete(feedback);
	}
	
}