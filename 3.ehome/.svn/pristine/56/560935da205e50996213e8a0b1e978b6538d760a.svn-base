package com.its.modules.operation.entity;

import java.util.Date;

import com.its.common.utils.excel.annotation.ExcelField;

public class Mobile {
	private Long mobileNo;
	private Date registerDate;

	@ExcelField(title="用户名",align=2,sort=10)
	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@ExcelField(title="注册时间", align=2, sort=20)
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "Mobile [mobileNo=" + mobileNo + ", registerDate=" + registerDate + "]";
	}

}
