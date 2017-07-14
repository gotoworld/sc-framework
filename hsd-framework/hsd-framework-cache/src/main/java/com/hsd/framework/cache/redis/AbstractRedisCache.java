package com.hsd.framework.cache.redis;

import com.hsd.framework.cache.*;
import com.hsd.framework.cache.util.ObjectSerializer;
import com.hsd.framework.config.AppConfig;
import com.hsd.framework.util.Converter;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Pool;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;


/**
 * 基于redis的缓存实现方案
 * 
 * Created by gumutianqi@gmail.com
 * 
 */
@Slf4j
public abstract class AbstractRedisCache<T> extends AbstractCache<T> {

	private String poolName;

	protected Pool jedisPool;

	CachePoolManager poolManager;

//	Class<? extends T> clazz = T;

	public AbstractRedisCache() {
		this(null);
	}

	public AbstractRedisCache(String poolName) {
		super();
		this.poolName = poolName;
		init();
	}

//	@Override
	public synchronized void init() {
		if (poolManager == null)
			poolManager = CachePoolManager.getInstance();

		if(jedisPool == null){
			jedisPool = poolName == null || poolName.length() < 1 ? poolManager.get( ) : poolManager.get(poolName );
		}
		if (jedisPool.isClosed()) {
			throw new CacheException("JedisPool没有初始化或者已经关闭.");
		}

	}

	@Override
	protected Object doGet(String key, CacheOperaterType type) {
		key = AppConfig.getKey(key);
		if (key == null ) {
			return null;
		}
		log.debug("doGet key={}, OpType={} ", key, type);

		Jedis jedis = getResource();
		try {
			byte[] bkey = Converter.toUTF8( key);
			/**通用的处理，*/
			if (type == OpType.GET) {
				byte[] val = jedis.get(bkey);
//				Class[] classes = (Class[])type.getOther();
//				if (classes == null )
					return (T) ObjectSerializer.unSerialize(val );
//				else
//					return (T) ObjectSerializer.unSerialize(val, classes);
//			} else if (type == OpType.GETSET) {
//				return ObjectSerializer.unSerialize(jedis.getset(bkey ) ); //jedis.get(key );
			} else if (type == OpType.DEL) {
				return jedis.del(key ); //jedis.get(key );
			} else if (type == Cache.OpType.EXISTS) {
				return jedis.exists(bkey);
			} else if (type == Cache.OpType.TTL) {
				return jedis.ttl(bkey);
			}

			return doGet0(jedis, bkey, type);
		} catch (JedisConnectionException je) {
			log.error(je.getMessage(), je);
			jedis = returnBrokenResource(jedis);
			throw new CacheException(je );
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CacheException(e );
		} finally {
			returnResource(jedis);
		}
	}

	@Override
	protected void doPut(String key, Object object, CacheOperaterType type) {
		key = AppConfig.getKey(key);
		if (key == null || object == null) {
			return;
		}
		log.debug("doPut key={}, OpType={} ", key, type);

		Jedis jedis = getResource();
		try {
			byte[] bkey = Converter.toUTF8( key);
			byte[] result = null;
//			Class objClass = object.getClass();
//			byte[] b_class = Converter.toUTF8("[" + objClass.getName() + "]");
			byte[] value = ObjectSerializer.serialize(object);

			/**通用的处理，*/
			if (type == OpType.PUT) {
				jedis.set(bkey, value);
			} else if (type == OpType.SETEX) {
				int expire = (int)(((Long)type.getOther()) / 1000);
				jedis.setex(bkey, expire, value);
			} else if (type == Cache.OpType.EXPIRE) {
				Long expire = (Long)type.getOther();
				jedis.pexpire(bkey, expire);
			} else if (type == Cache.OpType.EXPIREAT) {
				Long expire = (Long)type.getOther();
				jedis.pexpireAt(bkey, expire);
			} else {
				doPut0(jedis, bkey, value, type);
			}
		} catch (JedisConnectionException je) {
			log.error(je.getMessage(), je);
			jedis = returnBrokenResource(jedis);
			throw new CacheException(je );
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new CacheException(e );
		} finally {
			returnResource(jedis);
		}
	}

	protected abstract Object doGet0(Jedis jedis, byte[] key,CacheOperaterType type) throws Exception;
	protected abstract void doPut0(Jedis jedis, byte[] key, byte[] value, CacheOperaterType type) throws Exception;

//	public String getStats() {
//		checkJedisPool();
//		try {
//			StringBuffer sb=new StringBuffer();
//			sb.append("JedisPool[");
//			sb.append("numActive = ").append(jedisPool.getNumActive());
//			sb.append(",numIdle = ").append(jedisPool.getNumIdle());
//			sb.append(",numWaiters = ").append(jedisPool.getNumWaiters());
//			sb.append("]");
//			return sb.toString();
//		} catch (Exception e) {
//			throw new CacheException(e);
//		}
//	}
//
//	public int freeMemoryElements(int numberToFree) {
//		throw new CacheException("RedisCache不支持freeMemoryElements方法.");
//	}

//	public void destroy() {
//		if(jedisPool!=null){
//		   jedisPool.destroy();
//		}
//	}

	/**
	 * 从jedis连接池获得Jedis客户端
	 * @return
	 */
	public Jedis getResource(){
		checkJedisPool();
		int timeoutCount = 0;

		Exception ex = null;
		while (timeoutCount < 3) // 如果是网络超时则多试几次
		{
			try
			{
				Jedis jedis = (Jedis)jedisPool.getResource();
				return jedis;
			} catch (Exception e)
			{
				// 底层原因是SocketTimeoutException，不过redis已经捕捉且抛出JedisConnectionException，不继承于前者
				if (e instanceof JedisConnectionException || e instanceof SocketTimeoutException)
				{
					timeoutCount++;
					ex = e;
					log.warn("getJedis timeoutCount={}", timeoutCount);
				}else
				{
					log.warn("jedisInfo。NumActive=" + jedisPool.getNumActive() + ", NumIdle="
							+ jedisPool.getNumIdle() + ", NumWaiters="
							+ jedisPool.getNumWaiters() + ", isClosed="
							+ jedisPool.isClosed());
					log.error("getJedis error", e);
					break;
				}
			}
		}
		throw new CacheException(ex);
//		return (Jedis)jedisPool.getResource();
	}

	protected void checkJedisPool() {
//		if(jedisPool==null){
//		   jedisPool = jedisClient.getJedisPool();
//		}
		if (jedisPool.isClosed()) {
			throw new CacheException("JedisPool没有初始化或者已经关闭.");
		}
	}

	/**
	 * 销毁连接
	 *
	 * @param jedis
	 * @author http://blog.csdn.net/java2000_wl
	 */
	protected Jedis returnBrokenResource(Jedis jedis) {
		if (jedis == null) {
			return null;
		}
		try {
			//容错
			jedisPool.returnBrokenResource(jedis);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @param jedis
	 * @author http://blog.csdn.net/java2000_wl
	 */
	protected void returnResource(Jedis jedis) {
		if (jedis == null) {
			return;
		}
		try {
			jedisPool.returnResource(jedis);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
