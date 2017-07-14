package com.hsd.framework.cache;

import com.hsd.framework.cache.config.CacheConfig;
import redis.clients.util.Pool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache连接池管理器（多连接池），根据配置“连接池名”获取连接池
 * Created by Administrator on 16-10-9.
 */
public class CachePoolManager {

    private static CachePoolManager instance;

    private Map<String, Pool> pools = new ConcurrentHashMap<String, Pool>();

    public CachePoolManager() {

    }

    public static CachePoolManager getInstance() {
        if(instance == null) {
            instance = (CachePoolManager) CacheConfig.getBean("cachePoolManager");
            if (instance == null)
                instance = new CachePoolManager();
        }
        return instance;
    }

    public void put(String name, Pool pool) {
        pools.put(name, pool);
    }

    public Pool get(String name) {
        if (name == null || name.length() < 1)
            return get();
        return pools.get(name);
    }

    /**
     * 当只有一个连接池时，可直接获取
     * @return
     */
    public Pool get() {
        if ( pools.size() < 2 )
            return (Pool)pools.entrySet().iterator().next();
        throw new IllegalArgumentException("无法选择默认Jedis Pool");
    }

}
