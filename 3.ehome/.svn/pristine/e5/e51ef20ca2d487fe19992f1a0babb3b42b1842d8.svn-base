/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 短信验证码发送记录Entity
 * @author like
 * @version 2017-07-21
 */
public class VerifyCodeRecord extends DataEntity<VerifyCodeRecord> {
	
	private static final long serialVersionUID = 1L;
	private String phoneNum;		// 手机号码
	private String code;		// 验证码
	private Date sendTime;		// 发送时间
	private String codeType;		// 验证码类型：1快速登录；2注册；3忘记密码
	
	public VerifyCodeRecord() {
		super();
	}

	public VerifyCodeRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="手机号码长度必须介于 0 和 64 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=64, message="验证码长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@Length(min=0, max=1, message="验证码类型：1快速登录；2注册；3忘记密码长度必须介于 0 和 1 之间")
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
}