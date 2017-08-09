/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.rong.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.common.persistence.DataEntity;

/**
 * rongEntity
 * @author 刘浩浩
 * @version 2017-08-09
 */
public class ActToken extends DataEntity<ActToken> {
	
	private static final long serialVersionUID = 1L;
	private String accountid;		// 用户信息表ID
	private String token;		// token值
	private Date tokencreatetime;		// token创建时间
	private Date createtime;		// 本条记录生成时间
	
	public ActToken() {
		super();
	}

	public ActToken(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户信息表ID长度必须介于 0 和 64 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=255, message="token值长度必须介于 0 和 255 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTokencreatetime() {
		return tokencreatetime;
	}

	public void setTokencreatetime(Date tokencreatetime) {
		this.tokencreatetime = tokencreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}