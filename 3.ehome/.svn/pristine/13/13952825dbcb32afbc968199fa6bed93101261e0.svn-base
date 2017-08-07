/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.forward.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 转发Entity
 * @author wmm
 * @version 2017-08-03
 */
public class SocialForward extends DataEntity<SocialForward> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 发言人，用户ID
	private String subjectid;		// 话题ID
	private String tag;		// 标签
	private String reason;		// 转发原因
	private String visrange;		// 可见范围 1：公开 2：好友可见； 3：粉丝可见
	private String forbitcomment;		// 是否禁止评论 1：是；0：否
	private String forbidforward;		// 是否禁止转发 1：是； 0：否
	private Date createtime;		// 发布时间
	private String readnum;		// 阅读次数
	private String speakid;		// 转发发言的ID，tb_speak.id
	private String delflag;		// 删除标识0：已删除 1：正常
	
	private String username;  //发言人，用户姓名
	
	public SocialForward() {
		super();
	}

	public SocialForward(String id){
		super(id);
	}

	@Length(min=0, max=64, message="发言人，用户ID长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="话题ID长度必须介于 0 和 64 之间")
	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	
	@Length(min=0, max=200, message="标签长度必须介于 0 和 200 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=11, message="可见范围 1：公开 2：好友可见； 3：粉丝可见长度必须介于 0 和 11 之间")
	public String getVisrange() {
		return visrange;
	}

	public void setVisrange(String visrange) {
		this.visrange = visrange;
	}
	
	@Length(min=0, max=11, message="是否禁止评论 1：是；0：否长度必须介于 0 和 11 之间")
	public String getForbitcomment() {
		return forbitcomment;
	}

	public void setForbitcomment(String forbitcomment) {
		this.forbitcomment = forbitcomment;
	}
	
	@Length(min=0, max=11, message="是否禁止转发 1：是； 0：否长度必须介于 0 和 11 之间")
	public String getForbidforward() {
		return forbidforward;
	}

	public void setForbidforward(String forbidforward) {
		this.forbidforward = forbidforward;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=64, message="阅读次数长度必须介于 0 和 64 之间")
	public String getReadnum() {
		return readnum;
	}

	public void setReadnum(String readnum) {
		this.readnum = readnum;
	}
	
	@Length(min=0, max=64, message="speakid长度必须介于 0 和 64 之间")
	public String getSpeakid() {
		return speakid;
	}

	public void setSpeakid(String speakid) {
		this.speakid = speakid;
	}
	
	@Length(min=0, max=11, message="删除标识0：已删除 1：正常长度必须介于 0 和 11 之间")
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}