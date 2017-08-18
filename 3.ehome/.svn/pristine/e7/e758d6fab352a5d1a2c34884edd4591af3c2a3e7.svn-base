/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.module.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.business.entity.BusinessCategorydict;
import com.its.modules.business.service.BusinessCategorydictService;
import com.its.modules.business.service.BusinessInfoService;
import com.its.modules.module.entity.VillageLinerecombusitype;
import com.its.modules.module.entity.VillageLinerecombusitypedetail;
import com.its.modules.module.entity.VillageLinerecommodule;
import com.its.modules.module.entity.VillageLinerecomspecial;
import com.its.modules.module.entity.VillageLinerecomspecialdetail;
import com.its.modules.module.service.ModuleManageService;
import com.its.modules.property.entity.PropertyCompany;
import com.its.modules.property.service.PropertyCompanyService;
import com.its.modules.village.entity.VillageLine;
import com.its.modules.village.service.VillageLineService;

/**
 * 楼盘信息产品线Controller
 * 
 * @author zhujiao
 * @version 2017-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/module/villageLine")
public class VillageLineController extends BaseController {

    @Autowired
    private VillageLineService villageLineService;

    @Autowired
    private ModuleManageService moduleManageService;
    @Autowired
    private PropertyCompanyService propertyCompanyService;
    @Autowired
    private BusinessInfoService businessInfoService;
    @Autowired
    private BusinessCategorydictService businessCategorydictService;

    @ModelAttribute
    public VillageLine get(@RequestParam(required = false) String id) {
        VillageLine entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = villageLineService.getModel(id);
        }
        if (entity == null) {
            entity = new VillageLine();
        }
        return entity;
    }

    @RequiresPermissions("module:villageLine:view")
    @RequestMapping(value = { "list", "" })
    public String list(VillageLine villageLine, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<VillageLine> page = villageLineService.findLineList(new Page<VillageLine>(request, response), villageLine);
        // 所属物业公司下拉列表内容设置
        Map<String, String> propertyCompanyMap = new HashMap<String, String>();
        List<PropertyCompany> allPropertyCompany = propertyCompanyService.findList(new PropertyCompany());
        if (allPropertyCompany != null && !allPropertyCompany.isEmpty()) {
            for (PropertyCompany tempPropertyCompany : allPropertyCompany) {
                propertyCompanyMap.put(tempPropertyCompany.getId(), tempPropertyCompany.getCompanyName());
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("propertyCompanyMap", propertyCompanyMap);
        return "modules/module/villageLineList";
    }

    @RequiresPermissions("module:villageLine:setModule")
    @RequestMapping(value = "form")
    public String form(VillageLine villageLine, Model model, HttpServletRequest request) {
        model.addAttribute("villageLine", villageLine);
        // 获取排序之后生活和社区模块
        List<String> moduleIds = getModuleIds(villageLine.getCommunityModule());
        model.addAttribute("getCommunityModuleList", moduleManageService.getSetModuleList(moduleIds));
        List<String> LifeoduleIds = getModuleIds(villageLine.getLifeModule());
        model.addAttribute("getLifeModuleList", moduleManageService.getSetModuleList(LifeoduleIds));

        model.addAttribute("lifeModuleList", moduleManageService.getLifeModule());
        model.addAttribute("communityModuleList", moduleManageService.getCommunityModuleList());
        return "modules/module/setModule";
    }

    @RequiresPermissions("module:villageLine:batchSetModule")
    @RequestMapping(value = "batchFrom")
    public String batchFrom(VillageLine villageLine, Model model) {
        model.addAttribute("villageLine", villageLine);
        // 获取排序之后生活和社区模块
        List<String> moduleIds = getModuleIds(villageLine.getCommunityModule());
        model.addAttribute("getCommunityModuleList", moduleManageService.getSetModuleList(moduleIds));
        List<String> LifeoduleIds = getModuleIds(villageLine.getLifeModule());
        model.addAttribute("getLifeModuleList", moduleManageService.getSetModuleList(LifeoduleIds));

        model.addAttribute("lifeModuleList", moduleManageService.getLifeModule());
        model.addAttribute("communityModuleList", moduleManageService.getCommunityModuleList());
        return "modules/module/batchSetModule";
    }

    /**
     * 设置模块
     * 
     * @param villageLine
     * @param model
     * @param redirectAttributes
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月25日 上午10:31:24
     */
    @RequiresPermissions("module:villageLine:setModule")
    @RequestMapping(value = "setModule")
    public String setModule(VillageLine villageLine, Model model, RedirectAttributes redirectAttributes) {
        villageLine.setSetState("1");
        villageLine.setSetTime(new Date());
        villageLineService.setModule(villageLine);
        addMessage(redirectAttributes, "保存楼盘模块设置成功");
        return "redirect:" + Global.getAdminPath() + "/module/villageLine/?repage";
    }

    /**
     * 批量设置模块（根据楼盘信息设置模块数据）
     * 
     * @param villageLine
     * @param model
     * @param redirectAttributes
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月25日 上午10:31:40
     */
    @RequiresPermissions("module:villageLine:batchSetModule")
    @RequestMapping(value = "batchSetModule")
    public String batchSetModule(VillageLine villageLine, Model model, RedirectAttributes redirectAttributes) {
        villageLine.setSetState("1");
        villageLine.setSetTime(new Date());
        villageLineService.batchSetModule(villageLine);
        addMessage(redirectAttributes, "保存楼盘批量模块设置成功");
        return "redirect:" + Global.getAdminPath() + "/module/villageLine/?repage";
    }

    /**
     * 推荐管理
     * 
     * @param villageLine
     * @param request
     * @param response
     * @param model
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月26日 上午9:21:30
     */
    @RequiresPermissions("module:villageLine:recommendList")
    @RequestMapping(value = { "recommendList" })
    public String recommendList(VillageLine villageLine, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<VillageLine> page = villageLineService.findLineList(new Page<VillageLine>(request, response), villageLine);
        // 所属物业公司下拉列表内容设置
        Map<String, String> propertyCompanyMap = new HashMap<String, String>();
        List<PropertyCompany> allPropertyCompany = propertyCompanyService.findList(new PropertyCompany());
        if (allPropertyCompany != null && !allPropertyCompany.isEmpty()) {
            for (PropertyCompany tempPropertyCompany : allPropertyCompany) {
                propertyCompanyMap.put(tempPropertyCompany.getId(), tempPropertyCompany.getCompanyName());
            }
        }
        model.addAttribute("page", page);
        model.addAttribute("propertyCompanyMap", propertyCompanyMap);
        return "modules/module/recommendList";
    }

    /**
     * 设置推荐 -首页推荐页面
     * 
     * @param villageLine
     * @param model
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月26日 上午9:45:35
     */
    @RequiresPermissions("module:villageLine:mainRecom")
    @RequestMapping(value = "mainRecomFrom")
    public String mainRecomFrom(VillageLine villageLine, Model model) {
        model.addAttribute("villageLine", villageLine);
        // 获取排序之后首页推荐模块
        List<String> mainRecomIds = getModuleIds(villageLine.getMaintRecomModule());
        model.addAttribute("getMainList", moduleManageService.getSetModuleList(mainRecomIds));

        List<String> moduleIds = getModuleIds(villageLine.getCommunityModule() + villageLine.getLifeModule());
        model.addAttribute("moduleList", moduleManageService.getSetModuleList(moduleIds));
        return "modules/module/mainRecomModule";
    }

    /**
     * 推荐管理 - 首页推荐保存修改
     * 
     * @param villageLine
     * @param model
     * @param redirectAttributes
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月26日 上午11:26:38
     */
    @RequiresPermissions("module:villageLine:mainRecom")
    @RequestMapping(value = "updateMaintRecomModule")
    public String updateMaintRecomModule(VillageLine villageLine, Model model, RedirectAttributes redirectAttributes) {
        villageLine.setRecomSetState("1");
        villageLine.setRecomSetTime(new Date());
        villageLineService.updateMaintRecomModule(villageLine);
        addMessage(redirectAttributes, "保存首页设置成功");
        return "redirect:" + Global.getAdminPath() + "/module/villageLine/recommendList";
    }

    /**
     * 设置推荐 -社区推荐页面
     * 
     * @param villageLine
     * @param model
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年8月2日 下午6:25:01
     */
    @RequiresPermissions("module:villageLine:mainRecom")
    @RequestMapping(value = "communityRecomFrom")
    public String communityRecomFrom(VillageLine villageLine, Model model) {
        model.addAttribute("villageLine", villageLine);
        // 获取排序之后社区推荐模块
        List<String> communityRecomIds = getModuleIds(villageLine.getCommunityRecomModule());
        model.addAttribute("getCommunityList", moduleManageService.getSetModuleList(communityRecomIds));

        List<String> moduleIds = getModuleIds(villageLine.getCommunityModule());
        model.addAttribute("moduleList", moduleManageService.getSetModuleList(moduleIds));
        return "modules/module/communityRecomModule";
    }

    /**
     * 推荐管理 - 社区推荐保存修改
     * 
     * @param villageLine
     * @param model
     * @param redirectAttributes
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年8月2日 下午6:26:18
     */
    @RequiresPermissions("module:villageLine:mainRecom")
    @RequestMapping(value = "updateCommunityRecomModule")
    public String updateCommunityRecomModule(VillageLine villageLine, Model model, RedirectAttributes redirectAttributes) {
        villageLine.setRecomSetState("1");
        villageLine.setRecomSetTime(new Date());
        villageLineService.updateCommunityRecomModule(villageLine);
        addMessage(redirectAttributes, "保存首页设置成功");
        return "redirect:" + Global.getAdminPath() + "/module/villageLine/recommendList";
    }

    /**
     * 设置推荐 -优家推荐页面
     * 
     * @param villageLine
     * @param model
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月26日 上午11:33:26
     */
    @RequiresPermissions("module:villageLine:lifeRecom")
    @RequestMapping(value = "lifeRecomFrom")
    public String lifeRecomFrom(VillageLine villageLine, Model model, HttpServletRequest request) {
        //
     // ----------------------通过图片ID获取图片路径---开始--------------------

        for (VillageLinerecommodule imgItem : villageLine.getRecomModuleList()) {
            if (StringUtils.isNotBlank(imgItem.getPicUrl())) {
                try {
                    imgItem.setPicId(imgItem.getPicUrl());
                    imgItem.setPicUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imgItem.getPicUrl()));
                } catch (IOException | MyException e) {
                }
            }
        }
        for (VillageLinerecomspecial item : villageLine.getRecomSpecialList()) {
            for (VillageLinerecomspecialdetail imgItem : item.getRecomSpecialDetailList()) {
                if (StringUtils.isNotBlank(imgItem.getPicUrl())) {
                    try {
                        imgItem.setPicId(imgItem.getPicUrl());
                        imgItem.setPicUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imgItem.getPicUrl()));
                    } catch (IOException | MyException e) {
                    }
                }
            }
        }
        for (VillageLinerecombusitype item : villageLine.getRecomBusTypeList()) {
            for (VillageLinerecombusitypedetail imgItem : item.getRecomBusTypeDetailList()) {
                if (StringUtils.isNotBlank(imgItem.getPicUrl())) {
                    try {
                        imgItem.setPicId(imgItem.getPicUrl());
                        imgItem.setPicUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imgItem.getPicUrl()));
                    } catch (IOException | MyException e) {
                    }
                }
            }
        }
        // ----------------------通过图片ID获取图片路径---结束--------------------
        
        model.addAttribute("villageLine", villageLine);
        // 获取排序之后生活推荐模块
        List<String> lifeRecomIds = getModuleIds(villageLine.getLifeRecomModule());
        model.addAttribute("getlifeList", moduleManageService.getSetModuleList(lifeRecomIds));
        // 获取排序之后模块--获取该楼盘下有关联的所有模块
        List<String> moduleListIds = getModuleIds(villageLine.getCommunityModule() + villageLine.getLifeModule());
        model.addAttribute("getModuleList", moduleManageService.getSetModuleList(moduleListIds));
        // 获取该楼盘下有关联的所有分类
        model.addAttribute("getCategoryList", moduleManageService.getCategoryList(moduleListIds));
        // 获取排序后的生活模块的列表
        List<String> moduleIds = getModuleIds(villageLine.getLifeModule());
        model.addAttribute("lifeModuleList", moduleManageService.getSetModuleList(moduleIds));
        // 获取所有商家的列表
        model.addAttribute("allBusList", businessInfoService.findAllList());
        // 获取所有模块的列表
        model.addAttribute("moduleList", moduleManageService.findAllList());
        return "modules/module/lifeRecomModule";
    }

    /**
     * 推荐管理 - 优家推荐保存修改（生活）
     * 
     * @param villageLine
     * @param model
     * @param redirectAttributes
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月26日 上午11:26:38
     */
    @RequiresPermissions("module:villageLine:lifeRecom")
    @RequestMapping(value = "updateLifeRecomModule")
    public String updateLifeRecomModule(VillageLine villageLine, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        villageLine.setRecomSetState("1");
        villageLine.setRecomSetTime(new Date());
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            if (entity.getKey().startsWith("file")) {// 模块推荐的图片上传
                int index = Integer.parseInt(entity.getKey().replace("file", ""));
                VillageLinerecommodule villageLinerecommodule = villageLine.getRecomModuleList().get(index);
                // 上传文件名
                MultipartFile mf = entity.getValue();
                try {
                    if (mf.getSize() != 0) {
                        String picUrl = MyFDFSClientUtils.uploadFile(request, mf);
                        villageLinerecommodule.setPicUrl(picUrl);
                    }else{
                        if(StringUtils.isNoneBlank(villageLinerecommodule.getPicId())){
                            villageLinerecommodule.setPicUrl(null);
                        }
                    }
                } catch (Exception ex) {
                    addMessage(redirectAttributes, "上传模块推荐图片失败");
                }
            } else if (entity.getKey().startsWith("specialFile")) {// 专题推荐的图片上传
                String[] indexArr = entity.getKey().split("_");
                // 上传文件名
                MultipartFile mf = entity.getValue();
                List<VillageLinerecomspecialdetail> recomSpecialDetailList = villageLine.getRecomSpecialList().get(Integer.parseInt(indexArr[1])).getRecomSpecialDetailList();
                try {
                    if (mf.getSize() != 0) {
                        String picUrl = MyFDFSClientUtils.uploadFile(request, mf);
                        recomSpecialDetailList.get(Integer.parseInt(indexArr[2])).setPicUrl(picUrl);
                    }else{
                        if(StringUtils.isNoneBlank( recomSpecialDetailList.get(Integer.parseInt(indexArr[2])).getPicId())){
                            recomSpecialDetailList.get(Integer.parseInt(indexArr[2])).setPicUrl(null);
                        }
                    }
                } catch (Exception ex) {
                    addMessage(redirectAttributes, "上传专题推荐明细图片失败");
                }
            } else if (entity.getKey().startsWith("busTyefile")) {// 商家分类推荐的图片上传
                String[] indexArr = entity.getKey().split("_");
                // 上传文件名
                MultipartFile mf = entity.getValue();
                // 获取商家分类明细推荐的List
                List<VillageLinerecombusitypedetail> recomBusTypeDetailList = villageLine.getRecomBusTypeList().get(Integer.parseInt(indexArr[1])).getRecomBusTypeDetailList();
                try {
                    if (mf.getSize() != 0) {
                        String picUrl = MyFDFSClientUtils.uploadFile(request, mf);
                        recomBusTypeDetailList.get(Integer.parseInt(indexArr[2])).setPicUrl(picUrl);
                    }else{
                        if(StringUtils.isNoneBlank(recomBusTypeDetailList.get(Integer.parseInt(indexArr[2])).getPicId())){
                            recomBusTypeDetailList.get(Integer.parseInt(indexArr[2])).setPicUrl(null);
                        }
                    }
                } catch (Exception ex) {
                    addMessage(redirectAttributes, "上传商家分类明细图片失败");
                }
            }
        }
        villageLineService.updateLifeRecomModule(villageLine);
        addMessage(redirectAttributes, "保存首页设置成功");
        return "redirect:" + Global.getAdminPath() + "/module/villageLine/recommendList";
    }

    /**
     * 通过商家ID获取分类列表 （设置推荐中用到）
     * 
     * @param businessinfoId
     * @return
     * @return List<BusinessCategorydict>
     * @author zhujiao
     * @date 2017年8月3日 下午7:47:02
     */
    @ResponseBody
    @RequestMapping(value = "getTypeList")
    public List<BusinessCategorydict> getTypeList(String businessinfoId) {
        return businessCategorydictService.getListBybusId(businessinfoId);
    }

    /**
     * 获取选中的社区和生活模块的ID集合
     * 
     * @param str
     * @return
     * @return List<String>
     * @author zhujiao
     * @date 2017年7月26日 上午11:16:47
     */
    public List<String> getModuleIds(String str) {
        List<String> moduleIds = new ArrayList<>();
        if (str != null) {
            String[] arr = str.split(",");
            for (int i = 0; i < arr.length; i++) {
                moduleIds.add(arr[i]);
            }
        }
        return moduleIds;
    }

}