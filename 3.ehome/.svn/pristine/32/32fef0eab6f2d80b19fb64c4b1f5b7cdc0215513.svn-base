/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.goods.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.its.common.persistence.Page;
import com.its.common.web.BaseController;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.modules.goods.entity.GoodsInfo;
import com.its.modules.goods.entity.GoodsInfoPic;
import com.its.modules.goods.entity.GoodsSkuPrice;
import com.its.modules.goods.entity.SkuKey;
import com.its.modules.goods.entity.SortInfo;
import com.its.modules.goods.service.GoodsInfoService;
import com.its.modules.goods.service.SkuKeyService;
import com.its.modules.goods.service.SortInfoService;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.entity.BusinessUnit;
import com.its.modules.setup.service.BusinessInfoService;
import com.its.modules.setup.service.BusinessUnitService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

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
     * 商品分类信息Service
     */
    @Autowired
    private SortInfoService sortInfoService;

    /**
     * 商品信息Service
     */
    @Autowired
    private GoodsInfoService goodsInfoService;

    /** 商品规格名称Service */
    @Autowired
    private SkuKeyService skuKeyService;

    /** 商家单位信息Service */
    @Autowired
    private BusinessUnitService businessUnitService;

    /** 商家信息管理Service */
    @Autowired
    private BusinessInfoService businessInfoService;

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
     * 商品管理列表显示
     * 
     * @param goodsInfo
     *            检索信息
     * @param sortItem
     *            排序项目
     * @param sort
     *            排序
     * @param warnNum
     *            显示库存警告商品
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = { "list", "" })
    public String list(GoodsInfo goodsInfo, @RequestParam(required = false) String sortItem,
            @RequestParam(required = false) String sort, @RequestParam(required = false) String warnNum,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();

        // 根据商家ID取得显示信息
        SortInfo sortInfo = new SortInfo();
        sortInfo.setBusinessInfoId(user.getBusinessinfoId());
        // 应该显示商品分类
        sortInfo.setType("0");
        // 根据登录的商家ID取得分类信息
        List<SortInfo> sortInfoList = sortInfoService.findList(sortInfo);
        model.addAttribute("sortInfoList", sortInfoList);

        // 只显示属于当前商家的商品
        goodsInfo.setBusinessInfoId(user.getBusinessinfoId());
        goodsInfo.setSort(sort);
        goodsInfo.setSortItem(sortItem);

        // 库存预警数量
        goodsInfo.setWarnNum(warnNum);
        // 一览显示信息取得
        Page<GoodsInfo> page = goodsInfoService.findPage(new Page<GoodsInfo>(request, response), goodsInfo);

        // 图片显示编辑
        for (GoodsInfo goodsItem : page.getList()) {
            if (StringUtils.isNotBlank(goodsItem.getImgs())) {
                String[] imageNames = goodsItem.getImgs().split(",");
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
        model.addAttribute("goodsInfo", goodsInfo);
        return "modules/goods/goodsInfoList";
    }

    /**
     * 商品新建修改页面预处理
     * 
     * @param goodsInfo
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
     * @param requestSrc
     *            请求源
     * @param model
     * @return
     */
    @RequestMapping(value = "form")
    public String form(GoodsInfo goodsInfo, @RequestParam(required = false) String sortItem,
            @RequestParam(required = false) String sort, @RequestParam(required = false) String requestSrc,
            HttpServletRequest request, Model model) {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        SortInfo sortInfo = new SortInfo();
        // 根据商家ID取得显示信息
        sortInfo.setBusinessInfoId(user.getBusinessinfoId());

        // 分类应该显示商品分类
        sortInfo.setType("0");
        // 根据登录的商家ID取得分类信息
        List<SortInfo> sortInfoList = sortInfoService.findList(sortInfo);
        model.addAttribute("sortInfoList", sortInfoList);

        // 根据登陆者商家ID取得对应商家信息
        BusinessInfo businessInfo = businessInfoService.get(user.getBusinessinfoId());
        // 如果为1则启用自定义单位
        if (businessInfo != null && "1".equals(businessInfo.getCustomUnitFlag())) {
            // 根据商家ID取得自定义分类
            BusinessUnit businessUnit = new BusinessUnit();
            businessUnit.setBusinessInfoId(user.getBusinessinfoId());
            // 取得商家自定义单位
            List<BusinessUnit> businessUnitList = businessUnitService.findList(businessUnit);
            model.addAttribute("businessUnitList", businessUnitList);
        }

        // 如果该商品有图片的信息
        if (StringUtils.isNotBlank(goodsInfo.getImgs())) {
            String[] imageNames = goodsInfo.getImgs().split(",");
            // 保存画面显示用URL
            List<GoodsInfoPic> imageUrls = new ArrayList<GoodsInfoPic>();
            for (int i = 0; i < imageNames.length; i++) {
                try {
                    // 根据DB中文件ID取得图片URL
                    GoodsInfoPic goodsInfoPic = new GoodsInfoPic();
                    goodsInfoPic.setImgUrl(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[i]));
                    goodsInfoPic.setMyfileid(imageNames[i]);
                    imageUrls.add(goodsInfoPic);
                } catch (IOException | MyException e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("imgUrls", imageUrls);
        }
        // 规格复选框检索条件
        SkuKey skuKey = new SkuKey();
        // 根据商家ID取得显示信息
        skuKey.setBusinessInfoId(user.getBusinessinfoId());
        // 虽然现阶段只有1种规格，为了以后扩展方便设为LIST
        List<SkuKey> skuKeyList = skuKeyService.findSkuKeyAndValueList(skuKey);
        model.addAttribute("skuKeyList", skuKeyList);

        // 将LIST画面的排序信息暂时存放在FORM画面中
        goodsInfo.setSort(sort);
        goodsInfo.setSortItem(sortItem);
        model.addAttribute("goodsInfo", goodsInfo);
        // 将请求源传到jsp页面
        model.addAttribute("requestSrc", requestSrc != null ? requestSrc : StringUtils.EMPTY);
        return "modules/goods/goodsInfoForm";
    }

    /**
     * 商品信息保存处理
     * 
     * @param goodsInfo
     *            商品信息Entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save")
    public String save(GoodsInfo goodsInfo, @RequestParam(required = false) String sortItem,
            @RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
            Model model) {

        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();

        // 商家ID设定
        goodsInfo.setBusinessInfoId(user.getBusinessinfoId());

        // 如果本身没有图片，且画面上没有添加
        if (StringUtils.isBlank(goodsInfo.getImgs())
                && (goodsInfo.getPicList() == null || goodsInfo.getPicList().size() == 0)) {
            addMessage(model, "未选择任何图片");
            model.addAttribute("type", "error");
            return form(goodsInfo, goodsInfo.getSortItem(), goodsInfo.getSort(), null, request, model);

        }
        // 删除后的图片名称
        String imgUrlsForUpdate = goodsInfo.getImgs();
        if (StringUtils.isNotBlank(goodsInfo.getDelImageName())) {
            String[] delImgName = goodsInfo.getDelImageName().split(",");
            for (String imgName : delImgName) {
                imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName + ",", "");
                // 避免该文件在最后一个
                imgUrlsForUpdate = imgUrlsForUpdate.replace(imgName, "");
            }
        }
        // 本身有图片，但全部删除场合
        if (StringUtils.isBlank(imgUrlsForUpdate)
                && (goodsInfo.getPicList() == null || goodsInfo.getPicList().size() == 0)) {
            addMessage(model, "未选择任何图片");
            model.addAttribute("type", "error");
            return form(goodsInfo, goodsInfo.getSortItem(), goodsInfo.getSort(), null, request, model);
        }

        if (!beanValidator(model, goodsInfo)) {
            return form(goodsInfo, goodsInfo.getSortItem(), goodsInfo.getSort(), null, request, model);
        }

        // 限购数量设定
        if (StringUtils.isNotBlank(goodsInfo.getQuota())) {
            // 不限购的场合
            if ("0".equals(goodsInfo.getQuota())) {
                goodsInfo.setQuotaNum("");
            }
        }
        Integer allStock = NumberUtils.INTEGER_ZERO;
        // 是否含有库存信息
        boolean hasSkuPring = false;
        // 总库存信息
        for (GoodsSkuPrice goodsSkuPrice : goodsInfo.getGoodsSkuPriceList()) {
            if (!beanValidator(model, goodsSkuPrice)) {
                return form(goodsInfo, goodsInfo.getSortItem(), goodsInfo.getSort(), null, request, model);
            }
            if (goodsSkuPrice.getId() == null) {
                continue;
            }
            if (GoodsSkuPrice.DEL_FLAG_NORMAL.equals(goodsSkuPrice.getDelFlag())) {
                hasSkuPring = true;
                allStock = allStock + goodsSkuPrice.getStock();
            }
        }
        // 当有规格价格时,库存显示规格库存之和
        // 且商品表优惠价字段无效
        if (hasSkuPring) {
            goodsInfo.setBenefitPrice(null);
            goodsInfo.setStock(allStock);
        }
        // 判断是否上架
        // 新增商品后若总库存等于0为下架状态,且库存默认为0
        if (goodsInfo.getStock() == null || goodsInfo.getStock().intValue() == 0) {
            goodsInfo.setState("0");
            goodsInfo.setStock(NumberUtils.INTEGER_ZERO);
        } else {
            // 新增商品后若总库存大于0为上架状态
            goodsInfo.setState("1");
        }
        goodsInfoService.save(goodsInfo);

        // 文件删除
        if (StringUtils.isNotBlank(goodsInfo.getDelImageName())) {
            String[] delImgName = goodsInfo.getDelImageName().split(",");
            for (String imgName : delImgName) {

                try {
                    // 750X563压缩版文件删除
                    MyFDFSClientUtils.delete_file(request, imgName);
                    // 220 * 165压缩版文件删除
                    MyFDFSClientUtils.delete_file(request, imgName + "_compress1");
                    // 134 * 100压缩版文件删除
                    MyFDFSClientUtils.delete_file(request, imgName + "_compress2");
                } catch (IOException | MyException e) {
                    addMessage(model, "图片删除失败");
                    model.addAttribute("type", "error");
                    return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
                }

            }
        }

        // 图片上传
        List<String> img_file_id_list = new ArrayList<String>();
        if (goodsInfo.getPicList() != null) {

            for (GoodsInfoPic goodsInfoPic : goodsInfo.getPicList()) {
                if (StringUtils.isEmpty(goodsInfoPic.getImgBase64())) {
                    continue;
                }
                String img_file_id;
                try {
                    // 取得文件类型
                    String fileType = goodsInfoPic.getType().split("/")[1];
                    // 将图片上传至工具类，并压缩图片
                    img_file_id = MyFDFSClientUtils.uploadFile(goodsInfoPic.getImgBase64(), fileType, true, request);
                    img_file_id_list.add(img_file_id);
                } catch (IOException e) {
                    addMessage(model, "图片保存失败");
                    model.addAttribute("type", "error");
                    // 返回列表页面并保持排序
                    return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
                } catch (MyException e) {
                    addMessage(model, "图片保存失败");
                    model.addAttribute("type", "error");
                    // 返回列表页面并保持排序
                    return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
                }

            }
        }
        // 将上传成功的图片地址更新到DB中
        goodsInfoService.imgNameUpdate(goodsInfo, img_file_id_list);
        addMessage(model, "保存商品成功");
        return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
    }

    @RequestMapping(value = "delete")
    public String delete(GoodsInfo goodsInfo, @RequestParam(required = false) String sortItem,
            @RequestParam(required = false) String sort, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        goodsInfoService.delete(goodsInfo);
        addMessage(model, "删除商品成功");
        return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
    }

    /**
     * 复数删除
     * 
     * @param goodsid
     *            要处理的商品ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
     * @return
     */
    @RequestMapping(value = "muliDelete")
    public String muliDelete(@RequestParam(required = true) String goodsid,
            @RequestParam(required = false) String sortItem, @RequestParam(required = false) String sort,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        goodsInfoService.muliDelete(goodsid);
        addMessage(model, "勾选商品删除成功");

        return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
    }

    /**
     * 复数上架
     * 
     * @param goodsid
     *            要处理的商品ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
     * @return
     */
    @RequestMapping(value = "muliGrounding")
    public String muliGrounding(@RequestParam(required = true) String goodsid,
            @RequestParam(required = false) String sortItem, @RequestParam(required = false) String sort,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        // 复数上架商品
        List<String> cannotGroundingGoods = goodsInfoService.muliGrounding(goodsid);

        // 根据上面返回来的总库存为0的商品弹出信息
        if (cannotGroundingGoods != null && cannotGroundingGoods.size() > 0) {
            StringBuffer goodsName = new StringBuffer();
            for (String cannotGroundingGoodsName : cannotGroundingGoods) {
                goodsName.append(cannotGroundingGoodsName);
                goodsName.append("，");
            }
            addMessage(model, "您勾选的商品中，" + goodsName + "商品库存为0，无法进行上架操作");
            model.addAttribute("type", "error");
            model.addAttribute("goodsid", goodsid);
        } else {
            addMessage(model, "勾选商品已上架");
        }
        return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
    }

    /**
     * 复数下架
     * 
     * @param goodsid
     *            要处理的商品ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
     * @return
     */
    @RequestMapping(value = "muliUndercarriage")
    public String muliUndercarriage(@RequestParam(required = true) String goodsid,
            @RequestParam(required = false) String sortItem, @RequestParam(required = false) String sort,
            HttpServletRequest request, HttpServletResponse response, Model model) {

        goodsInfoService.muliUndercarriage(goodsid);
        addMessage(model, "勾选商品下架成功");
        return list(new GoodsInfo(), sortItem, sort, null, request, response, model);
    }

    /**
     * 复数下架
     * 
     * @param goodsid
     *            要处理的商品ID
     * @param sortItem
     *            排序的项目
     * @param sort
     *            升序还是降序
     * @return
     */
    @RequestMapping(value = "sortInfoRefresh")
    public String sortInfoRefresh() {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();

        // 根据商家ID取得显示信息
        SortInfo sortInfo_where = new SortInfo();
        sortInfo_where.setBusinessInfoId(user.getBusinessinfoId());
        // 应该显示商品分类
        sortInfo_where.setType("0");
        // 根据登录的商家ID取得分类信息
        List<SortInfo> sortInfoList = sortInfoService.findList(sortInfo_where);
        // JSON格式返回值
        JSONObject json = new JSONObject();
        for (SortInfo sortInfo : sortInfoList) {
            json.put("id", sortInfo.getId());
            json.put("name", sortInfo.getName());
        }
        return json.toString();
    }
}