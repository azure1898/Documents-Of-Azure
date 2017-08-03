package com.its.modules.sys.web;

import com.its.common.persistence.Page;
import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.goods.entity.GoodsInfo;
import com.its.modules.goods.entity.GoodsInfoPic;
import com.its.modules.goods.entity.SortInfo;
import com.its.modules.goods.service.GoodsInfoService;
import com.its.modules.goods.service.SortInfoService;
import com.its.modules.lesson.entity.LessonInfo;
import com.its.modules.lesson.service.LessonInfoService;
import com.its.modules.order.entity.OrderGoods;
import com.its.modules.order.entity.OrderService;
import com.its.modules.order.service.*;
import com.its.modules.service.entity.ServiceInfo;
import com.its.modules.service.service.ServiceInfoService;
import com.its.modules.setup.entity.BusinessCategorydict;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.service.BusinessCategorydictService;
import com.its.modules.setup.service.BusinessInfoService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/sys/index")
public class IndexController extends BaseController {

    @Autowired
    private FieldInfoService fieldInfoService;
    /** 商品订单表Service */
    @Autowired
    private OrderGoodsService orderGoodsService;

    /** 商品订单明细表表Service */
    @Autowired
    private OrderGoodsListService orderGoodsListService;

    /** 订单跟踪表Service */
    @Autowired
    private OrderTrackService orderTrackService;

    /** 退款信息明细表Service */
    @Autowired
    private OrderRefundInfoService orderRefundInfoService;

    /** 商户分类Service */
    @Autowired
    private BusinessCategorydictService businessCategorydictService;

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

    /**
     * 商家信息管理Service
     */
    @Autowired
    private BusinessInfoService businessInfoService;

    /** 商品订单明细表表Service */
    @Autowired
    private OrderServiceListService orderServiceListService;
    /**
     * 订单-服务类Service
     */

    @Autowired
    private OrderServiceService orderServiceService;

    /** 分类种类：服务 */
    private static final String SORT_TYPE_SERVICE = "1";

    /**
     * 服务管理Service
     */
    @Autowired
    private ServiceInfoService serviceInfoService;

    /**
     * 订单-课程培训类Service
     */
    @Autowired
    private OrderLessonService orderLessonService;

    /**
     * 场地预约订单表Service
     */
    @Autowired
    private OrderFieldService orderFieldService;

    /**
     * 课程培训类Service
     */
    @Autowired
    private LessonInfoService lessonInfoService;

    /* @RequiresPermissions("sys:index:view") */
    @RequestMapping(value = { "list", "" })
    public String list(OrderGoods orderGoods, GoodsInfo goodsInfo, OrderService orderService, ServiceInfo serviceInfo,
            HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从SESSION中取得商家信息
        User user = UserUtils.getUser();
        BusinessInfo businessInfo = businessInfoService.getJoinArea(user.getBusinessinfoId());
        model.addAttribute("businessInfo", businessInfo);
        // 商家概况
        Integer goodsCount = 0, goodsCountStock = 0, goodsOrderCount = 0, serviceCount = 0, serviceCountStock = 0,
                serviceOrderCount = 0, lessonCount = 0, lessonCountStock = 0, lessonOrderCount = 0, fieldCount = 0,
                fieldCountStock = 0, fieldOrderCount = 0;
        Double goodsMoney = 0.0, serviceMoney = 0.0, lessonMoney = 0.0, fieldMoney = 0.0;
        // 商品相关查询
        GoodsInfo goodsInfo_where = new GoodsInfo();
        goodsCount = goodsInfoService.findAllListCount(goodsInfo_where);// 商品个数
        // 商家设置库存预警
        if ("1".equals(businessInfo.getStockWarn())) {
            goodsInfo_where.setStock(Integer.parseInt(businessInfo.getStockWarnNum()));
            goodsCountStock = goodsInfoService.findAllListCount(goodsInfo_where);// 商品库存不足个数
        }

        goodsOrderCount = orderGoodsService.findAllListCount();// 本周商品订单金额
        goodsMoney = orderGoodsService.findAllListMoney();// 本周商品订单金额
        // 服务相关查询
        ServiceInfo serviceInfo_where = new ServiceInfo();
        serviceCount = serviceInfoService.findAllListCount(serviceInfo_where);// 服务个数
        // 商家设置库存预警
        if (businessInfo.getStockWarn().equals("1")) {
            serviceInfo_where.setStock(Integer.parseInt(businessInfo.getStockWarnNum()));
            serviceCountStock = serviceInfoService.findAllListCount(serviceInfo_where);// 服务库存不足个数
        }

        serviceOrderCount = orderServiceService.findAllListCount();// 本周服务订单数
        serviceMoney = orderServiceService.findAllListMoney();// 本周服务订单金额
        // 课程相关查询
        LessonInfo lessonInfo_where = new LessonInfo();
        lessonCount = lessonInfoService.findAllListCount(lessonInfo_where);// 课程个数
        // 商家设置库存预警
        if (businessInfo.getStockWarn().equals("1")) {
            lessonInfo_where.setStock("1");
            lessonCountStock = lessonInfoService.findAllListCount(lessonInfo_where);// 课程约满个数
        }

        lessonOrderCount = orderLessonService.findAllListCount();// 本周课程订单数
        lessonMoney = orderLessonService.findAllListMoney();// 本周课程订单金额
        // 场地相关查询
        FieldInfo fieldInfo_where = new FieldInfo();
        fieldCount = fieldInfoService.findAllListCount(fieldInfo_where);// 场地个数
        // 商家设置库存预警
        if (businessInfo.getStockWarn().equals("1")) {
            fieldInfo_where.setStock("1");
            fieldCountStock = fieldInfoService.findAllListCount(fieldInfo_where);// 场地约满个数
        }

        fieldOrderCount = orderFieldService.findAllListCount();// 本周场地订单个数
        fieldMoney = orderFieldService.findAllListMoney();

        model.addAttribute("goodsCount", goodsCount);// 商品个数
        model.addAttribute("goodsCountStock", goodsCountStock);// 商品库存不足个数
        model.addAttribute("goodsOrderCount", goodsOrderCount);// 本周商品订单数
        model.addAttribute("goodsMoney", goodsMoney);// 本周商品订单金额
        model.addAttribute("serviceCount", serviceCount);// 服务个数
        model.addAttribute("serviceCountStock", serviceCountStock);// 服务库存不足个数
        model.addAttribute("serviceOrderCount", serviceOrderCount);// 本周服务订单数
        model.addAttribute("serviceMoney", serviceMoney);// 本周服务订单金额
        model.addAttribute("lessonCount", lessonCount);// 课程个数
        model.addAttribute("lessonCountStock", lessonCountStock);// 课程约满个数
        model.addAttribute("lessonOrderCount", lessonOrderCount);// 本周课程订单数
        model.addAttribute("lessonMoney", lessonMoney);// 本周课程订单金额
        model.addAttribute("fieldCount", fieldCount);// 场地个数
        model.addAttribute("fieldCountStock", fieldCountStock);// 场地约满个数
        model.addAttribute("fieldOrderCount", fieldOrderCount);// 本周场地订单个数
        model.addAttribute("fieldMoney", fieldMoney);// 本周场地订单总金额
        // ------------------------------------------商品相关信息查询
        // 开始------------------------

        // 商家分类信息检索条件
        Map<String, String> paramer = new HashMap<String, String>();
        // 根据当前登陆者的商家ID进行检索
        paramer.put("businessInfoId", user.getBusinessinfoId());
        // 将上方导航下拉菜单默认选中为商品订单
        model.addAttribute("nowProdType", "0");

        // 只显示当前商家对应的订单
        orderGoods.setBusinessInfoId(user.getBusinessinfoId());
        // 只显当前商家商品的订单
        orderGoods.setProdType("0");
        Page<OrderGoods> pageGoods = orderGoodsService.findPage(new Page<OrderGoods>(request, response, 5), orderGoods);
        Integer goodsSum1 = 0;// 待付款商品订单
        Integer goodsSum2 = 0;// 待受理商品订单
        Integer goodsSum3 = 0;// 待配送商品订单
        Integer goodsSum4 = 0;// 待完成商品订单
        for (OrderGoods orderGoodsTemp : pageGoods.getList()) {
            // 为了排他处理这里使用乐观锁以更新日时控制
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderGoodsTemp.setUpdateDateString(sdf.format(orderGoodsTemp.getUpdateDate()));
            if (orderGoodsTemp.getPayState().equals("0")) {// 待付款商品订单
                goodsSum1++;
            }
            if (orderGoodsTemp.getOrderState().equals("0")) {// 待受理商品订单
                goodsSum2++;
            }
            if (orderGoodsTemp.getOrderState().equals("1")) {// 待配送商品订单
                goodsSum3++;
            }
            if (orderGoodsTemp.getOrderState().equals("2")) {// 待完成商品订单
                goodsSum4++;
            }
        }
        model.addAttribute("goodsSum1", goodsSum1);
        model.addAttribute("goodsSum2", goodsSum2);
        model.addAttribute("goodsSum3", goodsSum3);
        model.addAttribute("goodsSum4", goodsSum4);
        model.addAttribute("pageGoods", pageGoods);

        // 商品库存---
        // 商家设置库存预警
        if (businessInfo.getStockWarn().equals("1")) {
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
            // 一览显示信息取得
            goodsInfo.setSortItem("a.create_date");
            goodsInfo.setSort("ASC");
            goodsInfo.setStock(Integer.parseInt(businessInfo.getStockWarnNum()));
            Page<GoodsInfo> pageGoodsStock = goodsInfoService.findPage(new Page<GoodsInfo>(request, response, 5),
                    goodsInfo);

            for (GoodsInfo goodsItem : pageGoodsStock.getList()) {
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
            model.addAttribute("pageGoodsStock", pageGoodsStock);
        }

        // ------------------------------------------商品相关信息查询
        // 结束------------------------

        // ------------------------------------------服务相关信息查询
        // 开始------------------------
        // 只显示当前商家对应的订单
        orderService.setBusinessInfoId(user.getBusinessinfoId());
        // 只显当前商家服务的订单
        orderService.setProdType("1");
        Page<OrderService> pageService = orderServiceService.findPage(new Page<OrderService>(request, response, 5),
                orderService);
        // 商家分类信息检索条件
        paramer.clear();
        // 根据当前登陆者的商家ID进行检索
        paramer.put("businessInfoId", user.getBusinessinfoId());
        // 将上方导航下拉菜单默认选中为服务订单
        model.addAttribute("nowProdType", "1");
        Integer serviceSum1 = 0;// 待付款服务订单
        Integer serviceSum2 = 0;// 待受理服务订单
        Integer serviceSum3 = 0;// 待完成商品订单
        for (OrderService orderServiceTemp : pageService.getList()) {
            // 为了排他处理这里使用乐观锁以更新日时控制
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderServiceTemp.setUpdateDateString(sdf.format(orderServiceTemp.getUpdateDate()));
            if (orderServiceTemp.getPayState().equals("0")) {// 待付款商品订单
                serviceSum1++;
            }
            if (orderServiceTemp.getOrderState().equals("0")) {// 待受理商品订单
                serviceSum2++;
            }
            if (orderServiceTemp.getOrderState().equals("2")) {// 待完成商品订单
                serviceSum3++;
            }
        }
        model.addAttribute("serviceSum1", serviceSum1);
        model.addAttribute("serviceSum2", serviceSum2);
        model.addAttribute("serviceSum3", serviceSum3);
        model.addAttribute("pageService", pageService);

        // 服务库存--------------
        if (businessInfo.getStockWarn().equals("1")) {
            // 根据商家ID取得显示信息
            SortInfo sortInfo = new SortInfo();
            sortInfo.setBusinessInfoId(user.getBusinessinfoId());
            // 应该显示服务分类
            sortInfo.setType(SORT_TYPE_SERVICE);
            // 根据登录的商家ID取得分类信息
            List<SortInfo> sortInfoList = sortInfoService.findList(sortInfo);
            model.addAttribute("sortInfoList", sortInfoList);

            // 只显示属于当前商家的服务
            serviceInfo.setBusinessInfoId(user.getBusinessinfoId());
            // 取得一览数据
            serviceInfo.setSortItem("a.create_date");
            serviceInfo.setSort("ASC");
            serviceInfo.setStock(Integer.parseInt(businessInfo.getStockWarnNum()));
            Page<ServiceInfo> pageServiceStock = serviceInfoService.findPage(new Page<ServiceInfo>(request, response),
                    serviceInfo);

            for (ServiceInfo serviceItem : pageServiceStock.getList()) {
                if (StringUtils.isNotBlank(serviceItem.getImgs())) {
                    String[] imageNames = serviceItem.getImgs().split(",");
                    // 图片url集合
                    List<String> imageUrls = new ArrayList<String>();
                    try {
                        imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
                    } catch (IOException | MyException e) {
                    }
                    serviceItem.setImageUrls(imageUrls);
                }
            }

            model.addAttribute("pageServiceStock", pageServiceStock);
        }
        // ------------------------------------------服务相关信息查询
        // 结束------------------------

        return "modules/sys/index";
    }
}
