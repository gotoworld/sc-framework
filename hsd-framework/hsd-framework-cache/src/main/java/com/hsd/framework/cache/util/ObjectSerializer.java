package com.hsd.framework.cache.util;

import com.hsd.framework.util.Converter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Cache对象（反）序列化，装入可实例化的实体CacheSerializable
 * Created by Administrator on 16-11-25.
 */
@Slf4j
public class ObjectSerializer {

    /**
     * 使用Json序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        EnableSerializable.O obj = EnableSerializable.getObjectSerializable(object);

        String val = Converter.toJson(obj, false, false);
        return Converter.toUTF8(val);
    }

    /**
     * 反序列化对象：Serializable或json对象
     * @param bytes
     * @return
     */
    public static <T>T unSerialize(byte[] bytes) {
        if (bytes == null)
            return null;

        if (bytes[0] == '[' || bytes[0] == '{') {
//            log.debug("unSerialize: of JsonClassSerializer ");
            if (Arrays.binarySearch(bytes, 2, 4, (byte)'@') > 0) {
                EnableSerializable.O obj = Converter.parseObject(bytes, EnableSerializable.O.class);
                return (T) obj.getO();
            } else {
                //兼容旧版本的缓存数据
                CacheSerializable obj = Converter.parseObject(bytes, CacheSerializable.class);
                return (T) obj.getObject();
            }
        } else {
            return (T)Converter.unSerializeObject(bytes);
        }
    }

//    public static <T>T unSerialize(byte[] bytes, Class<?>... fieldClazz) {
//        if (bytes == null)
//            return null;
//
//        if (bytes[0] == '[' || bytes[0] == '{') {
////            log.debug("unSerialize: of JsonClassSerializer ");
//            EnableSerializable.ObjectSerializable obj = Converter.parseObject(bytes , EnableSerializable.ObjectSerializable.class, fieldClazz);
//            return (T)obj.getObject();
//        } else {
//            return (T)Converter.unSerializeObject(bytes);
//        }
//    }
}
