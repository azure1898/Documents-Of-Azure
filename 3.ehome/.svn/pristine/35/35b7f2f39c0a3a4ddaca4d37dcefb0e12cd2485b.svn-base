package com.its.modules.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.its.common.persistence.Page;
import com.its.common.service.CrudService;
import com.its.modules.app.dao.AccountApplicationDao;
import com.its.modules.app.entity.AccountApplication;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 会员的应用Service
 * 
 * @author sushipeng
 * 
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class AccountApplicationService extends CrudService<AccountApplicationDao, AccountApplication> {

	public AccountApplication get(String id) {
		return super.get(id);
	}

	public List<AccountApplication> findList(AccountApplication accountApplication) {
		return super.findList(accountApplication);
	}

	public Page<AccountApplication> findPage(Page<AccountApplication> page, AccountApplication accountApplication) {
		return super.findPage(page, accountApplication);
	}

	@Transactional(readOnly = false)
	public void save(AccountApplication accountApplication) {
		super.save(accountApplication);
	}

	@Transactional(readOnly = false)
	public int update(AccountApplication accountApplication) {
		return dao.update(accountApplication);
	}

	@Transactional(readOnly = false)
	public void delete(AccountApplication accountApplication) {
		super.delete(accountApplication);
	}

	/**
	 * 获取某用户在某楼盘下的常用应用
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @return List<AccountApplication>
	 */
	public List<AccountApplication> getAccountApplicationList(String accountId, String villageInfoId) {
		return dao.getAccountApplicationList(accountId, villageInfoId);
	}

	/**
	 * 批量删除用户常用应用
	 * 
	 * @param accountId
	 *            用户ID
	 * @param villageInfoId
	 *            楼盘ID
	 * @param moduleIds
	 *            模块ID集合
	 * @return 操作的行数
	 */
	public int deleteList(List<String> list, String accountId, String villageInfoId) {
		return dao.deleteList(list, accountId, villageInfoId);
	}

	/**
	 * 编辑常用应用
	 */
	@Transactional(readOnly = false)
	public boolean editMyApplication(String userId, String buildingId, String modules) {
		try {
			modules = modules.replaceAll("&quot;", "\"");
			// 获取常用模块数据
			List<AccountApplication> accountApplications = this.getAccountApplicationList(userId, buildingId);
			List<String> oldModuleIds = new ArrayList<String>();
			for (AccountApplication accountApplication : accountApplications) {
				oldModuleIds.add(accountApplication.getModuleManageId());
			}
			JSONArray array = JSONArray.fromObject(modules);
			if (array == null || array.size() == 0) {
				if (oldModuleIds.size() != 0) {
					int deleteRow = this.deleteList(oldModuleIds, userId, buildingId);
					if (deleteRow == 0) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return false;
					}
				}
				return true;
			}

			// 解析接收到的参数
			List<JSONObject> objects = new ArrayList<JSONObject>();
			List<String> newModuleIds = new ArrayList<String>();
			for (Object obj : array) {
				JSONObject object = JSONObject.fromObject(obj);
				objects.add(object);
				newModuleIds.add((String) object.get("moduleID"));
			}

			// 需要添加的应用
			for (String moduleId : newModuleIds) {
				if (!oldModuleIds.contains(moduleId)) {
					AccountApplication accountApplication = new AccountApplication();
					accountApplication.setAccountId(userId);
					accountApplication.setVillageInfoId(buildingId);
					accountApplication.setModuleManageId(moduleId);
					accountApplication.setSortNum(this.getSortNum(objects, moduleId));
					this.save(accountApplication);
				}
			}
			// 需要删除的应用
			List<String> toDelete = new ArrayList<String>();
			for (String moduleId : oldModuleIds) {
				if (!newModuleIds.contains(moduleId)) {
					toDelete.add(moduleId);
				}
			}
			if (toDelete.size() != 0) {
				int deleteRow = this.deleteList(toDelete, userId, buildingId);
				if (deleteRow == 0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return false;
				}
			}
			// 需要更新的应用
			List<String> toUpdate = new ArrayList<String>();
			for (String moduleId : oldModuleIds) {
				if (newModuleIds.contains(moduleId)) {
					toUpdate.add(moduleId);
				}
			}
			if (toUpdate.size() != 0) {
				List<AccountApplication> applications = this.getAccountApplicationList(userId, buildingId);
				for (AccountApplication accountApplication : applications) {
					for (String moduleId : toUpdate) {
						if (moduleId.equals(accountApplication.getModuleManageId())) {
							accountApplication.setSortNum(this.getSortNum(objects, accountApplication.getModuleManageId()));
							int updateRow = this.update(accountApplication);
							if (updateRow == 0) {
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
								return false;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.warn("编辑常用应用==========>" + e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 获取指定模块对应的排序
	 * 
	 * @param objects
	 *            解析后的参数
	 * @param moduleId
	 *            指定模块
	 * @return 指定模块对应的排序
	 */
	public Integer getSortNum(List<JSONObject> objects, String moduleId) {
		for (JSONObject jsonObject : objects) {
			if (moduleId.equals((String) jsonObject.get("moduleID"))) {
				return (Integer) jsonObject.get("sort");
			}
		}
		return 0;
	}
}