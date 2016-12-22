/*
 *  类描述待补充.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016年5月3日  wxg      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 httpProxy  System. - All Rights Reserved.
 *
 */
package com.wu1g.thread.robot;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wu1g.bean.robot.BaseInfoBean;
import com.wu1g.bean.robot.BaseSiteBean;
import com.wu1g.bean.robot.ConfigJsonBean;
import com.wu1g.bean.robot.ToInfoBean;
import com.wu1g.common.CommonConstant;
import com.wu1g.common.ReflectUtil;
import com.wu1g.common.ResourcesUtil;
import com.wu1g.common.ValidatorUtil;
import com.wu1g.service.robot.IBaseFileManager;
import com.wu1g.service.robot.IBaseInfoManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.druid.sql.parser.ParserException;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import com.wu1g.common.StrUtil;

/**
 * <p>
 * 类功能说明待补充
 * </p>
 * <dl>
 * [功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
public class HtmlThread implements Runnable {
	private static transient Logger		log			= LogManager.getLogger( HtmlThread.class );
	/** 当前页面url地址 */
	private String						linkUrl;
	/** 存放客户端请求的报文头 */
	protected HashMap<String, String>	requestHeader;
	/** htmlutil浏览器客户端实例 */
	private WebClient					webClient;
	/** htmlutil浏览器cookie */
	private Set<Cookie>					cookies;

	/**
	 * 线程池
	 */
	private ExecutorService				taskExecutor;
	/** 采集结果 业务处理 */
	private IBaseInfoManager baseInfoManager;
	/** 附件 业务处理 */
	private IBaseFileManager baseFileManager;
	/** 爬虫深度 */
	private Integer						webDepth	= 10;
	/** 站点信息bean */
	private BaseSiteBean siteBean;
	// /** 域名 */
	// private String myDomain;
	/** 网址网页深度 */
	private Map<String, Integer>		deepUrls;
	/** 存放已经处理过的Url集合及摘要 */
	private Map<String, String>			urlSet;
	/** 爬虫访问多线程标记 0不开启多线程 1开启多线程(容易被封IP) */
	private String						ROBOTS_THREAD_FLAG;
	/** 爬虫获取信息间隔时间(单位 seconds秒) */
	private int							ROBOTS_THREAD_SLEEP;

	// /** 存放还未处理的url的集合 */
	// private Queue<String> urlQueue;
	/** 私有.构造 */
	private HtmlThread() {
	}

	/**
	 * 
	 * @param _linkUrl
	 * @param _requestHeader
	 */
	public HtmlThread(String _linkUrl, HashMap<String, String> _requestHeader) {
		linkUrl = _linkUrl;
		requestHeader = _requestHeader;
	}

	/**
	 * 
	 * @param _ROBOTS_THREAD_FLAG爬虫访问多线程标记
	 *            0不开启多线程 1开启多线程(容易被封IP)
	 * @param _ROBOTS_THREAD_SLEEP
	 *            爬虫获取信息间隔时间(单位 MS毫秒)
	 * @param _url
	 *            站点地址
	 * @param _deepUrls
	 *            网址网页深度
	 * @param _urlSet
	 *            存放已经处理过的Url集合及摘要
	 * @param _urlQueue
	 *            存放还未处理的url的集合
	 * @param _siteBean
	 *            站点信息bean
	 * @param _taskExecutor
	 *            线程池
	 * @param _baseInfoManager
	 *            爬虫结果 业务处理
	 * @param _baseFileManager
	 */
	public HtmlThread(String _ROBOTS_THREAD_FLAG, int _ROBOTS_THREAD_SLEEP, String _url, Map<String, Integer> _deepUrls, Map<String, String> _urlSet,
			BaseSiteBean _siteBean, ExecutorService _taskExecutor, HashMap<String, String> _requestHeader, WebClient _webClient, Set<Cookie> _cookies,
			IBaseInfoManager _baseInfoManager, IBaseFileManager _baseFileManager) {
		ROBOTS_THREAD_FLAG = _ROBOTS_THREAD_FLAG;
		ROBOTS_THREAD_SLEEP = _ROBOTS_THREAD_SLEEP;
		linkUrl = _url;
		deepUrls = _deepUrls;
		urlSet = _urlSet;
		// urlQueue=_urlQueue;
		siteBean = _siteBean;
		taskExecutor = _taskExecutor;
		if (taskExecutor == null) {
			taskExecutor = Executors.newCachedThreadPool();
		}
		/// ** 存放客户端请求的报文头 */
		requestHeader = _requestHeader;
		/// ** htmlutil浏览器客户端实例 */
		webClient = _webClient;
		/// ** htmlutil浏览器cookie */
		cookies = _cookies;

		baseInfoManager = _baseInfoManager;
		if (ValidatorUtil.isIntNumber( _siteBean.getWeb_depth() )) {
			webDepth = Integer.parseInt( _siteBean.getWeb_depth() );
		}
		baseFileManager = _baseFileManager;
		// log.info("ok="+RobotThread.siteUrlOKCountMap.get(siteBean.getId())+"=all="+RobotThread.siteUrlAllCountMap.get(siteBean.getId()));
		// daying
		log.debug( "站点名称=" + siteBean.getTitle() + "=总深度=" + webDepth + "=已处理=" + urlSet.size() + "=当前深度=" + deepUrls.get( linkUrl ) + "--网址="
				+ linkUrl );
	}

	/**
	 * 单线程使用 参数设置方法
	 * 
	 * @param _url
	 *            站点地址
	 */
	public void setHtmlThread(String _url) {
		linkUrl = _url;
		// daying
		log.debug( "站点名称=" + siteBean.getTitle() + "=总深度=" + webDepth + "=已处理=" + urlSet.size() + "=当前深度=" + deepUrls.get( linkUrl ) + "--网址="
				+ linkUrl );
	}

	public void run() {
		Integer siteRunSwitcNUM = 1;
		// 线程 // 开始进行网络爬取。获取爬虫结果
		try {
			// // 强制关闭按钮 0关1开
			// if (RobotThread.siteRunSwitcMap.get( siteBean.getId() ) == null
			// || siteRunSwitcNUM != RobotThread.siteRunSwitcMap.get(
			// siteBean.getId() )) {
			// runSwitchClear();
			// // 关闭
			// return;
			// // throw new Exception("站点强制关闭!");
			// }
			// 待处理链接集合
			List<String> arryUrl = new ArrayList<String>();
			// log.info("当前队列中还未处理的Url数量：" + urlQueue.size());
			String theUrl = linkUrl;
			// 给要解析的url生成摘要，检测是否碰撞。
			String urlDigest = MD5Digest( theUrl );
			// 如果此URL未访问过
			if (!urlSet.containsKey( urlDigest )) {
				// --解析地址获取 信息----------
				String charSet = siteBean.getCharset();// 用户设置了编码
				if (ValidatorUtil.isEmpty( charSet )) {
					charSet = getCharSet( getHtml( theUrl, null ) );// 获取网页编码
				}
				String html = getHtml( theUrl, charSet );// 根据网页编码 再次获取html信息
				if (html != null) {
					// 获取当前页面的信息并保存入数据库
					// this.parseHtml( theUrl, html );TODO
					// --获取当前页面所有链接地址
					arryUrl = getHtmlLink( html, urlDigest, theUrl );
					/*
					 * 做这么复杂的代码 就是怕坑爹的运维人员 乱操作
					 */
					if (arryUrl != null && arryUrl.size() > 0) {
						// 站点处理链接总数量--------------------1------------------------------
						if (RobotThread.siteUrlAllCountMap.get( siteBean.getId() ) != null) {
							Integer num = RobotThread.siteUrlAllCountMap.get( siteBean.getId() );// 暂存数量
							RobotThread.siteUrlAllCountMap.remove( siteBean.getId() );// 移除当前
							RobotThread.siteUrlAllCountMap.put( siteBean.getId(), num + arryUrl.size() );// 存储
																											// 数量+1
						}
						// 创建线程解析-------------------------2-------------------------------
						for (String url_temp : arryUrl) {
							// --确定当前链接 深度
							deepUrls.put( url_temp, deepUrls.get( linkUrl ) + 1 );
							try {
								Thread.sleep( ROBOTS_THREAD_SLEEP );// ROBOTS_THREAD_SLEEP
																	// 毫秒后创建新线程
							} catch (InterruptedException e) {
								log.error( "Thread.sleep 错误!" );
							}
							// 先把超链接从队列中取出来,然后从头部删除。 //判断该url没有访问
							// 是否开启多线程
							if ("1".equals( ROBOTS_THREAD_FLAG )) {
								// 开启多线程 (容易被封ip)
								taskExecutor.execute( new HtmlThread( ROBOTS_THREAD_FLAG, ROBOTS_THREAD_SLEEP, url_temp, deepUrls, urlSet, siteBean,
										taskExecutor, requestHeader, webClient, cookies, baseInfoManager, baseFileManager ) );
								//
							} else {
								// 单线程处理
								// 参数设置
								setHtmlThread( url_temp );
								run();// 执行信息采集
							}
						}
					}
				}
			}
			// 已处理链接数量
			if (RobotThread.siteUrlOKCountMap.get( siteBean.getId() ) != null) {
				Integer num = RobotThread.siteUrlOKCountMap.get( siteBean.getId() );// 暂存数量
				RobotThread.siteUrlOKCountMap.remove( siteBean.getId() );// 移除当前
				RobotThread.siteUrlOKCountMap.put( siteBean.getId(), num + 1 );// 存储
																				// 数量+1
			}
			log.info( "ok=" + RobotThread.siteUrlOKCountMap.get( siteBean.getId() ) + "=all="
					+ RobotThread.siteUrlAllCountMap.get( siteBean.getId() ) );
			// 判断是否处理完成 已处理数量 与 总数量相等
			if (RobotThread.siteUrlOKCountMap.get( siteBean.getId() ) == RobotThread.siteUrlAllCountMap.get( siteBean.getId() )) {
				runSwitchClear();
			}
		} catch (Exception e) {
			// 问题
			log.error( "爬虫访问!" + linkUrl + "失败!", e );
		}
	}

	/**
	 * 通过htmlparser访问页面
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public String getHtml(String address, String charSet) {
		String htmlStr = null;
		try {
			if (webClient == null) {
				webClient = new WebClient();
				// 设置webClient的相关参数
				// 其他报文头字段可以根据需要添加
				webClient.getCookieManager().setCookiesEnabled( true );// 开启cookie管理
				webClient.getOptions().setJavaScriptEnabled( true );// 开启js解析。对于变态网页，这个是必须的
				webClient.getOptions().setCssEnabled( false );// 开启css解析。对于变态网页，这个是必须的。
				webClient.getOptions().setThrowExceptionOnFailingStatusCode( false );
				webClient.getOptions().setThrowExceptionOnScriptError( false );
				webClient.getOptions().setTimeout( 15000 );
			}

			if (requestHeader != null && requestHeader.size() > 0) {
				for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
					// log.info( "============================" + entry.getKey()
					// + ":" + entry.getValue() );
					if (!"cookie".equals( entry.getKey() )) {
						webClient.addRequestHeader( entry.getKey(), entry.getValue() );
						// webClient.addRequestHeader和request.setAdditionalHeader功能应该是一样的。选择一个即可。
					}
				}
				// 设置cookie。如果你有cookie，可以在这里设置
				String cookieArrStr = requestHeader.get( "cookie" );
				if (cookies != null && cookieArrStr != null && cookieArrStr.trim().length() > 0) {
					cookies = getCookieSet( linkUrl, cookieArrStr );
				}
			}

			if (cookies != null) {
				Iterator<Cookie> i = cookies.iterator();
				while (i.hasNext()) {
					webClient.getCookieManager().addCookie( i.next() );
				}
			}
			// // 准备工作已经做好了

			WebRequest request = new WebRequest( new URL( address ) );
			request.setCharset( charSet == null ? "utf-8" : charSet );
			// request.setProxyHost("127.0.0.1");
			// request.setProxyPort(8080);
			// 模拟浏览器打开一个目标网址
			final HtmlPage page = webClient.getPage( request );
			webClient.waitForBackgroundJavaScript(30000);//设置JS后台等待执行时间
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
			CookieManager CM = webClient.getCookieManager();
			cookies = CM.getCookies();// 返回的Cookie在这里，下次请求的时候可能可以用上啦。
//			 Iterator<Cookie> iterator = cookies.iterator();
//			 //
//			 while (iterator.hasNext()) {
//				 Cookie cookie = iterator.next();
//				 System.out.println("=cookie="+cookie  );
//			 }
			//
//			WebResponse webResponse = page.getWebResponse();
//			htmlStr = webResponse.getContentAsString();// charSet==null?"utf-8":charSet
			htmlStr=page.asXml();
			// log.info( htmlStr );
			// System.out.println( "Success!" );
		} catch (Throwable e) {
			log.error( "站点=" + address + "访问失败!" );
		}
		return htmlStr;
	}

	/**
	 * 获取当前页面所有链接地址
	 * 
	 * @param urlDigest
	 * @param theUrl
	 * @param charSet
	 * @return
	 */
	private List<String> getHtmlLink(String htmlStr, String urlDigest, String theUrl) {
		// 待处理链接集合
		List<String> arryUrl = new ArrayList<String>();
		try {
			// 所有超链接集合。
			List<String> urlList = new ArrayList<>();
			// 往散列表里写入摘要信息，表示该URL已经访问过了。
			urlSet.put( urlDigest, "摘要" );
			// 创建解析器 查找所有的超级链接
			// 先把整个的HTML源代码获取一下。// 再重新过滤一遍，只留下超链接。
			Document doc = Jsoup.parse( htmlStr );
			getUrl( urlList, doc, theUrl, "a", "href" );
			// 过滤 <frame> 标签的 filter，用来提取 frame 标签里的 src 属性所、表示的链接
			Elements iframeEls=doc.select("iframe");
			if(iframeEls!=null){
				for (Element el : iframeEls) {
					String iframeHtml=el.text();
					if(iframeHtml!=null){
						Document iframeDoc = Jsoup.parse(iframeHtml);
						if(iframeDoc!=null){
							getUrl( urlList, iframeDoc, theUrl, "a", "href" );
						}
					}
				}
			}
			log.info( "本页共有节点数:" + urlList.size() + "--网址--=" + theUrl );
			for (int i = 0; i < urlList.size(); i++) {
				// 当前链接
				String url_temp = urlList.get( i );
				// 判断检索深度
				if (deepUrls.get( theUrl ) < webDepth) {
					// 链接处理 (如果网站不符合规范)
					if (!ValidatorUtil.isEmpty( url_temp )) {
						url_temp = url_temp.trim();
						// 不是正常网站
						if (!ValidatorUtil.isUrl( url_temp ) && url_temp.length() > 0) {
							// 为了防止侧漏 这段代码应该不会执行吧
							url_temp = getAbsoluteURL( linkUrl, url_temp );
						}
						// 域名过滤
						if ("1".equals( ResourcesUtil.getData( "ROBOTS_DOMAIN_FLAG" ) )) {
							// 与站点域名相同
							String theUrlDomain = getDomain( theUrl );
							if (theUrlDomain != null && !theUrlDomain.equals( getDomain( url_temp ) )) {
								log.debug( "超出站点范围=" + url_temp );
								continue;
							}
						}
						// ------判断链接是否已访问----
						// 如果此URL未访问的!
						if (!urlSet.containsKey( MD5Digest( url_temp ) )) {
							// 加入待处理集合
							arryUrl.add( url_temp );
						}
					}
				} else {
					log.debug( "超出采集深度" + webDepth + ",放弃向下采集!"+url_temp );
				}
			}
		} catch (Exception e) {
			log.error( "站点=" + theUrl + "获取链接地址失败!" );
		}
		return arryUrl;
	}

	/**
	 * 获得网页的编码
	 * 
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public String getCharSet(String htmlStr) {
		String charSet = null;
		if (ValidatorUtil.isNullEmpty( htmlStr )) {
			return null;
		}
		try {
			Document doc = Jsoup.parse( htmlStr );
			Elements metaTags = doc.getElementsByTag( "meta" );
			if (metaTags != null) {
				for (Element el : metaTags) {
					// System.out.println( el.toString() );
					if (el.attr( "content" ) != null && el.attr( "content" ).toLowerCase().indexOf( "charset=" ) != -1) {
						String reg = "(?is)<meta.*charset=(.*)[\"|\'].*";
						String charset = el.toString().replaceAll( reg, "$1" );
						charSet = charset;
					} else {
						String charset = el.attr( "charset" );
						charSet = charset;
					}
					if (ValidatorUtil.notEmpty( charSet )) {
						return charSet;
					}
				}
			}
		} catch (Exception e) {
			log.error( "获取网页编码失败!");
		}
		log.debug( "charSet==" + charSet );
		return charSet;
	}

	/**
	 * 站点信息执行与统计信息清空
	 */
	private void runSwitchClear() {
		if (RobotThread.siteUrlAllCountMap != null) {
			// 清空当前站点 总数量
			RobotThread.siteUrlAllCountMap.remove( siteBean.getId() );
		}
		if (RobotThread.siteUrlOKCountMap != null) {
			// 清空当前站点已处理数量
			RobotThread.siteUrlOKCountMap.remove( siteBean.getId() );
		}
		if (RobotThread.siteRunSwitcMap != null) {
			// 清空当前站点执行开关
			RobotThread.siteRunSwitcMap.remove( siteBean.getId() );// 开
		}
	}

	/**
	 * cookie字符串合集转为htmlutil的cookie对象集合
	 * 
	 * @param linkUrl
	 * @param cookieArrStr
	 * @return
	 */
	public Set<Cookie> getCookieSet(String linkUrl, String cookieArrStr) {
		Set<Cookie> cookies = new HashSet<>();
		String domain = getDomain( linkUrl );
		if (cookieArrStr != null) {
			String[] cookieArr = cookieArrStr.split( ";" );
			if (cookieArr != null) {
				for (int i = 0; i < cookieArr.length; i++) {
					String cookieStr = cookieArr[ i ];
					if (cookieStr != null) {
						if (cookieStr.indexOf( "=" ) != -1) {
							String[] carr = new String[2];
							carr[ 0 ] = cookieStr.substring( 0, cookieStr.indexOf( "=" ) );
							carr[ 1 ] = cookieStr.substring( cookieStr.indexOf( "=" ) + 1, cookieStr.length() );
							String name = carr[ 0 ];
							String value = carr[ 1 ];
							String path = "/";
							Date expires = null;
							boolean secure = false;
							boolean httpOnly = false;
							if (name.indexOf( "session" ) != -1) {
								httpOnly = true;
							}
							cookies.add( new Cookie( domain, name, value, path, expires, secure, httpOnly ) );
						}
					}
				}
			}
		}
		return cookies;
	}

	/**
	 * 获取地址
	 * 
	 * @param list
	 * @param doc
	 * @param thePageUrl
	 * @param tag
	 * @param attr
	 */
	private void getUrl(List<String> list, Document doc, String thePageUrl, String tag, String attr) {
		Elements els = doc.select( tag + "[" + attr + "]" );// getElementsByTag(
															// tag );
		for (Element el : els) {
			try {
				String url = el.attr( attr );
				if (url == null || url.trim().length() == 0) {
					return;
				}
				log.debug( "==待处理==" + url );
				if (!url.startsWith( "http" )) {
					if (thePageUrl.startsWith( "http" )) {
						url = constructUrl( url, thePageUrl, false ).toString();
					} else {
						log.debug( "thePageUrl==" + thePageUrl + "===url===" + url );
						URI abs = new URI( thePageUrl ).resolve( url );
						URL absURL = abs.toURL(); // 转成URL
						url = absURL.toString();
					}
				}
				log.debug( "==已处理==" + url );
				list.add( url );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取css文件中的背景图地址
	 * 
	 * @param l
	 * @param cssContent
	 * @throws Exception
	 */
	private void cssParser(List<String> l, String cssContent) throws Exception {
		Pattern pat = Pattern.compile( "(?is)url\\((.*?)\\)" );
		Matcher mat = pat.matcher( cssContent );
		while (mat.find()) {
			String url = (mat.group( 1 ));
			if (url != null && url.trim().length() > 0) {
				l.add( url.replaceAll( "'", "" ).replaceAll( "\"", "" ) );
			}
		}
	}

	/**
	 * 根据本页url转化html中的相对路径url
	 * 
	 * @param link
	 * @param base
	 * @param strict
	 * @return
	 * @throws MalformedURLException
	 */
	public URL constructUrl(String link, String thePageUrl, boolean strict) throws MalformedURLException {
		int index;
		URL url;
		if (!strict && '?' == link.charAt( 0 )) {
			if (-1 != (index = thePageUrl.lastIndexOf( '?' )))
				thePageUrl = thePageUrl.substring( 0, index );
			url = new URL( thePageUrl + link );
		} else {
			url = new URL( new URL( thePageUrl ), link );
		}
		String path = url.getFile();
		boolean modified = false;
		boolean absolute = link.startsWith( "/" );
		if (!absolute)
			do {
				if (!path.startsWith( "/." ))
					break;
				if (path.startsWith( "/../" )) {
					path = path.substring( 3 );
					modified = true;
					continue;
				}
				if (!path.startsWith( "/./" ) && !path.startsWith( "/." ))
					break;
				path = path.substring( 2 );
				modified = true;
			} while (true);
		while (-1 != (index = path.indexOf( "/\\" ))) {
			path = path.substring( 0, index + 1 ) + path.substring( index + 2 );
			modified = true;
		}
		if (modified)
			url = new URL( url, path );
		return url;
	}

	/** 根据url解析获取主域 */
	public String getDomain(String url) {
		try {
			String host = new URL( url ).getHost().toLowerCase();// 此处获取值转换为小写
			Pattern pattern = Pattern.compile(
					"[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn||\\.xin|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)" );
			Matcher matcher = pattern.matcher( host );
			while (matcher.find()) {
				return matcher.group();
			}
		} catch (MalformedURLException e) {
		}
		return "";
	}

	/**
	 * 将URL摘要，形成一个摘要字符串，并且存放在一个散列表中，进行碰撞检测，以防止重复地访问同一个URL
	 * 
	 * @param theUrl
	 *            当前地址
	 * @return
	 * @throws Exception
	 */
	private String MD5Digest(String theUrl) {
		// 要把字节流转换成字符串。
		StringBuffer buf = new StringBuffer( "" );
		try {
			// 获取一个MD5算法
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( theUrl.getBytes() );
			byte[] bt = md.digest();
			// log.info("已经转换了字节流:"+bt.length+" 摘要数据");
			// 用来获取每个byte的数值
			int i;
			for (int offset = 0; offset < bt.length; offset++) {
				// 用每一个byte填充StringBuffer
				i = bt[ offset ];
				// System.out.print("字节:"+i);
				if (i < 0) {
					i += 256;
				}
				// 两位的,在前边补齐0,以使一个byte显示为两个16进制的数字
				if (i < 16) {
					buf.append( "0" );
				}
				buf.append( Integer.toHexString( i ) );
			}
		} catch (Exception e) {
			log.error( "地址摘要生成失败!" + theUrl );
		}
		return buf.toString();
	}

	/**
	 * 解析html信息 保存解析到的数据
	 * 
	 * @param url
	 *            网址信息连接。
	 * @param html
	 *            网页信息
	 */
	public BaseInfoBean parseHtml(String url, String htmlStr) {
		BaseInfoBean bean = new BaseInfoBean();
		try {
			Document doc = Jsoup.parse( htmlStr );
			// ---------------配置信息-----------------------
			ConfigJsonBean configJsonBean = siteBean.getConfigJsonBean();
			if (configJsonBean != null) {
				// 全部字段a*
				final String[] fieldNames_a = { "title", "author", "public_date", "brief_info", "detail_info", "detail_info_no_html" };
				for (int i = 0; i < fieldNames_a.length; i++) {
					// 具体字段配置
					ToInfoBean cbean = null;
					String fieldName = fieldNames_a[ i ];
					try {
						cbean = (ToInfoBean) ReflectUtil.getValueByFieldName( configJsonBean, fieldName );
					} catch (Exception e) {
						log.error( "反射机制获取配置字段属性出错!", e );
					}
					if (cbean != null) {
						try {
							// --根据每个字段的不同配置获取值 并赋值给当前字段
							ReflectUtil.setValueByFieldName( bean, fieldName, getFilterHtmlInfo( cbean, doc ) );
						} catch (Exception e) {
							log.error( "反射机制设置字段属性值出错!", e );
						}
					}
				}
				// 远程地址
				bean.setRemote_url( url );
				/*
				 * 保存 爬虫找到的信息
				 */
				if (bean != null // bean不能为空
						// 标题和内容不能为空
						&& !ValidatorUtil.isEmpty( bean.getTitle() ) && !ValidatorUtil.isEmpty( bean.getDetail_info() )) {

					bean.setSite_id( siteBean.getId() );// 站点id
					bean.setStatus( "0" );// 状态(0存储1追踪)
					bean.setType( "1" );// 类型(0:RSS,1,采集)
					bean.setCreate_id( "html采集线程" );// 建立者id
					bean.setCreate_ip( "0.0.0.0" );// 建立者ip
					// bean.setUpdate_id("系统线程");//修改者id
					// bean.setUpdate_ip("0.0.0.0");//修改者ip
					bean.setInfo_type( siteBean.getInfo_type() );// 信息分类
					// log.error("=============1==============================================");
					// log.error(bean.getDetail_info());
					// log.error("=============2==============================================");
					String id = baseInfoManager.saveBaseInfo( bean );
					if (id != null) {
						// 信息id
						bean.setId( id );
						// ----图片下载--多线程---
						taskExecutor.execute( new ImgThread( CommonConstant.robots.baseInfo, siteBean.getConfigJsonBean(), "robots", bean,
								fieldNames_a, baseInfoManager, baseFileManager ) );
					}
				}
			}
		} catch (ParserException ex) {
			bean = null;
			log.error( "地址访问失败=" + url );
		}
		return bean;
	}

	/**
	 * 根据条件获取html过滤对象
	 * 
	 * @param tag
	 * @param pro_name
	 * @param pro_value
	 * @return
	 */
	private Elements geNodeFilter(Document doc, String tag, String pro_name, String pro_value) {
		Elements nf = null;
		if (ValidatorUtil.notEmpty( tag ) && ValidatorUtil.notEmpty( pro_name )) {
			if (ValidatorUtil.notEmpty( pro_value )) {
				nf = doc.select( tag + "[" + pro_name + "$=" + pro_value + "]" );
			} else {
				nf = doc.select( tag + "[" + pro_name + "]" );
			}
		} else if (ValidatorUtil.notEmpty( tag )) {
			nf = doc.getElementsByTag( tag );
		}
		return nf;
	}

	/**
	 * 相对路径转为绝对路径
	 * 
	 * @param baseURI
	 * @param relativePath
	 * @return
	 */
	@SuppressWarnings("finally")
	public String getAbsoluteURL(String baseURI, String relativePath) {
		String abURL = null;
		try {
			URI base = new URI( baseURI );// 基本网页URI
			URI abs = base.resolve( relativePath );// 解析于上述网页的相对URL，得到绝对URI
			URL absURL = abs.toURL();// 转成URL
			// System.out.println(absURL);
			abURL = absURL.toString();
		} catch (Exception e) {
			log.error( "图片相对路径转为绝对路径错误!", e );
		} finally {
			return abURL;
		}
	}

	/**
	 * 获取信息的内容
	 * 
	 * @param filter
	 * @param parser
	 * @return info 网址信息内容
	 */
	private String getFilterHtmlInfo(ToInfoBean cbean, Document doc) {
		// 标签 属性 属性值
		Elements els = geNodeFilter( doc, cbean.getTag(), cbean.getPro(), cbean.getVal() );
		if (els == null) {
			return null;
		}
		String info = null;
		try {
			// 获取内层标签的值
			if (ValidatorUtil.notEmpty( cbean.getA_tag() )) {
				StringBuilder data = new StringBuilder();
				StringBuilder a_data = new StringBuilder();
				// 第几个
				if (ValidatorUtil.isIntNumber( cbean.getNum() )) {
					data.append( getNodeText( false, els.get( getNum( cbean.getNum() ) ) ) );
				} else {
					// --未指定(默认全部)
					for (int i = 0; i < els.size(); i++) {
						data.append( getNodeText( false, els.get( i ) ) );
					}
				}
				info = data.toString();
				// 获取内层标签的值
				if (ValidatorUtil.notEmpty( info )) {
					// 标签 属性 属性值
					Elements a_els = geNodeFilter( doc, cbean.getA_tag(), cbean.getA_pro(), cbean.getA_val() );
					if (a_els == null) {
						return null;
					}
					// 第几个
					if (ValidatorUtil.isIntNumber( cbean.getA_num() )) {
						a_data.append( getNodeText( "1".equals( cbean.getIs_self() ), a_els.get( getNum( cbean.getA_num() ) ) ) );
					} else {
						// --未指定(默认全部)
						for (int a_i = 0; a_i < a_els.size(); a_i++) {
							a_data.append( getNodeText( "1".equals( cbean.getIs_self() ), a_els.get( a_i ) ) );
						}
					}
				}
				info = a_data.toString();
			} else {
				StringBuilder data = new StringBuilder();
				// 第几个
				if (ValidatorUtil.isIntNumber( cbean.getNum() )) {
					data.append( getNodeText( "1".equals( cbean.getIs_self() ), els.get( getNum( cbean.getNum() ) ) ) );
				} else {
					// --未指定(默认全部)
					for (int i = 0; i < els.size(); i++) {
						data.append( getNodeText( "1".equals( cbean.getIs_self() ), els.get( i ) ) );
					}
				}
				info = data.toString();
			}
			/* 纯文本标记 */
			if ("1".equals( cbean.getIs_html() )) {
				info = StrUtil.clearHtml( info );
			}
			/* 字符截取标记0否1是 */
			if ("1".equals( cbean.getIs_sub() )) {
				if (ValidatorUtil.notEmpty( info ) && ValidatorUtil.notEmpty( cbean.getS_str() ) && ValidatorUtil.notEmpty( cbean.getE_str() )) {
					// (字符截取)开始字符串
					Integer s_num = info.indexOf( cbean.getS_str() ) + cbean.getS_str().length();
					// (字符截取)结束始字符串
					Integer e_num = info.indexOf( cbean.getE_str() );
					if (s_num != -1 && e_num != -1) {
						info = StrUtil.subStr( info, s_num, e_num );
					}
				}
			}
			/* 字符替换标记0否1是 */
			if ("1".equals( cbean.getIs_replace() )) {
				// --- 新 替换 旧
				info = StrUtil.replaceAll( info, cbean.getStr_old(), cbean.getStr_new() );
			}

		} catch (Exception e) {
			log.debug( "获取内容 失败!" );
		}
		return info;
	}

	/**
	 * 处理数字(第几个)
	 * 
	 * @param strNum
	 * @return
	 */
	public Integer getNum(String strNum) {
		Integer num = 0;
		try {
			num = Integer.parseInt( strNum );
			num--;
		} catch (Exception e) {
		}
		if (num < 0) {
			num = 0;
		}
		return num;
	}

	/**
	 * 获取标签节点的值
	 * 
	 * @param is_self
	 *            是否抓取标签本身false否true是
	 * @param el
	 * @return
	 */
	public String getNodeText(boolean is_self, Element el) {
		String infoText = null;
		try {
			if (is_self) {
				infoText = el.outerHtml();
			} else {
				infoText = el.text();
			}
		} catch (Exception e1) {
		}
		// -----------------
		if (infoText == null) {
			infoText = "";
		}
		return infoText;
	}

	/** test */
	public static void main(String[] args) throws Exception {
		Thread t = new Thread( new HtmlThread( "http://wu1g119.iteye.com/blog/2290908", null ) );
		t.start();
	}
}
