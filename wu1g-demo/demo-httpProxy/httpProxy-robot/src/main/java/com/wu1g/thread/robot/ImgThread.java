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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wu1g.service.robot.IBaseFileManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wu1g.bean.robot.BaseFileBean;
import com.wu1g.bean.robot.BaseInfoBean;
import com.wu1g.bean.robot.ConfigJsonBean;
import com.wu1g.bean.robot.ObjBean;
import com.wu1g.bean.robot.ToInfoBean;
import com.wu1g.common.CommonConstant;
import com.wu1g.common.DateUtil;
import com.wu1g.common.ImageUtil;
import com.wu1g.common.ReflectUtil;
import com.wu1g.common.ResourcesUtil;
import com.wu1g.common.StrUtil;
import com.wu1g.common.ValidatorUtil;
import com.wu1g.common.WebUtil;
import com.wu1g.service.robot.IBaseInfoManager;

/**
 * <p>
 * 类功能说明待补充
 * </p>
 * <dl>
 * [功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
public class ImgThread implements Runnable {
	private static transient Logger	log				= LogManager.getLogger( ImgThread.class );

	// n0图象宽度
	private static final int		IMAGE_N0_WIDTH	= 100;
	// n0图象高度
	private static final int		IMAGE_N0_HEIGHT	= 100;
	// n1图象宽度
	private static final int		IMAGE_N1_WIDTH	= 400;
	// n1图象高度
	private static final int		IMAGE_N1_HEIGHT	= 400;
	// n2图象宽度
	private static final int		IMAGE_N2_WIDTH	= 700;
	// n2图象高度
	private static final int		IMAGE_N2_HEIGHT	= 700;
	// n3图像宽度
	private static final int		IMAGE_N3_WIDTH	= 1200;
	// n3图像高度
	private static final int		IMAGE_N3_HEIGHT	= 1200;
	/** 站点id */
	private String					site_id;
	/** 信息id */
	private String					info_id;
	/** 信息远程地址 */
	private String					remote_url;
	/** 采集结果 业务处理 */
	private IBaseInfoManager		baseInfoManager;
	/** 采集附件 业务处理 */
	private IBaseFileManager baseFileManager;
	/** 采集结果集 */
	private BaseInfoBean infoBean;
	/** 站点信息获取类型 */
	private String					siteType;
	/** 站点抓取配置 bean */
	private ConfigJsonBean configJsonBean;
	/** 字段集合 */
	private String[]				fieldNames;
	/** 信息存储表 */
	private CommonConstant.robots robots;

	/**
	 * 构造器
	 */
	public ImgThread() {
	}

	/**
	 * 构造器(基本信息)
	 * 
	 * @param _robots
	 * @param _configJsonBean
	 * @param _siteType
	 * @param _infoBean
	 * @param _fieldNames
	 * @param _baseInfoManager
	 * @param _baseFileManager
	 */
	public ImgThread(CommonConstant.robots _robots, ConfigJsonBean _configJsonBean, String _siteType, BaseInfoBean _infoBean, String[] _fieldNames, IBaseInfoManager _baseInfoManager, IBaseFileManager _baseFileManager) {
		robots = _robots;
		configJsonBean = _configJsonBean;
		siteType = _siteType;
		site_id = _infoBean.getSite_id();
		info_id = _infoBean.getId();
		remote_url = _infoBean.getRemote_url();
		fieldNames = _fieldNames;
		baseInfoManager = _baseInfoManager;
		baseFileManager = _baseFileManager;
		infoBean = _infoBean;
	}

	/**
	 * 执行线程
	 */
	public void run() {
		// 线程
		try {
			// -------robots--------begin----------------------------------
			// 全部字段
			if (fieldNames != null) {
				switch (robots) {
				case baseInfo:
					//
					BaseInfoBean bean = new BaseInfoBean();
					// 图片存在
					if (ReflectBean( infoBean, bean, infoBean.getId() )) {
						bean.setId( infoBean.getId() );// id
						bean.setUpdate_id( "img下载线程" );//
						bean.setUpdate_ip( "0.0.0.0" );
						bean.setUpdate_flag( true );// 更新标记
						// ==更新数据库==
						baseInfoManager.saveOrUpdateBaseInfo( bean );
						// --------------------end---------------------------------
					}
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			// 问题
			log.error( "图片下载出错!!" );
		}
	}

	/**
	 * 使用反射机制取出bean对象的属性并处理
	 * 
	 * @param infoBean
	 * @param newbean
	 * @param info_id
	 */
	public boolean ReflectBean(Object infoBean, Object newbean, String info_id) {
		boolean img_flag = false;
		if (fieldNames != null)
			for (int i = 0; i < fieldNames.length; i++) {
				// 抓取到的信息
				String info = null;
				// 具体字段配置
				ToInfoBean cbean = null;
				try {
					info = (String) ReflectUtil.getValueByFieldName( infoBean, fieldNames[ i ] );
					if (configJsonBean != null) {
						cbean = (ToInfoBean) ReflectUtil.getValueByFieldName( configJsonBean, fieldNames[ i ] );
					}
				} catch (Exception e) {
					log.error( "反射机制获取配置字段属性出错!", e );
				}
				if (info != null) {
					try {
						// ==获取图片==
						List<ObjBean> img_urls = getImgStr1( info, cbean != null ? cbean.getReal_src() : null );
						if (img_urls != null && img_urls.size() > 0) {
							for (int num = 0; num < img_urls.size(); num++) {
								ObjBean imgObj = img_urls.get( num );
								// 下载图片信息 返回图片路径
								Map<String, String> map = downImage( num, info_id, imgObj );
								if (map != null) {
									for (Entry<String, String> s : map.entrySet()) {
										// 远程路径 换为本地路径
										info = StringUtils.replace( info, String.valueOf( s.getKey() ), String.valueOf( s.getValue() ) );
									}
								}
							}
							img_flag = true;
							// --根据每个字段的不同配置获取值 并赋值给当前字段
							ReflectUtil.setValueByFieldName( newbean, fieldNames[ i ], info );
						}
					} catch (Exception e) {
						log.error( "反射机制设置字段属性值出错!", e );
					}
				}
			}
		return img_flag;
	}

	/**
	 * 下载指定URL路径的图片
	 * 
	 * @param info_id
	 *            信息id
	 * @param num
	 *            附件序号
	 * @param path
	 *            附件远程路径
	 * @return
	 */
	public Map<String, String> downImage(int num, String info_id, ObjBean imgBean) {

		String old_img = imgBean.getStr5();// 原始标签
		Map<String, String> map = null;
		try {
			String path = imgBean.getStr1();// 图片路径
			String new_url = "";
			// 通过 ImageTag 过滤器获取的图片路径 已经过处理 所以此次可以略过
			// //网站是否正确
			if (!ValidatorUtil.isUrl( path )) {
				path = getAbsoluteURL( remote_url, imgBean.getStr1() );
			}
			// 网站是否正确
			if (ValidatorUtil.isUrl( path )) {
				map = new HashMap<String, String>();
				// 图片原始地址在数据库中已存在
				BaseFileBean filebean = new BaseFileBean();
				filebean.setRemote_url( path );// 图片原始地址
				filebean = baseFileManager.findBaseFileBeanByRemoteUrl( filebean );
				if (filebean != null) {
					BaseFileBean bean = new BaseFileBean();
					// bean.setId(IdUtils.createUUID(32));//ID
					bean.setVersions( "0" );// versions
					bean.setSite_id( site_id );// 站点id
					bean.setInfo_id( info_id );// 信息id
					// //没有名称
					if (ValidatorUtil.isEmpty( imgBean.getStr2() )) {
						bean.setName( "附件(" + (num + 1) + ")" );// 附件名称
					} else {
						bean.setName( imgBean.getStr2() );// 附件名称
					}
					bean.setSort_num( (num + 1) + "" );// sort_num
					bean.setFile_size( filebean.getFile_size() );// 附件大小
					bean.setNote( filebean.getNote() );// 附件描述
					bean.setType( 1 + "" );// 附件类型
					bean.setRemote_url( filebean.getRemote_url() );// 信息来源url
					// bean.setLocal_url2(null);//本地存储路径2(美化)
					bean.setLocal_url( filebean.getLocal_url() );// 本地存储路径
					// bean.setDate_created();//数据输入日期
					bean.setCreate_id( "系统线程" );// 建立者id
					bean.setCreate_ip( "0.0.0.0" );// 建立者ip
					// bean.setLast_updated();//资料更新日期
					bean.setUpdate_id( "系统线程" );// 修改者id
					bean.setUpdate_ip( "0.0.0.0" );// 修改者ip

					baseFileManager.saveBaseFile( bean );
					// 图片已保存 读取数据库中的本地路径
					map.put( old_img, StrUtil.replaceAll( old_img, imgBean.getStr3(), filebean.getLocal_url() ) );
				} else {
					// 图片未保存 下载保存
					URL url = new URL( path );
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod( "GET" );
					conn.setConnectTimeout( 5000 );
					if (conn.getResponseCode() == 200) {
						InputStream inputStream = conn.getInputStream();

						// 图片保存目录
						String dirName = ResourcesUtil.getData( "UPLOAD_IMG_ROOT" );
						// 文件大小
						Long fileSize = conn.getContentLengthLong();
						// 相对路径(文件夹)
						String path_temp = dirName + "/" + DateUtil.getDateStr2( new Date() );
						// //文件夹 根目录+相对路径
						String savePath = ResourcesUtil.getData( "UPLOAD_ROOT_FOLDER" ) + "/" + path_temp;
						// 文件名
						String fileName = WebUtil.getTime( "yyyyMMddHHmmss" ) + WebUtil.getRandomString( 5 ) + "." + WebUtil.getFileExt( path );
						// 创建文件夹
						File saveDirFile = new File( savePath );
						if (!saveDirFile.exists()) {
							saveDirFile.mkdirs();
						}
						// 检查目录写权限
						if (!saveDirFile.canWrite()) {
							log.error( "目录没有写权限，写入文件失败" );
							throw new Exception();
						}
						// 本地路径
						new_url = "/upload/" + path_temp + "/n4/" + fileName;
						// 路径处理
						new_url = new_url.replaceAll( "//", "/" );

						// 文件保存目录路径
						String savePathN0 = savePath + "/n0/";
						String savePathN1 = savePath + "/n1/";
						String savePathN2 = savePath + "/n2/";
						String savePathN3 = savePath + "/n3/";
						String savePathN4 = savePath + "/n4/";
						// 创建文件夹
						File saveDirFileN0 = new File( savePathN0 );
						if (!saveDirFileN0.exists()) {
							saveDirFileN0.mkdirs();
						}
						File saveDirFileN1 = new File( savePathN1 );
						if (!saveDirFileN1.exists()) {
							saveDirFileN1.mkdirs();
						}
						File saveDirFileN2 = new File( savePathN2 );
						if (!saveDirFileN2.exists()) {
							saveDirFileN2.mkdirs();
						}
						File saveDirFileN3 = new File( savePathN3 );
						if (!saveDirFileN3.exists()) {
							saveDirFileN3.mkdirs();
						}
						File saveDirFileN4 = new File( savePathN4 );
						if (!saveDirFileN4.exists()) {
							saveDirFileN4.mkdirs();
						}
						// -----------------1--------------------
						// BufferedImage bi = null;
						// //读取图片流
						// bi = javax.imageio.ImageIO.read(inputStream);
						// //过滤图片
						// if(bi.getWidth()<100&&bi.getHeight()<100){
						// System.out.println("图片太小不需要");
						// }
						File file = new File( saveDirFileN4, fileName );
						FileOutputStream outStream = new FileOutputStream( file );
						int len = -1;
						byte[] b = new byte[1024];
						while ((len = inputStream.read( b )) != -1) {
							outStream.write( b, 0, len );
						}
						outStream.flush();
						outStream.close();
						inputStream.close();
						// ------------------------存储图片---n0,n1,n2,n3--begin-----------------------------------------------
						if ("1".equals( ResourcesUtil.getData( "ROBOTS_IMG_FLAG" ) )) {
							ImageUtil.resizeNx( savePathN4, savePathN0, fileName, fileName, IMAGE_N0_WIDTH, IMAGE_N0_HEIGHT, true );
							ImageUtil.resizeNx( savePathN4, savePathN1, fileName, fileName, IMAGE_N1_WIDTH, IMAGE_N1_HEIGHT, false );
							ImageUtil.resizeNx( savePathN4, savePathN2, fileName, fileName, IMAGE_N2_WIDTH, IMAGE_N2_HEIGHT, false );
							ImageUtil.resizeNx( savePathN4, savePathN3, fileName, fileName, IMAGE_N3_WIDTH, IMAGE_N3_HEIGHT, false );
						}
						// ------------------------存储图片---n0,n1,n2,n3--end-----------------------------------------------------
						// ------------------2--保存附件------------------------------------------
						/*
						 * 判断文件是否保存成功 文件存在
						 */
						if (new File( saveDirFileN4, fileName ).exists()) {
							BaseFileBean bean = new BaseFileBean();
							// bean.setId(IdUtils.createUUID(32));//ID
							// bean.setVersions("");//versions
							bean.setSite_id( site_id );// 站点id
							bean.setInfo_id( info_id );// 信息id
							// //没有名称
							if (ValidatorUtil.isEmpty( imgBean.getStr2() )) {
								bean.setName( "附件(" + (num + 1) + ")" );// 附件名称
							} else {
								bean.setName( imgBean.getStr2() );// 附件名称
							}
							bean.setSort_num( (num + 1) + "" );// sort_num
							bean.setFile_size( fileSize + "" );// 附件大小
							// bean.setNote();//附件描述
							bean.setType( 1 + "" );// 附件类型
							bean.setRemote_url( path );// 信息来源url
							// bean.setLocal_url2(null);//本地存储路径2(美化)
							bean.setLocal_url( new_url );// 本地存储路径
							// bean.setDate_created();//数据输入日期
							bean.setCreate_id( "系统线程" );// 建立者id
							bean.setCreate_ip( "0.0.0.0" );// 建立者ip
							// bean.setLast_updated();//资料更新日期
							bean.setUpdate_id( "系统线程" );// 修改者id
							bean.setUpdate_ip( "0.0.0.0" );// 修改者ip

							baseFileManager.saveBaseFile( bean );
							// 远程地址:本地地址
							map.put( old_img, StrUtil.replaceAll( old_img, imgBean.getStr3(), new_url ) );
						}
					}
				}
			}
		} catch (Exception e) {
			map = null;
			log.debug( "图片下载保存失败!" + imgBean.getStr1() );
		}
		return map;
	}

	// /**
	// * 得到网页中所有图片的地址
	// *
	// * @param url
	// * 网页地址
	// * @param dataSrc
	// * 图片真实地址属性
	// * @return
	// */
	// public List<ObjBean> getImgStr2(String url, String dataSrc) {
	// List<ObjBean> list = null;
	// try {
	// Parser parser = new Parser( url );
	// list = new ArrayList<ObjBean>();
	// ObjBean objbean = null;
	// NodeFilter nf = new NodeClassFilter( ImageTag.class );
	// NodeList nodeList = (NodeList) parser.parse( nf );
	// for (int i = 0; i < nodeList.size(); i++) {
	// try {
	// ImageTag xx = (ImageTag) nodeList.elementAt( i );
	// objbean = new ObjBean();
	// objbean.setStr1( xx.getImageURL() );// 图片地址
	// objbean.setStr2( xx.getAttribute( "title" ) );// 图片文字
	// if (ValidatorUtil.isEmpty( objbean.getStr2() )) {
	// objbean.setStr2( xx.getAttribute( "alt" ) );// 图片文字
	// }
	// objbean.setStr3( xx.getAttribute( "src" ) );
	// //
	// if (ValidatorUtil.isEmpty( dataSrc )) {
	// dataSrc = "data-src";
	// }
	// // 涂牛这种坑爹的网站
	// if (xx.getAttribute( dataSrc ) != null) {
	// objbean.setStr1( xx.getAttribute( dataSrc ) );
	// }
	// // 完整img标签
	// objbean.setStr5( xx.toHtml() );
	// // add
	// list.add( objbean );
	// } catch (Exception e) {
	// }
	// }
	// } catch (Exception e) {
	// }
	// return list;
	// }
	//
	/**
	 * 得到html信息中图片的地址
	 * 
	 * @param htmlStr
	 *            html信息
	 * @param dataSrc
	 *            图片真实地址属性
	 * @return
	 */
	public List<ObjBean> getImgStr1(String htmlStr, String dataSrc) {
		if (ValidatorUtil.isEmpty( htmlStr )) {
			return null;
		}
		List<ObjBean> list = null;
		try {
			Document doc = Jsoup.parse( htmlStr );
			list = new ArrayList<ObjBean>();
			ObjBean objbean = null;
			Elements els = doc.getElementsByTag( "img" );
			for (int i = 0; i < els.size(); i++) {
				try {
					Element el = els.get( i );
					objbean = new ObjBean();
					objbean.setStr1( el.attr( "src" ) );// 图片地址
					objbean.setStr2( el.attr( "title" ) );// 图片文字
					if (ValidatorUtil.isEmpty( objbean.getStr2() )) {
						objbean.setStr2( el.attr( "alt" ) );// 图片文字
					}
					objbean.setStr3( el.attr( "src" ) );
					//
					if (ValidatorUtil.isEmpty( dataSrc )) {
						dataSrc = "data-src";
					}
					// 涂牛这种坑爹的网站
					if (el.attr( dataSrc ) != null) {
						objbean.setStr1( el.attr( dataSrc ) );
					}
					// 完整img标签
					objbean.setStr5( el.outerHtml() );
					// add
					list.add( objbean );
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		return list;
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

	public static void main(String[] args) throws Exception {
		// String str="http://113.0.190.18:9090/upload/image/product/35032b6d857041dfa34e71f3c25c3903/n3/20121109150143eacsh.jpg";
		// //实例化url
		// URL url = new URL(str);
		// System.out.println(str.replaceAll(url.getPath(),""));
		// BufferedImage bi = null;
		// try {
		// //读取图片
		// bi = javax.imageio.ImageIO.read(url);
		// System.out.println(bi.getWidth());
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
		// String path="124385056_11n.jpg";
		// String remote_url="http://news.xinhuanet.com/2013-02/25/c_124385056.htm";
		// path=remote_url.replaceAll(remote_url.substring(remote_url.lastIndexOf("/")+1,remote_url.length()),"")+path;
		// System.out.println(path);

		// System.out.println(ValidatorUtil.isUrl("http://news.xinhuanet.com/xxxxxxxxx.xxx"));
		// List<ObjBean> img_urls=getImgStr1("<div><img src='/x.jpg' /></div>");
		// if(img_urls!=null){
		// for(ObjBean o:img_urls){
		// System.out.println(o.getStr1());
		// }
		// }
		// String url1="http://rich8w.iteye.com/blog/553713/";
		// String url2="123.jpg";
		// System.out.println(getAbsoluteURL(url1,url2));
	}

	/**
	 * 站点id取得
	 * 
	 * @return 站点id
	 */
	public String getSite_id() {
		return site_id;
	}

	/**
	 * 站点id设定
	 * 
	 * @param site_id
	 *            站点id
	 */
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	/**
	 * 信息id取得
	 * 
	 * @return 信息id
	 */
	public String getInfo_id() {
		return info_id;
	}

	/**
	 * 信息id设定
	 * 
	 * @param info_id
	 *            信息id
	 */
	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	/**
	 * 信息远程地址取得
	 * 
	 * @return 信息远程地址
	 */
	public String getRemote_url() {
		return remote_url;
	}

	/**
	 * 信息远程地址设定
	 * 
	 * @param remote_url
	 *            信息远程地址
	 */
	public void setRemote_url(String remote_url) {
		this.remote_url = remote_url;
	}

	/**
	 * 采集结果 业务处理取得
	 * 
	 * @return 采集结果 业务处理
	 */
	public IBaseInfoManager getBaseInfoManager() {
		return baseInfoManager;
	}

	/**
	 * 采集结果 业务处理设定
	 * 
	 * @param baseInfoManager
	 *            采集结果 业务处理
	 */
	public void setBaseInfoManager(IBaseInfoManager baseInfoManager) {
		this.baseInfoManager = baseInfoManager;
	}

	/**
	 * 采集附件 业务处理取得
	 * 
	 * @return 采集附件 业务处理
	 */
	public IBaseFileManager getBaseFileManager() {
		return baseFileManager;
	}

	/**
	 * 采集附件 业务处理设定
	 * 
	 * @param baseFileManager
	 *            采集附件 业务处理
	 */
	public void setBaseFileManager(IBaseFileManager baseFileManager) {
		this.baseFileManager = baseFileManager;
	}

	/**
	 * 采集结果集取得
	 * 
	 * @return 采集结果集
	 */
	public BaseInfoBean getInfoBean() {
		return infoBean;
	}

	/**
	 * 采集结果集设定
	 * 
	 * @param infoBean
	 *            采集结果集
	 */
	public void setInfoBean(BaseInfoBean infoBean) {
		this.infoBean = infoBean;
	}
}