/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.adver.web;

import java.io.IOException;
import java.util.List;

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
import com.its.modules.adver.entity.AdverManage;
import com.its.modules.adver.entity.AdverPosition;
import com.its.modules.adver.service.AdverManageService;
import com.its.modules.adver.service.AdverPositionService;
import com.its.modules.business.entity.BusinessInfo;
import com.its.modules.business.service.BusinessCategorydictService;
import com.its.modules.module.service.ModuleManageService;

/**
 * 广告管理-发布管理Controller
 * 
 * @author zhujiao
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/adver/adverManage")
public class AdverManageController extends BaseController {

    @Autowired
    private AdverManageService adverManageService;
    @Autowired
    private ModuleManageService moduleManageService;
    @Autowired
    private BusinessCategorydictService businessCategorydictService;

    @ModelAttribute
    public AdverManage get(@RequestParam(required = false) String id) {
        AdverManage entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = adverManageService.get(id);
        }
        if (entity == null) {
            entity = new AdverManage();
        }
        return entity;
    }

    @RequiresPermissions("adver:adverManage:view")
    @RequestMapping(value = { "list", "" })
    public String list(AdverManage adverManage, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<AdverManage> page = adverManageService.findPage(new Page<AdverManage>(request, response), adverManage);
        // 图片显示编辑
        for (AdverManage imgItem : page.getList()) {
            if (StringUtils.isNotBlank(imgItem.getAdverPic())) {
                try {
                    imgItem.setAdverPic(MyFDFSClientUtils.get_fdfs_file_url(request, imgItem.getAdverPic()));
                } catch (IOException | MyException e) {
                }
            }
        }
        model.addAttribute("page", page);
        model.addAttribute("adverManage", adverManage);
        return "modules/adver/adverManageList";
    }

    @RequiresPermissions("adver:adverManage:view")
    @RequestMapping(value = "form")
    public String form(AdverManage adverManage, Model model,HttpServletRequest request) {
        
        // 根据图片ID取得图片SRC
        try {
            adverManage.setAdverPic(MyFDFSClientUtils.get_fdfs_file_url(request,adverManage.getAdverPic()));
        } catch (IOException | MyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        model.addAttribute("adverManage", adverManage);
        model.addAttribute("allModule", moduleManageService.findAllList());
        model.addAttribute("allCategory", businessCategorydictService.findAllList());
        return "modules/adver/adverManageForm";
    }

    @RequiresPermissions(value = { "adver:adverManage:edit", "adver:adverManage:add" }, logical = Logical.OR)
    @RequestMapping(value = "save")
    public String save(AdverManage adverManage, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (!beanValidator(model, adverManage)) {
            return form(adverManage, model,request);
        }
        if (adverManage.getSkipTime() != null && ("").equals(adverManage.getSkipTime())) {
            adverManage.setSkipTime(null);
        }
        if (adverManage.getDisplayTimeInterval() != null && ("").equals(adverManage.getDisplayTimeInterval())) {
            adverManage.setDisplayTimeInterval(null);
        }
        // 文件上传处理
        if(file.getSize()>0){
            adverManage.setAdverPic(FileUtils.uploadImg(request,file));
        }
        adverManageService.saveAdverManage(adverManage);
        addMessage(redirectAttributes, "保存广告信息成功");
        return "redirect:" + Global.getAdminPath() + "/adver/adverManage/?repage";
    }

    @RequiresPermissions("adver:adverManage:delete")
    @RequestMapping(value = "delete")
    public String delete(AdverManage adverManage, RedirectAttributes redirectAttributes) {
        adverManageService.delete(adverManage);
        addMessage(redirectAttributes, "删除广告信息成功");
        return "redirect:" + Global.getAdminPath() + "/adver/adverManage/?repage";
    }

    /**
     * 验证广告标题是否有效
     * 
     * @param oldName
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkName")
    public String checkName(String oldName, String name) {
        if (name != null && name.equals(oldName)) {
            return "true";
        } else if (name != null && adverManageService.getModelByName(name) == null) {
            return "true";
        }
        return "false";
    }

}