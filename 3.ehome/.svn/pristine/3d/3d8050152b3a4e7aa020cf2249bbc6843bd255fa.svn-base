/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 点赞Entity
 * @author 刘浩浩
 * @version 2017-08-04
 */
public class SocialPraise extends DataEntity<SocialPraise> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 点赞人
	private Date praisetime;		// 点赞时间
	private String pid;		// 存id
	private int type;		// 发言：1； 评论：2 ； 转发：3。
	private String touserid;		// 被赞人
	private int state;		// 状态 1：已赞 0：取消赞
	
	public SocialPraise() {
		super();
	}

	public SocialPraise(String id){
		super(id);
	}

	@Length(min=0, max=64, message="点赞人长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPraisetime() {
		return praisetime;
	}

	public void setPraisetime(Date praisetime) {
		this.praisetime = praisetime;
	}
	
	@Length(min=0, max=64, message="存id长度必须介于 0 和 64 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Length(min=0, max=2, message="发言：1； 评论：2 ； 转发：3。长度必须介于 0 和 2 之间")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="被赞人长度必须介于 0 和 64 之间")
	public String getTouserid() {
		return touserid;
	}

	public void setTouserid(String touserid) {
		this.touserid = touserid;
	}
	
	@Length(min=0, max=11, message="状态 1：已赞 0：取消赞长度必须介于 0 和 11 之间")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}