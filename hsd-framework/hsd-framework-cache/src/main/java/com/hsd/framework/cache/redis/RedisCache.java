package com.hsd.framework.cache.redis;

import com.hsd.framework.cache.Cache;
import com.hsd.framework.cache.CacheOperaterType;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * 基于redis的缓存实现方案
 * 
 * Created by gumutianqi@gmail.com
 * 
 */
@Slf4j
public class RedisCache<T> extends AbstractRedisCache<T> implements Cache{

	public RedisCache() {
		this(null);
	}

	public RedisCache(String poolName) {
		super(poolName);
//		TypeReference<T> ref = new TypeReference<T>(){};
//
//		System.out.println("----ref-type:" + ref);
//		System.out.println("----ref-type:" + ref.getType().getClass().getName());
////		@SuppressWarnings("rawtypes")
//		Class clazz = getClass();
//
//		while (clazz != Object.class) {
//			Type t = clazz.getGenericSuperclass();
//			if (t instanceof ParameterizedType) {
//				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
//				for(Type p : args) {
//					System.out.println("----ref-type:clazz:" + clazz + " type:" + p);
////					if ("T".equals(p.getTypeName()) ) {
//////						this.entityClass = (Class<T>)p;
////						break;
////					}
//				}
////				this.entityClass = (Class<T>)args[0].getClass();
////				if (args[0] instanceof Class) {
////					this.entityClass = (Class<T>) args[0];
////					break;
////				}
//			}
//			clazz = clazz.getSuperclass();
//		}
	}

	@Override
	protected Object doGet0(Jedis jedis, byte[] key, CacheOperaterType type) throws Exception {
		return null;

	}

	@Override
	protected void doPut0(Jedis jedis, byte[] key, byte[] value, CacheOperaterType type) throws Exception {

	}


}
