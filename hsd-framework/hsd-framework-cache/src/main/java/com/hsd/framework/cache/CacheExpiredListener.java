package com.hsd.framework.cache;

/**
 * 监听缓存中某个记录超时接口
 * Created by gumutianqi@gmail.com on 16/8/10.
 */
public interface CacheExpiredListener {
    /**
     * 当缓存中的某个对象超时被清除的时候触发
     *
     * @param region: Cache region name
     * @param key:    cache key
     */
    void notifyElementExpired(String region, Object key);
}
