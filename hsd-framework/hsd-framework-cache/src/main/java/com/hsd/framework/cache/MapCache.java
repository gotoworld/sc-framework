package com.hsd.framework.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 16-10-24.
 */
public interface MapCache<T> extends Cache {
    public static enum OpType implements CacheOperaterType{
        GET,
        PUT,
        DEL,
        EXISTS,
        SETNX,
        LEN,
        KEYS,
        VALS,
        GETALL,

        ;
        private Object other;
        @Override
        public void setOther(Object other) {
            this.other = other;
        }

        @Override
        public Object getOther() {
            return other;
        }
    };

//    public void get(String field);    //：获取指定的hash field，如 hget hashtable field
//
//    public <T>T get(String field, Class<T> clazz);    //：获取指定的hash field，如 hget hashtable field
//
//    public List get(String[] fields);    //：获取全部指定的hash field，如 hmget hashtable field field1 field2

//    public boolean exists(String field);    //：判断指定field是否存在，如 hexists hashtable field4

//    public void put(String field, Object value);    //：设置hash field为指定值，如果key不存在，则先创建，如 hset hashtable field hello

//    public void expire(Long expire) ;
//    public void hsetnx(String field, Object value);    //：设置hash field为指定值，如果key不存在，则先创建，如果存在返回0，如 hsetnx hashtable field1 world
//    public void hmset();    //：同时设置hash的多个field，如 hmset hashtable field2 helloween field3 christmas

//    public void hincrby();    //：指定的hash field加上特定值，如 hincrby hashtable field4 5
//    public void hdecrby();    //：指定的hash field减去特定值，如 hdecrby hashtable field4 5
//    public void remove(String field);    //：删除指定hash的field，如 hdel hashtable field

    public boolean exists();

    public Long size();    //：返回指定hash的field数量，如 hlen hashtable

    public Set<String> keys();    //：返回hash的所有field，如 hkeys hashtable
    public List<T> values();    //：返回hash的所有value，如 hvals hashtable
    public Map<String, T> getAll();    //：获取某个hash中全部的field几value，如 hgetall hashtable
}
