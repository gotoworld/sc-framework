package com.hsd.config.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

public class ShrioRedisCacheManager extends AbstractCacheManager {

	private RedisTemplate<byte[], byte[]> shiroRedisTemplate;
	private long timeout;
	public ShrioRedisCacheManager(RedisTemplate<byte[], byte[]> shiroRedisTemplate,long _timeout) {
		this.shiroRedisTemplate = shiroRedisTemplate;
		this.timeout=_timeout;
	}

	@Override
	public Cache<byte[], byte[]> createCache(String name) throws CacheException {
		return new ShrioRedisCache<byte[], byte[]>(shiroRedisTemplate, name,timeout);
	}
}
