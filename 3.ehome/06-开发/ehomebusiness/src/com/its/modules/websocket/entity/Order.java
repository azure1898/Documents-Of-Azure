package com.its.modules.websocket.entity;

import java.util.List;

public class Order {

	/* 必填 */
	private String businessInfoId;// 商家id
	private int type;// 提醒类型--库存不足提醒(0商品,1服务,2课程,3场地)订单提醒(10商品,11服务)

	/* 库存提示必填 */
	private String id;// id(商品|服务|课程|场地)
	private String name;// 名称(商品|服务|课程|场地)
	private String skuKey;// 规格名称
	private String skuValue;// 规格值
	private String stock;// 库存数量

	private String url;// 跳转链接
	
	private List<OrderInfo> order;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessInfoId() {
		return businessInfoId;
	}

	public void setBusinessInfoId(String businessInfoId) {
		this.businessInfoId = businessInfoId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		if (type == 0) {// 商品库存不足提醒
			this.url = "/goods/goodsInfo";
		} else if (type == 1) {// 服务库存不足提醒
			this.url = "/service/serviceInfo";
		} else if (type == 2) {// 课程预约预警提醒
			this.url = "/lesson/lessonInfo";
		} else if (type == 3) {// 场地预约预警提醒
			this.url = "/field/fieldInfo";
		} else if (type == 10) {// 商品订单
			this.url = "/order/orderGoods";
		} else if (type == 11) {// 服务订单
			this.url = "/order/orderService";
		}
		this.type = type;
	}

	public List<OrderInfo> getOrder() {
		return order;
	}

	public void setOrder(List<OrderInfo> order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkuKey() {
		return skuKey;
	}

	public void setSkuKey(String skuKey) {
		this.skuKey = skuKey;
	}

	public String getSkuValue() {
		return skuValue;
	}

	public void setSkuValue(String skuValue) {
		this.skuValue = skuValue;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() ptitle:打开页签的title
	 */
	@Override
	public String toString() {
		String title = "", ptitle = "", content = "<div type=\""+type+"\" url=\""+url+"\">", sucbtn = "", celbtn = "返回", show = "true", buttons = "";
		if (type == 0) {// 商品库存不足提醒
			title = "您有商品即将售空:";
			content += "<a onclick=\"openPage(this)\" id=\""+id+"\">商品名称:" + name + "</a><br />";
			content += "规格(" + skuKey + "):" + skuValue + "<br />";
			content += "库存:" + stock;
			sucbtn = "进入商品列表";
			ptitle = "商品管理";
		} else if (type == 1) {// 服务库存不足提醒
			title = "您有服务即将售空:";
			content += "<a onclick=\"openPage(this)\" id=\""+id+"\">服务名称:" + name + "</a><br />";
			content += "库存:" + stock;
			sucbtn = "进入服务列表";
			ptitle = "服务管理";
		} else if (type == 2) {// 课程预约预警提醒
			title = "课程预约提醒:";
			content += "<a onclick=\"openPage(this)\" id=\""+id+"\">"+name + " 限制人数即将约满</a>";
			sucbtn = "进入课程预约列表";
			ptitle = "课程预约管理";
		} else if (type == 3) {// 场地预约预警提醒
			title = "场地预约提醒:";
			content += "<a onclick=\"openPage(this)\" id=\""+id+"\">"+name + " 场地即将约满</a>";
			sucbtn = "进入场地预约列表";
			ptitle = "场地预约管理";
		} else if (type == 10) {// 商品订单
			title = "您有新订单了:";
			for(OrderInfo oi :order){
				content += "<a onclick=\"openPage(this)\" id=\""+oi.getOrderNo()+"\">订单号:" + oi.getOrderNo() + "&nbsp;&nbsp;&nbsp;金额:"+oi.getSumMoney()+"</a><br/>";
			}
			sucbtn = "进入订单列表";
			ptitle = "商品订单";
		} else if (type == 11) {// 服务订单
			title = "您有新订单了:";
			for(OrderInfo oi :order){
				content += "<a onclick=\"openPage(this)\" id=\""+oi.getOrderNo()+"\">订单号:" + oi.getOrderNo() + "&nbsp;&nbsp;&nbsp;金额:"+oi.getSumMoney()+"</a><br/>";
			}
			sucbtn = "进入订单列表";
			ptitle = "服务订单";
		} else {
			show = "false";
		}
		content+="</div><span title=\""+ptitle+"\"></span>";
		buttons = "{'" + sucbtn + "':true,'" + celbtn + "':false}";
		return "{'title':'" + title + "','content':'" + content
				+ "','buttons':" + buttons + ",'show':'" + show + "','url':'"
				+ url + "','ptitle':'"+ptitle+"'}";
	}
}
