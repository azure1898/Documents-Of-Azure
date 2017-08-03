/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.notice.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.its.common.persistence.DataEntity;

/**
 * 社区公告Entity
 * @author ChenXiangyu
 * @version 2017-07-18
 */
public class NoticeManage extends DataEntity<NoticeManage> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 楼盘信息ID
	private String addrPro; // 地址_省
	private String addrCity; // 地址_市
	private String noticeTitle;		// 公告标题
	private String noticeAbstract;		// 摘要
	private String noticeContent;		// 内容
	private String syncCircleFlag;		// 是否同步到邻里圈
	private Date beginCreateDate;		// 开始 录入时间
	private Date endCreateDate;		// 结束 录入时间
	
	public NoticeManage() {
		super();
	}

	public NoticeManage(String id){
		super(id);
	}

	@NotBlank(message = "请选择楼盘")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	public String getAddrPro() {
		return addrPro;
	}

	public void setAddrPro(String addrPro) {
		this.addrPro = addrPro;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	@Length(min=1, max=64, message="公告标题长度必须介于 1 和 64 之间")
	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	@Length(min=1, max=200, message="摘要长度必须介于 1 和 200 之间")
	public String getNoticeAbstract() {
		return noticeAbstract;
	}

	public void setNoticeAbstract(String noticeAbstract) {
		this.noticeAbstract = noticeAbstract;
	}
	
	@Length(min=1, max=65535, message="摘要长度必须介于 1 和 65535 之间")
	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	public String getSyncCircleFlag() {
		return syncCircleFlag;
	}

	public void setSyncCircleFlag(String syncCircleFlag) {
		this.syncCircleFlag = syncCircleFlag;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}