/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.account.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.Logical;
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
import com.its.modules.account.entity.Account;
import com.its.modules.account.service.AccountService;
import com.its.modules.sys.utils.DictUtils;

/**
 * 用户注册Controller
 * @author ChenXiangyu
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/account/account")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;
	/** 字典类型：用户状态 */
	private static final String DICT_TYPE_USE_STATE = "use_state";
	/** 字典值：冻结 */
	private static final String DICT_VALUE_FROZEN = "1";
	/** 信息提示：冻结 */
	private static final String MSG_FROZEN = "冻结";
	/** 信息提示：取消冻结 */
	private static final String MSG_UNFROZEN = "取消冻结";
	
	@ModelAttribute
	public Account get(@RequestParam(required=false) String id) {
		Account entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accountService.get(id);
		}
		if (entity == null){
			entity = new Account();
		}
		return entity;
	}
	
	@RequiresPermissions("account:account:view")
	@RequestMapping(value = {"list", ""})
	public String list(Account account, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (StringUtils.isNotBlank(account.getNickname())) {
			// 特殊字符转义
			account.setNickname(StringEscapeUtils.unescapeHtml4(account.getNickname()));
        }
		
		Page<Account> page = accountService.findPage(new Page<Account>(request, response), account); 
		model.addAttribute("page", page);
		return "modules/account/accountList";
	}
	
	@RequiresPermissions(value={"account:account:frozen","account:account:unfrozen"},logical=Logical.OR)
	@RequestMapping(value = "updateUseState")
	public String updateUseState(Account account, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/account/account/?repage";
		}
		accountService.updateUseState(account);
		String flagName = MSG_FROZEN;
		if (!DictUtils.getDictValue(MSG_FROZEN, DICT_TYPE_USE_STATE, DICT_VALUE_FROZEN).equals(account.getUseState())) {
			flagName = MSG_UNFROZEN;
		}
		addMessage(redirectAttributes, flagName + "会员成功");
		return "redirect:"+Global.getAdminPath()+"/account/account/?repage";
	}

}