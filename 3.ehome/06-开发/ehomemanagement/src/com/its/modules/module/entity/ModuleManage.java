/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.its.common.persistence.DataEntity;

/**
 * 模块管理Entity
 * @author ChenXiangyu
 * @version 2017-06-26
 */
public class ModuleManage extends DataEntity<ModuleManage> {
	
	private static final long serialVersionUID = 1L;
	private String moduleName;		// 模块名称
	private String moduleType;		// 模块类别
	private String moduleUrl;		// 模块路径
	private String moduleIcon;		// 模块图标
	private String sortNum;		// 排序序号
	private String businessCategoryDictFlag;		// 是否来源于商家分类
	private String businessCategoryDictId;		// 商户分类字典表ID
	
	public ModuleManage() {
		super();
	}

	public ModuleManage(String id){
		super(id);
	}

	@NotBlank(message="请输入模块名称")
	@Length(min=1, max=64, message="模块名称长度必须介于 1 和 64 之间")
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@NotBlank(message="请选择模块类别")
	@Length(min=1, max=64, message="模块类别长度必须介于 1 和 64 之间")
	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	@Length(min=0, max=10, message="模块路径长度必须介于 0 和 10 之间")
	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	
	public String getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	
	@Length(min=0, max=11, message="排序序号长度必须介于 0 和 11 之间")
	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	
	@Length(min=0, max=1, message="是否来源于商家分类长度必须介于 0 和 1 之间")
	public String getBusinessCategoryDictFlag() {
		return businessCategoryDictFlag;
	}

	public void setBusinessCategoryDictFlag(String businessCategoryDictFlag) {
		this.businessCategoryDictFlag = businessCategoryDictFlag;
	}
	
	@Length(min=0, max=64, message="商户分类字典表ID长度必须介于 0 和 64 之间")
	public String getBusinessCategoryDictId() {
		return businessCategoryDictId;
	}

	public void setBusinessCategoryDictId(String businessCategoryDictId) {
		this.businessCategoryDictId = businessCategoryDictId;
	}
	
}