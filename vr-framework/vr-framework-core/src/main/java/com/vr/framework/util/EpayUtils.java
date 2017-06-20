package com.vr.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/10/26.
 */
public class EpayUtils
{
    /**
     * 生成20位随机数(2位业务类型+14位时间+4位随机)
     * @return
     */
    public static String getOrderNumber(Byte type){
        StringBuilder buf = new StringBuilder();
        try {
            String s_type = ""+type;
            if(type!=null && type.toString().length()<2){
                s_type = "0"+type;
            }
            buf.append(s_type);
            String str = UUID.randomUUID().toString().replaceAll("-","");
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");//14位
            buf.append(format.format(System.currentTimeMillis()));
            buf.append(str.substring(0,4));
        }catch (Exception e){
            e.printStackTrace();
        }
        return buf.toString();
    }

    /**
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }


    /**
     * 实体转换为Map
     * @param entity
     * @return
     */
    public static Map<String, String> beanToMap(Object entity){
        Map<String, String> parameter = new HashMap<String, String>();
        Field[]   fields   =   entity.getClass().getDeclaredFields();
        for(int i = 0; i < fields.length; i++){
            String fieldName =  fields[i].getName();
            Object o = null;
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = entity.getClass().getMethod(getMethodName, new Class[] {});
                o = getMethod.invoke(entity, new Object[] {});
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(o != null){
                parameter.put(fieldName, o.toString());
            }
        }
        return parameter;
    }

    public final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
