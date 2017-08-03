/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 最大编码表Entity
 * @author xzc
 * @version 2017-07-11
 */
public class SysCodeMax extends DataEntity<SysCodeMax> {
	
	private static final long serialVersionUID = 1L;
	private String codeName;		// 编码名称
	private Date codeDate;		// 最大编码日期
	private String codeValue;		// 最大编码值
	
	public SysCodeMax() {
		super();
	}

	public SysCodeMax(String id){
		super(id);
	}

	@Length(min=0, max=64, message="编码名称长度必须介于 0 和 64 之间")
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCodeDate() {
		return codeDate;
	}

	public void setCodeDate(Date codeDate) {
		this.codeDate = codeDate;
	}
	
	@Length(min=0, max=11, message="最大编码值长度必须介于 0 和 11 之间")
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	
}