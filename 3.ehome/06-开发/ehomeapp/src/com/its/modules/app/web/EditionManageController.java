/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.app.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.config.Global;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.web.BaseController;
import com.its.modules.app.entity.EditionManage;
import com.its.modules.app.service.EditionManageService;

import net.sf.json.JSONObject;

/**
 * 版本管理Controller
 * 
 * @author like
 * @version 2017-07-17
 */
@Controller
@RequestMapping(value = "${appPath}/my")
public class EditionManageController extends BaseController {

	@Autowired
	private EditionManageService editionManageService;

	/**
	 * 获取版本信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getVersionInfo")
	public String getVersionInfo() {
		EditionManage edition = editionManageService.getLatestEdition();
		if (edition == null) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"暂无版本信息\"}";
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("icon", "");
		data.put("version", edition.getEditionNo());
		data.put("desc", edition.getEditionInstruction());
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}

	/**
	 * 检查更新
	 * 
	 * @param version
	 *            用户当前版本号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/versionUpgrade")
	public String versionUpgrade(String version, HttpServletRequest request) {
		EditionManage edition = editionManageService.getLatestEdition();
		if (edition == null) {
			return "{\"code\":" + Global.CODE_PROMOT + ",\"message\":\"暂无版本信息\"}";
		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("version", edition.getEditionNo());
		data.put("download", MyFDFSClientUtils.get_fdfs_file_url(request,
				edition.getFileUrl()));/********************************* 需要返回完整路径 ************************************/
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("code", Global.CODE_SUCCESS);
		json.put("data", data);
		json.put("message", "成功");
		return JSONObject.fromObject(json).toString();
	}
}