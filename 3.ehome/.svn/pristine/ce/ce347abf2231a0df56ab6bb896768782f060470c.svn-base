/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.coupon.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.persistence.Page;
import com.its.common.web.BaseController;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.coupon.entity.GroupPurchase;
import com.its.modules.coupon.service.GroupPurchaseService;
import com.its.modules.coupon.entity.GroupPurchasetime;
import com.its.modules.coupon.service.GroupPurchasetimeService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

/**
 * 优惠验券管理Controller
 * @author caojing
 * @version 2017-07-25
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/groupPurchase")
public class GroupPurchaseController extends BaseController {

	@Autowired
	private GroupPurchaseService groupPurchaseService;
	
	@Autowired
	private GroupPurchasetimeService groupPurchasetimeService; 
	
	@ModelAttribute
	public GroupPurchase get(@RequestParam(required=false) String id) {
		GroupPurchase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = groupPurchaseService.get(id);
		}
		if (entity == null){
			entity = new GroupPurchase();
		}
		return entity;
	}
	
	/**
	 * 团购活动列表页
	 * @param groupPurchase
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("coupon:groupPurchase:view")
	@RequestMapping(value = {"list", ""})
	public String list(GroupPurchase groupPurchase, HttpServletRequest request, HttpServletResponse response, Model model) {
		//从session中获取用户信息
	    User user = UserUtils.getUser();
	    //获取本商户的ID
	    if(user !=null){
	    	groupPurchase.setBusinessinfoId(user.getBusinessinfoId());
	    }
	    
		Page<GroupPurchase> page = groupPurchaseService.findPage(new Page<GroupPurchase>(request, response), groupPurchase); 
		for(GroupPurchase groupPur:page.getList()){
			// 根据图片ID取得图片SRC
			try {
				groupPur.setGroupPurcPic(MyFDFSClientUtils.get_fdfs_file_url(request,groupPur.getGroupPurcPic()));
			} catch (IOException | MyException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("page", page);
		return "modules/coupon/groupPurchaseList";
	}

	/**
	 * 查询团购详情页数据
	 * @param groupPurchase
	 * @param model
	 * @return
	 */
	@RequiresPermissions("coupon:groupPurchase:view")
	@RequestMapping(value = "detail")
	public String getDetail(GroupPurchase groupPurchase, Model model,HttpServletRequest request) {
		GroupPurchase groupPurchaseDetail = new GroupPurchase();
		//获取团购详情主表信息
		if(groupPurchase !=null && StringUtils.isNoneBlank(groupPurchase.getId())){
			groupPurchaseDetail = groupPurchaseService.getDetail(groupPurchase.getId());
			//商家支持设置
			if(groupPurchaseDetail !=null){				
				model.addAttribute("supportTypeList", groupPurchaseDetail.getSupportTypeList());
			}
			
			model.addAttribute("id", groupPurchase.getId());
		}
		
		if(groupPurchaseDetail !=null){
			
		   // 根据图片ID取得图片SRC
		   try {
			   groupPurchaseDetail.setGroupPurcPic(MyFDFSClientUtils.get_fdfs_file_url(request,groupPurchaseDetail.getGroupPurcPic()));
		   } catch (IOException | MyException e) {
			   // TODO Auto-generated catch block
			  e.printStackTrace();
		   }
	    }
		
		model.addAttribute("groupPurchase", groupPurchaseDetail);
		return "modules/coupon/groupPurchaseDetail";
	}
	
	/**
	 * 绑定团购时间信息用
	 * 
	 * @param groupPurchaseId 团购ID
	 * @return
	 * @throws ParseException 
	 */

	@ResponseBody
	@RequestMapping(value = "bindList")
	public List<GroupPurchasetime> bindList(String groupPurchaseId) throws ParseException {
		List<GroupPurchasetime> list = new ArrayList<GroupPurchasetime>();
		//设置条件
		GroupPurchasetime groupPurchasetime = new GroupPurchasetime();
		groupPurchasetime.setGroupPurchaseId(groupPurchaseId);
		list = groupPurchasetimeService.findList(groupPurchasetime);
		
		return list;
	}

}