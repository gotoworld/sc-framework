<%--
/*
 * 通用标签
 *
 * VERSION  DATE        BY           REASON
 * -------- ----------- ------------ ------------------------------------------
 * 1.00     2015-10-21  wuxiaogang   程序・发布
 * 1.01     2015-10-29  wuxiaogang   程序・更新 加入spring标签
 * -------- ----------- ------------ ------------------------------------------
 * Copyright 2016 baseos System. - All Rights Reserved.
 *
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.com.baseos.common.CommonConstant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="customtag" uri="http://www.baseos.com.cn/custom-tags"%>
<%--  国际化.begin --%>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value='${_language==null?"zh_CN":_language}'/>
<fmt:setTimeZone value= "GMT+8" scope="request"/>

<%-- 国际化.end --%>