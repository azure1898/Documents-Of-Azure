package com.its.modules.app.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.common.utils.StringUtils;
import com.its.modules.app.dao.AccountDao;
import com.its.modules.app.entity.Account;
import com.its.modules.app.entity.RoomCertify;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 会员信息Service
 * 
 * @author like
 * 
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class AccountService extends CrudService<AccountDao, Account> {
	@Autowired
	private RoomCertifyService roomCertifyService;

	public Account get(String id) {
		return super.get(id);
	}

	public List<Account> findList(Account account) {
		return super.findList(account);
	}

	public Page<Account> findPage(Page<Account> page, Account account) {
		return super.findPage(page, account);
	}

	@Transactional(readOnly = false)
	public void save(Account account) {
		super.save(account);
	}

	@Transactional(readOnly = false)
	public void update(Account account) {
		dao.update(account);
	}

	@Transactional(readOnly = false)
	public void delete(Account account) {
		super.delete(account);
	}

	// --------------------------------------------------
	public Account getByPhoneNum(String phoneNum) {
		return dao.getByPhoneNum(phoneNum);
	}

	/**
	 * 验证手机号是否已经注册
	 * 
	 * @param phoneNum
	 */
	public boolean isExist(String phoneNum) {
		return dao.isExist(phoneNum);
	}

	/**
	 * 保存用户的楼盘选择
	 * 
	 * @param id
	 * @param villageID
	 */
	@Transactional(readOnly = false)
	public void saveAccountVillageID(String id, String villageID) {
		dao.saveAccountVillageID(id, villageID);
	}

	/**
	 * 调用物管接口查询客户信息，并作为房间认证数据保存
	 * 
	 * @param phoneNum
	 *            手机号
	 * @param villageInfoId
	 *            楼盘ID
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void certifyCustomer(Account account) {
		if (account == null || StringUtils.isBlank(account.getPhoneNum()) || StringUtils.isBlank(account.getVillageInfoId())
				|| "1".equals(account.getCertifyState())) {
			return;
		}
		String url = Global.getConfig("customerPath");
		logger.info("url:" + url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("json={");
			builder.append("\"contactNumber\" :").append("\"").append(account.getPhoneNum()).append("\",");
			builder.append("\"projectId\" :").append("\"").append(account.getVillageInfoId()).append("\",");
			builder.append("\"updateTime\" :").append("\"").append(DateFormatUtils.format(new Date(), "yyyyMMddmmssSSS")).append("\"}");
			// 查询客户信息接口的传入参数
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
			String data = jsonResults.getString("data");

			// 查询楼栋接口成功
			if (isSuccess && "2000".equals(code)) {
				JSONArray jsonList = JSONArray.fromObject(data);
				JSONObject object = jsonList.getJSONObject(0);
				JSONObject roomResult = object.getJSONArray("roomResult").getJSONObject(0);
				JSONObject customerResult = object.getJSONArray("customerResult").getJSONObject(0);
				String roomId = roomResult.getString("roomId");
				RoomCertify room = roomCertifyService.get(roomId);
				if (room != null) {
					room.setAccountId(account.getId());
					room.setCustomerType(customerResult.getString("type"));
					room.setCustomerName(customerResult.getString("name"));
					room.setPhoneNum(account.getPhoneNum());
					room.setCustomerId(customerResult.getString("customerId"));
					roomCertifyService.save(room);
					account.setCertifyState("1");
					this.save(account);
				}

				logger.info("查询客户接口成功");
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