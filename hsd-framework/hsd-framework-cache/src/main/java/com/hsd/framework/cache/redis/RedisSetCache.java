package com.hsd.framework.cache.redis;

import com.hsd.framework.cache.CacheException;
import com.hsd.framework.cache.CacheOperaterType;
import com.hsd.framework.cache.ListCache;
import com.hsd.framework.cache.SetCache;
import com.hsd.framework.util.Converter;
import com.hsd.framework.cache.util.ObjectSerializer;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基于redis的List对象实现，主要提供“栈”的功能。
 * 
 * Created by gumutianqi@gmail.com
 * 
 */
@Slf4j
public class RedisSetCache<T> extends AbstractRedisCache<T> implements SetCache<T> {
	private String listKey ;
	private byte[] b_listKey ;

	public RedisSetCache(String poolName, String listKey) {
		super(poolName);
		this.listKey = listKey;
		this.b_listKey = Converter.toUTF8(listKey );
	}

	@Override
	public void add(T... member) {
		doPut(this.listKey, member, SetCache.OpType.ADD);
	}

	@Override
	public void add(List<T> member) {
		add((T[])member.toArray());
	}

//	@Override
//	public <T> T get(String key, Class<T> clazz) {
////		return (T)doGet(key, SetCache.OpType.IMEMBER );
//		throw new CacheException("不支持");
//	}

	@Override
	public void put(String key, Object object, Long expire) {
//		super.put(key, object, expire);
//		super.expire(this.listKey, expire);
	}

	@Override
	public void put(String key, Object object) {
//		super.put(key, object);
	}

	@Override
	public Long remove(String key) {
		return (Long)doGet(key, SetCache.OpType.DEL );
	}

	@Override
	public boolean exists(String key) {
		Boolean is = (Boolean)doGet(key, SetCache.OpType.IS_MEMBER );
		return is == null ? false : is.booleanValue();
	}

	@Override
	public T pop() {
		return (T)doGet("sets-pop", SetCache.OpType.POP );
	}

	@Override
	public Set<T> pops(long count) {
		SetCache.OpType type = SetCache.OpType.POPS;
		type.setOther(count);
		return (Set<T>)doGet("sets-pops", type );
	}

	@Override
	public Long size() {
		return (Long)doGet("sets-size", SetCache.OpType.CARD );
	}

	@Override
	protected Object doGet0(Jedis jedis, byte[] key, CacheOperaterType type ) throws Exception {
		if (type == SetCache.OpType.IS_MEMBER) {
			return jedis.sismember(this.b_listKey, key );
		} else if (type == SetCache.OpType.DEL) {
			return jedis.srem(this.b_listKey, key );
		} else if (type == SetCache.OpType.MEMBER) {
			return jedis.smembers(this.b_listKey );
		} else if (type == SetCache.OpType.POP) {
			return (T) ObjectSerializer.unSerialize( jedis.spop(this.b_listKey ) );
		} else if (type == SetCache.OpType.POPS) {
			Long count = (Long)type.getOther();
			Set<byte[]> result = jedis.spop(this.b_listKey , count);
			Set<T> list = new HashSet<>();
			for(byte[] cur : result)
				list.add( (T)ObjectSerializer.unSerialize(  cur ) );
			return result;
		} else if (type == SetCache.OpType.CARD) {
			return jedis.scard(this.b_listKey );
		}
		return null;
	}

	@Override
	protected void doPut0(Jedis jedis, byte[] key, byte[] value, CacheOperaterType type) throws Exception {
		if (type == SetCache.OpType.ADD) {
			jedis.sadd(key, value);
		}
	}
}
