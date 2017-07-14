package com.hsd.framework.cache.redis;

import com.hsd.framework.cache.Cache;
import com.hsd.framework.cache.CacheOperaterType;
import com.hsd.framework.cache.MapCache;
import com.hsd.framework.util.Converter;
import com.hsd.framework.cache.util.ObjectSerializer;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.*;

/**
 * 基于redis的缓存实现方案
 * 
 * Created by gumutianqi@gmail.com
 * 
 */
@Slf4j
public class RedisMapCache<T> extends AbstractRedisCache<T> implements MapCache<T> {
	private byte[] b_listKey ;
	private String listKey ;

	public RedisMapCache(String poolName, String listKey) {
		super(poolName);
		this.listKey = listKey;
		this.b_listKey = Converter.toUTF8(listKey );
		Class clazz = getClass();

//		TypeVariable[] a = clazz.getTypeParameters();
//		this.entityClass = (Class<T>)a[0].getClass();
//
//		while (clazz != Object.class) {
//
////			Type[] bb = clazz.getGenericInterfaces();
//
//			Type t = clazz.getGenericSuperclass();
//			if (t instanceof ParameterizedType) {
//				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
//				if (args[0] instanceof Class) {
//					this.entityClass = (Class<T>) args[0];
//					break;
//				}
//			}
//			clazz = clazz.getSuperclass();
//		}
//		log.info("---------------T.class= " + entityClass);
	}

	@Override
	public T get(String field ) {
		return (T)doGet(field, MapCache.OpType.GET );
	}

	/**
	 * 当前key （MAP）是否存在
	 * @return
     */
	@Override
	public boolean exists() {
		Boolean val = (Boolean)doGet(this.listKey, Cache.OpType.EXISTS );
		return val == null ? false : val.booleanValue();
	}

	/**
	 * 字段是否存在
	 * @param field
	 * @return
     */
	@Override
	public boolean exists(String field) {
		Boolean val = (Boolean)doGet(field, MapCache.OpType.EXISTS );
		return val == null ? false : val.booleanValue();
	}

	@Override
	public void put(String field, Object value) {
		doPut(field, value, MapCache.OpType.PUT);
	}

	@Override
	public Long remove(String field) {
		return (Long)doGet(field, MapCache.OpType.DEL );
	}

	@Override
	public Long size() {
		return (Long)doGet("LEN", MapCache.OpType.LEN );
	}

	@Override
	public Set<String> keys() {
		return (Set<String>)doGet("KEYS", MapCache.OpType.KEYS );
	}

	@Override
	public List<T> values() {
		return (List<T>)doGet("VALS", MapCache.OpType.VALS );
	}

	@Override
	public Map<String, T> getAll() {
		return (Map<String, T>)doGet("getall", MapCache.OpType.GETALL );
	}

	@Override
	protected Object doGet0(Jedis jedis, byte[] key, CacheOperaterType type) throws Exception {
		byte[] val = null;
		if (type == Cache.OpType.GET) {
			return ObjectSerializer.unSerialize(jedis.get(key ) );
		} else if (type == Cache.OpType.DEL) {
			return jedis.del(key );
		} else if (type == MapCache.OpType.GET) {
			val = jedis.hget(b_listKey, key );
			return (T)ObjectSerializer.unSerialize(val );
		} else if (type == MapCache.OpType.DEL) {
			return (T)jedis.hdel(this.b_listKey, key);
		} else if (type == MapCache.OpType.EXISTS) {
			return (T)jedis.hexists(this.b_listKey, key);
		} else if (type == MapCache.OpType.LEN) {
			return (T)jedis.hlen(this.b_listKey);
		} else if (type == MapCache.OpType.GETALL) {
//			jedis.hgetAll(listKey, key);
		} else if (type == MapCache.OpType.KEYS) {
			return (T)jedis.hkeys(listKey);
		} else if (type == MapCache.OpType.VALS) {
			List result = new ArrayList<>();
			List<byte[]> list = jedis.hvals(b_listKey);
			for(byte[] cur : list) {
				result.add(ObjectSerializer.unSerialize(cur ));
			}
			return (T)result;
		}

		return null;
	}

	@Override
	protected void doPut0(Jedis jedis, byte[] key, byte[] value, CacheOperaterType type) throws Exception {
		if (type == MapCache.OpType.PUT) {
			jedis.hset(b_listKey, key, value);
		}
	}
}
