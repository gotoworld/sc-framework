package com.hsd.framework.cache;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-10-24.
 */
@Slf4j
public abstract class AbstractCache<T> implements Cache {
//    protected Class<T> entityClass;

    /**默认Cache超时时间：7天*/
    protected final static Long DEFAULT_EXPIRE = 24 * 60 * 60 * 1000 * 7L;

    public AbstractCache() {
    }

//    @Override
//    public Object get(String key) {
//        return get(key, Object.class);
//    }

    @Override
    public <T>T get(String key) {
        return (T)doGet(key, OpType.GET );
    }

//    @Override
//    public <T>T get(String key, Class<?>... fieldClazz) {
//        OpType get = OpType.GET;
//        if (fieldClazz != null) {
//            get.setOther(fieldClazz);
//        }
//
//        return (T)doGet(key, get );
//    }

    @Override
    public List<T> get(String[] keys) {
        List<T> objs = new ArrayList<>();
        if (keys != null && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                objs.add(get(keys[i]));
            }
        }
        return objs;
    }

    @Override
    public <T>T getSet(String key, T value) {
        return (T)doGet(key, OpType.GETSET);
    }

    @Override
    public void put(String key, Object object) {
//        doPut(key, object, Cache.OpType.PUT);
        put(key, object, DEFAULT_EXPIRE);
    }

    @Override
    public void put(String key, Object object, Long expire) {
        Cache.OpType type = OpType.SETEX;
        type.setOther(expire);
        doPut(key, object, type);
    }

    @Override
    public void expire(String key, Long expire) {
        Cache.OpType type = Cache.OpType.EXPIRE;
        type.setOther(expire);
        doPut(key, "", type);
    }

    @Override
    public void expireAt(String key, Long expire) {
        Cache.OpType type = Cache.OpType.EXPIREAT;
        type.setOther(expire);
        doPut(key, "", type);
    }

    @Override
    public Long remove(String key) {
        return (Long)doGet(key, OpType.DEL );
    }

    @Override
    public List<Long> remove(String[] keys) {
        List<Long> list = new ArrayList<>();
        if (keys != null && keys.length > 0) {
            for (int i = 0; i < keys.length; i++) {
                list.add(this.remove(keys[i]));
            }
        }
        return list;
    }

    @Override
    public boolean exists(String key) {
        Boolean val = (Boolean)doGet(key, OpType.EXISTS );
        return val == null ? false : val.booleanValue();
    }

    @Override
    public Long ttl(String key) {
        return (Long)doGet(key, Cache.OpType.TTL );
    }

    protected abstract void doPut(String key, Object object, CacheOperaterType type);

    protected abstract Object doGet(String key, CacheOperaterType type);
}
