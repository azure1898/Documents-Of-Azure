package com.its.modules.service.entity;

/**
 * 商品图片信息Entity
 * @author test
 * @version 2017-07-04
 */
public class ServiceInfoPic {
	private String imgBase64;		//BASE64编码格式的图片
	private String type;		// 图片格式
	
	public String getImgBase64() {
		return imgBase64;
	}
	public void setImgBase64(String imgBase64) {
		this.imgBase64 = imgBase64;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
