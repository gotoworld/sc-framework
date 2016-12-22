package com.wu1g.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

	/**
	 * 
	 * @param str  原始字符串
	 * @param len  截取长度
	 * @param append_str   补足字符串  如...
	 * @return
	 */
	public static  String  subLeft(String str ,int len ,String append_str){
		 if (str == null) 
		 {
			return "";
		 }
//		int len1 = len;
		String result1 = "";
		str = str.replaceAll("　", "");
		str = str.trim();
		
		if(str.length()>len){
			result1=str.substring(0,len);
		}else{
			result1=str;
		}
		
//		int len2 = 0;
//		for (int i = 0; i < str.length(); i++) {
//			if (str.codePointAt(i) > 255) {
//				len2 += 2;
//			} else {
//				len2 += 1;
//			}
//		}
//		if (len2 > len1) {
//			for (int i = 0; i < len1; i++) {
//				if (str.codePointAt(i) > 255) {
//					len1 -= 1;
//				}
//			}
//			result1 = str.substring(0, len1);
//			if (append_str != null) {
//				result1 = result1 + append_str;
//			}
//		} else {
//			result1 = str.trim();
//		}
		return result1;// 返回文本字符串
	}
	/**
	 * 
	 * @param str  原始字符串
	 * @param s_num  开始
	 * @param e_num  结束
	 * @return
	 */
	public static  String  subStr(String str ,int s_num,int e_num){
		 if (str == null) {
			return "";
		 }
		if(str.length()>s_num&&str.length()>e_num && e_num>s_num){
			return str.substring(s_num,e_num);
		}else{
			return str;
		}
	}
	/**
	 * 清除html 获取纯文本
	 * 
	 * @param content
	 *            HTML文本代码
	 * @return 处理后的纯文本
	 */
	public static  String clearHtml(String strHtml) {
		if (strHtml == null) {
			return "";
		}
		String htmlStr =strHtml;
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
        
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
        
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 
        htmlStr=htmlStr.replaceAll("&\\w*?;", "");//	
        htmlStr=htmlStr.replaceAll("\\s*", "");
		// 各种样式
		return htmlStr;
	}
	/**
	 * 字符串替换
	 * @param str
	 * @return
	 */
	public static String replaceAll(String str,String regex,String replacement){
		if(str!=null){
			return str.replaceAll(regex, replacement);
		}
		return "";
	}
	/**
	 * 字符串转换为大写
	 * @param str
	 * @return
	 */
	public static String toUpperCase(String str){
		if(str!=null){
			return str.toUpperCase();
		}
		return "";
	}
	/**
	 * 字符串转换为小写
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str){
		if(str!=null){
			return str.toLowerCase();
		}
		return "";
	}
	// test
	public static void main(String[] args) {

		// String str2 = "天使qqqqqqqqqqqq";
		// String result2 = "";
		// int index = 10;
		// for (int i = 0; i < str2.length(); i++) {
		// if (str2.codePointAt(i) > 255) {
		// index = index - 1;
		// }
		//
		// //System.out.println(result2);
		// }
		// result2 = str2.substring(0, index);
		// //System.out.println(result2);
		// --------------------------------------------------------------
//		 String str="<script language=\"JavaScript\">  var today = new Date(); document.write((today.getMonth() + 1) + \"月\" + today.getDate() + \"日\"); </script><div class=\"public_3_right\">"
//		 +"<p>&nxxxbsp;&这个;</p>"
//		 +"<p><span style=\"font-family: 宋体\"><font size=\"3\"><span lang=\"EN-US\"><span style=\"mso-spacerun: yes\">&nbsp;&nbsp;&nbsp; </span></span><span style=\"mso-ascii-theme-font: minor-latin; mso-fareast-theme-font: minor-fareast; mso-hansi-theme-font: minor-latin; mso-ascii-font-family: Calibri; mso-fareast-font-family: 宋体; mso-hansi-font-family: Calibri\">上海，又称「上海滩」。中国第一大城市，四大直辖市之一，中国国家中心城市，中国的经济、科技、工业、金融、贸易、会展和航运中心。</span></font></span><font size=\"3\"><span lang=\"EN-US\"><o:p></o:p></span></font></p>"
//		 +"<p><span style=\"font-family: 宋体\"><font size=\"3\"><span lang=\"EN-US\"><span style=\"mso-spacerun: yes\">&nbsp;&nbsp;&nbsp; </span></span><span style=\"mso-ascii-theme-font: minor-latin; mso-fareast-theme-font: minor-fareast; mso-hansi-theme-font: minor-latin; mso-ascii-font-family: Calibri; mso-fareast-font-family: 宋体; mso-hansi-font-family: Calibri\">上海位于中国大陆海岸线中部的长江口，拥有中国最大的外贸港口和最大的工业基地。隔海与日本九州岛相望，南濒杭州湾，西部与江苏、浙江两省相接。上海港的货物吞吐量和集装箱吞吐量均位居世界第一。</span></font></span><font size=\"3\"><span lang=\"EN-US\"><o:p></o:p></span></font></p>"
//		 +"<p class=\"MsoNormal\" style=\"margin: 0cm 0cm 0pt\"><span style=\"font-family: 宋体\"><font size=\"3\"><span lang=\"EN-US\"><span style=\"mso-spacerun: yes\">&nbsp;&nbsp;&nbsp; </span></span><span style=\"mso-ascii-theme-font: minor-latin; mso-fareast-theme-font: minor-fareast; mso-hansi-theme-font: minor-latin; mso-ascii-font-family: Calibri; mso-fareast-font-family: 宋体; mso-hansi-font-family: Calibri\">上海也是一座新兴的旅游城市，具有深厚的近代城市文化底蕴和众多的历史古迹，并成功举办</span><span lang=\"EN-US\">2010</span><span style=\"mso-ascii-theme-font: minor-latin; mso-fareast-theme-font: minor-fareast; mso-hansi-theme-font: minor-latin; mso-ascii-font-family: Calibri; mso-fareast-font-family: 宋体; mso-hansi-font-family: Calibri\">年世博会。如今上海已发展成为一个闪耀全球的国际化大都市，并致力于在</span><span lang=\"EN-US\">2020</span><span style=\"mso-ascii-theme-font: minor-latin; mso-fareast-theme-font: minor-fareast; mso-hansi-theme-font: minor-latin; mso-ascii-font-family: Calibri; mso-fareast-font-family: 宋体; mso-hansi-font-family: Calibri\">年建设成为国际金融中心和航运中心。</span></font></span></p>"
//		 +"<p class=\"MsoNormal\" style=\"text-align: center; margin: 0cm 0cm 0pt\"><img alt=\"\" style=\"width: 400px; height: 353px\" src=\"/upload/attachment/www/201206/200940019xnk.jpg\" /></p>"
//		 +"<p class=\"MsoNormal\" style=\"text-align: center; margin: 0cm 0cm 0pt\">&nbsp;<img width=\"400\" height=\"627\" alt=\"\" src=\"/upload/attachment/www/201206/2111220778v4.jpg\" /></p>"
//		 +"</div>";
//		 System.out.println(clearHtml(str));
		String s="	1 2	地方 飞	3";
		System.out.println(s.replaceAll("\\s*",""));
	}
}
