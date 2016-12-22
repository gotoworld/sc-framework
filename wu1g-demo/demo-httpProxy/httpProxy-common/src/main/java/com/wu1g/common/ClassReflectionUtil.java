/*
 *  反射实体类赋值.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016-4-7  wxg      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.common;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * 反射实体类赋值
 * </p>
 */
public class ClassReflectionUtil {
	private static final transient Logger		log	= LogManager.getLogger( ClassReflectionUtil.class );
	/**
	 * 反射实体类赋值
	 * 
	 * @param fromClass
	 *            用于赋值的实体类
	 * @param toClass
	 *            需要待赋值的实体类
	 */
	public static void reflectionAttr(Object fromClass, Object toClass) throws Exception{
		reflectionAttr( fromClass, toClass,null);
	}
	/**
	 * 反射实体类赋值
	 * 
	 * @param fromClass
	 *            用于赋值的实体类
	 * @param toClass
	 *            需要待赋值的实体类
	 * @param notCPFieldArray 
	 * 			  不需要复制的属性集合
	 */
	public static void reflectionAttr(Object fromClass, Object toClass,String[] notCPFieldArray) throws Exception {
		Class<?> fromClazz = Class.forName( fromClass.getClass().getName() );
		Class<?> toClazz = Class.forName( toClass.getClass().getName() );

		// 获取两个实体类的所有属性
		Field[] fromFields = fromClazz.getDeclaredFields();
		Field[] toFields = toClazz.getDeclaredFields();
		ClassReflectionUtil cr = new ClassReflectionUtil();

		// 遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则赋值
		for (Field fromField : fromFields) {
			if(notCPFieldArray!=null){
				for(int i=0;i<notCPFieldArray.length;i++){
					 if (fromField.getName().equals(notCPFieldArray[i])){
						 continue;
					 }
				}
			}
			Object value = cr.invokeGetMethod( fromClass, fromField.getName(), null);
			for (Field toField : toFields) {
				if (fromField.getName().equals( toField.getName() )) {
					Object[] obj = new Object[1];
					obj[ 0 ] = value;
					cr.invokeSetMethod( toClass, toField.getName(), obj);
				}
			}
		}
	}

	/**
	 * 
	 * 执行某个Field的getField方法
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            类的属性名称
	 * @param args
	 *            参数，默认为null
	 * @return
	 */
	private Object invokeGetMethod(Object clazz, String fieldName, Object[] args) {
		String methodName = fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );
		Method method = null;
		if(methodName.equals("SerialVersionUID")){
			return "";
		}
		
		try {
			method = Class.forName( clazz.getClass().getName() ).getDeclaredMethod( "get" + methodName );

			return method.invoke( clazz );
		} catch (Exception e) {
			log.error( "执行某个Field的getField方法  异常!",e );
			return "";
		}
	}

	/**
	 * 
	 * 执行某个Field的setField方法
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            类的属性名称
	 * @param args
	 *            参数，默认为null
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Object invokeSetMethod(Object clazz, String fieldName, Object[] args) {
		String methodName = fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );
		Method method = null;
		if(methodName.equals("SerialVersionUID")){
			return "";
		}
		
		try {
			Class[] parameterTypes = new Class[1];
			Class<?> c = Class.forName( clazz.getClass().getName() );
			Field field = c.getDeclaredField( fieldName );
			parameterTypes[ 0 ] = field.getType();
			method = c.getDeclaredMethod( "set" + methodName, parameterTypes );
			// 得到字段类型
			String parameterType = c.getSimpleName();
			if (parameterType.equals("String")) {
				method.invoke(clazz, args);
			} else{
				if(args[0]!=null && !"".equals(args[0])){
					String value = args[0].toString();
					if (parameterType.equals("Character")) {
						method.invoke(clazz,value.charAt(0));
					} else if (parameterType.equals("Boolean")) {
						method.invoke(clazz,value.equals("true") || value.equals("1") ? true : false);
					} else if (parameterType.equals("Short")) {
						method.invoke(clazz, Short.parseShort(value));
					} else if (parameterType.equals("Integer")) {
						method.invoke(clazz, Integer.parseInt(value));
					} else if (parameterType.equals("Float")) {
						method.invoke(clazz, Float.parseFloat(value));
					} else if (parameterType.equals("Long")) {
						method.invoke(clazz, Long.parseLong(value));
					} else if (parameterType.equals("Double")) {
						method.invoke(clazz, Double.parseDouble(value));
					} else if(parameterType.equals("BigDecimal")){
						method.invoke(clazz, new BigDecimal(value));
					} else{
						method.invoke(clazz, value);
					}
				}
			}
			return clazz;
		} catch (Exception e) {
			log.error( "执行某个Field的setField方法  异常!",e );
			return "";
		}
	}
}
