package com.hsd.msg.web.controller.msg;

import com.hsd.framework.Response;
import com.hsd.framework.annotation.NoAuthorize;
import com.hsd.framework.cache.util.RedisHelper;
import com.hsd.framework.security.MD5;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 一次性图片校验码
 */
@RestController
@NoAuthorize
public class ImgCaptchaController {
    private static final String acPrefix = "/api/util/sms/imgCaptcha/";
    public static final String prefix = "verify:img:";
    @Autowired
    private RedisHelper redisHelper;
    /** 校验码json */
    @RequestMapping(value = acPrefix+"/json", method = {RequestMethod.POST, RequestMethod.GET})
    public Response json(HttpServletRequest request, HttpServletResponse response) {
        Response result=new Response(0,"success");
        try {
            // 设置页面不缓存
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            result.data=encodeBase64ImgCode();
        } catch (Throwable e) {
            result=Response.error(e.getMessage());
        }
        return result;
    }
    /** 校验码html */
    @RequestMapping(value = acPrefix+"/html")
    public void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=utf-8");
        Map captcha=encodeBase64ImgCode();
        out.println("<input id=\"imgCaptchaId\" name=\"imgCaptchaId\" type=\"hidden\" value=\"" + captcha.get("id") + "\">");
        out.println("<img class=\"imgCaptchaImage\" src=\"" + captcha.get("img") + "\">");
        out.close();
    }

    // 渲染随机背景颜色
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    //渲染固定背景颜色
    private Color getBgColor() {
        return new Color(200, 200, 200);
    }
    private BufferedImage drawImg(String captcha) {
        return drawImg(false,captcha);
    }
    //生成认证码
    private String generateCaptcha(int len){
        Random random = new Random();
        // 取随机产生的认证码(8位数字和字母混合)
        String captcha="";
        char[] seds = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(seds.length);
            char cc = seds[index];
            captcha += cc;
        }
        return captcha;
    }

    // 画验证码图形
    private BufferedImage drawImg(boolean isDrawLine,String captcha) {
        // 在内存中创建图象
        int width = 120, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        Random random = new Random();
        // 设定背景色
        g.setColor(getBgColor());
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // 画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);

        /**随机产生155条干扰线，使图象中的认证码不易被其它程序探测到*/

        if (isDrawLine) {
            g.setColor(getRandColor(160, 200));
            for (int i = 0; i < 155; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
        }

        for (int i = 0; i < captcha.length(); i++) {
            if ((i + 1) % 2 == 0) {
                g.setColor(new Color(255, 0, 0));
            } else {
                g.setColor(new Color(0, 0, 0));
            }
            // 将认证码随机打印不同的颜色显示出来
            //g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(captcha.substring(i,i+1) + "", 13 * i + 6, 16);
        }
        // 图象生效
        g.dispose();

        return image;
    }
    private Map encodeBase64ImgCode() throws IOException {
        Map captchaMap=new HashMap();
        //生成认证码
        String captcha=generateCaptcha(5);
        BufferedImage codeImg =drawImg(captcha);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(codeImg, "JPEG", out);
        byte[] b = out.toByteArray();
        String imgString ="data:image/JPEG;base64," + Base64.encodeBase64String(b);//获取通过base64加密后图形码字符串
        String uuid=MD5.md5Hex(imgString);//生成验证码uuid
        captchaMap.put("id", uuid);
        captchaMap.put("img",imgString);
        //将认证码存入redis
        redisHelper.set(prefix+uuid,captcha,30, TimeUnit.MINUTES);//N分钟
        return  captchaMap;
    }
}
