package com.hsd.framework.cache.redis;

import com.hsd.framework.cache.*;
//import com.hsd.framework.util.Converter;
import com.hsd.framework.cache.util.ObjectSerializer;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于redis的List对象实现，主要提供“栈”的功能。
 * 
 * Created by gumutianqi@gmail.com
 * 
 */
@Slf4j
public class RedisListCache<T> extends AbstractRedisCache<T> implements ListCache<T> {
	private String listKey ;

	public RedisListCache(String poolName, String listKey) {
		super(poolName);
		this.listKey = listKey;
	}

	@Override
	public T get(String key ) {
//		return super.get(this.listKey);
		throw new CacheException("ListCache no support");
	}

	@Override
	public void put(String key, Object object, Long expire) {
//		super.put(key, object, expire);
//		super.expire(this.listKey, expire);
		throw new CacheException("ListCache no support");
	}

	@Override
	public void put(String key, Object object) {
//		super.put(key, object);
		throw new CacheException("ListCache no support");
	}

	@Override
	public void remove() {
		this.remove(this.listKey);
	}

	@Override
	public Long remove(String key) {
		return super.remove(this.listKey);
	}

	@Override
	public void lpush( T object) {
		doPut(listKey, object, ListCache.OpType.LPUSH);
	}
	@Override
	public void rpush( T object) {
		doPut(listKey, object, ListCache.OpType.RPUSH);
	}
	@Override
	public T lpop() {
		return (T)doGet(listKey, ListCache.OpType.LPOP );
	}
	@Override
	public T rpop() {
		return (T)doGet(listKey, ListCache.OpType.RPOP );
	}
	@Override
	public List<T> blpop() {
		return (List<T>)doGet(listKey, ListCache.OpType.BLPOP );
	}
	@Override
	public List<T> brpop() {
		return (List<T>)doGet(listKey, ListCache.OpType.BRPOP );
	}

	@Override
	protected Object doGet0(Jedis jedis, byte[] key, CacheOperaterType type ) throws Exception {
		if (type == ListCache.OpType.BLPOP || type == ListCache.OpType.BRPOP) {
			List<byte[]> result = null;
			if (type == ListCache.OpType.BLPOP) {
				result = jedis.blpop(new byte[][]{key});
			} else if (type == ListCache.OpType.BRPOP) {
				result = jedis.brpop(new byte[][]{key});
			}

			List<T> list = new ArrayList<>();
			for (byte[] cur : result) {
				list.add((T) ObjectSerializer.unSerialize(cur ));
			}
			return list;
		} else {
			byte[] result = null;
			if (type == ListCache.OpType.LPOP) {
				result = jedis.lpop(key );
			} else if (type == ListCache.OpType.RPOP) {
				result = jedis.rpop(key );
			}
			return (T)ObjectSerializer.unSerialize( result );
		}
	}

	@Override
	protected void doPut0(Jedis jedis, byte[] key, byte[] value, CacheOperaterType type) throws Exception {
		if (type == ListCache.OpType.LPUSH) {
			jedis.lpush(key, value);
		} else if (type == ListCache.OpType.RPUSH) {
			jedis.rpush(key, value);
		}
	}
}
