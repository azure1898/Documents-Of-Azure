/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.entity.FamilyInfo;

import net.sf.json.JSONObject;

import com.its.modules.app.dao.FamilyInfoDao;

/**
 * 家属成员信息Service
 * @author like
 * @version 2017-07-21
 */
@Service
@Transactional(readOnly = true)
public class FamilyInfoService extends CrudService<FamilyInfoDao, FamilyInfo> {

	public FamilyInfo get(String id) {
		return super.get(id);
	}
	
	public List<FamilyInfo> findList(FamilyInfo familyInfo) {
		return super.findList(familyInfo);
	}
	
	public Page<FamilyInfo> findPage(Page<FamilyInfo> page, FamilyInfo familyInfo) {
		return super.findPage(page, familyInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(FamilyInfo familyInfo) {
		super.save(familyInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(FamilyInfo familyInfo) {
		super.delete(familyInfo);
	}
	
	/**
	 * 获取房间的家属集合
	 * @param roomId
	 * @return
	 */
	public List<FamilyInfo> getRoomFamilies(String roomId){
		return dao.getRoomFamilies(roomId);
	}
	
	/**
	 * 添加家属信息是，远程提交
	 * @param contactNumber	家属电话号码
	 * @param name	客户姓名
	 * @param customerId	客户id
	 * @param roomId	房间id
	 * @throws HttpException
	 * @throws IOException
	 */
	public void submitRemoteFamily(String contactNumber,String name,String customerId,String roomId) throws HttpException, IOException{
		JSONObject custom = new JSONObject();
		custom.accumulate("contactNumber", contactNumber);
		custom.accumulate("name", name);
		custom.accumulate("customerId", customerId);
		custom.accumulate("roomId", roomId);
		
		JSONObject jsonObject = new JSONObject();
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.accumulate("json", custom);
		jsonObject.accumulate("params", jsonParams);
		String sendUrl = "/propertyAPI/family.do";
		String result = submitUrl(jsonObject, sendUrl);
		
	}
	@SuppressWarnings("deprecation")
	public static String submitUrl(JSONObject jsonobject,String url) throws IOException, HttpException {
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(300000);
		
		RequestEntity requestEntity=new StringRequestEntity(jsonobject.toString());
		method.setRequestEntity(requestEntity);
		httpClient.executeMethod(method);
		String result = method.getResponseBodyAsString();
		return result;
	}
}