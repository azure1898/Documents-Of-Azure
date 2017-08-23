/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.speak.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.web.BaseController;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.account.entity.Account;
import com.its.modules.account.service.AccountService;
import com.its.modules.comment.entity.SocialComment;
import com.its.modules.comment.service.SocialCommentService;
import com.its.modules.praise.entity.SocialPraise;
import com.its.modules.praise.service.SocialPraiseService;
import com.its.modules.speak.common.SentUtil;
import com.its.modules.speak.entity.SocialSpeak;
import com.its.modules.speak.entity.SocialSpeakPic;
import com.its.modules.speak.service.SocialSpeakService;
import com.its.modules.subject.entity.SocialSubject;
import com.its.modules.subject.service.SocialSubjectService;
import com.its.modules.subrelation.entity.SocialSubRelation;
import com.its.modules.subrelation.service.SocialSubRelationService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;
import com.its.modules.village.entity.VillageInfo;
import com.its.modules.village.service.VillageInfoService;

/**
 * 发言管理Controller
 * @author wmm
 * @version 2017-08-01
 */
@Controller
@RequestMapping(value = "${adminPath}/speak/socialSpeak")
public class SocialSpeakController extends BaseController {

	@Autowired
	private SocialSpeakService socialSpeakService;
	
	@Autowired
	private SocialSubRelationService socialSubRelationService;
	
	@Autowired
	private SocialCommentService socialCommentService;
	
	@Autowired
	private SocialPraiseService socialPraiseService;
	
	@Autowired
	private SocialSubjectService socialSubjectService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private VillageInfoService villageInfoService;
	
	private static final String SMSURL = "http://localhost:8080/ehomeapp/app/msgSend/speakSendMsg";
	
	@ModelAttribute
	public SocialSpeak get(@RequestParam(required=false) String id) {
		SocialSpeak entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = socialSpeakService.get(id);
		}
		if (entity == null){
			entity = new SocialSpeak();
		}
		return entity;
	}
	
	@RequiresPermissions("speak:socialSpeak:view")
	@RequestMapping(value = {"list", ""})
	public String list(SocialSpeak socialSpeak, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SocialSpeak> page = socialSpeakService.findPage(new Page<SocialSpeak>(request, response), socialSpeak); 
		for(SocialSpeak ss : page.getList()) {
			List<SocialSubject> subList = socialSubjectService.findSubListBySpeakId(ss.getId());
			String villageName = "";
			String villageInfoIds = ss.getVillageinfoid();
			if(!StringUtils.isEmpty(villageInfoIds)) {
				String[] villageIds = villageInfoIds.split(",");
				for(int i=0; i<villageIds.length; i++) {
					String villageId = villageIds[i];
					VillageInfo villageInfo = villageInfoService.get(villageId);
					if(i == (villageIds.length-1)) {
						villageName+=villageInfo.getVillageName();
					} else {
						villageName+=(villageInfo.getVillageName()+",");
					}
				}
				ss.setVillageinfoname(villageName);
			}
			ss.setSubList(subList);
		}
		// 图片显示编辑
        for (SocialSpeak goodsItem : page.getList()) {
            if (StringUtils.isNotBlank(goodsItem.getImages())) {
                String[] imageNames = goodsItem.getImages().split(",");
                goodsItem.setPicSize(imageNames.length);
                // 图片url集合
                List<String> imageUrls = new ArrayList<String>();
                try {
                    imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
                } catch (IOException | MyException e) {
                }
                goodsItem.setImageUrls(imageUrls);
            }
        }
		model.addAttribute("page", page);
		model.addAttribute("proId", request.getParameter("addrpro"));
		model.addAttribute("cityId", request.getParameter("addrcity"));
		model.addAttribute("villageId", request.getParameter("villageInfoId"));
		return "modules/speak/socialSpeakList";
	}

	@RequiresPermissions("speak:socialSpeak:view")
	@RequestMapping(value = "form")
	public String form(SocialSpeak socialSpeak, Model model) {
		User user = UserUtils.getUser();
		String userId = user.getId();
		SocialSpeak ss = socialSpeakService.findUser(userId);
		socialSpeak.setForbitcomment("0");
		socialSpeak.setForbidforward("0");
		model.addAttribute("socialSpeak", socialSpeak);
		if(ss != null) {
			model.addAttribute("auserid", ss.getAuserid());
			model.addAttribute("ausername", ss.getAusername());
		}
		return "modules/speak/socialSpeakForm";
	}
	
	@RequiresPermissions("speak:socialSpeak:view")
	@RequestMapping(value = "detail")
	public String detail(String id, Model model, HttpServletRequest request) {
		SocialSpeak socialSpeak = new SocialSpeak();
		socialSpeak.setId(id);
		SocialSpeak speak = socialSpeakService.findById(socialSpeak);
		speak.setDelflag(1);
		speak.setReadnum((Integer.parseInt(speak.getReadnum() == null ? "0" : speak.getReadnum())+1)+"");
		socialSpeakService.save(speak);
		SocialComment socialComment = new SocialComment();
		socialComment.setSpeakid(id);
		List<SocialComment> commentList = socialCommentService.findBySpeakId(socialComment);
		List<SocialComment> socialCommentList = new ArrayList<SocialComment>();
		for(SocialComment comment : commentList) {
			socialCommentList.add(comment);
			List<SocialComment> replyComment = socialCommentService.findChildComment(comment);
			socialCommentList.addAll(replyComment);
		}
		List<SocialSpeak> ssfList = socialSpeakService.findForwardsByRootId(id);
		SocialPraise socialPraise = new SocialPraise();
		socialPraise.setPid(id);
		socialPraise.setType("1");
		List<SocialPraise> praise = socialPraiseService.findBySpeakId(socialPraise);
		
		// 图片显示编辑
       
        if (StringUtils.isNotBlank(speak.getImages())) {
            String[] imageNames = speak.getImages().split(",");
            speak.setPicSize(imageNames.length);
            // 图片url集合
            List<String> imageUrls = new ArrayList<String>();
            try {
            	for(int i=0; i<imageNames.length; i++) {
            		imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[i] + "_compress2"));
            	}
            } catch (IOException | MyException e) {
            }
            speak.setImageUrls(imageUrls);
        }
		model.addAttribute("socialSpeak", speak);
		model.addAttribute("socialComment", socialCommentList);
		model.addAttribute("commentSize", socialCommentList.size());
		model.addAttribute("socialForward", ssfList);
		model.addAttribute("fowardSize", ssfList.size());
		model.addAttribute("socialPraise", praise);
		model.addAttribute("praiseSize", praise.size());
		return "modules/speak/socialSpeakDetail";
	}

	@RequiresPermissions("speak:socialSpeak:edit")
	@RequestMapping(value = "save")
	public String save(SocialSpeak socialSpeak, Model model, RedirectAttributes redirectAttributes, 
			HttpServletRequest request, HttpServletResponse response) {
		socialSpeak.setCreatetime(new Date());
		socialSpeak.setUserid(socialSpeak.getAuserid());
		socialSpeak.setIstop("0");
		socialSpeak.setReadnum("0");
		socialSpeak.setIsspeak(1);
		socialSpeak.setDelflag(1);
		if (!beanValidator(model, socialSpeak)){
			return form(socialSpeak, model);
		}
		// 图片上传
        List<String> img_file_id_list = new ArrayList<String>();
        if (socialSpeak.getPicList() != null) {

            for (SocialSpeakPic goodsInfoPic : socialSpeak.getPicList()) {
                if (StringUtils.isEmpty(goodsInfoPic.getImgBase64())) {
                    continue;
                }
                String img_file_id;
                try {
                    // 取得文件类型
                    String fileType = goodsInfoPic.getType().split("/")[1];
                    // 将图片上传至工具类，并压缩图片
                    img_file_id = MyFDFSClientUtils.uploadFile(goodsInfoPic.getImgBase64(), fileType,
                            true, request);
                    img_file_id_list.add(img_file_id);
                } catch (IOException e) {
                    addMessage(model, "图片保存失败");
                    model.addAttribute("type", "error");
                    // 返回列表页面并保持排序
                    return list(new SocialSpeak(), request, response, model);
                } catch (MyException e) {
                    addMessage(model, "图片保存失败");
                    model.addAttribute("type", "error");
                    // 返回列表页面并保持排序
                    return list(new SocialSpeak(), request, response, model);
                }

            }
        }
        StringBuffer imgUrls = new StringBuffer();
        for (String imgUrl : img_file_id_list) {
        	imgUrls.append(imgUrl);
        	imgUrls.append(",");
        }
        socialSpeak.setImages(imgUrls.toString());
		socialSpeakService.save(socialSpeak);
		String tag = socialSpeak.getTag();
		SocialSubject socialSubject = new SocialSubject();
		if(!StringUtils.isEmpty(tag)) {
			tag = "#"+tag+"#";
			socialSubject = socialSubjectService.findByTag(tag);
			if(socialSubject == null || socialSubject == null) {
				socialSubject = new SocialSubject();
				socialSubject.setSubname(tag);
				socialSubject.setIsrecommend("0");
				socialSubject.setVillageInfoId(socialSpeak.getVillageinfoid());
				socialSubject.setCreaterid(socialSpeak.getUserid());
				Account account = accountService.get(socialSpeak.getUserid());
				socialSubject.setCreatername(account.getNickname());
				socialSubject.setCreatetime(new Date());
				socialSubjectService.save(socialSubject);
			}
		}
		SocialSpeak newSocialSpeak = socialSpeakService.get(socialSpeak);
		SocialSubRelation socialSubRelation = new SocialSubRelation();
		socialSubRelation.setSpeakid(newSocialSpeak.getId());
		if(!StringUtils.isEmpty(tag)) {
			socialSubRelation.setSubjectid(socialSpeak.getSubjectid());
		} else {
			socialSubRelation.setSubjectid(socialSubject.getId());
		}
		socialSubRelationService.save(socialSubRelation);
		addMessage(redirectAttributes, "保存发言成功");
		
		Account account = accountService.get(socialSpeak.getAuserid());
		
		String villageInfoIds = socialSpeak.getVillageinfoid();
		String[] villageIds = villageInfoIds.split(",");
		
		String param = "userId="+account.getId()+"&userNickname="+account.getNickname()+""
				+ "content="+socialSpeak.getContent()+"&villageIds="+villageIds;
		
		String ret = SentUtil.sendPost(SMSURL,param);
		if (ret.indexOf("失败") < 0) {
			return "redirect:"+Global.getAdminPath()+"/speak/socialSpeak/?repage";
		} else {
			return "消息发送失败";
		}
	}
	
	@RequiresPermissions("speak:socialSpeak:edit")
	@RequestMapping(value = "delete")
	public String delete(SocialSpeak socialSpeak, RedirectAttributes redirectAttributes) {
		socialSpeakService.delete(socialSpeak);
		addMessage(redirectAttributes, "删除发言成功");
		return "redirect:"+Global.getAdminPath()+"/speak/socialSpeak/?repage";
	}
	
	@RequiresPermissions(value="speak:socialSpeak:edit")
	@ResponseBody
	@RequestMapping(value = "changeDelFlag")
	public String changeDelFlag(String id, RedirectAttributes redirectAttributes) {
		String result = "1";
		SocialSpeak socialSpeak = new SocialSpeak();
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/speak/socialSpeak/?repage";
		}
		socialSpeak.setId(id);
		socialSpeak.setDelflag(0);
		int resultNum = socialSpeakService.changeDelFlag(socialSpeak);
		if(resultNum == 0) {
			result = "0";
		}
		return result;
	}
	
	@RequiresPermissions(value="speak:socialSpeak:edit")
	@ResponseBody
	@RequestMapping(value = "changeTop")
	public String changeTop(String id, String istop, RedirectAttributes redirectAttributes) {
		String result = "1";
		SocialSpeak socialSpeak = new SocialSpeak();
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/speak/socialSpeak/?repage";
		}
		socialSpeak.setId(id);
		socialSpeak.setIstop(istop);
		if("0".equals(istop)) {
			socialSpeak.setToptime(null);
		} else {
			socialSpeak.setToptime(new Date());
		}
		int resultNum = socialSpeakService.changeTop(socialSpeak);
		if(resultNum == 0) {
			result = "0";
		}
		return result;
	}

}