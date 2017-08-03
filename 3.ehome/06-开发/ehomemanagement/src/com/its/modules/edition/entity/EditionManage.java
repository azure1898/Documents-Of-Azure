/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.edition.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.its.common.persistence.DataEntity;

/**
 * 版本管理Entity
 * @author ChenXiangyu
 * @version 2017-06-29
 */
public class EditionManage extends DataEntity<EditionManage> {
	
	private static final long serialVersionUID = 1L;
	private String productType;      // 产品类型（产品线+系统类型）
	private String productLine;		// 产品线
	private String systemType;		// 系统类型
	private String editionName;		// 版本名称
	private String editionNo;		// 版本号
	private String editionInstruction;		// 版本说明
	private String fileUrl;		// 文件上传路径
	private String fileSize;		// 文件大小
	private Date createDateStart;     // 发布时期范围开始
	private Date createDateEnd;      // 发布时期范围结束
	
	public EditionManage() {
		super();
	}

	public EditionManage(String id){
		super(id);
	}

	/**
	 * 取得产品类型（产品线+系统类型）
	 * @return 产品类型
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * 设置产品类型
	 * @param productType 产品类型（产品线+系统类型）
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Length(min=1, max=1, message="产品线长度必须介于 1 和 1 之间")
	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	
	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	
	@Length(min=1, max=32, message="版本名称长度必须介于 1 和 32 之间")
	public String getEditionName() {
		return editionName;
	}

	public void setEditionName(String editionName) {
		this.editionName = editionName;
	}
	
	@Length(min=1, max=16, message="版本号长度必须介于 1 和 16 之间")
	public String getEditionNo() {
		return editionNo;
	}

	public void setEditionNo(String editionNo) {
		this.editionNo = editionNo;
	}
	
	@Length(min=0, max=2000, message="版本说明长度必须介于 0 和 2000 之间")
	public String getEditionInstruction() {
		return editionInstruction;
	}

	public void setEditionInstruction(String editionInstruction) {
		this.editionInstruction = editionInstruction;
	}
	
	@Length(min=1, max=64, message="上传软件长度必须介于 1 和 64 之间")
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	@Length(min=0, max=16, message="文件大小长度必须介于 0 和 16 之间")
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	
}