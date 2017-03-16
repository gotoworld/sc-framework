package com.wu1g.framework.util;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.*;

import java.io.*;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

public class BeetlUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	/**
	 * 根据模板生产文件
	 * @param context
	 * @param saveFileName
	 */
	public static void renderToFile(String readTlFileName,Map context,String saveFileName){
//		StringTemplateResourceLoader resourceLoader=new StringTemplateResourceLoader();
		try {
			ClasspathResourceLoader resourceLoader=new ClasspathResourceLoader();
			Configuration cfg=Configuration.defaultConfiguration();
			cfg.addPkg( "com.wu1g" );
			GroupTemplate gt=new GroupTemplate(resourceLoader,cfg);
			gt.registerFunctionPackage("krpano", new KrSceneImageUtil());
			gt.registerFunctionPackage("validator", new ValidatorUtil());
			gt.registerFunctionPackage("strUtil", new StrUtil());
			Template template=gt.getTemplate( readTlFileName );
//			String output=template.render();
			template.binding( context );
			 // 输出流  
			StringWriter writer = new StringWriter();
			template.renderTo( writer );

			File dir=new File(saveFileName.substring(0,saveFileName.lastIndexOf("/")));
			if(!dir.isDirectory()){
				dir.mkdirs();
			}
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
	 */
	public static String renderToString(String readTlFileName,Map context){
		try {
			ClasspathResourceLoader resourceLoader=new ClasspathResourceLoader();
			Configuration cfg=Configuration.defaultConfiguration();
			cfg.addPkg( "com.wu1g" );
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
