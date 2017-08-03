package com.its.modules.app.bean;

import com.its.modules.app.entity.GoodsSkuPrice;

public class GoodsSkuBean extends GoodsSkuPrice {

	private static final long serialVersionUID = -6013723087275988141L;

	private String skuKeyName;

	private String skuValueName;

	public String getSkuKeyName() {
		return skuKeyName;
	}

	public void setSkuKeyName(String skuKeyName) {
		this.skuKeyName = skuKeyName;
	}

	public String getSkuValueName() {
		return skuValueName;
	}

	public void setSkuValueName(String skuValueName) {
		this.skuValueName = skuValueName;
	}
}
