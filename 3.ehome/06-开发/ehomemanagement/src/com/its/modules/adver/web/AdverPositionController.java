/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.adver.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.adver.entity.AdverPosition;
import com.its.modules.adver.service.AdverPositionService;

/**
 * 位置管理Controller
 * 
 * @author ChenXiangyu
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/adver/adverPosition")
public class AdverPositionController extends BaseController {

    @Autowired
    private AdverPositionService adverPositionService;
    /** 前台CHECKBOX被选中时后台接收到的VALUE */
    private static final String CHECKBOX_CHECKED = "on";
    /** 是否开屏标记：开屏 */
    private static final String OPEN_SCREEN_FLAG_TRUE = "1";

    @ModelAttribute
    public AdverPosition get(@RequestParam(required = false) String id) {
        AdverPosition entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = adverPositionService.get(id);
        }
        if (entity == null) {
            entity = new AdverPosition();
        }
        return entity;
    }

    @RequiresPermissions("adver:adverPosition:view")
    @RequestMapping(value = { "list", "" })
    public String list(AdverPosition adverPosition, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<AdverPosition> page = adverPositionService.findPage(new Page<AdverPosition>(request, response), adverPosition);
        model.addAttribute("page", page);
        return "modules/adver/adverPositionList";
    }

    @RequiresPermissions("adver:adverPosition:view")
    @RequestMapping(value = "form")
    public String form(AdverPosition adverPosition, Model model) {
        model.addAttribute("adverPosition", adverPosition);
        return "modules/adver/adverPositionForm";
    }

    @RequiresPermissions(value={"adver:adverPosition:add","adver:adverPosition:edit"},logical=Logical.OR)
    @RequestMapping(value = "save")
    public String save(AdverPosition adverPosition, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, adverPosition)) {
            return form(adverPosition, model);
        }
        String tempScreenFlag = adverPosition.getOpenScreenFlag();
        if (tempScreenFlag != null && !tempScreenFlag.isEmpty() && CHECKBOX_CHECKED.equals(tempScreenFlag)) {
            adverPosition.setOpenScreenFlag(OPEN_SCREEN_FLAG_TRUE);
        } else {
            adverPosition.setOpenScreenFlag(StringUtils.EMPTY);
        }
        adverPositionService.save(adverPosition);
        addMessage(redirectAttributes, "保存位置信息成功");
        return "redirect:" + Global.getAdminPath() + "/adver/adverPosition/?repage";
    }

    @RequiresPermissions("adver:adverPosition:delete")
    @RequestMapping(value = "delete")
    public String delete(AdverPosition adverPosition, RedirectAttributes redirectAttributes) {
        adverPositionService.delete(adverPosition);
        addMessage(redirectAttributes, "删除位置信息成功");
        return "redirect:" + Global.getAdminPath() + "/adver/adverPosition/?repage";
    }

    /**
     * 根据产品线获取全部位置信息
     * 
     * @author zhujiao
     * @date 2017年7月4日 下午7:35:03
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "getPositonList")
    public List<AdverPosition> getPositonList(String moduleCode) {

        return adverPositionService.findAllList(moduleCode);
    }
    
    /**
     * 验证是否可为开屏
     * @param oldOpenScreenFlag 修改前开屏标记
     * @param openScreenFlag 修改后开屏标记
     * @return 是否可为开屏
     */
    @ResponseBody
    @RequiresPermissions("adver:adverPosition:view")
    @RequestMapping(value = "checkOpenScreenFlag")
    public String checkOpenScreenFlag(String oldOpenScreenFlag, String openScreenFlag, String oldModuleCode, String moduleCode) {
        AdverPosition adverPosition = new AdverPosition();
        adverPosition.setModuleCode(moduleCode);
            if (openScreenFlag !=null && (openScreenFlag.isEmpty() || openScreenFlag.equals(oldOpenScreenFlag)) 
                    && (moduleCode.isEmpty() || moduleCode.equals(oldModuleCode))) {
                return Boolean.TRUE.toString();
            } else if (openScreenFlag ==null || openScreenFlag.isEmpty() || moduleCode == null || moduleCode.isEmpty()) {
                return Boolean.TRUE.toString();
            } else if (openScreenFlag !=null && !openScreenFlag.isEmpty() 
                    && adverPositionService.getOpenScreenOfModuleCount(adverPosition) == 0) {
                return Boolean.TRUE.toString();
            } 
            return Boolean.FALSE.toString();
    }
    
    /**
	 * 验证位置名称是否有效
	 * 
	 * @param oldPositionName 更新前位置名称
	 * @param positionName 位置名称
	 * @param oldModuleCode 更新前产品线
	 * @param moduleCode 产品线
	 * @return 是否有效
	 */
	@ResponseBody
	@RequiresPermissions("adver:adverPosition:view")
	@RequestMapping(value = "checkPositionName")
	public String checkPositionName(String oldPositionName, String positionName, String oldModuleCode, String moduleCode) {
		if (positionName !=null && positionName.equals(oldPositionName) && moduleCode != null && moduleCode.equals(oldModuleCode)) {
			return "true";
		} else if (positionName !=null && adverPositionService.getPositionByPositionName(positionName, moduleCode) == null) {
			return "true";
		} else if (positionName == null || moduleCode == null) {
			// 未填写位置名称 或 未选择产品线
			return "true";
		}
		return "false";
	}
}