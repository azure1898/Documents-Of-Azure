/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.comment.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 评论Entity
 * @author wmm
 * @version 2017-08-03
 */
public class SocialComment extends DataEntity<SocialComment> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 评论/回复人
	private Date createtime;		// 评论/回复时间
	private String content;		// 评论/回复内容
	private int iscomment;		// 是否评论 1：是 0 ：否
	private String fid;		// 回复评论的id， social_comment.id
	private String speakid;		// 发言id social_speak.id
	private String subjectid;		// 话题id
	private int delflag;		// 删除标识0：删除 1：正常
	
	private String username;  //评论/回复人姓名
	private String replyname;  //被评论/回复人姓名
	
	public SocialComment() {
		super();
	}

	public SocialComment(String id){
		super(id);
	}

	@Length(min=0, max=64, message="评论/回复人长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=11, message="是否评论 1：是 0 ：否长度必须介于 0 和 11 之间")
	public int getIscomment() {
		return iscomment;
	}

	public void setIscomment(int iscomment) {
		this.iscomment = iscomment;
	}
	
	@Length(min=0, max=64, message="fid长度必须介于 0 和 64 之间")
	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
	
	@Length(min=0, max=64, message="speakid长度必须介于 0 和 64 之间")
	public String getSpeakid() {
		return speakid;
	}

	public void setSpeakid(String speakid) {
		this.speakid = speakid;
	}
	
	@Length(min=0, max=64, message="话题id长度必须介于 0 和 64 之间")
	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	
	@Length(min=0, max=11, message="删除标识0：删除 1：正常长度必须介于 0 和 11 之间")
	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReplyname() {
		return replyname;
	}

	public void setReplyname(String replyname) {
		this.replyname = replyname;
	}
	
}