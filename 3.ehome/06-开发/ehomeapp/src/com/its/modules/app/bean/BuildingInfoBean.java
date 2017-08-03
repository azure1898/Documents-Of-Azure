package com.its.modules.app.bean;

import java.util.List;

import com.its.modules.app.entity.BuildingInfo;
import com.its.modules.app.entity.RoomCertify;

public class BuildingInfoBean extends BuildingInfo {
	private static final long serialVersionUID = 7267390475680118148L;
	// 已认证的房间列表
	private List<RoomCertify> roomCertifies;

	public List<RoomCertify> getRoomCertifies() {
		return roomCertifies;
	}

	public void setRoomCertifies(List<RoomCertify> roomCertifies) {
		this.roomCertifies = roomCertifies;
	}
}