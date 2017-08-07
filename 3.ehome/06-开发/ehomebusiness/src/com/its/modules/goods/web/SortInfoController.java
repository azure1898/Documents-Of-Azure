/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.its.common.config.Global;
import com.its.common.web.BaseController;
import com.its.common.utils.StringUtils;
import com.its.modules.goods.entity.GoodsInfo;
import com.its.modules.goods.entity.SortInfo;
import com.its.modules.goods.service.GoodsInfoService;
import com.its.modules.goods.service.SortInfoService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

/**
 * 商品分类信息Controller
 * @author liuhl
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/sortInfo")
public class SortInfoController extends BaseController {

    @Autowired
    private SortInfoService sortInfoService;
    
    @Autowired
    private GoodsInfoService goodsInfoService;
    
    @ModelAttribute
    public SortInfo get(@RequestParam(required=false) String id) {
        SortInfo entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = sortInfoService.get(id);
        }
        if (entity == null){
            entity = new SortInfo();
        }
        return entity;
    }
    
    @RequestMapping(value = {"list", ""})
    public String list(SortInfo sortInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        // 只查询出当前用户的分类信息
        sortInfo.setBusinessInfoId(user.getBusinessinfoId());
        // 只查询商品分类信息
        sortInfo.setType("0");
        List<SortInfo> list = sortInfoService.findList(sortInfo); 
        model.addAttribute("list", list);
        return "modules/goods/sortInfoList";
    }

    @RequestMapping(value = "save")
    public String save(SortInfo sortInfo, Model model, RedirectAttributes redirectAttributes) {

        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        //从SESSION中取得商家信息
        sortInfo.setBusinessInfoId(user.getBusinessinfoId());
        //从商品分类页面追加的固定为0
        sortInfo.setType("0");
        //创建者为当前用户
        sortInfo.setCreateBy(user);
        //当前时间
        sortInfo.setCreateDate(new Date());
        if (StringUtils.isNotBlank(sortInfo.getId())) {
            //更新这为当前用户
            sortInfo.setUpdateBy(user);
            //当前时间
            sortInfo.setUpdateDate(new Date());
        }
        if (!beanValidator(redirectAttributes, sortInfo)){
            return "redirect:"+Global.getAdminPath()+"/goods/sortInfo/?repage";
        }
        sortInfoService.save(sortInfo);
        addMessage(redirectAttributes, "保存商品分类成功");
        return "redirect:"+Global.getAdminPath()+"/goods/sortInfo/?repage";
    }
    
    @RequestMapping(value = "delete")
    public String delete(SortInfo sortInfo, RedirectAttributes redirectAttributes, Model model) {
        // 根据商品分类ID来取得商品信息
        List<GoodsInfo> goodsInfoList = goodsInfoService.findGoodsInfoList(sortInfo.getId());
        // 如果该分类已经被应用在商品中则无法删除
        if (goodsInfoList != null && goodsInfoList.size() > 0) {
            //SortInfo sortInfo = sortInfoService.get(sortInfo.getId());
            StringBuffer sb = new StringBuffer();
            sb.append("还有商品使用“");
            sb.append(sortInfo.getName());
            sb.append("”分类，请确认删除商品后再尝试删除分类。");
            redirectAttributes.addFlashAttribute("type", "error");
            addMessage(redirectAttributes, sb.toString());
            return "redirect:"+Global.getAdminPath()+"/goods/sortInfo/?repage";
        } else {
            sortInfoService.delete(sortInfo);
            addMessage(redirectAttributes, "删除商品分类成功");
            return "redirect:"+Global.getAdminPath()+"/goods/sortInfo/?repage";
        }
    }

}