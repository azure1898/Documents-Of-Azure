package com.its.modules.sys.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.its.common.utils.MyFDFSClientUtils;
import com.its.common.utils.StringUtils;
import com.its.common.web.BaseController;
import com.its.modules.field.entity.FieldInfo;
import com.its.modules.field.entity.FieldPartitionPrice;
import com.its.modules.field.service.FieldInfoService;
import com.its.modules.goods.entity.GoodsInfo;
import com.its.modules.goods.service.GoodsInfoService;
import com.its.modules.goods.service.SortInfoService;
import com.its.modules.lesson.entity.LessonInfo;
import com.its.modules.lesson.service.LessonInfoService;
import com.its.modules.order.entity.OrderField;
import com.its.modules.order.entity.OrderGoods;
import com.its.modules.order.entity.OrderLesson;
import com.its.modules.order.entity.OrderService;
import com.its.modules.order.service.OrderFieldService;
import com.its.modules.order.service.OrderGoodsListService;
import com.its.modules.order.service.OrderGoodsService;
import com.its.modules.order.service.OrderLessonService;
import com.its.modules.order.service.OrderRefundInfoService;
import com.its.modules.order.service.OrderServiceListService;
import com.its.modules.order.service.OrderServiceService;
import com.its.modules.order.service.OrderTrackService;
import com.its.modules.service.entity.ServiceInfo;
import com.its.modules.service.service.ServiceInfoService;
import com.its.modules.setup.entity.BusinessInfo;
import com.its.modules.setup.service.BusinessCategorydictService;
import com.its.modules.setup.service.BusinessInfoService;
import com.its.modules.sys.entity.User;
import com.its.modules.sys.utils.UserUtils;

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
    public String list(HttpServletRequest request,HttpServletResponse response, Model model) throws java.text.ParseException {
				/* 从SESSION中取得商家信息 */
		User user = UserUtils.getUser();
		BusinessInfo businessInfo = businessInfoService.getJoinArea(user.getBusinessinfoId());
		model.addAttribute("businessInfo", businessInfo);
		if ("1".equals(businessInfo.getStockWarn())) {
			int warnNum = Integer.valueOf(businessInfo.getStockWarnNum());
			model.addAttribute("warnNum",warnNum);
		}
		/*商家产品模式*/
		List<String> ptList = businessInfoService.getPtlistById(businessInfo.getId());
		model.addAttribute("ptlist", ptList);
		/******************** 产品统计  ***************************************************/
							/* 商品 */
		GoodsInfo goodInfo = new GoodsInfo(); 
		goodInfo.setBusinessInfoId(businessInfo.getId());
		List<GoodsInfo> goodsInfoList = goodsInfoService.findList(goodInfo);
		model.addAttribute("goodsCount", goodsInfoList.size());// 商品总数
		List<GoodsInfo> goodsStock = new ArrayList<GoodsInfo>();//库存不足商品
		int goodsbzCount = 0;// 库存不足数量
		if ("1".equals(businessInfo.getStockWarn())) {
			goodsbzCount = 0;
			int warnNum = Integer.valueOf(businessInfo.getStockWarnNum());
			for (GoodsInfo gi : goodsInfoList) {
				if ((gi.getStock()==null?2147483647:gi.getStock()) <= warnNum) {
					goodsbzCount++;
					if(goodsStock.size()<5){
						 // 图片显示编辑
			            if (StringUtils.isNotBlank(gi.getImgs())) {
			                String[] imageNames = gi.getImgs().split(",");
			                // 图片url集合
			                List<String> imageUrls = new ArrayList<String>();
			                try {
			                    imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
			                } catch (IOException | MyException e) {
			                }
			                gi.setImageUrls(imageUrls);
			            }
				        goodsStock.add(gi);
					}
						
				}
			}
		}
		model.addAttribute("goodsbzCount", goodsbzCount);// 商品库存不足数量
							/* 服务 */
		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setBusinessInfoId(businessInfo.getId());
		List<ServiceInfo> serviceInfoList = serviceInfoService.findList(serviceInfo);
		model.addAttribute("serviceCount", serviceInfoList.size());// 总数
		List<ServiceInfo> serviceStock = new ArrayList<ServiceInfo>();//库存不足服务
		int servicesbzCount = 0;// 库存不足数量
		if ("1".equals(businessInfo.getStockWarn())) {
			servicesbzCount = 0;
			int warnNum = Integer.valueOf(businessInfo.getStockWarnNum());
			for (ServiceInfo si : serviceInfoList) {
				if (si.getStock() <= warnNum) {
					servicesbzCount++;
					if(serviceStock.size()<5){
						if (StringUtils.isNotBlank(si.getImgs())) {
							String[] imageNames = si.getImgs().split(",");
							// 图片url集合
							List<String> imageUrls = new ArrayList<String>();
							try {
								imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
							} catch (IOException | MyException e) {
							}
							si.setImageUrls(imageUrls);
						}
						serviceStock.add(si);
					}
						
				}
			}
		}
		model.addAttribute("servicesbzCount", servicesbzCount);// 库存不足数
								/* 课程培训 */
		LessonInfo lessonInfo = new LessonInfo();
		lessonInfo.setBusinessInfoId(businessInfo.getId());
		List<LessonInfo> lessonInfoList = lessonInfoService.findList(lessonInfo);
		model.addAttribute("lessonCount", lessonInfoList.size());// 总数
//    	LessonInfo lessonInfo = new LessonInfo();
//    	lessonInfo.setStock("1");
//		model.addAttribute("lessonbzCount",  lessonInfoService.findAllListCount(lessonInfo));// 约满数
    	int lessonbzCount = 0; 
		List<LessonInfo> lessonStock = new ArrayList<LessonInfo>();//约满的课程信息
		for(LessonInfo li:lessonInfoList){
			if(li.getPeopleLimit()==0){
				lessonbzCount++;
				if(lessonStock.size()<5){
					if (StringUtils.isNotBlank(li.getImgs())) {
						String[] imageNames = li.getImgs().split(",");
						// 图片url集合
						List<String> imageUrls = new ArrayList<String>();
						try {
							imageUrls.add(MyFDFSClientUtils.get_fdfs_file_url(request, imageNames[0] + "_compress2"));
						} catch (IOException | MyException e) {
						}
						li.setImageUrls(imageUrls);
					}
					lessonStock.add(li);
				}
					
			}
		}
		model.addAttribute("lessonbzCount",lessonbzCount);// 约满数
								/* 场地预约 */
		Date date = new Date();
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(date); 
		calendar.add(Calendar.DAY_OF_WEEK, 6); // 目前的時間加6天    
		Date enddate =calendar.getTime();//6天后
		//今天开始时间
		Date startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").parse(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(date));
		Date endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").parse(new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(enddate));
		
		FieldInfo fieldInfo = new FieldInfo();
		if (fieldInfo.getPartitionPrice()==null){
			fieldInfo.setPartitionPrice(new FieldPartitionPrice());
//			fieldInfo.getPartitionPrice().setAppointmentTime(new Date());
			fieldInfo.getPartitionPrice().setStartTime(startTime);
			fieldInfo.getPartitionPrice().setEndTime(endTime);
		}
		List<FieldInfo> fieldInfoList = fieldInfoService.findList(fieldInfo);
		model.addAttribute("fieldCount", fieldInfoList.size());// 总数
		List<FieldInfo> fieldStock = new ArrayList<FieldInfo>();//约满的场地信息
		int fieldbzCount=0;//约满数
		boolean _b = true;
		for(FieldInfo fi:fieldInfoList){
			List<FieldPartitionPrice> fppList =  fi.getFieldPartitionPriceList();
			if(fppList==null || fppList.size()<1){continue;}
			_b=true;
			for(FieldPartitionPrice fpp:fppList){
				if(fpp.getState().equals("0")){//可预约
					_b=false;break;
				}
			}
			if(_b){
				fieldbzCount++;
				if(fieldStock.size()<5){
					fieldStock.add(fi);
				}
			}
		}
		model.addAttribute("fieldbzCount",fieldbzCount);
		model.addAttribute("fieldStock",fieldStock);
//		model.addAttribute("fieldbzCount", fieldInfoService.getCountFull(new Date()));// 查询某天约满数
		//查询约满的场地信息
		/******************** 本周订单 ***************************************************/
						/* 获取本周开始结束日期 */
		Date beginCreateDate = new SimpleDateFormat("yyyy-MM-dd 00:00:00").parse(new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(getBeginDayOfWeek()));
		Date endCreateDate = new SimpleDateFormat("yyyy-MM-dd 23:59:59").parse(new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(getEndDayOfWeek()));
		model.addAttribute("beginCreateDate",new SimpleDateFormat("yyyy-MM-dd").format(beginCreateDate));
		model.addAttribute("endCreateDate",new SimpleDateFormat("yyyy-MM-dd").format(endCreateDate));
			/* 商品订单 */
		OrderGoods orderGoods = new OrderGoods();
		orderGoods.setBeginCreateDate(beginCreateDate);
		orderGoods.setEndCreateDate(endCreateDate);
		orderGoods.setBusinessInfoId(businessInfo.getId());
//		orderGoods.setIsCancel(1);//排除已取消订单
		List<OrderGoods> orderGoodsList = orderGoodsService.findList(orderGoods);
		int orderGoodsCount=0;//已完成订单总数
//		model.addAttribute("orderGoodsCount", orderGoodsList.size());// 总数
							/* 服务订单 */
		OrderService orderService = new OrderService();
		orderService.setBeginCreateDate(beginCreateDate);
		orderService.setEndCreateDate(endCreateDate);
		orderService.setBusinessInfoId(businessInfo.getId());
//		orderService.setIsCancel(1);//排除已取消订单
		List<OrderService> orderServiceList = orderServiceService.findList(orderService);
		int orderServiceCount=0;//已完成订单总数
//		model.addAttribute("orderServiceCount", orderServiceList.size());// 总数
							/* 课程培训 */
		OrderLesson orderLesson = new OrderLesson();
		orderLesson.setBeginCreateDate(beginCreateDate);
		orderLesson.setEndCreateDate(endCreateDate);
		orderLesson.setBusinessInfoId(businessInfo.getId());
//		orderLesson.setIsCancel(1);//排除已取消订单
		List<OrderLesson> orderLessonList = orderLessonService.findList(orderLesson);
		int orderLessonCount=0;//已完成订单总数
//		model.addAttribute("orderLessonCount", orderLessonList.size());// 总数
							/* 场地预约 */
		OrderField orderField = new OrderField();
		orderField.setBeginCreateDate(beginCreateDate);
		orderField.setEndCreateDate(endCreateDate);
		orderField.setBusinessInfoId(businessInfo.getId());
//		orderField.setIsCancel(1);//排除已取消订单
		List<OrderField> orderFieldList = orderFieldService.findList(orderField);
		int orderFieldCount=0;//已完成订单总数
//		model.addAttribute("orderFieldCount", orderFieldList.size());// 总数
		/******************** 本周收入 ***************************************************/
		Double goodsInclu=0.0,//商品收入
				serviceInclu=0.0,//服务收入
				lessonInclu=0.0,//课程收入
				fieldInclu=0.0;//场地收入
		for(OrderGoods og:orderGoodsList){
//			if(!og.getOrderState().equals("4")){//排除取消的订单
//				goodsInclu+=og.getSumMoney();
//			}
			if(og.getOrderState().equals("3")){//已完成订单数
				orderGoodsCount++;
				goodsInclu+=og.getPayMoney();
			}
		}
		for(OrderService os:orderServiceList){
//			if(!os.getOrderState().equals("3")){
//				serviceInclu+=os.getSumMoney();
//			}
			if(os.getOrderState().equals("2")){
				orderServiceCount++;
				serviceInclu+=os.getPayMoney();
			}
		}
		for(OrderLesson ol:orderLessonList){
//			if(!ol.getOrderState().equals("2")){
//				lessonInclu+=ol.getSumMoney();
//			}
			if(ol.getOrderState().equals("1")){
				orderLessonCount++;
				lessonInclu+=ol.getPayMoney();
			}
		}
		for(OrderField of:orderFieldList){
//			if(!of.getOrderState().equals("2")){
//				fieldInclu+=of.getSumMoney();
//			}
			if(of.getOrderState().equals("1")){
				orderFieldCount++;
				fieldInclu+=of.getPayMoney();
			}
		}
		model.addAttribute("goodsInclu", goodsInclu);
		model.addAttribute("serviceInclu", serviceInclu);
		model.addAttribute("lessonInclu", lessonInclu);
		model.addAttribute("fieldInclu", fieldInclu);
		
		model.addAttribute("orderGoodsCount", orderGoodsCount);
		model.addAttribute("orderServiceCount", orderServiceCount);
		model.addAttribute("orderLessonCount", orderLessonCount);
		model.addAttribute("orderFieldCount", orderFieldCount);
		/******************** 待办事宜 ***************************************************/
			/*商品类 待处理订单*/
		OrderGoods ogoods = new OrderGoods();
		ogoods.setPending(1);
		ogoods.setBusinessInfoId(businessInfo.getId());
		List<OrderGoods> pendingOrderGoodsList = orderGoodsService.findList(ogoods);
		int pendingPayGoods=0,//待付款
				pendingHandleGoods=0,//待受理
					pendingDisGoods=0,//待配送
						pendingSuccessGoods=0;//待完成
		List<OrderGoods> newOrderGoodsList = new ArrayList<OrderGoods>();
		for(OrderGoods og :pendingOrderGoodsList ){
			if(newOrderGoodsList.size()<5){
				if(og.getUpdateDate()!=null){
					og.setUpdateDateString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(og.getUpdateDate()));
				}
				newOrderGoodsList.add(og);
			}
			if(og.getOrderState().equals("0")){//待受理
				pendingHandleGoods++;
			}
			if(og.getOrderState().equals("1")){//已受理>待配送
				pendingDisGoods++;
			}
			if(og.getPayState().equals("0")){//未支付 >待付款
				pendingPayGoods++;
			}
			if(og.getOrderState().equals("2")){//配送中>待完成
				pendingSuccessGoods++;
			}
		}
		model.addAttribute("newOrderGoodsList", newOrderGoodsList);//只显示五条
		model.addAttribute("pendingHandleGoods", pendingHandleGoods);
		model.addAttribute("pendingDisGoods", pendingDisGoods);
		model.addAttribute("pendingPayGoods", pendingPayGoods);
		model.addAttribute("pendingSuccessGoods", pendingSuccessGoods);
		model.addAttribute("goodsStock", goodsStock);//库存不足商品
		
			/*服务类 待处理订单*/
		
		OrderService oService = new OrderService();
		oService.setPending(1);
		oService.setBusinessInfoId(businessInfo.getId());
		List<OrderService> pendingOrderServiceList = orderServiceService.findList(oService);
		int pendingPayService=0,//待付款
				pendingHandleService=0,//待受理
						pendingSuccessService=0;//待完成
		List<OrderService> newOrderServiceList = new ArrayList<OrderService>();//存放五条
		for(OrderService os :pendingOrderServiceList ){
			if(newOrderServiceList.size()<5){
				if(os.getUpdateDate()!=null){
					os.setUpdateDateString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(os.getUpdateDate()));
				}
				newOrderServiceList.add(os);
			}
			if(os.getOrderState().equals("0")){//待受理
				pendingHandleService+=1;
			}
			if(os.getPayState()==null){os.setPayState("0");}//此种情况应该不会存在.
			if(os.getPayState().equals("0")){//未支付 >待付款
				pendingPayService+=1;
			}
			if(os.getOrderState().equals("1")){//已受理>待完成
				pendingSuccessService+=1;
			}
		}
		model.addAttribute("newOrderServiceList", newOrderServiceList);//只显示五条
		model.addAttribute("pendingPayService", pendingPayService);
		model.addAttribute("pendingHandleService", pendingHandleService);
		model.addAttribute("pendingSuccessService", pendingSuccessService);
		model.addAttribute("serviceStock", serviceStock);//库存不足服务
		
		
				/*课程预约类 待处理订单(待付款)*/
		
		OrderLesson oLesson= new OrderLesson();
//		oLesson.setPending(1);
		oLesson.setPayState("0");
		oLesson.setBusinessInfoId(businessInfo.getId());
		List<OrderLesson> pendingOrderLessonList = orderLessonService.findList(oLesson);
		int pendingPayLesson=0,//待付款
				pendingHandleLesson=0;//待预约
		List<OrderLesson> newOrderLessonList = new ArrayList<OrderLesson>();//存放五条
		for(OrderLesson ol :pendingOrderLessonList ){
			if(newOrderLessonList.size()<5 && !ol.getOrderState().equals("2")){
				if(ol.getUpdateDate()!=null){
					ol.setUpdateDateString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ol.getUpdateDate()));
				}
				newOrderLessonList.add(ol);
			}
//			if(ol.getOrderState().equals("0")){//待预约
//				pendingHandleLesson+=1;
//			}
//			if(ol.getPayState().equals("0")){//未支付 >待付款
//				pendingPayLesson+=1;
//			}
			if(!ol.getOrderState().equals("2")){
				pendingPayLesson+=1;
			}
		}
		model.addAttribute("newOrderLessonList", newOrderLessonList);//只显示五条
		model.addAttribute("pendingPayLesson", pendingPayLesson);
		model.addAttribute("pendingHandleLesson", pendingHandleLesson);
		model.addAttribute("lessonStock", lessonStock);//约满的课程信息
		
//		System.out.println("lessonStock.size()"+lessonStock.size());
		
				/*场地类 待处理订单(待付款)*/
		
		OrderField oField= new OrderField();
//		oField.setPending(1);
		oField.setPayState("0");
		oField.setBusinessInfoId(businessInfo.getId());
		List<OrderField> pendingOrderFieldList = orderFieldService.findList(oField);
		int pendingPayField=0,//待付款
				pendingHandleField=0;//待预约
		List<OrderField> newOrderFieldList = new ArrayList<OrderField>();//存放五条
		for(OrderField of :pendingOrderFieldList ){
			if(newOrderFieldList.size()<5 && !of.getOrderState().equals("2")){
				if(of.getUpdateDate()!=null){
					of.setUpdateDateString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(of.getUpdateDate()));
				}
				newOrderFieldList.add(of);
			}
//			if(of.getOrderState().equals("0")){//待预约
//				pendingHandleField+=1;
//			}
//			if(of.getPayState().equals("0")){//未支付 >待付款
//				pendingPayField+=1;
//			}
			if(!of.getOrderState().equals("2")){
				pendingPayField+=1;
			}
		}
		model.addAttribute("newOrderFieldList", newOrderFieldList);//只显示五条
		model.addAttribute("pendingPayField", pendingPayField);
		model.addAttribute("pendingHandleField", pendingHandleField);
//		model.addAttribute("lessonStock", lessonStock);//库存不足服务
		
		return "modules/sys/index";
	}
    
    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return cal.getTime();
    }
    //获取本周的结束时间
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());  
        cal.add(Calendar.DAY_OF_WEEK, 6); 
        return cal.getTime();
    }
}
