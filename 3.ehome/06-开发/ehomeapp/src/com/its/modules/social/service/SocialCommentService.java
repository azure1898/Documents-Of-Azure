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
import com.its.modules.social.bean.SocialCommentBean;
import com.its.modules.social.dao.SocialCommentDao;
import com.its.modules.social.entity.SocialComment;

/**
 * 评论Service
 * @author wmm
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = true)
public class SocialCommentService extends CrudService<SocialCommentDao, SocialComment> {
	
	@Autowired
	private SocialCommentDao socialCommentDao;

	public SocialComment get(String id) {
		return super.get(id);
	}
	
	public List<SocialComment> findList(SocialComment socialComment) {
		return super.findList(socialComment);
	}
	
	public Page<SocialComment> findPage(Page<SocialComment> page, SocialComment socialComment) {
		return super.findPage(page, socialComment);
	}
	
	@Transactional(readOnly = false)
	public void save(SocialComment socialComment) {
		super.save(socialComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(SocialComment socialComment) {
		super.delete(socialComment);
	}
	
	/**
	 * @Description：根据发言ID获取评论bean集合
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param userId 用户ID
	 * @param socialComment 查询参数
	 * @param pageIndex 分页页码(从0开始为起始页)
	 * @param pageSize
	 * @return
	 */
	public List<SocialCommentBean> findCommentBeanList(String userId, SocialComment socialComment, int pageIndex, int pageSize){
		return socialCommentDao.findCommentBeanList(userId, socialComment, pageIndex, pageSize);
	}
	
	/**
	 * @Description：根据发言id获取一级评论个数
	 * @Author：刘浩浩
	 * @Date：2017年8月4日
	 * @param speakId
	 * @return
	 */
	public int getSecCmtCount(String commentId){
		return socialCommentDao.getSecCmtCount(commentId);
	}
}