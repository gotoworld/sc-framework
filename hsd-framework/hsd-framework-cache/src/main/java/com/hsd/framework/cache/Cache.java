package com.hsd.framework.cache;

import java.util.List;

/**
 * 
 * 功能说明: 缓存通用接口
 * <p>
 * 
 * 开发人员: 刘良旭 <br>
 * 开发时间: 2016-10-9 <br>
 * <br>
 */
public interface Cache {
	public static enum OpType implements CacheOperaterType{
		GET,
		GETSET,	//设置key的值，并返回key的旧值
		PUT,
		INCR,		//对key的值做++操作，并返回新的值，当key不存在时，设置key值为0再进行加操作
		INCRBY,	//对key的值做加指定值操作，当key不存在时，设置key值为0再进行加操作
		DECR,		//对key的值做--操作，当key不存在时，设置key值为0再进行减操作
		DECRBY,	//对key的值做减指定值操作，当key不存在时，设置key值为0再进行减操作
		DEL,
		SETNX,		//设置key对应的值为string类型的value，如果key已经存在，返回0
		SETEX,		//置key对应的值为string类型的value，并指定此键值对的有效期
		APPEND,	//给指定key的字符串追加value，返回新字符串值的长度，当key不存在时，添加该key
		STRLEN,	//返回指定key的value长度

		EXPIRE,	//key过期（毫秒）
		EXPIREAT,	//key过期(指定的时间点)（毫秒）
		EXISTS,
		TTL,		//返回一个key还能活多久，单位为秒
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

	/**
	 * 
	 * 缓存初始化
	 *
	 */
	void init();

	/**
	 * 当为hash是，参数key代表field
	 * @param key
	 */
	<T>T get(String key);

//	/**
//	 * 当为hash是，参数key代表field
//	 * @param key
//	 * @param <T>
//     * @return
//     */
//	<T> T get(String key, Class<?>... fieldClazz);

	/**
	 * 当为hash是，参数key代表field
	 * @param keys
	 * @return
	 */
	<T> List<T> get(String[] keys);

	/**
	 * 自动将key对应到value并且返回原来key对应的value
	 * @param key
     * @return
     */
	<T>T getSet(String key, T value);
	/**
	 * 当为hash是，参数key代表field
	 * @param key
	 * @param object
	 * @
	 */
	void put(String key, Object object);

	/**
	 * 指定此键值对的有效期
	 * @param key
	 * @param object
	 * @param expire
     */
	void put(String key, Object object, Long expire);

	/**
	 * 设置key过期时间
	 * @param key
	 * @param expire
     */
	public void expire(String key, Long expire) ;

	/**
	 * 设置key过期时间
	 * @param key
	 * @param expire
	 */
	public void expireAt(String key, Long expire) ;

	/**
	 * 删除指定key，(如指定hash则为指定的field)
	 * @param key	 *
	 */
	Long remove(String key);

	/**
	 * 删除指定key【】，(如指定hash则为指定的field【】)
	 * @param keys
	 */
	List<Long> remove(String[] keys);

	/**
	 * 判断指定key是否存在，(如指定hash则为指定的field)
	 * @param key
	 * @return
     */
	public boolean exists(String key);

	public Long ttl(String key);
}
