/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 区域管理Entity
 * @author like
 * @version 2017-07-03
 */
public class SysArea extends DataEntity<SysArea> {
	
	private static final long serialVersionUID = 1L;
	private SysArea parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private String sort;		// 排序
	private String code;		// 区域编码
	private String type;		// 区域类型
	private String isOpen;		// 是否已开通
	
	public SysArea() {
		super();
	}

	public SysArea(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public SysArea getParent() {
		return parent;
	}

	public void setParent(SysArea parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=100, message="区域编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=1, message="区域类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=1, message="是否已开通长度必须介于 1 和 1 之间")
	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	
}