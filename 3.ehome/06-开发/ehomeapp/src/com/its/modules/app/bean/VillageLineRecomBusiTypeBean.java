package com.its.modules.app.bean;

import java.util.List;

import com.its.modules.app.entity.BusinessInfo;
import com.its.modules.app.entity.VillageLineRecomBusiType;
import com.its.modules.app.entity.VillageLineRecomBusiTypeDetail;

public class VillageLineRecomBusiTypeBean extends VillageLineRecomBusiType {
	private static final long serialVersionUID = -2910108193060580056L;
	private BusinessInfo businessInfo;
	private List<VillageLineRecomBusiTypeDetail> villageLineRecomBusiTypeDetails;

	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
	}

	public List<VillageLineRecomBusiTypeDetail> getVillageLineRecomBusiTypeDetails() {
		return villageLineRecomBusiTypeDetails;
	}

	public void setVillageLineRecomBusiTypeDetails(List<VillageLineRecomBusiTypeDetail> villageLineRecomBusiTypeDetails) {
		this.villageLineRecomBusiTypeDetails = villageLineRecomBusiTypeDetails;
	}
}