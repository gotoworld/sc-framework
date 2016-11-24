package com.wu1g.common.utils;

import java.util.Random;

/**
 * Created by Administrator on 2016/11/17.
 */
public class MessageUtil {

    public static String sendMessage(String num,String amount,String mobile, int type,int codeLength){

        String code = "-1";

        //随机数 验证码
        StringBuffer intCount = new StringBuffer();
        for (int i=0;i<codeLength;i++){
            intCount.append(new Random().nextInt(10));
        }

        //先用阿里大鱼发送
        String[] returnStr = AliDaYuSMSUtil.getRequest(num,amount,mobile,type,intCount.toString());

        if(returnStr[0].equals("0")){
            code = returnStr[1];
        }else{
            // 再用聚合发送
            returnStr = JuheSMSUtil.getRequest2(num, amount, mobile, type,intCount.toString());
            // 如果成功
            if (returnStr[0].equals("0")) {
                code = returnStr[1];
            } else {
                // 由于某种原因聚合没成功，则用美圣发送
                code = MeiShengSmsUtil.sendSmsCodeUseTemp(mobile,intCount.toString());
            }
        }

        return code;
    }
}
