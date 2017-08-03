/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.goods.entity.GoodsInfo;
import com.its.modules.goods.service.GoodsInfoService;

/**
 * 商品信息Controller
 * 
 * @author test
 * @version 2017-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsInfo")
public class GoodsInfoController extends BaseController {

    /**
     * 商品信息Service
     */
    @Autowired
    private GoodsInfoService goodsInfoService;

    /**
     * 商品信息修改新建的预处理
     * 
     * @param id
     *            商品ID
     * @return
     */
    @ModelAttribute
    public GoodsInfo get(@RequestParam(required = false) String id) {
        GoodsInfo entity = null;
        // 修改的场合
        if (StringUtils.isNotBlank(id)) {
            entity = goodsInfoService.get(id);
        }
        if (entity == null) {
            entity = new GoodsInfo();
        }
        return entity;
    }

    /**
     * 通过商家ID获取全部商品信息列表
     * @param businessInfoId
     * @return
     * @return List<GoodsInfo>
     * @author zhujiao
     * @date 2017年7月19日 下午4:06:13
     */
    @ResponseBody
    @RequestMapping(value = "bindGoodsList")
    public List<GoodsInfo> bindGoodsList(String businessInfoId) {
        List<GoodsInfo> list = new ArrayList<GoodsInfo>();
        list = goodsInfoService.findAllList(businessInfoId);
        return list;
    }
}