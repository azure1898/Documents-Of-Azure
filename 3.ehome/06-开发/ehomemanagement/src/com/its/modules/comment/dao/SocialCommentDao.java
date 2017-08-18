/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.comment.dao;

import java.util.List;

import com.its.common.persistence.CrudDao;
import com.its.common.persistence.annotation.MyBatisDao;
import com.its.modules.comment.entity.SocialComment;

/**
 * 评论DAO接口
 * @author wmm
 * @version 2017-08-03
 */
@MyBatisDao
public interface SocialCommentDao extends CrudDao<SocialComment> {
	
	/**
	 * 通过发言ID查询评论列表
	 * @param socialComment
	 * @return
	 */
	public List<SocialComment> findBySpeakId(SocialComment socialComment);
	
	/**
	 * 软删除评论
	 * @param socialComment
	 * @return
	 */
	public int changeDelFlag(SocialComment socialComment);
	
	/**
	 * 通过ID查询子评论
	 * @param socialComment
	 * @return
	 */
	public List<SocialComment> findChildComment(SocialComment socialComment);
	
}