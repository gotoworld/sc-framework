/*
 * 验证图片生成工具类
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00     2015.01.14  wuxiaogang      程序.发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2015 baseos System. - All Rights Reserved.
 *
 */
package com.wu1g.framework.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * <p>验证图片生成类 </p>
 * <ol>
 * [提供机能]
 * <li>随机生成验证图片</li>
 * </ol>
 */
public class VerifyUtil {

	private static final transient Logger log = Logger.getLogger(VerifyUtil.class);

	 /**
     * <p>生成验证图片。</p>
     * <ol>[功能概要]
     * <li>随机生成验证图片。
     * </ol>
     * @return 无
     */
	public static void createVerifyCode(HttpServletResponse response, String key, HttpServletRequest req) {
	
		int width = 70, height = 25;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		g.setColor(getRandColor(160, 200));
		
		for(int i = 0; i < 155; i++) {
		    int x = random.nextInt(width);
		    int y = random.nextInt(height);
		    int xl = random.nextInt(12);
		    int yl = random.nextInt(12);
		    g.drawLine(x, y, x+xl, y+yl);
		}

		String sRand = "";
		for(int i = 0; i < 4; i++) {
		    String code = String.valueOf(randomChar());
		    code = code.replace('0', 'W');
			code = code.replace('o', 'R');
			code = code.replace('I', 'E');
			code = code.replace('1', 'T');
		    sRand += code;
		    g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110),20 + random.nextInt(110)));
		    g.drawString(code, 15 * i + 6, 18);
		}
		
		log.info("生成的验证码："+ sRand);
		
		try {
			// 禁止图像缓存。  
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setContentType("image/jpeg"); 
			//加密
			sRand = MD5Util.convertMD5(sRand);
			
			// 将认证码存入Session,以后需要拿来验证用户是否输入正确
			req.getSession().setAttribute(key, sRand);
			
//			// 将认证码存入COOKIE,以后需要拿来验证用户是否输入正确
//			Cookie verifyCdCookie = new Cookie(key, sRand);
//			// 设定临时Cookie
//			verifyCdCookie.setMaxAge(-1);
//			verifyCdCookie.setPath("/");
//			response.addCookie(verifyCdCookie);
			
			// 将图像输出到Servlet输出流中。  
			ServletOutputStream sos = response.getOutputStream();  
			
			ImageIO.write(image, "jpeg", sos);
			
			sos.flush();
			sos.close();
			
			sos = null;
			
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public static boolean checkVeifyCode(HttpServletRequest req, String inputVerifyId) throws Exception {

		String verifyCode = (String) req.getSession().getAttribute(CommonConstant.SESSION_VERIFY_CODE);
		if (ValidatorUtil.isNullEmpty(verifyCode)) {
			return false;
		} else {
			//解密后验证
			return MD5Util.convertMD5(verifyCode).toLowerCase().equals(req.getParameter(inputVerifyId)!=null?req.getParameter(inputVerifyId).toLowerCase():"");
		}
	}
	
    /**
     * <p>取得随机颜色。</p>
     * <ol>[功能概要]
     * <li>取得随机颜色。
     * </ol>
     * @return 颜色对象
     */
	private static Color getRandColor(int fc, int bc) {
		
	    Random random = new Random();
	    if(fc > 255) {
	        fc = 255;
	    }
	    if(bc > 255) {
	        bc = 255;
	    }
	    int r = fc + random.nextInt(bc - fc);
	    int g = fc + random.nextInt(bc - fc);
	    int b = fc + random.nextInt(bc - fc);
	    
	    return new Color(r, g, b);
	}

	private static char randomChar() {
		Random r = new Random();
		String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
		return s.charAt(r.nextInt(s.length()));
	}
}
