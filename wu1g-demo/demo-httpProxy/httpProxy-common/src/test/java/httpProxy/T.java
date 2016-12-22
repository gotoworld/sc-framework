/*
 *  类描述待补充.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016年5月4日  wxg      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 httpProxy  System. - All Rights Reserved.
 *
 */
package httpProxy;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.util.Cookie;

import com.wu1g.common.ValidatorUtil;

/**
 * <p>
 * 类功能说明待补充
 * </p>
 * <dl>
 * [功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
public class T {
	private static transient Logger log = LogManager.getLogger( T.class );

	public static void main(String[] args) throws Exception {
		// String linkUrl="http://wu1g119.iteye.com/blog/2290908";
		// String cookieArrStr=" _javaeye_cookie_id_=1462342307949553;
		// _javaeye3_session_=BAh7CDoQX2NzcmZfdG9rZW4iMXlTMTV3ZCtSSjhRUlpkYk1wKzVleURIekNMbXlGMmhCSUgxV1pPdklrTm89Og9zZXNzaW9uX2lkIiVkMTQzMzA0MjFhY2MzOTA0Zjc1NGE5ZjU2N2JhYTMxMjoMdXNlcl9pZGkD2mIN--531e740a7aab4f70644efd6fd657e5825c1956d1;
		// dc_tos=o6n2vk; dc_session_id=1462342736113; __utma=191637234.2104650520.1462342333.1462342333.1462342333.1;
		// __utmb=191637234.4.10.1462342333; __utmc=191637234; __utmz=191637234.1462342333.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none);
		// __utmt=1; remember_me=no";
		//
		// Set<Cookie> cookies=getCookieSet( linkUrl, cookieArrStr );
		// Iterator<Cookie> i = cookies.iterator();
		// while (i.hasNext()) {
		// Cookie c=i.next();
		// log.error( "===c==="+c );
		// }

//		String htmlStr = "<!DOCTYPE html><!--[if IE 8]> <html lang=\"zh-CN\" class=\"ie8 no-js\"> <![endif]--><!--[if IE 9]> <html lang=\"zh-CN\" class=\"ie9 no-js\"> <![endif]--><!--[if !IE]><!--> <html lang=\"zh-CN\" class=\"no-js\"> <!--<![endif]--><head> <meta http-equiv=Content-Type content=\"text/html;charset=gb2318-8\"><meta chazrset=\"utf-8\" /><meta http-equiv=\"X-UA-Compatible\" content=\"charset=utf-16,IE=edge,Chrome=1\"><meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\" /><meta name=\"description\" content=\"description of your site\" /><meta name=\"author\" content=\"author of the site\" /><title>sportworld</title><link rel=\"stylesheet\" href=\"css/base.css\" /><style>	html {		height: 100%;	}		body {		height: 100%;		background: url(img/header.jpg) no-repeat center/cover;	}		#container {		margin: 0 auto;		text-align: center;		position: absolute;		top: 50%;		margin-top: -150px;		left: 0;		right: 0;		color: #ebebeb;	}		#container h1 {		font-weight: 200;		font-size: 40px;		text-transform: capitalize;	}	#container h4{		font-size: 24px;		font-weight: normal;		margin: 20px auto;		color: #ccc;	}		#container a {		display: inline-block;		background-color: #45b6af;		width: 140px;		height: 40px;		line-height: 40px;		text-align: center;		margin: 60px;		cursor: pointer;		text-transform: uppercase;	}	#container a abbr{		text-decoration: none;		display: block;		color: #fff;	}</style></head><!-- BEGIN BODY --><body class=\"page-header-fixed\">	<div id=\"container\">		<h1>welcome to sports world china</h1>		<h4>before entering the system , please select a language version , chinese or english ?</h4>		<a href=\"/index.ac?language=zh_CN\"><abbr title=\"中文\">中文</abbr></a>		<a href=\"/index.ac?language=en_US\"><abbr title=\"英文\">english</abbr></a>	</div></body><!-- END BODY --></html>";
//		System.out.println( getCharSet( htmlStr ) );
		
//		String x="<meta http-equiv=\"Content-Type\" content='text/html;charset=utf-23'>";
//		String reg="(?is)<meta.*charset=(.*)[\"|\'].*";
//		System.out.println( x.replaceAll( reg, "$1" ) );
		
//		URI abs = new URI( "http://m.ltaaa.com/1/2/3.jsp" ).resolve( "2.jsp" );
//		URL absURL = abs.toURL(); // 转成URL
//		System.out.println( absURL.toString() ); 
		String html="<!DOCTYPE html><html><head><meta charset=\"utf-8\" /></head><body> <div class=\"c1\" src=\"xxx.iframe\" > <div> <div></div><div>1111</div></div></div> </body></html>";
		Document doc=Jsoup.parse( html );
		Elements iframeEls=doc.select("div.c1");
		if(iframeEls!=null){
			for (Element el : iframeEls) {
				String iframeHtml=el.html();
				System.out.println( iframeHtml );
//				if(iframeHtml!=null){
//					Document iframeDoc = Jsoup.parse(iframeHtml);
//					if(iframeDoc!=null){
//						System.out.println( iframeDoc );
//					}
//				}
			}
		}
		
	}

	public static void analyzeHtml(String htmlStr) {
		Document doc = Jsoup.parse( htmlStr );
	}

	/**
	 * 获得网页的编码
	 * 
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public static String getCharSet(String htmlStr) {
		String charSet = null;
		try {
			Document doc = Jsoup.parse( htmlStr );
			Elements metaTags = doc.getElementsByTag( "meta" );
			if (metaTags != null) {
				for (Element el : metaTags) {
					System.out.println( el.toString() );
					if (el.attr( "content" ) != null && el.attr( "content" ).toLowerCase().indexOf( "charset=" )!=-1) {
						String reg="(?is)<meta.*charset=(.*)[\"|\'].*";
						String charset=el.toString().replaceAll( reg, "$1" ) ;
						charSet = charset;
					} else {
						String charset = el.attr( "charset" );
						charSet = charset;
					}
					if(ValidatorUtil.notEmpty( charSet )){
						return charSet;
					}
				}
			}
		} catch (Exception e) {
			log.error( "获取网页编码失败!",e );
		}
		return charSet;
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
	private static void getUrl(List<String> list, Document doc, String thePageUrl, String tag, String attr) {
		Elements els = doc.getElementsByTag( tag );
		for (Element el : els) {
			try {
				String url = el.attr( attr );
				if (url == null || url.trim().length() == 0) {
					return;
				}
				System.out.println( "==1==" + url );
				if (!url.startsWith( "http" )) {
					if (thePageUrl.startsWith( "http" )) {
						url = constructUrl( url, thePageUrl, false ).toString();
					} else {
						URI abs = new URI( thePageUrl ).resolve( url );
						URL absURL = abs.toURL(); // 转成URL
						url = absURL.toString();
					}
				}
				System.out.println( "==2==" + url );
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
	private static void cssParser(List<String> l, String cssContent) throws Exception {
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
	 * cookie字符串合集转为htmlutil的cookie对象集合
	 * 
	 * @param linkUrl
	 * @param cookieArrStr
	 * @return
	 */
	public static Set<Cookie> getCookieSet(String linkUrl, String cookieArrStr) {
		Set<Cookie> cookies = new HashSet<>();
		String domain = getUrlDomain( linkUrl );
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
	 * 根据本页url转化html中的相对路径url
	 * 
	 * @param link
	 * @param base
	 * @param strict
	 * @return
	 * @throws MalformedURLException
	 */
	public static URL constructUrl(String link, String thePageUrl, boolean strict) throws MalformedURLException {
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
	public static String getUrlDomain(String url) {
		try {
			String host = new URL( url ).getHost().toLowerCase();// 此处获取值转换为小写
			Pattern pattern = Pattern.compile( "[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)" );
			Matcher matcher = pattern.matcher( host );
			while (matcher.find()) {
				return matcher.group();
			}
		} catch (MalformedURLException e) {
		}
		return "";
	}

}
