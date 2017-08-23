package com.its.modules.app.bean;

import com.its.modules.app.entity.GoodsInfo;

public class GoodsInfoBean extends GoodsInfo {
	private static final long serialVersionUID = -3040572973282794461L;
	// 购物车中商品规格ID
	public String goodsSkuPriceID;
	public Double cartPrice;
	// 购买数量
	public int cartNumber;

	public String getGoodsSkuPriceID() {
		return goodsSkuPriceID;
	}

	public void setGoodsSkuPriceID(String goodsSkuPriceID) {
		this.goodsSkuPriceID = goodsSkuPriceID;
	}

	public Double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(Double cartPrice) {
		this.cartPrice = cartPrice;
	}

	public int getCartNumber() {
		return cartNumber;
	}

	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}
}