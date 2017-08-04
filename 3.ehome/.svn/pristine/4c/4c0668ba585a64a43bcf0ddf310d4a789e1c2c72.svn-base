/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.service;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.app.dao.FamilyInfoDao;
import com.its.modules.app.entity.FamilyInfo;

import net.sf.json.JSONObject;

/**
 * 家属成员信息Service
 * 
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
	 * 
	 * @param roomId
	 * @return
	 */
	public List<FamilyInfo> getRoomFamilies(String roomId) {
		return dao.getRoomFamilies(roomId);
	}

	/**
	 * 新增家庭成员接口
	 * 
	 * @param contactNumber
	 * @param name
	 * @param customerId
	 * @param roomId
	 */
	@Async
	public void submitRemoteFamily(String contactNumber, String name, String customerId, String roomId) {
		if (StringUtils.isBlank(contactNumber) || StringUtils.isBlank(name) || StringUtils.isBlank(customerId) || StringUtils.isBlank(roomId)) {
			return;
		}
		String url = Global.getConfig("familyPath");
		logger.info("url:" + url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("json={").append("\"contactNumber\" :").append("\"").append(contactNumber).append("\",").append("\"name\" :").append("\"")
					.append(name).append("\",").append("\"customerId\" :").append("\"").append(customerId).append("\",").append("\"roomId\" :").append("\"")
					.append(roomId).append("\"}");
			String villagePara = builder.toString();
			HttpPost req = new HttpPost(url);
			req.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			StringEntity content = new StringEntity(villagePara, "UTF-8");
			req.setEntity(content);
			CloseableHttpResponse response = httpClient.execute(req);
			HttpEntity entity = response.getEntity();
			String results = EntityUtils.toString(entity, "UTF-8");
			logger.info("---------查询客户信息接口的返回result列表：results-----------");
			logger.info(results);
			System.out.println(results);
			// 使用JSONObject
			JSONObject jsonResults = JSONObject.fromObject(results);
			// 返回状态
			boolean isSuccess = jsonResults.getBoolean("isSuccess");
			// 错误信息
			String msg = jsonResults.getString("msg");
			// 状态码
			String code = jsonResults.getString("code");
			// 数据信息字段
			// String data = jsonResults.getString("data");

			// 查询楼栋接口成功
			if (isSuccess && "2000".equals(code)) {

				logger.info("新增家庭成员接口成功");
			} else {
				logger.warn("状态码:" + code + ";错误信息:" + msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}