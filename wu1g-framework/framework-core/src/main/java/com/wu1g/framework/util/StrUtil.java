package com.wu1g.framework.util;

import com.wu1g.framework.config.AppConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtil {

    private static final String rootFolderUpload = AppConfig.getProperty("common.fileServer.rootFolder.upload");
    private static final String rootFolderDownload = AppConfig.getProperty("common.fileServer.rootFolder.download");

    /**
     * @param str        原始字符串
     * @param len        截取长度
     * @param append_str 补足字符串  如...
     * @return
     */
    public static String subLeft(String str, int len, String append_str) {
        if (str == null) {
            return "";
        }
//		int len1 = len;
        String result1 = "";
        str = str.replaceAll("　", "");
        str = str.trim();

        if (str.length() > len) {
            result1 = str.substring(0, len);
        } else {
            result1 = str;
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
     * @param str   原始字符串
     * @param s_num 开始
     * @param e_num 结束
     * @return
     */
    public static String subStr(String str, int s_num, int e_num) {
        if (str == null) {
            return "";
        }
        if (str.length() > s_num && str.length() > e_num && e_num > s_num) {
            return str.substring(s_num, e_num);
        } else {
            return str;
        }
    }

    /**
     * 清除html 获取纯文本
     *
     * @param strHtml HTML文本代码
     * @return 处理后的纯文本
     */
    public static String clearHtml(String strHtml) {
        if (strHtml == null) {
            return "";
        }
        String htmlStr = strHtml;
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); //过滤script标签

        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); //过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); //过滤html标签
        htmlStr = htmlStr.replaceAll("&\\w*?;", "");//
        htmlStr = htmlStr.replaceAll("\\s*", "");
        // 各种样式
        return htmlStr;
    }

    /**
     * 字符串替换
     *
     * @param str
     * @return
     */
    public static String replaceAll(String str, String regex, String replacement) {
        if (str != null) {
            return str.replaceAll(regex, replacement);
        }
        return "";
    }

    /**
     * 字符串转换为大写
     *
     * @param str
     * @return
     */
    public static String toUpperCase(String str) {
        if (str != null) {
            return str.toUpperCase();
        }
        return "";
    }

    /**
     * 字符串转换为小写
     *
     * @param str
     * @return
     */
    public static String toLowerCase(String str) {
        if (str != null) {
            return str.toLowerCase();
        }
        return "";
    }

    /**
     * InputStream 转为 String
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            //  e.printStackTrace();   
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //-------------------------------------------------------------------------
    private static final char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'};

    private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }

    /**
     * 将字符串编码成 Unicode 形式的字符串. 如 "黄" to "\u9EC4"
     * Converts unicodes to encoded \\uxxxx and escapes
     * special characters with a preceding slash
     *
     * @param theString   待转换成Unicode编码的字符串。
     * @param escapeSpace 是否忽略空格，为true时在空格后面是否加个反斜杠。
     * @return 返回转换后Unicode编码的字符串。
     */
    public static String toUnicode(String theString, boolean escapeSpace) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);

        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            // Handle common case first, selecting largest block that
            // avoids the specials below
            if ((aChar > 61) && (aChar < 127)) {
                if (aChar == '\\') {
                    outBuffer.append('\\');
                    outBuffer.append('\\');
                    continue;
                }
                outBuffer.append(aChar);
                continue;
            }

            switch (aChar) {
                case ' ':
                    if (x == 0 || escapeSpace) outBuffer.append('\\');
                    outBuffer.append(' ');
                    break;
                case '\t':
                    outBuffer.append('\\');
                    outBuffer.append('t');
                    break;
                case '\n':
                    outBuffer.append('\\');
                    outBuffer.append('n');
                    break;
                case '\r':
                    outBuffer.append('\\');
                    outBuffer.append('r');
                    break;
                case '\f':
                    outBuffer.append('\\');
                    outBuffer.append('f');
                    break;
                case '=': // Fall through
                case ':': // Fall through
                case '#': // Fall through
                case '!':
                    outBuffer.append('\\');
                    outBuffer.append(aChar);
                    break;
                default:
                    if ((aChar < 0x0020) || (aChar > 0x007e)) {
                        // 每个unicode有16位，每四位对应的16进制从高位保存到低位
                        outBuffer.append('\\');
                        outBuffer.append('u');
                        outBuffer.append(toHex((aChar >> 12) & 0xF));
                        outBuffer.append(toHex((aChar >> 8) & 0xF));
                        outBuffer.append(toHex((aChar >> 4) & 0xF));
                        outBuffer.append(toHex(aChar & 0xF));
                    } else {
                        outBuffer.append(aChar);
                    }
            }
        }
        return outBuffer.toString();
    }

    /**
     * 从 Unicode 形式的字符串转换成对应的编码的特殊字符串。 如 "\u9EC4" to "黄".
     * Converts encoded \\uxxxx to unicode chars
     * and changes special saved chars to their original forms
     *
     * @param in  Unicode编码的字符数组。
     * @param off 转换的起始偏移量。
     * @param len 转换的字符长度。
     * @return 完成转换，返回编码前的特殊字符串。
     */
    public static String unicodeToStr(char[] in, int off, int len) {
        char aChar;
        char[] out = new char[len]; // 只短不长
        int outLen = 0;
        int end = off + len;

        while (off < end) {
            aChar = in[off++];
            if (aChar == '\\') {
                aChar = in[off++];
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = in[off++];
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                        }
                    }
                    out[outLen++] = (char) value;
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    out[outLen++] = aChar;
                }
            } else {
                out[outLen++] = (char) aChar;
            }
        }
        return new String(out, 0, outLen);
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append("＞");// 转义大于号
                    break;
                case '<':
                    sb.append("＜");// 转义小于号
                    break;
                case '\'':
                    sb.append("＇");// 转义单引号
                    break;
                case '\"':
                    sb.append("＂");// 转义双引号
                    break;
                case '&':
                    sb.append("＆");// 转义&
                    break;
                case '\\':
                    sb.append("＼ ");// 转义斜杠＼
                    break;
                case '#':
                    sb.append("＃");// 转义＃
                    break;
//			case '(':
//				sb.append("（");// 转义小括号（
//				break;
//			case ')':
//				sb.append("）");// 转义小括号）
//				break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (s == null) {
            return "";
        }
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 以某字符分割的所有单词 首字母大写
     */
    public static String toUpperCase(String str, String regex) {
        StringBuffer sb = new StringBuffer("");

        if (str == null) {
            return "";
        }
        String s[] = str.split(regex);

        if (s != null) {
            for (String temp : s) {
                sb.append(toUpperCaseFirstOne(temp));
            }
        }
        return sb.toString();
    }

    /**
     * 将服务器相对地址转为物理地址
     *
     * @param url
     * @return
     */
    public static String escapeRemoteToLocal(String url) {
        String local_url = null;
        if (url != null) {
            if (!ValidatorUtil.isUrl(url)) {
                local_url = url.replaceAll(
                        rootFolderDownload,
                        rootFolderUpload).replaceAll(
                        "//", "/");
            }
        }
        return local_url;
    }

    /**
     * 将物理地址转为服务器相对地址
     *
     * @param url
     * @return
     */
    public static String escapeLocalToRemote(String url) {
        String remote_url = null;
        if (url != null) {
            if (!ValidatorUtil.isUrl(url)) {
                remote_url = url.replaceAll(
                        rootFolderUpload,
                        rootFolderDownload)
                        .replaceAll("//", "/");
            }
        }
        return remote_url;
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
//		String s = "黄 \t彪\u5F6A";
//        System.out.println("Original:\t\t" + s);
//
//        s = toUnicode(s, true);
//        System.out.println("to unicode:\t\t" + s);
//
//        s = unicodeToStr("s的".toCharArray(), 0, s.length());
//        System.out.println("from unicode:\t" + s);

        for (int i = 0; i < 4; i++) {
            String s = "\" << onclick=\"alert(1);\"";
            s = xssEncode(s);
            System.out.println(s);
        }
    }
}
