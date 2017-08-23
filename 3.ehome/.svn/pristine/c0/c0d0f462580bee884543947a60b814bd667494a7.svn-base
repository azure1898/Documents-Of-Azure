/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.subrelation.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 发言和话题关联Entity
 * @author wmm
 * @version 2017-08-02
 */
public class SocialSubRelation extends DataEntity<SocialSubRelation> {
	
	private static final long serialVersionUID = 1L;
	private String subjectid;		// 话题id
	private String speakid;		// 发言id
	private String commentid;		// 评论id
	
	public SocialSubRelation() {
		super();
	}

	public SocialSubRelation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="话题id长度必须介于 0 和 64 之间")
	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	
	@Length(min=0, max=64, message="发言id长度必须介于 0 和 64 之间")
	public String getSpeakid() {
		return speakid;
	}

	public void setSpeakid(String speakid) {
		this.speakid = speakid;
	}
	
	@Length(min=0, max=64, message="评论id长度必须介于 0 和 64 之间")
	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	
}