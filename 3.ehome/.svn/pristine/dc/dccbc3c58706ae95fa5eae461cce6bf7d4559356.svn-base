/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.notice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.notice.entity.NoticeManage;
import com.its.modules.notice.service.NoticeManageService;
import com.its.modules.sys.utils.DictUtils;

/**
 * 社区公告Controller
 * @author ChenXiangyu
 * @version 2017-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/notice/noticeManage")
public class NoticeManageController extends BaseController {

	@Autowired
	private NoticeManageService noticeManageService;
	/** 复选框值：被选中 */
	private static final String CHECKBOX_CHECKED = "on";
	/** 复选框值：未被选中 */
	private static final String CHECKBOX_UNCHECKED = "1";
	
	@ModelAttribute
	public NoticeManage get(@RequestParam(required=false) String id) {
		NoticeManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = noticeManageService.get(id);
		}
		if (entity == null){
			entity = new NoticeManage();
		}
		return entity;
	}
	
	@RequiresPermissions("notice:noticeManage:view")
	@RequestMapping(value = {"list", ""})
	public String list(NoticeManage noticeManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NoticeManage> page = noticeManageService.findPage(new Page<NoticeManage>(request, response), noticeManage); 
		model.addAttribute("page", page);
		// 楼盘信息
		model.addAttribute("addrpro", noticeManage.getAddrPro());
		model.addAttribute("addrcity", noticeManage.getAddrCity());
		model.addAttribute("addrVillage", noticeManage.getVillageInfoId());
		return "modules/notice/noticeManageList";
	}

	@RequiresPermissions("notice:noticeManage:view")
	@RequestMapping(value = "form")
	public String form(NoticeManage noticeManage, Model model) {
		model.addAttribute("noticeManage", noticeManage);
		return "modules/notice/noticeManageForm";
	}

	@RequiresPermissions("notice:noticeManage:edit")
	@RequestMapping(value = "save")
	public String save(NoticeManage noticeManage, Model model, RedirectAttributes redirectAttributes) {
		// 输入框HTML特殊字符转义
		if (noticeManage.getNoticeTitle() != null && noticeManage.getNoticeTitle() != "") {
			noticeManage.setNoticeTitle(StringEscapeUtils.unescapeHtml4(noticeManage.getNoticeTitle()));
		}
		if (noticeManage.getNoticeAbstract() != null && noticeManage.getNoticeAbstract() != "") {
			noticeManage.setNoticeAbstract(StringEscapeUtils.unescapeHtml4(noticeManage.getNoticeAbstract()));
		}
		if (StringUtils.isNotBlank(noticeManage.getNoticeContent())) {
			// 特殊字符转义
			noticeManage.setNoticeContent(StringEscapeUtils.unescapeHtml4(noticeManage.getNoticeContent()));
			// "&"等特殊字符转义
			noticeManage.setNoticeContent(StringEscapeUtils.unescapeHtml4(noticeManage.getNoticeContent()));
        }
		if (!beanValidator(model, noticeManage)){
			return form(noticeManage, model);
		}
		
		// 转换邻里圈flag
		if (CHECKBOX_CHECKED.equals(noticeManage.getSyncCircleFlag())) {
			noticeManage.setSyncCircleFlag(DictUtils.getDictValue("是", "yes_no", "1"));
		} else if (CHECKBOX_UNCHECKED.equals(noticeManage.getSyncCircleFlag())) {
			noticeManage.setSyncCircleFlag(DictUtils.getDictValue("否", "yes_no", "0"));
		}
		
		noticeManageService.save(noticeManage);
		addMessage(redirectAttributes, "保存公告成功");
		return "redirect:"+Global.getAdminPath()+"/notice/noticeManage/?repage";
	}
	
	@RequiresPermissions("notice:noticeManage:edit")
	@RequestMapping(value = "delete")
	public String delete(NoticeManage noticeManage, RedirectAttributes redirectAttributes) {
		noticeManageService.delete(noticeManage);
		addMessage(redirectAttributes, "删除公告成功");
		return "redirect:"+Global.getAdminPath()+"/notice/noticeManage/?repage";
	}

}