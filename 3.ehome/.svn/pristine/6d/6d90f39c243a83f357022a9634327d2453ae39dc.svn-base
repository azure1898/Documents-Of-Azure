package com.its.modules.rabbitmq.entity;

import com.its.common.persistence.DataEntity;

public class DataLog extends DataEntity<DataLog> {

	private static final long serialVersionUID = 1L;

	private String receiveData;// 接收数据
	private String receiveDataType;// 接收数据类型 0用户详情,1产品流水
	private String checkState;// 匹配状态检查 0不匹配,1匹配

	public DataLog(String receiveData,String receiveDataType,String checkState){
		this.receiveData=receiveData;
		this.receiveDataType=receiveDataType;
		this.checkState=checkState;
	}
	
	public String getReceiveData() {
		return receiveData;
	}

	public void setReceiveData(String receiveData) {
		this.receiveData = receiveData;
	}

	public String getReceiveDataType() {
		return receiveDataType;
	}

	public void setReceiveDataType(String receiveDataType) {
		this.receiveDataType = receiveDataType;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
}
