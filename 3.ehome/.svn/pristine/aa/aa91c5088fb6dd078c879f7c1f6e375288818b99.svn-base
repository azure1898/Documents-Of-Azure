/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.comment.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.its.modules.account.entity.Account;
import com.its.modules.account.service.AccountService;
import com.its.modules.comment.entity.SocialComment;
import com.its.modules.comment.service.SocialCommentService;
import com.its.modules.speak.common.SentUtil;
import com.its.modules.speak.entity.SocialSpeak;
import com.its.modules.speak.service.SocialSpeakService;
import com.its.modules.sys.utils.UserUtils;

/**
 * 评论Controller
 * 
 * @author wmm
 * @version 2017-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/comment/socialComment")
public class SocialCommentController extends BaseController {

	@Autowired
	private SocialCommentService socialCommentService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SocialSpeakService socialSpeakService;

	private static final String SMSURL = "http://localhost:8080/ehomeapp/app/msgSend/commentMsg";

	@ModelAttribute
	public SocialComment get(@RequestParam(required = false) String id) {
		SocialComment entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = socialCommentService.get(id);
		}
		if (entity == null) {
			entity = new SocialComment();
		}
		return entity;
	}

	@RequiresPermissions("comment:socialComment:view")
	@RequestMapping(value = { "list", "" })
	public String list(SocialComment socialComment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SocialComment> page = socialCommentService.findPage(new Page<SocialComment>(request, response), socialComment);
		model.addAttribute("page", page);
		return "modules/comment/socialCommentList";
	}

	@RequiresPermissions("comment:socialComment:view")
	@RequestMapping(value = "form")
	public String form(SocialComment socialComment, Model model) {
		model.addAttribute("socialComment", socialComment);
		return "modules/comment/socialCommentForm";
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public String save(SocialComment socialComment, Model model, RedirectAttributes redirectAttributes) {
		String result = "1";
		socialComment.setCreatetime(new Date());
		socialComment.setUserid(UserUtils.getUser().getId());
		socialComment.setIscomment(1);
		socialComment.setDelflag(1);
//		if (!beanValidator(model, socialComment)) {
//			return form(socialComment, model);
//		}
		socialCommentService.save(socialComment);
		addMessage(redirectAttributes, "保存评论成功");

		int type = 2;// 消息类别 1 : 直接回复 2 : 回复主贴下其他人

		if (socialComment.getIscomment() == 1) {
			type = 1;
		}
		String userId = socialComment.getUserid();// 评论人的id
		String userNickname = accountService.get(userId).getNickname();// 评论人的昵称
		SocialSpeak socialSpeak = socialSpeakService.get(socialComment.getSpeakid());// 发言
		SocialSpeak ss = socialSpeakService.findUser(socialSpeak.getUserid());
		Account atAccount = accountService.get(ss.getAuserid());// 发言人
		String atUserNickname = atAccount.getNickname();// 发言人昵称
		String content = socialComment.getContent();// 评论内容
		String toUserId = atAccount.getId();// 发言人id
		
		String param = "type="+type+"&userId="+userId+"&userNickname="+userNickname+""
				+ "&atUserNickname="+atUserNickname+"&content="+content+"&toUserId="+toUserId;

		//即时通讯
		String ret = SentUtil.sendPost(SMSURL,param);
		if (ret.indexOf("失败") < 0) {
			return result;
		} else {
			result = "消息发送失败";
			return result;
		}

	}

	@RequiresPermissions("comment:socialComment:edit")
	@RequestMapping(value = "delete")
	public String delete(SocialComment socialComment, RedirectAttributes redirectAttributes) {
		socialCommentService.delete(socialComment);
		addMessage(redirectAttributes, "删除评论成功");
		return "redirect:" + Global.getAdminPath() + "/comment/socialComment/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "changeDelFlag", method = { RequestMethod.POST, RequestMethod.GET })
	public String changeDelFlag(String id, RedirectAttributes redirectAttributes) {
		String result = "1";
		SocialComment socialComment = new SocialComment();
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/comment/socialComment/?repage";
		}
		socialComment.setId(id);
		socialComment.setDelflag(0);
		int resultNum = socialCommentService.changeDelFlag(socialComment);
		if (resultNum == 0) {
			result = "0";
		}
		return result;
	}

}