/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.subject.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jasig.cas.client.util.CommonUtils;
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
import com.its.common.web.BaseController;
import com.its.common.utils.StringUtils;
import com.its.modules.subject.entity.SocialSubject;
import com.its.modules.subject.service.SocialSubjectService;
import com.its.modules.sys.utils.UserUtils;

/**
 * 话题管理Controller
 * @author wmm
 * @version 2017-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/subject/socialSubject")
public class SocialSubjectController extends BaseController {

	@Autowired
	private SocialSubjectService socialSubjectService;
	
	@ModelAttribute
	public SocialSubject get(@RequestParam(required=false) String id) {
		SocialSubject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = socialSubjectService.get(id);
		}
		if (entity == null){
			entity = new SocialSubject();
		}
		return entity;
	}
	
	@RequiresPermissions("subject:socialSubject:view")
	@RequestMapping(value = {"list", ""})
	public String list(SocialSubject socialSubject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SocialSubject> page = socialSubjectService.findPage(new Page<SocialSubject>(request, response), socialSubject); 
		model.addAttribute("page", page);
		model.addAttribute("proId", request.getParameter("addrpro"));
		model.addAttribute("cityId", request.getParameter("addrcity"));
		model.addAttribute("villageId", request.getParameter("villageInfoId"));
		model.addAttribute("listSize", page.getPageSize());
		return "modules/subject/socialSubjectList";
	}

	@RequiresPermissions("subject:socialSubject:view")
	@RequestMapping(value = "form")
	public String form(SocialSubject socialSubject, Model model) {
		model.addAttribute("socialSubject", socialSubject);
		return "modules/subject/socialSubjectForm";
	}

	@RequiresPermissions("subject:socialSubject:edit")
	@RequestMapping(value = "save")
	public String save(SocialSubject socialSubject, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, socialSubject)){
//			return form(socialSubject, model);
//		}
		if(CommonUtils.isEmpty(socialSubject.getId())) {
			socialSubject.setCreaterid(UserUtils.getUser().getId());
			socialSubject.setCreatername(UserUtils.getUser().getName());
			socialSubject.setCreatetime(new Date());
			socialSubject.setOrdernum("0");
		}
		socialSubjectService.save(socialSubject);
		addMessage(redirectAttributes, "保存话题成功");
		return "redirect:"+Global.getAdminPath()+"/subject/socialSubject/?repage";
	}
	
	@RequiresPermissions("subject:socialSubject:edit")
	@RequestMapping(value = "delete")
	public String delete(SocialSubject socialSubject, RedirectAttributes redirectAttributes) {
		socialSubjectService.delete(socialSubject);
		addMessage(redirectAttributes, "删除话题成功");
		return "redirect:"+Global.getAdminPath()+"/subject/socialSubject/?repage";
	}
	
	/**
	 * 保存顺序
	 * 
	 * @param groupPurchaseId 团购ID
	 * @return
	 */
	@RequiresPermissions("subject:socialSubject:sort")
	@ResponseBody
	@RequestMapping(value = "saveOrderNum")
	public String saveOrderNum(String groupId, String ordernum) {
		
		String  result="1";
		
		//列表也存在排序数据
		if(StringUtils.isNotBlank(groupId)){
			//团购主键
			String[] groupIds=groupId.split(",");
			//排序number
			String[] ordernums=ordernum.split(",");
			
			for(int i=1;i<groupIds.length;i++){
				SocialSubject socialSubject = new SocialSubject();
				socialSubject.setId(groupIds[i]);
				socialSubject.setOrdernum(ordernums[i]);
				
				int resultNum = socialSubjectService.updateOrderNum(socialSubject);
				//更新不成功
				if(resultNum == 0){
					result = "0";
				}
			}
		}
		
		return result;
	}
	
	@RequiresPermissions(value="subject:socialSubject:edit")
	@ResponseBody
	@RequestMapping(value = "updateRecommend")
	public String updateRecommend(String elemId, String isrecommend, RedirectAttributes redirectAttributes) {
		String result = "1";
		SocialSubject socialSubject = new SocialSubject();
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/subject/socialSubject/?repage";
		}
		socialSubject.setId(elemId);
		socialSubject.setIsrecommend(isrecommend);
		int resultNum = socialSubjectService.updateRecommend(socialSubject);
		if(resultNum == 0) {
			result = "0";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="loadSubject", method={RequestMethod.POST,RequestMethod.GET})
	public List<SocialSubject> loadSubject(HttpServletRequest request){
		List<SocialSubject> allList = socialSubjectService.findAll();
		return allList;
	}

}