package com.wu1g.common.utils;



/**
 * <p>系统程序用常量</p>
 * @author wuxiaogang
 *
 */
public class CommonConstant {
	/** 数据库事务默认超时时间 */
	public static final int DB_DEFAULT_TIMEOUT = 300;
	/** 分页对象KEY */
	public static final String PAGEROW_OBJECT_KEY = "PAGEROW_OBJECT_KEY";
	/** 分页偏移量名称 */
	public static final String PAGEROW_OFFSET_NAME = "offset";
	/** 默认画面每页的记录数 */
	public static final int PAGEROW_DEFAULT_COUNT = 15;
	/** 画面显示的页码数量 */
	public static final int PAGEROW_CURR_NENT_COUNT = 15;
	/** SESSION里面存放认证码 */
	public static final String SESSION_VERIFY_CODE = "SESSION_VERIFY_CODE";
	/** SESSION里面存放 用户信息 */
	public static final String SESSION_KEY_USER = "SESSION_KEY_USER";
	/** SESSION里面存放系统 用户信息 */
	public static final String SESSION_SYS_KEY_USER = "SESSION_SYS_KEY_USER";
	/** SESSION里面存放 用户信息 */
	public static final String SESSION_SYS_KEY_USER_PERMS = "SESSION_SYS_KEY_USER_PERMS";
	/** 路径分隔符 */
	public static final String PATH_SEPARATOR = "/";
	/** 系统默认编码 */
	public static final String DEFAULT_ENCODE = "UTF-8";
	/** 空字符串 */
	public static final String EMPTY_STRING = "";
	/** 空格 */
	public static final String BLANK_STRING = " ";
	/** 异常信息 */
	public static final String LOG_ERROR_TITLE = "异常信息";
	
	/** 认证信息类型：邮箱 */
	public static final String VERIFY_INFO_TYPE_MAIL = "1";
	/** 认证信息类型：手机 */
	public static final String VERIFY_INFO_TYPE_MOBILE = "2";
	/** 邮箱认证信息有效期限：24小时 */
	public static final int VERIFY_INFO_MAIL_EXPIRATION = 1440;
	/** 手机认证信息有效期限：30分钟 */
	public static final int VERIFY_INFO_MOBILE_EXPIRATION = 30;
	/** 绑定手机时的手机号码ID */
	public static final String SESSION_KEY_BIND_MOBILE_ID = "sessionKey_bindMobile";
	//================================微信+公共号====begin=============================
	/** SESSION里面存放公共账号信息 */
	public static final String SESSION_WECHAT_BEAN = "SESSION_WECHAT_BEAN_";
	/** SESSION里面存放公共账号信息 flag */
	public static final String SESSION_WECHAT_BEAN_FLAG = "SESSION_WECHAT_BEAN_FLAG_";
	/** 存放 微信公共账号的Access_token */
	public static final String SESSION_KEY_USER_WECHAT_ACCESS_TOKEN = "SESSION_KEY_USER_WECHAT_ACCESS_TOKEN";
	//================================微信+公共号=====end==============================
	//================================微信+企业号====begin=============================
	/** SESSION里面存放企业账号信息 */
	public static final String SESSION_WECHAT_QIYE_BEAN = "SESSION_WECHAT_QIYE_BEAN_";
	/** SESSION里面存放企业账号信息 flag */
	public static final String SESSION_WECHAT_QIYE_BEAN_FLAG = "SESSION_WECHAT_QIYE_BEAN_FLAG_";
	/** 存放 微信企业账号的Access_token */
	public static final String SESSION_KEY_USER_WECHAT_QIYE_ACCESS_TOKEN = "SESSION_KEY_USER_WECHAT_QIYE_ACCESS_TOKEN";
	/** 存放 微信企业账号的应用套件令牌 suite_token */
	public static final String SESSION_KEY_USER_WECHAT_QIYE_SUITE_ACCESS_TOKEN = "SESSION_KEY_USER_WECHAT_QIYE_SUITE_ACCESS_TOKEN";
	/** 存放 微信企业号 【调用企业接口所需】的access_token */
	public static final String SESSION_KEY_USER_WECHAT_QIYE_CORP_ACCESS_TOKEN = "SESSION_KEY_USER_WECHAT_QIYE_CORP_ACCESS_TOKEN";
	//================================微信+企业号=====end==============================


	
	/** 存放会员登录信息 */
	public static final String SESSION_KEY_USER_MEMBER_INFO = "SESSION_KEY_USER_MEMBER_INFO";
	/** 存放教师登录信息 */
	public static final String SESSION_KEY_USER_TEACHER_INFO = "SESSION_KEY_USER_TEACHER_INFO";
	


}
