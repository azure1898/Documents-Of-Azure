/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.social.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * socialEntity
 * @author 刘浩浩
 * @version 2017-08-07
 */
public class SocialRelation extends DataEntity<SocialRelation> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户ID
	private String subuserid;		// 从用户id
	private Date createtime;		// 关注时间
	private int isblack;		// 是否屏蔽，1：是；0：否
	
	public SocialRelation() {
		super();
	}

	public SocialRelation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户ID长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="从用户id长度必须介于 0 和 64 之间")
	public String getSubuserid() {
		return subuserid;
	}

	public void setSubuserid(String subuserid) {
		this.subuserid = subuserid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=11, message="是否屏蔽，1：是；0：否长度必须介于 0 和 11 之间")
	public int getIsblack() {
		return isblack;
	}

	public void setIsblack(int isblack) {
		this.isblack = isblack;
	}
	
}