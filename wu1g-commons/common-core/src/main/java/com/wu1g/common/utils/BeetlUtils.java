/*
* 类功能说明待完善
* VERSION       DATE       BY              REASON
* --------  ------------  --------------  ---------------------------
* 1.00      2015-12-7    xiaogang.wu           程序.发布
* --------  ------------  --------------  ---------------------------
* Copyright 2015 ec4j System. - All Rights Reserved.
*/
package com.wu1g.common.utils;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.Map;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

/**
 *<p>类功能说明待完善</p>
 * <dl>[功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
public class BeetlUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	/**
	 * 根据模板生产文件
	 * @param tlFileName
	 * @param context
	 * @param saveFileName
	 */
	public static void renderToFile(String readTlFileName,Map context,String saveFileName){
//		StringTemplateResourceLoader resourceLoader=new StringTemplateResourceLoader();
		try {
			ClasspathResourceLoader resourceLoader=new ClasspathResourceLoader();
			Configuration cfg=Configuration.defaultConfiguration();
			cfg.addPkg( "cn.com" );
			GroupTemplate gt=new GroupTemplate(resourceLoader,cfg);
			Template template=gt.getTemplate( readTlFileName );
//			String output=template.render();
			template.binding( context );
			 // 输出流  
			StringWriter writer = new StringWriter();
			template.renderTo( writer );
			// 输出到文件  
			FileOutputStream of = new FileOutputStream(saveFileName);
			of.write(writer.toString().getBytes( "utf-8" ));
			of.flush();
			of.close();
			System.out.println("==success=="+saveFileName);
		} catch (Exception e) {
			System.err.println("==error=="+saveFileName);
			e.printStackTrace();
		}
	}
	/**
	 * 根据模板生产字符串
	 * @param tlFileName
	 * @param context
	 * @param saveFileName
	 */
	public static String renderToString(String readTlFileName,Map context){
//		StringTemplateResourceLoader resourceLoader=new StringTemplateResourceLoader();
		try {
			ClasspathResourceLoader resourceLoader=new ClasspathResourceLoader();
			Configuration cfg=Configuration.defaultConfiguration();
			cfg.addPkg( "cn.com" );
			GroupTemplate gt=new GroupTemplate(resourceLoader,cfg);
			Template template=gt.getTemplate( readTlFileName );
			template.binding( context );
			String output=template.render();
			return output;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
