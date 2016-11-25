/*
 * 分页显示标签类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2013.11.24  WuXiaogang        程序,发布
 * 2.00     2013.06.09  WuXiaogang        程序,更新  解决同一页面多个分页问题
 * 3.00     2016.03.27  WuXiaogang        程序,更新  自定义分页pageInfo换为com.github.pagehelper.PageInfo
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2016 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.web.tag;

import com.github.pagehelper.PageInfo;
import com.wu1g.framework.util.CommonConstant;
import com.wu1g.framework.util.ValidatorUtil;
import org.beetl.core.GeneralVarTagBinding;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * <p>分页显示标签</p>
 */
public class PageExtTag extends GeneralVarTagBinding  {
    private static final long serialVersionUID = 7313107737668941157L;

    /** 函数名 */
    private String func;
    /** 参数数组 */
    private String params;


    /**
     * 输出分页显示模型。
     * @return 跳转类型
     */
    @Override
	public void render() {
    	if(args!=null && args.length>=2){
    		Map parMap=(Map) args[1];
    		func=""+parMap.get( "func" );
    		params=""+parMap.get( "params" );
    	}
    	
    	String HTML_3DOT_STRING = "<a class=\"dot\">...</a>";
   	 
        HttpServletRequest request = (HttpServletRequest)ctx.getGlobal("request");
        
	     PageInfo<?> pageInfo = (PageInfo<?>)request.getAttribute(CommonConstant.PAGEROW_OBJECT_KEY);
	     /** 当前页码 */
	     int currentPageNo = pageInfo.getPageNum();
	     /** 总页数 */
	     int pageCount = pageInfo.getPages();
	     /** 总纪录数 */
	     long recordCount = pageInfo.getTotal();
//	     /** 每页显示的纪录数 */
//	     int pageRowCount = pageInfo.getPageSize();
	     /** 是否存在后一条记录 */
	     boolean hasNext = (pageInfo.getPageNum()>=pageInfo.getPages())?false:true;
	     /** 是否存在前一条记录 */
	     boolean hasPrevious = pageInfo.getPageNum()<=1?false:true;
	     /** 画面显示的页码数量 */
	     int pageNoCount = CommonConstant.PAGEROW_CURR_NENT_COUNT;	 
//	     /** 当前偏移量 */
//	     int currOffset = pageInfo.getStartRow();
	
	
	     // CSS代码
	     StringBuffer cssStr = new StringBuffer();
	     cssStr.append("<style type=\"text/css\">");
	     cssStr.append(".b_page{ width:738px; height:30px; display:block; clear:both;}");
	     cssStr.append(".b_page { width:auto; height:30px; line-height:30px; color:#5B5B5B; margin:7px 0 0 20px; float:right;}");
	     cssStr.append(".b_page a { width:auto; height:25px; line-height:22px; border:1px solid #cdcdcb; float:left; margin-right:4px; padding:0 8px; }");
	     cssStr.append(".b_page a.dot{ border:none;padding:0 1px}.b_page a.dot:hover{border:none; cursor:text; background:none;color:#5B5B5B;}");
	     cssStr.append(".b_page a:hover { background:#739a2c; border:1px solid #4b6f0a; cursor:pointer; color:#FFF; }");
	     cssStr.append(".b_page a.on { background:#739a2c; border:1px solid #4b6f0a; cursor:pointer; color:#FFF; }");
	     cssStr.append(".b_page a, .b_page a:visited { color:#5B5B5B; font-size:12px; display:block;}");
	     cssStr.append(".b_page a:hover,.b_page li:hover a{ color:#fff; display:block;}");
	     cssStr.append(".b_page a.disabled { background:#FFF;  border:1px solid #cdcdcb; color:#5B5B5B; cursor:text; }");
	     cssStr.append(".clear{clear:both;display:block;overflow:hidden;visibility:hidden;width:0px;height:0px;}");
	     cssStr.append("</style>");
	     // 上一页
	     StringBuffer prePage = new StringBuffer();
	     if (hasPrevious) {
	         prePage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(pageInfo.getPrePage()) +"\">");
	         prePage.append("上一页");
	         prePage.append("</a>");
	     } else {
	         prePage.append("<a class=\"disabled\">上一页</a>");
	     }
	
	
	     // 下一页
	     StringBuffer nextPage = new StringBuffer();
	     if (hasNext) {
	         nextPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(pageInfo.getNextPage()) +"\">");
	         nextPage.append("下一页");
	         nextPage.append("</a>");
	     } else {
	         nextPage.append("<a class=\"disabled\">下一页</a>");
	     }
	
	
	     // 中间页
	     StringBuffer midPage = new StringBuffer();
	
	     // 只有一页数据
	     if (pageCount == 1) {
	     	midPage.append("<a class=\"on\">1</a>");
	
	     // 总页数<=显示页数
	     } else if (pageCount > 1 && pageCount <= pageNoCount) {
	         for (int i = 1; i <= pageCount; i++) {
	         	if (currentPageNo == i) {
	         		midPage.append("<a class=\"on\">"+ i +"</a>");
	         	} else {
	         		midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript( (i)) +"\">");
	         		midPage.append(i);
	         		midPage.append("</a>");
	         	}
	         }
	         
	     // 总页数>显示页数
	     } else if (pageCount > pageNoCount) {
	
	     	// 当前页数在起始显示页数内
	     	if (currentPageNo <= pageNoCount) {
	         	for (int i = 1; i <= pageCount; i++) {
	         		if (currentPageNo == i) {
	         			midPage.append("<a class=\"on\">"+ i +"</a>");
	         		} else {
	         			midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(+i ) +"\">");
	         			midPage.append(i);
	         			midPage.append("</a>");
	         		}
	         		
	         		if (i == pageNoCount) {
	         			// 加上...
	         			midPage.append(HTML_3DOT_STRING);
	         			// 加上末页数字
	         			midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript( pageInfo.getPages()) +"\">");
	         			midPage.append(pageCount);
	         			midPage.append("</a>");
	         			break;
	         		}
	         	}
	         
	         // 当前页数在末页显示页数内
	     	} else if (currentPageNo > (pageCount - pageNoCount)) {
	     	
	         	// 加上首页数字
	         	midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(1)+"\">");
	         	midPage.append(1);
	         	midPage.append("</a>");
	         	// 加上...
	         	midPage.append(HTML_3DOT_STRING);
	
	         	for (int i = (pageCount - pageNoCount + 1); i <= pageCount; i++) {
	         		if (currentPageNo == i) {
	         			midPage.append("<a class=\"on\">"+ i +"</a>");
	         		} else {
	         			midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(i) +"\">");
	         			midPage.append(i);
	         			midPage.append("</a>");
	         		}
	         	}
	     	} else {
	     	    // 加上首页数字
	         	midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(1)+"\">");
	         	midPage.append(1);
	         	midPage.append("</a>");
	         	// 加上...
	         	midPage.append(HTML_3DOT_STRING);
	         	for (int i = currentPageNo; i < (currentPageNo + pageNoCount); i++) {
	         		if (currentPageNo == i) {
	         			midPage.append("<a class=\"on\">"+ i +"</a>");
	         		} else {
	         			midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(i) +"\">");
	         			midPage.append(i);
	         			midPage.append("</a>");
	         		}
	         	}
	         	// 加上...
	         	midPage.append(HTML_3DOT_STRING);
	         	// 加上末页数字
	         	midPage.append("<a href=\"javascript:;\" onclick=\""+ getPageScript(pageInfo.getPages())+"\">");
	         	midPage.append(pageCount);
	         	midPage.append("</a>");
	     	}
	     // 没有数据
	     } else {
	     	midPage.append("");
	     }
	     // 汇总输出
	     StringBuffer pageCom = new StringBuffer();
	     pageCom.append(cssStr);
	     pageCom.append("<div class=\"b_page\">");
	     pageCom.append(prePage);
	     pageCom.append(midPage);
	     pageCom.append(nextPage);
	     pageCom.append("<a class=\"disabled\">共"+ recordCount +"条</a>");
	     pageCom.append("<div class=\"clear\"></div>");
	     pageCom.append("</div>");
	     
        try {
			if (recordCount == 0) {
				ctx.byteWriter.writeString("");
			} else {
				ctx.byteWriter.writeString(pageCom.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private String  getPageScript(Integer offset){
	     //TODO 翻页函数
	     StringBuffer javascriptStr = new StringBuffer();
	     if (ValidatorUtil.isNullEmpty(params)) {
		     javascriptStr.append(""+ func +"("+offset+");");
	     } else {
		     javascriptStr.append(""+ func +"("+offset+","+ params +");");
	     }
	     return javascriptStr.toString();
    }

	/**
	 * 函数名取得
	 * @return 函数名
	 */
	public String getFunc() {
	    return func;
	}


	/**
	 * 函数名设定
	 * @param func 函数名
	 */
	public void setFunc(String func) {
	    this.func = func;
	}


	/**
	 * 参数数组取得
	 * @return 参数数组
	 */
	public String getParams() {
	    return params;
	}


	/**
	 * 参数数组设定
	 * @param params 参数数组
	 */
	public void setParams(String params) {
	    this.params = params;
	}
}
