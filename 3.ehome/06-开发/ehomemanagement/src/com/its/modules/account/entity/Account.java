/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.account.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.its.common.persistence.DataEntity;

/**
 * 用户注册Entity
 * @author ChenXiangyu
 * @version 2017-07-04
 */
public class Account extends DataEntity<Account> {
	
	private static final long serialVersionUID = 1L;
	private String villageInfoId;		// 用户浏览过的最后一个楼盘的ID
	private String phoneNum;		// 账号/手机号
	private String nickname;		// 昵称
	private String pwd;		// 密码
	private String photo;		// 头像
	private String sex;		// 性别
	private String height;		// 身高
	private String birthYear;		// 出生年份
	private String weight;		// 体重
	private String terminalSource;		// 终端来源
	private String certifyState;		// 认证状态
	private Date registDate;		// 注册时间
	private String bindBraceletFlag;		// 是否配置手环
	private String braceletName;		// 手环名称
	private Double walletBalance;		// 钱包余额
	private String braceletNum;		// 手环序列号
	private Double walletPrincipal;		// 钱包本金
	private String faceRecognitionFlag;		// 人脸识别采集状态
	private Double walletPresent;		// 钱包赠送
	private String useState;		// 使用状态
	private Date beginRegistDate;		// 开始 注册时间
	private Date endRegistDate;		// 结束 注册时间
	
	// add by Liuqi,2017-07-10
	private Date lastLoginTime; //上次登录时间
	private Date beginLastLoginTime; // 开始 上次登录时间
	private Date endLastLoginTime; // 结束 上次登录时间
	private String unOrder;// 未下单，为空，则是下单会员
	private Date beginPayTime;// 开始 支付时间 对应 订单表（五张）中的pay_time 
	private Date endPayTime; // 结束 支付时间 对应 订单表（五张）中的pay_time 
	private List<String> moduleManageIds; // 模块id集合
	
	public Account() {
		super();
	}

	public Account(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户浏览过的最后一个楼盘的ID长度必须介于 0 和 64 之间")
	public String getVillageInfoId() {
		return villageInfoId;
	}

	public void setVillageInfoId(String villageInfoId) {
		this.villageInfoId = villageInfoId;
	}
	
	@Length(min=0, max=11, message="账号/手机号长度必须介于 0 和 11 之间")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	@Length(min=0, max=64, message="昵称长度必须介于 0 和 64 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=10, message="密码长度必须介于 0 和 10 之间")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Length(min=0, max=200, message="头像长度必须介于 0 和 200 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="身高长度必须介于 0 和 64 之间")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	@Length(min=0, max=64, message="出生年份长度必须介于 0 和 64 之间")
	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	
	@Length(min=0, max=64, message="体重长度必须介于 0 和 64 之间")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=1, message="终端来源长度必须介于 0 和 1 之间")
	public String getTerminalSource() {
		return terminalSource;
	}

	public void setTerminalSource(String terminalSource) {
		this.terminalSource = terminalSource;
	}
	
	@Length(min=0, max=1, message="认证状态长度必须介于 0 和 1 之间")
	public String getCertifyState() {
		return certifyState;
	}

	public void setCertifyState(String certifyState) {
		this.certifyState = certifyState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	@Length(min=0, max=1, message="是否配置手环长度必须介于 0 和 1 之间")
	public String getBindBraceletFlag() {
		return bindBraceletFlag;
	}

	public void setBindBraceletFlag(String bindBraceletFlag) {
		this.bindBraceletFlag = bindBraceletFlag;
	}
	
	@Length(min=0, max=64, message="手环名称长度必须介于 0 和 64 之间")
	public String getBraceletName() {
		return braceletName;
	}

	public void setBraceletName(String braceletName) {
		this.braceletName = braceletName;
	}
	
	public Double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}
	
	@Length(min=0, max=64, message="手环序列号长度必须介于 0 和 64 之间")
	public String getBraceletNum() {
		return braceletNum;
	}

	public void setBraceletNum(String braceletNum) {
		this.braceletNum = braceletNum;
	}
	
	public Double getWalletPrincipal() {
		return walletPrincipal;
	}

	public void setWalletPrincipal(Double walletPrincipal) {
		this.walletPrincipal = walletPrincipal;
	}
	
	@Length(min=0, max=1, message="人脸识别采集状态长度必须介于 0 和 1 之间")
	public String getFaceRecognitionFlag() {
		return faceRecognitionFlag;
	}

	public void setFaceRecognitionFlag(String faceRecognitionFlag) {
		this.faceRecognitionFlag = faceRecognitionFlag;
	}
	
	public Double getWalletPresent() {
		return walletPresent;
	}

	public void setWalletPresent(Double walletPresent) {
		this.walletPresent = walletPresent;
	}
	
	@Length(min=0, max=1, message="使用状态长度必须介于 0 和 1 之间")
	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}
	
	public Date getBeginRegistDate() {
		return beginRegistDate;
	}

	public void setBeginRegistDate(Date beginRegistDate) {
		this.beginRegistDate = beginRegistDate;
	}
	
	public Date getEndRegistDate() {
		return endRegistDate;
	}

	public void setEndRegistDate(Date endRegistDate) {
		this.endRegistDate = endRegistDate;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getBeginLastLoginTime() {
		return beginLastLoginTime;
	}

	public void setBeginLastLoginTime(Date beginLastLoginTime) {
		this.beginLastLoginTime = beginLastLoginTime;
	}

	public Date getEndLastLoginTime() {
		return endLastLoginTime;
	}

	public void setEndLastLoginTime(Date endLastLoginTime) {
		this.endLastLoginTime = endLastLoginTime;
	}

	public String getUnOrder() {
		return unOrder;
	}

	public void setUnOrder(String unOrder) {
		this.unOrder = unOrder;
	}

	public Date getBeginPayTime() {
		return beginPayTime;
	}

	public void setBeginPayTime(Date beginPayTime) {
		this.beginPayTime = beginPayTime;
	}

	public Date getEndPayTime() {
		return endPayTime;
	}

	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}

	public List<String> getModuleManageIds() {
		return moduleManageIds;
	}

	public void setModuleManageIds(List<String> moduleManageIds) {
		this.moduleManageIds = moduleManageIds;
	}
		
}