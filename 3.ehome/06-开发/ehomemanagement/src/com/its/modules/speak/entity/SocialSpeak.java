/**
 * Copyright &copy; 2012-2014 <a href="https://its111.com">Its111</a> All rights reserved.
 */
package com.its.modules.speak.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.its.common.persistence.DataEntity;
import com.its.modules.subject.entity.SocialSubject;

/**
 * 发言管理Entity
 * @author wmm
 * @version 2017-08-01
 */
public class SocialSpeak extends DataEntity<SocialSpeak> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 发言人，用户ID
	private String subjectid;		// 话题ID
	private String subjectname;     //话题名称
	private String tag;		// 标签
	private String content;		// 文字内容
	private String visrange;		// 可见范围 1：公开 2：好友可见； 3：粉丝可见
	private String forbitcomment;		// 是否禁止评论 1：是；0：否
	private String forbidforward;		// 是否禁止转发 1：是； 0：否
	private Date createtime;		// 发布时间
	private String istop;		// 是否置顶 0：否； 1：是
	private Date toptime;       // 置顶时间
	private String readnum;		// 阅读次数
	private String plateid;		// 发言所属板块 sys_plate.id
	private int isspeak;		// 发言标识1：发言；0：转发
	private String reason;		// 转发原因
	private String fid;		// 转发父id
	private String rootid;		// 转发原发言id
	private Date beginCreatetime;		// 开始 发布时间
	private Date endCreatetime;		// 结束 发布时间
	
	private String noticeid;		// 公告ID
	private String title;		// 标题
	private String summary;		// 摘要
	private String remarks;     // 备注
	
	private String villageinfoid;  //楼盘Id
	private String villageinfoname;  //楼盘名称
	private String images;  //图片
	private int delflag;  //删除状态
	
	private String sumforward;  //转发总数
	private String sumcomment;  //评论总数
	private String username;  //发言人姓名
	private String auserid;  //app对应发言人，用户id
	private String ausername;  //app对应发言人姓名
	
	private String addrpro;  //所在省份
	private String addrcity;  //所在城市
	
	private String delImgName;//删除的
	
	private List<SocialSpeakPic> picList;
	
	private List<String> imageUrls; //一览用图片路径
	private int picSize;
	
	private int source; //来源
	private String delflagId;
	
	private List<SocialSubject> subList;  // 话题list
	
	public SocialSpeak() {
		super();
	}

	public SocialSpeak(String id){
		super(id);
	}

	@Length(min=1, max=32, message="发言人，用户ID长度必须介于 1 和 32 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=32, message="话题ID长度必须介于 0 和 32 之间")
	public String getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	
	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	@Length(min=0, max=200, message="标签长度必须介于 0 和 200 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=2, message="可见范围 1：公开 2：好友可见； 3：粉丝可见长度必须介于 1 和 2 之间")
	public String getVisrange() {
		return visrange;
	}

	public void setVisrange(String visrange) {
		this.visrange = visrange;
	}
	
	@Length(min=1, max=2, message="是否禁止评论 1：是；0：否长度必须介于 1 和 2 之间")
	public String getForbitcomment() {
		return forbitcomment;
	}

	public void setForbitcomment(String forbitcomment) {
		this.forbitcomment = forbitcomment;
	}
	
	@Length(min=1, max=2, message="是否禁止转发 1：是； 0：否长度必须介于 1 和 2 之间")
	public String getForbidforward() {
		return forbidforward;
	}

	public void setForbidforward(String forbidforward) {
		this.forbidforward = forbidforward;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="发布时间不能为空")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=1, max=2, message="是否置顶 0：否； 1：是长度必须介于 1 和 2 之间")
	public String getIstop() {
		return istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}
	
	public Date getToptime() {
		return toptime;
	}

	public void setToptime(Date toptime) {
		this.toptime = toptime;
	}

	@Length(min=1, max=11, message="阅读次数长度必须介于 1 和 11 之间")
	public String getReadnum() {
		return readnum;
	}

	public void setReadnum(String readnum) {
		this.readnum = readnum;
	}
	
	@Length(min=0, max=32, message="plateid长度必须介于 0 和 32 之间")
	public String getPlateid() {
		return plateid;
	}

	public void setPlateid(String plateid) {
		this.plateid = plateid;
	}
	
	public Date getBeginCreatetime() {
		return beginCreatetime;
	}

	public void setBeginCreatetime(Date beginCreatetime) {
		this.beginCreatetime = beginCreatetime;
	}
	
	public Date getEndCreatetime() {
		return endCreatetime;
	}

	public void setEndCreatetime(Date endCreatetime) {
		this.endCreatetime = endCreatetime;
	}

	public String getVillageinfoid() {
		return villageinfoid;
	}

	public void setVillageinfoid(String villageinfoid) {
		this.villageinfoid = villageinfoid;
	}

	public String getVillageinfoname() {
		return villageinfoname;
	}

	public void setVillageinfoname(String villageinfoname) {
		this.villageinfoname = villageinfoname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getDelflag() {
		return delflag;
	}

	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

	public String getSumforward() {
		return sumforward;
	}

	public void setSumforward(String sumforward) {
		this.sumforward = sumforward;
	}

	public String getSumcomment() {
		return sumcomment;
	}

	public void setSumcomment(String sumcomment) {
		this.sumcomment = sumcomment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddrpro() {
		return addrpro;
	}

	public void setAddrpro(String addrpro) {
		this.addrpro = addrpro;
	}

	public String getAddrcity() {
		return addrcity;
	}

	public void setAddrcity(String addrcity) {
		this.addrcity = addrcity;
	}

	public String getDelImgName() {
		return delImgName;
	}

	public void setDelImgName(String delImgName) {
		this.delImgName = delImgName;
	}

	public List<SocialSpeakPic> getPicList() {
		return picList;
	}

	public void setPicList(List<SocialSpeakPic> picList) {
		this.picList = picList;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getPicSize() {
		return picSize;
	}

	public void setPicSize(int length) {
		this.picSize = length;
	}

	public List<SocialSubject> getSubList() {
		return subList;
	}

	public void setSubList(List<SocialSubject> subList) {
		this.subList = subList;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getAuserid() {
		return auserid;
	}

	public void setAuserid(String auserid) {
		this.auserid = auserid;
	}

	public String getAusername() {
		return ausername;
	}

	public void setAusername(String ausername) {
		this.ausername = ausername;
	}

	public int getIsspeak() {
		return isspeak;
	}

	public void setIsspeak(int isspeak) {
		this.isspeak = isspeak;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getRootid() {
		return rootid;
	}

	public void setRootid(String rootid) {
		this.rootid = rootid;
	}

	public String getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Length(min=0, max=255, message="备注长度必须介于 0 和 255 之间")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelflagId() {
		return delflagId;
	}

	public void setDelflagId(String delflagId) {
		this.delflagId = delflagId;
	}
	

}