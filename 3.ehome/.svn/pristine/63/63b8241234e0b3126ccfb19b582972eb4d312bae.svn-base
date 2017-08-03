/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.village.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.company.entity.CompanyInfo;
import com.its.modules.company.service.CompanyInfoService;
import com.its.modules.property.entity.PropertyCompany;
import com.its.modules.property.service.PropertyCompanyService;
import com.its.modules.sys.entity.Area;
import com.its.modules.sys.service.AreaService;
import com.its.modules.sys.utils.DictUtils;
import com.its.modules.sys.utils.UserUtils;
import com.its.modules.village.entity.VillageInfo;
import com.its.modules.village.entity.VillageLine;
import com.its.modules.village.service.VillageInfoService;
import com.its.modules.village.service.VillageLineService;

/**
 * 楼盘信息Controller
 * 
 * @author zhujiao
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/village/villageInfo")
public class VillageInfoController extends BaseController {

	@Autowired
	private VillageInfoService villageInfoService;
	@Autowired
	private VillageLineService villageLineService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private PropertyCompanyService propertyCompanyService;
	/** 字典值：正常 */
	private static final String DICT_VALUE_NOMAL = "0";
	/** 字典值：冻结 */
	private static final String DICT_VALUE_FROZEN = "1";
	/** 字典类型：使用状态 */
	private static final String DICT_TYPE_USE_STATE = "use_state";
	/** 信息提示：冻结 */
	private static final String MSG_FROZEN = "冻结";
	/** 信息提示：取消冻结 */
	private static final String MSG_UNFROZEN = "取消冻结";

	@ModelAttribute
	public VillageInfo get(@RequestParam(required = false) String id) {
		VillageInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = villageInfoService.get(id);
		}
		if (entity == null) {
			entity = new VillageInfo();
		}
		return entity;
	}

	@RequiresPermissions("village:villageInfo:view")
	@RequestMapping(value = { "list", "" })
	public String list(VillageInfo villageInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 获取当前用户权限下的楼盘ID
		String villageInfoIdsStr = UserUtils.getUser().getVillageInfoIds();
		List<String> villageInfoIdList = new ArrayList<String>();
		if (villageInfoIdsStr != null && !villageInfoIdsStr.isEmpty()) {
			String[] villageInfoIds = villageInfoIdsStr.split(",");
			for (String villageInfoId : villageInfoIds) {
				if (villageInfoId != null && !villageInfoId.isEmpty()) {
					villageInfoIdList.add(villageInfoId);
				}
			}
		}
		villageInfo.setUserVillageIds(villageInfoIdList);
		Page<VillageInfo> page = villageInfoService.findPage(new Page<VillageInfo>(request, response), villageInfo);
		
		// 设置城市名、所属开发商名、所属物业名
		for(VillageInfo tempVillageInfo : page.getList()){
			// 城市名
			Area area = areaService.get(tempVillageInfo.getAddrCity());
			if(area != null){
				tempVillageInfo.setAddrCityName(area.getName());
			}
			// 所属开发商名
			CompanyInfo companyInfo = companyInfoService.get(tempVillageInfo.getCompanyInfoId());
			if(companyInfo != null){
				tempVillageInfo.setCompanyInfoName(companyInfo.getCompanyName());
			}
			// 所属物业名
			PropertyCompany propertyCompany = propertyCompanyService.get(tempVillageInfo.getPropertyCompanyId());
			if(propertyCompany != null){
				tempVillageInfo.setPropertyCompanyName(propertyCompany.getCompanyName());
			}
		}
		
		// 所属物业公司下拉列表内容设置
		Map<String,String> propertyCompanyMap = new HashMap<String,String>();
		List<PropertyCompany> allPropertyCompany = propertyCompanyService.findList(new PropertyCompany());
		if (allPropertyCompany != null && !allPropertyCompany.isEmpty()) {
			for (PropertyCompany tempPropertyCompany : allPropertyCompany) {
				propertyCompanyMap.put(tempPropertyCompany.getId(), tempPropertyCompany.getCompanyName());
			}
		}
		
		model.addAttribute("page", page);
		model.addAttribute("propertyCompanyMap", propertyCompanyMap);
		model.addAttribute("proId", villageInfo.getAddrPro());
		model.addAttribute("cityId", villageInfo.getAddrCity());
		model.addAttribute("villageId", villageInfo.getId());
		return "modules/village/villageInfoList";
	}

	@RequiresPermissions("village:villageInfo:view")
	@RequestMapping(value = "form")
	public String form(VillageInfo villageInfo, Model model) {
		// 所属地产公司下拉列表内容设置
		Map<String,String> companyInfoMap = new HashMap<String,String>();
		List<CompanyInfo> allCompanyInfo = companyInfoService.findList(new CompanyInfo());
		if (allCompanyInfo != null && !allCompanyInfo.isEmpty()) {
			for (CompanyInfo tempCompanyInfo : allCompanyInfo) {
				companyInfoMap.put(tempCompanyInfo.getId(), tempCompanyInfo.getCompanyName());
			}
		}
		
		// 所属物业公司下拉列表内容设置
		Map<String,String> propertyCompanyMap = new HashMap<String,String>();
		List<PropertyCompany> allPropertyCompany = propertyCompanyService.findList(new PropertyCompany());
		if (allPropertyCompany != null && !allPropertyCompany.isEmpty()) {
			for (PropertyCompany tempPropertyCompany : allPropertyCompany) {
				propertyCompanyMap.put(tempPropertyCompany.getId(), tempPropertyCompany.getCompanyName());
			}
		}
		
		model.addAttribute("villageInfo", villageInfo);
		model.addAttribute("companyInfoMap", companyInfoMap);
		model.addAttribute("propertyCompanyMap", propertyCompanyMap);
		return "modules/village/villageInfoForm";
	}

	@RequiresPermissions("village:villageInfo:edit")
	@RequestMapping(value = "save")
	public String save(VillageInfo villageInfo, Model model, RedirectAttributes redirectAttributes) {
		// 
		villageInfo.setVillageName(StringEscapeUtils.unescapeHtml4(villageInfo.getVillageName()));
		villageInfo.setPhoneNum(StringEscapeUtils.unescapeHtml4(villageInfo.getPhoneNum()));
		
		if (!beanValidator(model, villageInfo)) {
			return form(villageInfo, model);
		}
		villageInfoService.save(villageInfo);
		addMessage(redirectAttributes, "保存楼盘信息成功");
		return "redirect:" + Global.getAdminPath() + "/village/villageInfo/?repage";
	}
	
	/**
	 * 关联产品线页面
	 * @param villageInfo
	 * @param model
	 * @return
	 */
	@RequiresPermissions("village:villageInfo:view")
	@RequestMapping(value = "formProductLine")
	public String formProductLine(VillageInfo villageInfo, Model model) {
		// 获取所有关联产品线组成的字符串并传递到前台
		VillageLine villageLine = new VillageLine();
		villageLine.setVillageInfoId(villageInfo.getId());
		List<VillageLine> villageLineList = villageLineService.findList(villageLine);
		StringBuilder productLines = new StringBuilder();
		int index = 0;
		if (villageLineList != null && !villageLineList.isEmpty()) {
			for (VillageLine tempVillageLine : villageLineList) {
				if (index != 0 
						&& tempVillageLine.getProductLine() != null 
						&& tempVillageLine.getProductLine() != "") {
					productLines.append(",");
				}
				productLines.append(tempVillageLine.getProductLine());
				index++;
			}
		}
		
		model.addAttribute("villageInfo", villageInfo);
		model.addAttribute("productLines", productLines.toString());
		return "modules/village/villageInfoLine";
	}
	
	/**
	 * 更新关联的产品线
	 * @param villageInfo
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("village:villageInfo:edit")
	@RequestMapping(value = "saveProductLine")
	public String saveProductLine(VillageInfo villageInfo, Model model, RedirectAttributes redirectAttributes) {
		try {
			if (villageInfoService.saveProductLine(villageInfo)) {
				addMessage(redirectAttributes, "关联产品线成功");
			} else {
				addMessage(redirectAttributes, "关联产品线失败");
			}
		} catch (Exception e) {
			addMessage(redirectAttributes, "关联产品线失败");
		}
		return "redirect:" + Global.getAdminPath() + "/village/villageInfo/?repage";
	}

	@RequiresPermissions("village:villageInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(VillageInfo villageInfo, RedirectAttributes redirectAttributes) {
		villageInfoService.delete(villageInfo);
		addMessage(redirectAttributes, "删除楼盘信息成功");
		return "redirect:" + Global.getAdminPath() + "/village/villageInfo/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "getVillageTree")
	public List<VillageInfo> getVillageTree(VillageInfo villageInfo, RedirectAttributes redirectAttributes) {
		List<VillageInfo> list = new ArrayList<>();

		list = villageInfoService.getVillageTree(villageInfo);
	
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "getUserVillageTree")
	public List<VillageInfo> getUserVillageTree(VillageInfo villageInfo, RedirectAttributes redirectAttributes) {
		List<VillageInfo> list = new ArrayList<>();
		list = villageInfoService.getUserVillageTree(villageInfo);
		
		return list;
	}
	
	/**
	 * 获取当前用户权限下的未冻结的楼盘信息
	 * @param request
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="findList", method={RequestMethod.POST,RequestMethod.GET})
	public List<VillageInfo> findList(HttpServletRequest request, @RequestParam String provinceId, @RequestParam String cityId){
		// 根据省、市、用户权限下的楼盘ID、正常状态进行查询
		VillageInfo villageInfo = new VillageInfo();
		villageInfo.setAddrPro(provinceId);
		villageInfo.setAddrCity(cityId);
		villageInfo.setState(DICT_VALUE_NOMAL);
		List<VillageInfo> VillageInfoList = villageInfoService.findListByLoginUser(villageInfo);
		return VillageInfoList;
	}
	
	/**
	 * 获取当前用户权限下的所有状态的楼盘信息
	 * @param request
	 * @param parentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="findListAllState", method={RequestMethod.POST,RequestMethod.GET})
	public List<VillageInfo> findListAllState(HttpServletRequest request, @RequestParam String provinceId, @RequestParam String cityId){
		VillageInfo villageInfo = new VillageInfo();
		villageInfo.setAddrPro(provinceId);
		villageInfo.setAddrCity(cityId);
		List<VillageInfo> VillageInfoList = villageInfoService.findListByLoginUser(villageInfo);
		return VillageInfoList;
	}
	
	@RequiresPermissions(value="village:villageInfo:edit")
	@RequestMapping(value = "updateState")
	public String updateState(VillageInfo villageInfo, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/village/villageInfo/?repage";
		}
		villageInfoService.updateState(villageInfo);
		String flagName = MSG_FROZEN;
		if (!DictUtils.getDictValue(MSG_FROZEN, DICT_TYPE_USE_STATE, DICT_VALUE_FROZEN).equals(villageInfo.getState())) {
			flagName = MSG_UNFROZEN;
		}
		addMessage(redirectAttributes, flagName + "楼盘成功");
		return "redirect:"+Global.getAdminPath()+"/village/villageInfo/?repage";
	}
}