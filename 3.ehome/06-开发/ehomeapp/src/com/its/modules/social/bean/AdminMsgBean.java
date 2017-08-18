package com.its.modules.social.bean;

import com.its.modules.app.entity.NoticeManage;


/**
 * @Description：查询结果封装bean
 * @Author：邵德才
 * @Date：2017年8月15日
 */
public class AdminMsgBean extends NoticeManage {

	private static final long serialVersionUID = 1L;
	private String photo;		// 头像 
	private String nickname;		// 昵称
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}