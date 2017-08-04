/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 模块管理Entity
 * @author sushipeng
 * @version 2017-08-03
 */
public class ModuleManage extends DataEntity<ModuleManage> {
	
	private static final long serialVersionUID = 1L;
	private String moduleName;		// 模块名称
	private String moduleType;		// 模块类别：0主导航  1社区  2生活
	private String moduleUrl;		// 模块路径
	private String moduleIcon;		// 模块图标
	private Integer sortNum;		// 排序序号
	private String businessCategoryDictFlag;		// 是否来源于商户分类
	private String businessCategoryDictId;		// 商户分类字典表ID
	private String phoneModuleCode;		// 手机端模块编码
	
	public ModuleManage() {
		super();
	}

	public ModuleManage(String id){
		super(id);
	}

	@Length(min=0, max=64, message="模块名称长度必须介于 0 和 64 之间")
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@Length(min=0, max=64, message="模块类别：0主导航  1社区  2生活长度必须介于 0 和 64 之间")
	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	@Length(min=0, max=200, message="模块路径长度必须介于 0 和 200 之间")
	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	
	@Length(min=0, max=64, message="模块图标长度必须介于 0 和 64 之间")
	public String getModuleIcon() {
		return moduleIcon;
	}

	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Length(min=0, max=1, message="是否来源于商户分类长度必须介于 0 和 1 之间")
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
	
	@Length(min=0, max=2, message="手机端模块编码长度必须介于 0 和 2 之间")
	public String getPhoneModuleCode() {
		return phoneModuleCode;
	}

	public void setPhoneModuleCode(String phoneModuleCode) {
		this.phoneModuleCode = phoneModuleCode;
	}
	
}