/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.account.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.its.common.persistence.Page;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.account.entity.RoomCertify;
import com.its.modules.account.service.RoomCertifyService;

/**
 * 房间认证Controller
 * @author ChenXiangyu
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/account/roomCertify")
public class RoomCertifyController extends BaseController {

	@Autowired
	private RoomCertifyService roomCertifyService;
//	@Autowired
//	private VillageInfoService villageInfoService;
	
	@ModelAttribute
	public RoomCertify get(@RequestParam(required=false) String id) {
		RoomCertify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = roomCertifyService.get(id);
		}
		if (entity == null){
			entity = new RoomCertify();
		}
		return entity;
	}
	
	@RequiresPermissions("account:roomCertify:view")
	@RequestMapping(value = {"list", ""})
	public String list(RoomCertify roomCertify, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (StringUtils.isNotBlank(roomCertify.getCustomerName())) {
			// 特殊字符转义
			roomCertify.setCustomerName(org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(roomCertify.getCustomerName()));
		}
		
		Page<RoomCertify> page = roomCertifyService.findPage(new Page<RoomCertify>(request, response), roomCertify); 
		model.addAttribute("page", page);
		return "modules/account/roomCertifyList";
	}

	// 该机能开发未正式开始
//	@RequiresPermissions("account:roomCertify:view")
//	@RequestMapping(value = "form")
//	public String form(RoomCertify roomCertify, Model model) {
//		model.addAttribute("roomCertify", roomCertify);
//		return "modules/account/roomCertifyForm";
//	}

	// 该机能开发未正式开始
//	@RequiresPermissions("account:roomCertify:edit")
//	@RequestMapping(value = "save")
//	public String save(RoomCertify roomCertify, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, roomCertify)){
//			return form(roomCertify, model);
//		}
//		roomCertifyService.save(roomCertify);
//		addMessage(redirectAttributes, "保存房间认证成功");
//		return "redirect:"+Global.getAdminPath()+"/account/roomCertify/?repage";
//	}
//	
	// 该机能开发未正式开始
//	@RequiresPermissions("account:roomCertify:edit")
//	@RequestMapping(value = "delete")
//	public String delete(RoomCertify roomCertify, RedirectAttributes redirectAttributes) {
//		roomCertifyService.delete(roomCertify);
//		addMessage(redirectAttributes, "删除房间认证成功");
//		return "redirect:"+Global.getAdminPath()+"/account/roomCertify/?repage";
//	}

}