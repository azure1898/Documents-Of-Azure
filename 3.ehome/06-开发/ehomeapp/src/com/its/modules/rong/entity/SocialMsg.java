/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.rong.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.common.persistence.DataEntity;

/**
 * 消息表Entity
 * @author 刘浩浩
 * @version 2017-08-11
 */
public class SocialMsg extends DataEntity<SocialMsg> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 发消息的用户id
	private String username;		// 发送消息用户名称
	private String touserid;		// 接受消息用户ID
	private String content;		// 消息内容
	private int isnotice;		// 是否通知 1:已经通知 0 ：未通知
	private Date noticetime;		// 通知时间
	private int firtype;		// 1：订单消息2：管理员消息：3：邻里圈消息
	private int sectype;		// 2.1:配送消息2.2：取消订单 2.3：临期提醒2.4：团购已消费 2.5：团购退款  3.1：公告通知 3.2：管理员发言  4.1：@我的消息 4.2：评论我的消息 4.3：关注我的消息
	private int isread;		// 是否已读 1：已读 0：未读
	private String remark;		// 备注
	
	public SocialMsg() {
		super();
	}

	public SocialMsg(String id){
		super(id);
	}

	@Length(min=0, max=64, message="发消息的用户id长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="发送消息用户名称长度必须介于 0 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=64, message="接受消息用户ID长度必须介于 0 和 64 之间")
	public String getTouserid() {
		return touserid;
	}

	public void setTouserid(String touserid) {
		this.touserid = touserid;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=11, message="是否通知 1:已经通知 0 ：未通知长度必须介于 0 和 11 之间")
	public int getIsnotice() {
		return isnotice;
	}

	public void setIsnotice(int isnotice) {
		this.isnotice = isnotice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNoticetime() {
		return noticetime;
	}

	public void setNoticetime(Date noticetime) {
		this.noticetime = noticetime;
	}
	
	@Length(min=0, max=11, message="1：订单消息2：管理员消息：3：邻里圈消息长度必须介于 0 和 11 之间")
	public int getFirtype() {
		return firtype;
	}

	public void setFirtype(int firtype) {
		this.firtype = firtype;
	}
	
	@Length(min=0, max=11, message="1:配送消息2：取消订单3：临期提醒4：团购已消费 5：团购退款  11：公告通知 12：管理员发言  21：@我的消息 22：评论我的消息 23：关注我的消息长度必须介于 0 和 11 之间")
	public int getSectype() {
		return sectype;
	}

	public void setSectype(int sectype) {
		this.sectype = sectype;
	}
	
	@Length(min=0, max=11, message="是否已读 1：已读 0：未读长度必须介于 0 和 11 之间")
	public int getIsread() {
		return isread;
	}

	public void setIsread(int isread) {
		this.isread = isread;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}