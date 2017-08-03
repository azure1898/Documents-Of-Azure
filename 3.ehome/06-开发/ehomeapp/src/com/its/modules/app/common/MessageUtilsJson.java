package com.its.modules.app.common;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.its.common.config.Global;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageUtilsJson {
	
	private MessageUtilsJson(){};
	
    private static final  String sendUrl=Global.getConfig("sms.sendUrl");
    private static final String USERID=Global.getConfig("sms.userid");
    private static final String PASSWORD=Global.getConfig("sms.password");
    private static final String TEMPLATE =Global.getConfig("sms.template");
	
	public static int sendMsg(String phone,String content) throws HttpException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("id",1);
		jsonObject.accumulate("method","send");
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.accumulate("userid", USERID);
		jsonParams.accumulate("password", PASSWORD);
		
		JSONObject jsonSubmits = new JSONObject();
		jsonSubmits.accumulate("content", URLEncoder.encode(TEMPLATE.replaceAll("#code#", content), "UTF-8"));
		jsonSubmits.accumulate("phone", phone);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonSubmits);
		
		jsonParams.accumulate("submit",jsonArray);
		jsonObject.accumulate("params", jsonParams);
		
		//System.out.println(jsonObject.toString());
		String result = submitUrl(jsonObject, sendUrl);
		JSONObject resultJson = JSONObject.fromObject(result);
		int resultCode = resultJson.getJSONArray("result").getJSONObject(0).getInt("return");
		return resultCode;
		
	}
	
	// 获取短信状态
	public static String getMsgStatus() throws HttpException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("id","1");
		jsonObject.accumulate("method","report");
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.accumulate("userid", USERID);
		jsonParams.accumulate("password", PASSWORD);
		jsonObject.accumulate("params", jsonParams);
		
		String result = submitUrl(jsonObject, sendUrl);
		return result;
	}
	
	public static String getMsgUp() throws HttpException, IOException{
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("id","1");
		jsonObject.accumulate("method","upmsg");
		
		JSONObject jsonParams = new JSONObject();
		jsonParams.accumulate("userid", USERID);
		jsonParams.accumulate("password", PASSWORD);
		jsonObject.accumulate("params", jsonParams);
		System.out.println(jsonObject.toString());
		String result = submitUrl(jsonObject, sendUrl);
		return result;
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
