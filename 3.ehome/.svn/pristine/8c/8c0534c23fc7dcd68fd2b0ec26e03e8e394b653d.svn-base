package com.its.modules.app.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 访客邀请Entity
 * 
 * @author sushipeng
 * 
 * @version 2017-08-07
 */
public class VisitorInvite extends DataEntity<VisitorInvite> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// 会员ID
	private String villageInfoId;		// 楼盘ID
	private String apartmentCertifyId;		// 房间认证ID
	private String visitorName;		// 访客姓名
	private String sex;		// 性别：0-男；1-女
	private Date visitDate;		// 来访时间
	private String qrCode;		// 二维码
	private String inviteState;		// 邀请状态：0-正常；1-已作废；2-已过期
	private Date invalidDate;		// 失效时间
	
	public VisitorInvite() {
		super();
	}

	public VisitorInvite(String id){
		super(id);
	}

	@Length(min=0, max=64, message="会员ID长度必须介于 0 和 64 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=64, message="楼盘ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=64, message="房间认证ID长度必须介于 0 和 64 之间")
	public String getApartmentCertifyId() {
		return apartmentCertifyId;
	}

	public void setApartmentCertifyId(String apartmentCertifyId) {
		this.apartmentCertifyId = apartmentCertifyId;
	}
	
	@Length(min=0, max=64, message="访客姓名长度必须介于 0 和 64 之间")
	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	
	@Length(min=0, max=1, message="性别：0-男；1-女长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	@Length(min=0, max=255, message="二维码长度必须介于 0 和 255 之间")
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	
	@Length(min=0, max=1, message="邀请状态：0-正常；1-已作废；2-已过期长度必须介于 0 和 1 之间")
	public String getInviteState() {
		return inviteState;
	}

	public void setInviteState(String inviteState) {
		this.inviteState = inviteState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}
	
}