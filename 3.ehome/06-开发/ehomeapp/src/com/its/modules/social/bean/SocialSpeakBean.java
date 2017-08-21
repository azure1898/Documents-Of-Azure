package com.its.modules.social.bean;

import com.its.modules.social.entity.SocialSpeak;

/**
 * @Description：查询结果封装bean
 * @Author：刘浩浩
 * @Date：2017年8月4日
 */
public class SocialSpeakBean extends SocialSpeak {

	private static final long serialVersionUID = 1L;
	
	private int isFocus;//是否关注 1：是 0：否
	
	private int isPraise;//是否点赞  1:是 0:否
	
	private int countForward;//转发数量
	
	private int countComment;// 评论数量
	
	private int countPraise;//点赞数量
	
	private String nickName;//昵称
	
	private String photo;//头像
	
	private String aid;//account id

	public int getIsFocus() {
		return isFocus;
	}

	public void setIsFocus(int isFocus) {
		this.isFocus = isFocus;
	}

	public int getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(int isPraise) {
		this.isPraise = isPraise;
	}

	public int getCountForward() {
		return countForward;
	}

	public void setCountForward(int countForward) {
		this.countForward = countForward;
	}

	public int getCountComment() {
		return countComment;
	}

	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}

	public int getCountPraise() {
		return countPraise;
	}

	public void setCountPraise(int countPraise) {
		this.countPraise = countPraise;
	}

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

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}
	
	
}