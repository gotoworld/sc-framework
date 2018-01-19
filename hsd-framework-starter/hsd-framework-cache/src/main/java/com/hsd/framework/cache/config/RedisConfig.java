package com.hsd.framework.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsd.framework.cache.util.IdGeneratorIsRedis;
import com.hsd.framework.config.AppConfig;
import com.hsd.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Redis服务器对象缓存配置(对象缓存和Session缓存)
 */
@Configuration
//@EnableCaching
@Slf4j
public class RedisConfig extends CachingConfigurerSupport {

	/** 加载属性文件数据 */
	@Bean
	public RedisProperties redisProperties() {
		return new RedisProperties();
	}

	/** 分布式有序id生成器*/
	@Bean(name = "IdGenerator")
	public IdGeneratorIsRedis idGenerator(){
		RedisProperties redisProperties=redisProperties();
		IdGeneratorIsRedis.IdGeneratorBuilder idGeneratorBuilder = IdGeneratorIsRedis.builder();
		if(redisProperties.getIdGenNodes()!=null){
			List<String> idGenNodes=redisProperties.getIdGenNodes();
			idGenNodes.forEach(idGenNodeStr -> {
				String[] idGenNodeArr=idGenNodeStr.split("@");//host:port:luaSha@password
				String[] hostAndPortAndLuaSha=idGenNodeArr[0].split(":");
				String host=hostAndPortAndLuaSha[0];
				int port=Integer.parseInt(hostAndPortAndLuaSha[1]);
				String luaSha=hostAndPortAndLuaSha[2];
				String password=idGenNodeArr.length>1?idGenNodeArr[1]:null;
				if(ValidatorUtil.notEmpty(password)){
					idGeneratorBuilder.addHost(host, port,redisProperties.getTimeout(),AppConfig.checkPassword(password), luaSha);
				}else{
					idGeneratorBuilder.addHost(host, port, luaSha);
				}
			});
		}
		return idGeneratorBuilder.build();
	}

	/** 主键生成器 */
	@Bean
	public KeyGenerator commonKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				String key = sb.toString();
				log.info("key:" + key);
				return key;
			}
		};

	}

	private JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		RedisProperties.Pool props = redisProperties().getPool();
		config.setMaxTotal(props.getMaxActive());
		config.setMaxIdle(props.getMaxIdle());
		config.setMinIdle(props.getMinIdle());
		config.setMaxWaitMillis(props.getMaxWait());
		return config;
	}

	@Bean(name = "secondaryRedisConnectionFactory")
	public RedisConnectionFactory secondaryRedisConnectionFactory() {
		log.info("2.1 初始化Redis缓存服务器(普通对象)... ...");
		RedisProperties redisProperties=redisProperties();
		JedisConnectionFactory redisConnectionFactory =null;
		if(redisProperties().getCluster().isEnable()){
			redisConnectionFactory=new JedisConnectionFactory(new RedisClusterConfiguration(redisProperties.getCluster().getNodes()));
		}else{
			redisConnectionFactory=new JedisConnectionFactory(jedisPoolConfig());
			redisConnectionFactory.setDatabase(redisProperties.getSecondaryDatabase());
			redisConnectionFactory.setHostName(redisProperties.getHost());
			redisConnectionFactory.setPort(redisProperties.getPort());
			redisConnectionFactory.setTimeout(redisProperties.getTimeout());
		}
		if(ValidatorUtil.notEmpty(redisProperties.getPassword())){
			redisConnectionFactory.setPassword(AppConfig.checkPassword(redisProperties.getPassword()));
		}
		if(!redisProperties().getCluster().isEnable()){
			redisConnectionFactory.afterPropertiesSet();
		}
		return redisConnectionFactory;
	}

	@Bean(name = "secondaryStringRedisTemplate")
	public RedisTemplate<String, String> redisTemplate(@Qualifier("secondaryRedisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		log.info("2.2 初始化Redis模板(普通对象)... ...");
		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		template.setValueSerializer(redisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public CacheManager cacheManager(@Qualifier("secondaryStringRedisTemplate") RedisTemplate redisTemplate) {
		CacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
		return redisCacheManager;
	}

	/** 设置redisTemplate的存储格式 */
	@Bean
	public RedisSerializer redisSerializer() {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		om.setSerializationInclusion(Include.NON_EMPTY);// 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper
//		GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
		return new GenericJackson2JsonRedisSerializer(om);
	}
	
	/** RedisTemplate */
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> sessionRedisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(secondaryRedisConnectionFactory());
		RedisSerializer stringSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(redisSerializer());
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(redisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	public static void main(String[] args) {
		String[] idGenNodeArr="192.168.108.100:6379:c5809078fa6d652e0b0232d552a9d06d37fe819c@AES:Jdd7GJIx8oXFqMvDfLqEVg==".split("@");//host:port:luaSha&password
		String[] hostAndPortAndLuaSha=idGenNodeArr[0].split(":");
		String host=hostAndPortAndLuaSha[0];
		int port=Integer.parseInt(hostAndPortAndLuaSha[1]);
		String luaSha=hostAndPortAndLuaSha[2];
		String password=idGenNodeArr.length>1?idGenNodeArr[1]:null;
		if(ValidatorUtil.notEmpty(password)){
			System.out.println(host+":"+port+":"+AppConfig.checkPassword(password)+":"+luaSha);
		}else{
			System.out.println(host+":"+port+":"+luaSha);
		}
	}
}
