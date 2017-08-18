package com.its.modules.social.common;

/**
 * 邻里圈常量类
 *
 */
public class SocialGlobal {
	
	/** 板块ID */
	public static final int SYS_PLATE_ID = 1;
	
	/** 分页信息 首页10条，其他的20条 */
	public static final int PAGE_SIZE_INDEX = 10;
	public static final int PAGE_SIZE = 20;

	/** 查看发言列表是否全部   0：只看我关注的； 1：查看全部 */
	public static final int SPEAK_LIST_NOT_ALL = 0;

	public static final int SPEAK_LIST_ALL = 1;
	
	/** 发言删除标识 0：已删除  1： 未删除 */
	public static final int SPEAK_DEL_FLAG_YES = 0;
	
	public static final int SPEAK_DEL_FLAG_NO = 1;

	/** 话题关系类型  1：发言 2：评论*/
	public static final int SUB_RELATION_SPK = 1;
	
	public static final int SUB_RELATION_CMT = 2;
	
	/** 评论删除标识 0：已删除  1： 未删除 */
	public static final int COMMENT_DEL_FLAG_YES = 0;
	
	public static final int COMMENT_DEL_FLAG_NO = 1;
	
	/** 点赞类别标识  1：发言 2：评论*/
	public static final int PRAISE_TYPE_SPEAK = 1;
	public static final int PRAISE_TYPE_COMMENT = 2;
	
	/** 提醒1：发言；2：评论 ；3：转发。*/
	public static final int TIPS_TYPE_SPEAK = 1;
	public static final int TIPS_TYPE_COMMENT = 2;
	public static final int TIPS_TYPE_FORWARD = 3;
	
	/** 是否通知 1：已通知 0：未通知*/
	public static final int TIPS_IS_NOTICE_YES = 1;
	public static final int TIPS_IS_NOTICE_NO = 0;
	
	/** 是否评论 1：是 0 ：否 */
	public static final int COMMENT_ISCOMMENT_YES = 1;
	public static final int COMMENT_ISCOMMENT_NO = 0;
	
	/** 点赞状态 1：已赞  0：取消赞 */
	public static final int PRAISE_STATE_YES = 1;
	public static final int PRAISE_STATE_NO = 0;
	
	/** 是否同时转发1：是；0：否 */
	public static final int COMMENT_AND_FORWARD_YES = 1;
	
	public static final int COMMENT_AND_FORWARD_NO = 0;
	
	/** 可见范围 1：公开 2：好友可见； 3：粉丝可见 */
	public static final int FORWARD_VIS_RANGE_OPEN = 1;
	
	public static final int FORWARD_VIS_RANGE_FRIEND = 2;
	
	public static final int FORWARD_VIS_RANGE_FANS = 3;
	
	/** 是否禁止评论 1：是；0：否 */
	public static final int FORWARD_FORBIT_COMMENT_YES = 1;
	
	public static final int FORWARD_FORBIT_COMMENT_NO = 0;
	
	/** 是否禁止转发 1：是； 0：否 */
	public static final int FORWARD_FORBIT_FORWARD_YES = 1;
	
	public static final int FORWARD_FORBIT_FORWARD_NO = 0;
	
	/** 我家 我的赞是否是评论 */
	public static final int MYHOME_MYPRAISE_COMMENT_YES = 1;
	public static final int MYHOME_MYPRAISE_COMMENT_NO = 0;
	
	/** 发言标识1：发言；0：转发 */
	public static final int SPEAK_IS_SPEAK_YES = 1;
	public static final int SPEAK_IS_SPEAK_NO = 0;
	
	/** 是否转发1：是；0否 */
	public static final int SPEAK_IS_FORWORD_YES = 1;
	public static final int SPEAK_IS_FORWORD_NO = 0;
	
	/** 是否关注1：关注 0 ：取消关注 */
	public static final int RELATION_IS_FOCUS_YES = 1;
	public static final int RELATION_IS_FOCUS_NO = 0;
	
	/** 是否屏蔽，1：是；0：否 */
	public static final int RELATION_IS_BLACK_YES = 1;
	public static final int RELATION_IS_BLACK_NO = 0;
	
	/** 是否是新话题 */
	public static final int SUBJECT_IS_NEW_YES = 1;
	public static final int SUBJECT_IS_NEW_NO = 0;
	
	/** 是否推荐话题 */
	public static final int SUBJECT_IS_RECOMMEND_YES = 1;
	public static final int SUBJECT_IS_RECOMMEND_NO = 0;
	
	/** 是否置顶 */
	public static final int SPEAK_IS_TOP_YES = 1;
	public static final int SPEAK_IS_TOP_NO = 0;
	
	/** 是否已读 */
	public static final int TIPS_IS_READ_YES = 1;
	public static final int TIPS_IS_READ_NO = 0;
	
	/** 是否同时评论 */
	public static final int SPEAK_IS_COMMENT_YES = 1;
	public static final int SPEAK_IS_COMMENT_NO = 0;
	
}
