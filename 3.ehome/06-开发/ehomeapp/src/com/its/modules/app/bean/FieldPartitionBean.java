package com.its.modules.app.bean;

import com.its.modules.app.entity.FieldPartitionPrice;

public class FieldPartitionBean extends FieldPartitionPrice {

	private static final long serialVersionUID = -4035781099560311649L;

	private String fieldName;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
