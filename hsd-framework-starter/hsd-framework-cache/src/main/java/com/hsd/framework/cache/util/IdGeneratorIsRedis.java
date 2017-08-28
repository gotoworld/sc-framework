package com.hsd.framework.cache.util;

import com.hsd.framework.IdGenerator;
import com.hsd.framework.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class IdGeneratorIsRedis implements IdGenerator {
    /**
     * JedisPool, luaSha
     */
    List<Pair<JedisPool, String>> jedisPoolList;
    int retryTimes;

    int index = 0;

    private IdGeneratorIsRedis() {

    }

    private IdGeneratorIsRedis(List<Pair<JedisPool, String>> jedisPoolList, int retryTimes) {
        this.jedisPoolList = jedisPoolList;
        this.retryTimes = retryTimes;
    }

    static public IdGeneratorBuilder builder() {
        return new IdGeneratorBuilder();
    }

   public static class IdGeneratorBuilder {
        List<Pair<JedisPool, String>> jedisPoolList = new ArrayList();
        int retryTimes = 5;

        public IdGeneratorBuilder addHost(String host, int port, String luaSha) {
            jedisPoolList.add(Pair.of(new JedisPool(host, port), luaSha));
            return this;
        }
        public IdGeneratorBuilder addHost(String host, int port,int timeout,String password,String luaSha) {
            jedisPoolList.add(Pair.of(new JedisPool(new GenericObjectPoolConfig(),host, port,timeout, AppConfig.checkPassword(password)), luaSha));
            return this;
        }

        public IdGeneratorBuilder retryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
            return this;
        }

        public IdGeneratorIsRedis build() {
            return new IdGeneratorIsRedis(jedisPoolList, retryTimes);
        }
    }

    public long nextId() {
        return nextId("default", 0);
    }
    public long nextId(String tab) { return nextId(tab, 0); }
    public long nextId(String tab, long shardId) {
        for (int i = 0; i < retryTimes; ++i) {
            Long id = innerNext(tab, shardId);
            if (id != null) {
                return id;
            }
        }
        throw new RuntimeException("Can not generate id!");
    }

    Long innerNext(String tab, long shardId) {
        index++;
        Pair<JedisPool, String> pair = jedisPoolList.get(index % jedisPoolList.size());
        JedisPool jedisPool = pair.getLeft();

        String luaSha = pair.getRight();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<Long> result = (List<Long>) jedis.evalsha(luaSha, 2, tab, "" + shardId);
            long id = buildId(result.get(0), result.get(1), result.get(2),
                    result.get(3));
            return id;
        } catch (JedisConnectionException e) {
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
            log.error("generate id error!", e);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return null;
    }

    public static long buildId(long second, long microSecond, long shardId, long seq) {
        long miliSecond = (second * 1000 + microSecond / 1000);
        return (miliSecond << (12 + 10)) + (shardId << 10) + seq;
    }

    public static List<Long> parseId(long id) {
        long miliSecond = id >>> 22;
        // 2 ^ 12 = 0xFFF
        long shardId = (id & (0xFFF << 10)) >> 10;
        long seq = id & 0x3FF;

        List<Long> re = new ArrayList<Long>(4);
        re.add(miliSecond);
        re.add(shardId);
        re.add(seq);
        return re;
    }
}
