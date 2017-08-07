package com.its.modules.social.bean;

import com.its.modules.social.entity.SocialForward;

/**
 * @Description：查询结果封装bean
 * @Author：刘浩浩
 * @Date：2017年8月4日
 */
public class SocialForwardBean extends SocialForward {

	private static final long serialVersionUID = 1L;
	
	private String nickName;//昵称
	
	private String photo;//头像

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}