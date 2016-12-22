package com.wu1g.common;

import java.io.File;


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
	/** 路径分隔符 */
	public static final String PATH_SEPARATOR = File.separator;
	/** 系统默认编码 */
	public static final String DEFAULT_ENCODE = "UTF-8";
	/** 空字符串 */
	public static final String EMPTY_STRING = "";
	/** 空格 */
	public static final String BLANK_STRING = " ";
	/** 所属地区县 */
	public static final String LOG_ERROR_TITLE = "异常信息";
	//------------------------RSS-----------------------------
	public static final String RSS_DOM_ROOT_TITLE = "//channel/title";

	public static final String RSS_DOM_ROOT_LINK = "//channel/link";

	public static final String RSS_DOM_ROOT_DESCRIPTION = "//channel/description";

	public static final String RSS_DOM_CHILRDEN_ROOT = "//channel/item";

	public static final String RSS_DOM_CHILRDEN_ROOT_TITLE = "title";

	public static final String RSS_DOM_CHILRDEN_ROOT_LINK = "link";
	
	public static final String RSS_DOM_CHILRDEN_ROOT_AUTHOR = "author";
	
	public static final String RSS_DOM_CHILRDEN_ROOT_PUBDATE = "pubDate";

	public static final String RSS_DOM_CHILRDEN_ROOT_DESCRIPTION = "description";
	
	/** 爬虫采集结果信息保存表 */
	public static enum robots{baseInfo,baseInfoComments,baseInfoConsult,baseInfoTicket,baseFile};
}
