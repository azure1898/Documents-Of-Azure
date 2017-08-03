/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.FileUtils;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.module.entity.ModuleManage;
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.village.entity.VillageLine;
import com.its.modules.village.service.VillageLineService;

/**
 * 模块管理Controller
 * @author ChenXiangyu
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/module/moduleManage")
public class ModuleManageController extends BaseController {

	/** 消息类型：error */
	private static final String MSG_TYPE_ERROR = "error";
	@Autowired
	private ModuleManageService moduleManageService;
	@Autowired
	private VillageLineService villageLineService;
	
	@ModelAttribute
	public ModuleManage get(@RequestParam(required=false) String id) {
		ModuleManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = moduleManageService.get(id);
		}
		if (entity == null){
			entity = new ModuleManage();
		}
		return entity;
	}
	
	@RequiresPermissions("module:moduleManage:view")
	@RequestMapping(value = {"list", ""})
	public String list(ModuleManage moduleManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ModuleManage> page = moduleManageService.findPage(new Page<ModuleManage>(request, response), moduleManage); 
		for (ModuleManage tempModuleManage : page.getList()) {
			try {
					// 取得图标路径
					tempModuleManage.setModuleIcon(MyFDFSClientUtils.get_fdfs_file_url(request, tempModuleManage.getModuleIcon()));
			} catch (FileNotFoundException e) {
				StringBuilder msg = new StringBuilder("获取文件失败：文件");
				msg.append(moduleManage.getModuleIcon());
				msg.append("未找到");
				logger.warn(msg.toString());
				moduleManage.setModuleIcon(StringUtils.EMPTY);
			} catch (IOException e) {
				StringBuilder msg = new StringBuilder("获取文件失败：");
				msg.append(e.getMessage());
				logger.warn(msg.toString());
				moduleManage.setModuleIcon(StringUtils.EMPTY);
			} catch (MyException e) {
				StringBuilder msg = new StringBuilder("获取文件失败：");
				msg.append(e.getMessage());
				logger.warn(msg.toString());
				moduleManage.setModuleIcon(StringUtils.EMPTY);
			}
		}
		model.addAttribute("page", page);
		return "modules/module/moduleManageList";
	}

	@RequiresPermissions("module:moduleManage:view")
	@RequestMapping(value = "form")
	public String form(ModuleManage moduleManage, Model model, HttpServletRequest request) {
		try {
			// 取得图标路径
			moduleManage.setModuleIcon(MyFDFSClientUtils.get_fdfs_file_url(request, moduleManage.getModuleIcon()));
		} catch (FileNotFoundException e) {
			StringBuilder msg = new StringBuilder("获取文件失败：文件");
			msg.append(moduleManage.getModuleIcon());
			msg.append("未找到");
			logger.warn(msg.toString());
			moduleManage.setModuleIcon(StringUtils.EMPTY);
		} catch (IOException e) {
			StringBuilder msg = new StringBuilder("获取文件失败：");
			msg.append(e.getMessage());
			logger.warn(msg.toString());
			moduleManage.setModuleIcon(StringUtils.EMPTY);
		} catch (MyException e) {
			StringBuilder msg = new StringBuilder("获取文件失败：");
			msg.append(e.getMessage());
			logger.warn(msg.toString());
			moduleManage.setModuleIcon(StringUtils.EMPTY);
		}
		model.addAttribute("moduleManage", moduleManage);
		// 商家分类转换的模块名称，只可做图标的上传，不可更改模块名称。
		if (moduleManage.getId() != null && !moduleManage.getId().isEmpty() && Global.YES.equals(moduleManage.getBusinessCategoryDictFlag())) {
			return "modules/module/moduleManageEditForm";
		} else {
			return "modules/module/moduleManageAddForm";
		}
	}

	@RequiresPermissions(value={"module:moduleManage:add","module:moduleManage:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(ModuleManage moduleManage, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
		if (!beanValidator(model, moduleManage)){
			return form(moduleManage, model, request);
		}
		
		if (!(!moduleManage.getIsNewRecord() &&  (file == null || file.getSize() == 0))) {
			// 文件上传处理
			moduleManage.setModuleIcon(FileUtils.uploadImg(request, file));
		}
		
		moduleManageService.save(moduleManage);
		addMessage(redirectAttributes, "保存模块管理成功");
		return "redirect:"+Global.getAdminPath()+"/module/moduleManage/?repage";
	}
	
	@RequiresPermissions("module:moduleManage:delete")
	@RequestMapping(value = "delete")
	public String delete(ModuleManage moduleManage, RedirectAttributes redirectAttributes, Model model) {
		// 获取所有已设置模块的楼盘产品线信息
		List<VillageLine> villageLineList = villageLineService.findSettedList();
		// 获取所有已被楼盘应用的模块ID
		Set<String> settedModuleIds = new HashSet<String>();
		String[] settedCommunityModuleIds;
		String[] settedLifeModuleIds;
		for (VillageLine villageLine : villageLineList) {
			// 社区模块
			if (villageLine.getCommunityModule() != null && villageLine.getCommunityModule() != "") {
				settedCommunityModuleIds = villageLine.getCommunityModule().split(",");
				for (String settedCommunityModuleId : settedCommunityModuleIds) {
					settedModuleIds.add(settedCommunityModuleId);
				}
			}
			// 生活模块
			if (villageLine.getLifeModule() != null && villageLine.getLifeModule() != "") {
				settedLifeModuleIds = villageLine.getLifeModule().split(",");
				for (String settedLifeModuleId : settedLifeModuleIds) {
					settedModuleIds.add(settedLifeModuleId);
				}
			}
		}

		if (settedModuleIds.contains(moduleManage.getId())) {
			// 若当前要删除的模块存在于已被楼盘应用的模块中msgType
			addMessage(redirectAttributes, "该模块已被应用，不能删除");
			redirectAttributes.addFlashAttribute("msgType", MSG_TYPE_ERROR);
			return "redirect:" + Global.getAdminPath() + "/module/moduleManage/?repage";
		}
		 moduleManageService.delete(moduleManage);
		addMessage(redirectAttributes, "删除模块管理成功");
		return "redirect:" + Global.getAdminPath() + "/module/moduleManage/?repage";
	}

	/**
	 * 验证模块名称是否有效
	 * @param modulename 模块名称
	 * @return 是否有效
	 */
	@ResponseBody
	@RequiresPermissions("module:moduleManage:view")
	@RequestMapping(value = "checkModuleName")
	public String checkModuleName(String oldModulename, String modulename) {
		if (modulename !=null && modulename.equals(oldModulename)) {
			return "true";
		} else if (modulename !=null && moduleManageService.getModuleByModuleName(modulename) == null) {
			return "true";
		}
		return "false";
	}
}