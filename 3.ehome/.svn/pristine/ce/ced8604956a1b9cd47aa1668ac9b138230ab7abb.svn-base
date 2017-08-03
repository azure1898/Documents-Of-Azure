/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.its.common.web.BaseController;
import com.its.common.utils.StringUtils;
import com.its.modules.goods.entity.GoodsSkuPrice;
import com.its.modules.goods.entity.SkuKey;
import com.its.modules.goods.entity.SkuValue;
import com.its.modules.goods.service.GoodsSkuPriceService;
import com.its.modules.goods.service.SkuKeyService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

/**
 * 商品规格名称Controller
 * @author liuhl
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/skuKey")
public class SkuKeyController extends BaseController {

	/** 商品规格名称Service */
	@Autowired
	private SkuKeyService skuKeyService;
	
	/** 商品规格价格Service */
	@Autowired
	private GoodsSkuPriceService goodsSkuPriceService;
	
	@ModelAttribute
	public SkuKey get(@RequestParam(required=false) String id) {
		SkuKey entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = skuKeyService.get(id);
		}
		if (entity == null){
			entity = new SkuKey();
		}
		return entity;
	}
	
	@RequiresPermissions("goods:skuKey:view")
	@RequestMapping(value = {"list", ""})
	public String list(SkuKey skuKey, HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        // 根据商家ID取得显示信息
        skuKey.setBusinessInfoId(user.getBusinessinfoId());
		Page<SkuKey> page = skuKeyService.findPage(new Page<SkuKey>(request, response), skuKey); 
		model.addAttribute("page", page);
		return "modules/goods/skuKeyList";
	}

	@RequiresPermissions("goods:skuKey:view")
	@RequestMapping(value = "form")
	public String form(SkuKey skuKey, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("skuKey", skuKey);
		Page<SkuKey> page = skuKeyService.findPage(new Page<SkuKey>(request, response), skuKey); 
		model.addAttribute("page", page);
		return "modules/goods/skuKeyList";
	}

	/**
	 * 规格名称以及规格项目的更新操作
	 * 
	 * @param skuKey 规格名称
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("goods:skuKey:edit")
	@RequestMapping(value = "save")
	public String save(SkuKey skuKey, Model model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, skuKey)){
			return form(skuKey,request,response, model);
		}
		// 判断规格项删除前是否正在使用
        for (SkuValue skuValue : skuKey.getSkuValueList()) {
            if (skuValue.getId() == null) {
                continue;
            }
            boolean deleFlg = !SkuValue.DEL_FLAG_NORMAL.equals(skuValue.getDelFlag());
            // 如果该项目进行的是删除操作
            if (deleFlg) {
            	// 根据规格项id取得在使用的规格名称
        		List<GoodsSkuPrice> goodsSkuPriceList = goodsSkuPriceService.getGoodsSkuPriceBySkuValueID(skuValue);
        		// 如果该规格项正在使用
        		if (goodsSkuPriceList != null && goodsSkuPriceList.size() > 0) {
        			StringBuffer sb = new StringBuffer();
                    sb.append("还有商品使用“");
                    sb.append(skuKey.getName());
                    sb.append("”中的“");
                    sb.append(skuValue.getName());
                    sb.append("”规格，请确认删除商品后再尝试删除规格。");
                    model.addAttribute("type", "error");
                    addMessage(model, sb.toString());
                    return form(skuKey,request,response, model);
        		}
            }
        }

        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        //从SESSION中取得商家信息
        skuKey.setBusinessInfoId(user.getBusinessinfoId());

        // 现阶段只允许添加一个规格，后期改掉的话请删除下面代码
        List<SkuKey> skuKeylist = skuKeyService.findList(skuKey);
        if (StringUtils.isBlank(skuKey.getId()) && skuKeylist != null && skuKeylist.size() > 0) {
        	addMessage(redirectAttributes, "现阶段只允许添加一个规格");
        	redirectAttributes.addFlashAttribute("type", "error");
    		return "redirect:"+Global.getAdminPath()+"/goods/skuKey/?repage";
        }

        // 数据库插入操作
		skuKeyService.save(skuKey);
		addMessage(redirectAttributes, "保存商品规格名称成功");
		return "redirect:"+Global.getAdminPath()+"/goods/skuKey/?repage";
	}
	
	/**
	 * 规格删除操作
	 * 
	 * @param skuKey 商品规格名称Entity
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("goods:skuKey:edit")
	@RequestMapping(value = "delete")
	public String delete(SkuKey skuKey, RedirectAttributes redirectAttributes) {
		// 根据规格名称id取得在使用的规格名称
		List<GoodsSkuPrice> goodsSkuPriceList = goodsSkuPriceService.getGoodsSkuPriceBySkuKeyID(skuKey);
		// 如果该规格名称正在使用
		if (goodsSkuPriceList != null && goodsSkuPriceList.size() > 0) {
			StringBuffer sb = new StringBuffer();
            sb.append("还有商品使用“");
            sb.append(skuKey.getName());
            sb.append("”规格，请确认删除商品后再尝试删除规格。");
            redirectAttributes.addFlashAttribute("type", "error");
            addMessage(redirectAttributes, sb.toString());
			return "redirect:"+Global.getAdminPath()+"/goods/skuKey/?repage";
		}
		skuKeyService.delete(skuKey);
		addMessage(redirectAttributes, "删除商品规格名称成功");
		return "redirect:"+Global.getAdminPath()+"/goods/skuKey/?repage";
	}

}