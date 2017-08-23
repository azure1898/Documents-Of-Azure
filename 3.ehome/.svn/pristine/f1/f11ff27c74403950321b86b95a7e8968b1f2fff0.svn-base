/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.setup.web;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.persistence.Page;
import com.its.common.utils.DateUtils;
import com.its.common.utils.FileUtils;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.entity.BusinessSales;
import com.its.modules.setup.entity.BusinessServicetime;
import com.its.modules.setup.entity.BusinessUnit;
import com.its.modules.setup.service.BusinessCategorydictService;
import com.its.modules.setup.service.BusinessInfoService;
import com.its.modules.setup.service.BusinessSalesService;
import com.its.modules.setup.service.BusinessServicetimeService;
import com.its.modules.setup.service.BusinessUnitService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.service.SystemService;
import com.its.modules.sys.utils.UserUtils;

/**
 * 商家信息管理Controller
 * 
 * @author zhujiao
 * @version 2017-07-10
 */
@Controller
@RequestMapping(value = "${adminPath}/setup/businessInfo")
public class BusinessInfoController extends BaseController {

    @Autowired
    private BusinessInfoService businessInfoService;
    @Autowired
    private BusinessCategorydictService businessCategorydictService;
    @Autowired
    private BusinessServicetimeService businessServicetimeService;
    @Autowired
    private BusinessSalesService businessSalesService;
    @Autowired
    private BusinessUnitService businessUnitService;
    @Autowired
    private SystemService systemService;

    @ModelAttribute
    public BusinessInfo get(@RequestParam(required = false) String id) {
        BusinessInfo entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = businessInfoService.get(id);
        } else {
            entity = businessInfoService.get(UserUtils.getUser().getBusinessinfoId());
        }
        if (entity == null) {
            entity = new BusinessInfo();
        }
        return entity;
    }

    @RequiresPermissions("setup:businessInfo:view")
    @RequestMapping(value = { "list", "" })
    public String list(BusinessInfo businessInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<BusinessInfo> page = businessInfoService.findPage(new Page<BusinessInfo>(request, response), businessInfo);
        model.addAttribute("page", page);
        return "modules/setup/businessInfoList";
    }

    @RequiresPermissions("setup:businessInfo:view")
    @RequestMapping(value = "form")
    public String form(BusinessInfo businessInfo, Model model,HttpServletRequest request) {
        // 根据图片ID取得图片SRC
        try {
            businessInfo.setBusinessPic(MyFDFSClientUtils.get_fdfs_file_url(request,businessInfo.getBusinessPic()));
        } catch (IOException | MyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.addAttribute("businessInfo", businessInfo);
        model.addAttribute("allCategory", businessCategorydictService.findAllList());
        return "modules/setup/businessInfoForm";
    }

    @RequiresPermissions("setup:businessInfo:services")
    @RequestMapping(value = "edit")
    public String edit(BusinessInfo businessInfo, Model model) {
        model.addAttribute("businessInfo", businessInfo);
        model.addAttribute("allCategory", businessCategorydictService.findAllList());
        return "modules/setup/businessInfoEdit";
    }
    /**
     * 系统设置
     * 
     * @param businessInfo
     * @param model
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月18日 上午9:23:52
     */
    @RequiresPermissions("setup:businessInfo:settings")
    @RequestMapping(value = "settings")
    public String settings(BusinessInfo businessInfo, Model model) {
        model.addAttribute("businessInfo", businessInfo);
        return "modules/setup/settings";
    }

    @RequiresPermissions("setup:businessInfo:modifyPwd")
    @RequestMapping(value = "modifyPwd")
    public String modifyPwd(BusinessInfo businessInfo, String newPassword, Model model) {
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(newPassword)) {
            systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
            model.addAttribute("message", "修改密码成功");
        }
        model.addAttribute("businessInfo", businessInfo);
        return "modules/setup/settings";
    }
    

    /**
     * 商家营业设置
     * 
     * @param businessInfo
     * @param model
     * @param redirectAttributes
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月17日 上午10:42:02
     */
    @RequiresPermissions("setup:businessInfo:services")
    @RequestMapping(value = "updateBusiness")
    public String updateBusiness(BusinessInfo businessInfo, Model model, RedirectAttributes redirectAttributes) {
        // 最短上门时间
        if (businessInfo.getShortestArriveTime() != null && "".equals(businessInfo.getShortestArriveTime())) {
            businessInfo.setShortestArriveTime(null);
        }
        // 服务时间间隔
        if (businessInfo.getServiceTimeInterval() != null && "".equals(businessInfo.getServiceTimeInterval())) {
            businessInfo.setServiceTimeInterval(null);
        }
        // 上门服务费用
        if (businessInfo.getServiceCharge() != null && "".equals(businessInfo.getServiceCharge())) {
            businessInfo.setServiceCharge(null);
        }
        // 配送时间间隔
        if (businessInfo.getDistributeTimeInterval() != null && "".equals(businessInfo.getDistributeTimeInterval())) {
            businessInfo.setDistributeTimeInterval(null);
        }
        // 最短到达时间
        if (businessInfo.getShortestTime() != null && "".equals(businessInfo.getShortestTime())) {
            businessInfo.setShortestTime(null);
        }
        // 配送费用
        if (businessInfo.getDistributeCharge() != null && "".equals(businessInfo.getDistributeCharge())) {
            businessInfo.setDistributeCharge(null);
        }
        // 配送设置满额配送的金额和达标金额
        if (businessInfo.getFreeDistributeMoney() != null && "".equals(businessInfo.getFreeDistributeMoney())) {
            businessInfo.setFreeDistributeMoney(null);
        }
        if (businessInfo.getFullDistributeMoney() != null && "".equals(businessInfo.getFullDistributeMoney())) {
            businessInfo.setFullDistributeMoney(null);
        }

        businessInfoService.updateBusiness(businessInfo);
        addMessage(redirectAttributes, "修改商家信息成功");
        return "redirect:" + Global.getAdminPath() + "/setup/businessInfo/?repage";
    }

    /**
     * 保存商家基本信息
     * 
     * @param businessInfo
     * @param file
     * @param model
     * @param redirectAttributes
     * @return
     * @return String
     * @author zhujiao
     * @date 2017年7月17日 上午10:41:34
     */
    @RequiresPermissions("setup:businessInfo:edit")
    @RequestMapping(value = "save")
    public String save(BusinessInfo businessInfo, @RequestParam(value = "file", required = false) MultipartFile file, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        
        if(file.getSize()>0){
            businessInfo.setBusinessPic(FileUtils.uploadImg(request,file));
        }
        businessInfoService.updateBaseInfo(businessInfo);
        addMessage(redirectAttributes, "修改商家信息成功");
        return "redirect:" + Global.getAdminPath() + "/setup/businessInfo/?repage";
    }

    @ResponseBody
    @RequestMapping(value = "getInfo")
    public BusinessInfo getInfo(BusinessInfo businessInfo, Model model) {
        BusinessInfo info = businessInfo;
        return info;
    }

    @ResponseBody
    @RequestMapping(value = "updateState")
    public String updateState(BusinessInfo businessInfo, String state) {
        businessInfo.setBusinessState(state);
        businessInfoService.updateState(businessInfo);
        return "状态设置成功";
    }
    /**
     * 修改提醒设置
     * @param businessInfo
     * @param state
     * @return
     * @return String
     * @author zhujiao   
     * @date 2017年7月18日 下午6:33:58
     */
    @ResponseBody
    @RequestMapping(value = "updateSetting")
    public String updateSetting(BusinessInfo businessInfo, Model model) {
        businessInfoService.updateSetting(businessInfo);
        return "提醒设置成功";
    }

    /**
     * 根据时间间隔 获取 下拉时间列表 add by zhujiao
     * 
     * @param interval
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "bindList")
    public List<BusinessServicetime> bindList(String timetype, String businessinfoId) {
        List<BusinessServicetime> list = new ArrayList<BusinessServicetime>();
        list = businessServicetimeService.findAllList(timetype, businessinfoId);
        return list;
    }

    /**
     * 根据商家Id获取活动信息
     * 
     * @param businessinfoId
     * @return
     * @return List<BusinessSales>
     * @author zhujiao
     * @date 2017年7月17日 下午5:05:47
     */
    @ResponseBody
    @RequestMapping(value = "bindSales")
    public List<BusinessSales> bindSales(String businessinfoId) {
        List<BusinessSales> list = new ArrayList<BusinessSales>();
        list = businessSalesService.findAllList(businessinfoId);
        return list;
    }

    /**
     * 根据商家Id获取单位信息
     * 
     * @param businessinfoId
     * @return
     * @return List<BusinessUnit>
     * @author zhujiao
     * @date 2017年7月17日 下午5:07:23
     */
    @ResponseBody
    @RequestMapping(value = "bindUnit")
    public List<BusinessUnit> bindUnit(String businessinfoId) {
        List<BusinessUnit> list = new ArrayList<BusinessUnit>();
        list = businessUnitService.findAllList(businessinfoId);
        return list;
    }

    /**
     * 根据时间间隔 获取 下拉时间列表 add by zhujiao
     * 
     * @param interval
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "getTimeList")
    public List<Map<String, Object>> getTimeList(Integer interval) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        Integer num = interval / 60;
        if (num == 0) {
            num = 1;
        }
        for (int i = 0; i < 24 / num; i++) {
            map.put(i + "", String.format("%02d", i * num));
        }
        listmap.add(map);
        Integer rot = interval % 60;
        if (rot == 0) {
            map = new HashMap<String, Object>();
            map.put(0 + "", "00");
            listmap.add(map);
        } else {
            map = new HashMap<String, Object>();
            map.put(0 + "", "00");
            map.put(1 + "", "30");
            listmap.add(map);
        }
        return listmap;
    }

    /**
     * 效验时间是否符合规则
     * 
     * @author zhujiao
     * @param sHours
     * @param sMinutes
     * @param eHours
     * @param eMinutes
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getResult")
    private String getResult(String sHours, String sMinutes, String eHours, String eMinutes) {
        String[] arrsHours = sHours.split(",");
        String[] arrsMinutes = sMinutes.split(",");
        String[] arreHours = eHours.split(",");
        String[] arreMinutes = eMinutes.split(",");

        String[] stimes = new String[arrsHours.length];
        String[] etimes = new String[arrsHours.length];
        int count = 0;
        String msg = "";
        for (int i = 0; i < arrsHours.length; i++) {
            stimes[i] = arrsHours[i] + ":" + arrsMinutes[i];
            etimes[i] = arreHours[i] + ":" + arreMinutes[i];
            if (DateUtils.compareTo(stimes[i], etimes[i]) >= 0) {
                count += 1;
                msg = "时间段的开始时间应该小于结束时间";
            }
        }
        for (int i = 0; i < stimes.length - 1; i++) {
            if (DateUtils.compareTo(stimes[i + 1], etimes[i]) < 0) {
                count += 1;
                msg = "时间段的结束时间应该小于或者等于第二个时间段的开始时间";
            }
        }
        System.out.println("错误" + count + "条，消息提示为：" + msg);
        return msg;
    }

}