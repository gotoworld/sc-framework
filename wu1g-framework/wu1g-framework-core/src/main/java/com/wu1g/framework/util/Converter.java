package com.wu1g.framework.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;

/**
 * Created by Administrator on 16-9-19.
 */
public class Converter {

    public static int hexCharToInt(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    // 从十六进制字符串到字节数组转换
    public static byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((hexCharToInt(c0) << 4) | hexCharToInt(c1));
        }
        return b;
    }

    public static byte[] toUTF8(String val) {
        try
        {
            return val.getBytes("UTF-8");
        }catch(Exception e ){
            return val.getBytes();
        }
    }

    public static String toUTF8(byte[] val) {
        try
        {
            return new String(val, 0, val.length, "UTF-8");
        }catch(Exception e ){
            return new String(val, 0, val.length);
        }
    }

    public static Integer stringToInteger(String val) {
        try
        {
            return Integer.parseInt(val);
        }catch(Exception e ){
            return 0;
        }
    }

    public static Integer stringToInteger(String val, Integer defaultValue) {
        try
        {
            return Integer.parseInt(val);
        }catch(Exception e ){
            return defaultValue;
        }
    }

    public static Long stringToLong(String val) {
        try
        {
            return Long.parseLong(val);
        }catch(Exception e ){
            return 0L;
        }
    }

    public static Long stringToLong(String val, Long defaultValue) {
        try
        {
            return Long.parseLong(val);
        }catch(Exception e ){
            return defaultValue;
        }
    }

    /**
     * 将给定的json数据，反序列化成指定对象。
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(byte[] jsonData, Class<T> clazz)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try
        {
            return (T)objectMapper.readValue(jsonData, clazz);
        }catch(IOException e) {
            throw new IllegalArgumentException("parseObject:" + new String(jsonData), e);
        }
    }

    /**
     * 将给定的json数据(字符串)，反序列化成指定对象。
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonData, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try
        {
            T t = (T)objectMapper.readValue(jsonData, clazz);
            return t;
        }catch(IOException e) {
            throw new IllegalArgumentException("parseObject:" + jsonData, e);
        }
    }

    /**
     * 多泛型、或多级对象反序列化
     *  示例：new TypeReference<Response<A>>() {}      (Response<A>) s = (Response<A>)parseObject(json, new TypeReference<Response<A>>() {} );
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonData, TypeReference<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try
        {
            T t = (T)objectMapper.readValue(jsonData, clazz);
            return t;
        }catch(IOException e) {
            throw new IllegalArgumentException("parseObject:" + jsonData, e);
        }
    }

    /**
     * 多泛型、或多级对象反序列化
     * @param jsonData
     * @param clazz
     * @param fieldClazz    熟悉类或泛型等
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonData, Class<?> clazz, Class<?>... fieldClazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try
        {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(clazz, fieldClazz);
            T t = (T)objectMapper.readValue(jsonData, javaType);
            return t;
        }catch(IOException e) {
            throw new IllegalArgumentException("parseObject:" + jsonData, e);
        }
    }

    /**
     * 将对象序列化成jackson（json）字符串
     * @param obj
     * @return
     */
    public static String parseObject(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringEmp = new StringWriter();
        try
        {
            objectMapper.writeValue(stringEmp, obj);
            return stringEmp.toString();
        }catch(IOException e) {
            throw new IllegalArgumentException("parseObject:" + obj, e);
        }
    }

    /**
     * 序列化对象，如对象为Serializable则直接序列化；否则json序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        if ( object instanceof Serializable ) {
            return serializeObject(object);
        } else {
            String val = parseObject(object);
            return toUTF8(val);
        }
//        return serializeKryo(object );
    }

    /**
     * 反序列化对象：Serializable或json对象
     * @param bytes
     * @return
     */
    public static <T>T unSerialize(byte[] bytes) {
        if (bytes == null)
            return null;
//        return (T) unSerializeKryo(bytes, Object.class);
        if (bytes[0] == '[' || bytes[0] == '{') {
            return (T)parseObject(toUTF8(bytes), Object.class);
        } else {
            return unSerializeObject(bytes);
        }
    }

    /**
     * 反序列化对象：Serializable或json对象
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T unSerialize(byte[] bytes, Class<T> clazz) {
        if (bytes == null)
            return null;
//        return (T) unSerializeKryo(bytes , clazz);
        if (bytes[0] == '[' || bytes[0] == '{') {
            return (T) parseObject(toUTF8(bytes), clazz);
        } else {
            return (T) unSerializeObject(bytes);
        }
    }

//    public static <T> T unSerialize(String json, Class<T>[] clazz) {
//        if (json == null)
//            return null;
////        return (T) unSerializeKryo(bytes , clazz);
////        if (bytes[0] == '[' || bytes[0] == '{') {
//            return (T) parseObject(json, clazz);
////        } else {
////            return (T) unSerializeObject(bytes);
////        }
//    }

//    /**
//     * 序列化对象（Serializable）
//     * @param object
//     * @return
//     */
//    public static byte[] serializeKryo(Object object) {
//        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
//        try {
//            Schema schema = RuntimeSchema.getSchema(object.getClass());
//
//            byte[] bytes = ProtostuffIOUtil.toByteArray(object, schema, buffer);
//            return bytes;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally{
//            buffer.clear();
//        }
//    }
//    /**
//     * 反序列化对象（Serializable）
//     * @param bytes
//     * @param <T>
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T unSerializeKryo(byte[] bytes, Class<T> clazz) {
//        Schema schema = RuntimeSchema.getSchema(clazz);
//
////        for(byte[] bs : bytesList) {
////            Products product = new Products();
////            ProtostuffIOUtil.mergeFrom(bs, product, schema);
////            list.add(product);
////        }
//        try {
//            List<T> list = (List<T>)ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(bytes, 0, bytes.length), schema);
//
//            return list.get(0);
//        } catch (Exception e) {
//            throw new IllegalArgumentException(e);
//        }finally{
//        }
//    }

    /**
     * 序列化对象（Serializable）
     * @param object
     * @return
     */
    public static byte[] serializeObject(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(baos!=null){
                try {
                    baos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 反序列化对象（Serializable）
     * @param bytes
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T unSerializeObject(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }finally{
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(bais!=null){
                try {
                    bais.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
