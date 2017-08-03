/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.field.entity;

import com.its.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 场地预约子表_分段编辑临时表Entity
 * @author xzc
 * @version 2017-07-03
 */
public class FieldInfoPriceList extends DataEntity<FieldInfoPriceList> {

	private static final long serialVersionUID = 1L;
	private List<FieldInfoPrice> fieldInfoPrices;

	public FieldInfoPriceList(List<FieldInfoPrice> fieldInfoPrices) {
		this.fieldInfoPrices = fieldInfoPrices;
	}

	public FieldInfoPriceList() {
		super();
	}public FieldInfoPriceList(String id, List<FieldInfoPrice> fieldInfoPrices) {
		super(id);
		this.fieldInfoPrices = fieldInfoPrices;
	}


	public List<FieldInfoPrice> getFieldInfoPrices() {
		return fieldInfoPrices;
	}


	public void setFieldInfoPrices(List<FieldInfoPrice> fieldInfoPrices) {
		this.fieldInfoPrices = fieldInfoPrices;
	}
}