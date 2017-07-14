package com.hsd.framework.cache.distribute;

import com.hsd.framework.cache.CacheException;
import com.hsd.framework.cache.CachePoolManager;
import com.hsd.framework.cache.config.CacheConfig;
import com.hsd.framework.lock.AbstractLock;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Pool;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 16-10-9.
 */
@Slf4j
public class RedisLock extends AbstractLock {
    private final Pool jedisPool;

    // 锁的名字
    protected String lockKey;

    // 锁的有效时长(毫秒)
    protected long lockExpires = 2000;

    public RedisLock(Pool jedisPool, String lockKey) {
        this(jedisPool, lockKey, 2000);
    }
    public RedisLock(String poolName, String lockKey) {
        this(poolName, lockKey, 2000);
    }
    /**
     *
     * @param jedisPool
     * @param lockKey
     * @param lockExpires   毫秒，锁过期时间
     */
    public RedisLock(Pool jedisPool, String lockKey, long lockExpires) {
        this.jedisPool = jedisPool;
        this.lockKey = lockKey;
        this.lockExpires = lockExpires;
    }

    public RedisLock(String poolName, String lockKey, long lockExpires) {
        Pool jedisPool = ((CachePoolManager)CacheConfig.getBean("cachePoolManager")).get(poolName);

        this.jedisPool = jedisPool;
        this.lockKey = lockKey;
        this.lockExpires = lockExpires;
    }

    @Override
    protected void unlock0() {
        // TODO 判断锁是否过期
//        String value = jedisPool.getResource().get(lockKey);
//        if (!isTimeExpired(value)) {
//            doUnlock();
//        }
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.del(this.lockKey );
//            log.debug("release lock, keys :" + lockKey);
        } catch (JedisConnectionException je) {
            log.error(je.getMessage(), je);
            jedis = returnBrokenResource(jedis);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     *
     * @param timeout   毫秒值
     * @param interrupt 是否响应中断
     * @return
     * @throws InterruptedException
     */
    @Override
    protected boolean lock(long timeout, boolean interrupt) throws InterruptedException {
        if (interrupt) {
            checkInterruption();
        }

        long start = System.currentTimeMillis();

//        boolean useTimeout = timeout > 0;
        //当不指定timeout时，按最长等待时间
        timeout = timeout > 0 ? timeout : this.lockExpires + 1;
        Jedis jedis = null;
        try {
            jedis = this.getResource();
            do {
                if (interrupt) {
                    checkInterruption();
                }

                long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;//锁超时时间
                String stringOfLockExpireTime = String.valueOf(lockExpireTime);

                if (jedis.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
                    jedis.expire(lockKey, (int)lockExpires);
                    // TODO 成功获取到锁, 设置相关标识
                    locked = true;
                    setExclusiveOwnerThread(Thread.currentThread());
//                    log.debug(this.jedisPool + "获得锁1：");
                    return true;
                }

                String value = jedis.get(lockKey);
//                log.debug(this.jedisPool + "等待锁1：");
                if (value != null && isTimeExpired(value)) { // lock is expired
//                    log.debug(this.jedisPool + "等待锁过期：");
                    // 假设多个线程(非单jvm)同时走到这里
                    String oldValue = jedis.getSet(lockKey, stringOfLockExpireTime); // getset is atomic
                    // 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
                    // 加入拿到的oldValue依然是expired的，那么就说明拿到锁了
                    if (oldValue != null && isTimeExpired(oldValue)) {
                        // TODO 成功获取到锁, 设置相关标识
                        locked = true;
                        setExclusiveOwnerThread(Thread.currentThread());
//                        log.debug(this.jedisPool + "获得锁2：");
                        return true;
                    }
                } else {
                    // TODO lock is not expired, enter next loop retrying
                }
                TimeUnit.MICROSECONDS.sleep(60);
            } while (isTimeout(start, timeout) ) ;

        } catch (JedisConnectionException je) {
            log.error(je.getMessage(), je);
            jedis = returnBrokenResource(jedis);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(jedis);
        }
        return false;
    }

//    public boolean tryLock() {
//        long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;//锁超时时间
//        String stringOfLockExpireTime = String.valueOf(lockExpireTime);
//
//        if (jedis.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
//            // TODO 成功获取到锁, 设置相关标识
//            locked = true;
//            setExclusiveOwnerThread(Thread.currentThread());
//            return true;
//        }
//
//        String value = jedis.get(lockKey);
//        if (value != null && isTimeExpired(value)) { // lock is expired
//            // 假设多个线程(非单jvm)同时走到这里
//            String oldValue = jedis.getSet(lockKey, stringOfLockExpireTime); // getset is atomic
//            // 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
//            // 假如拿到的oldValue依然是expired的，那么就说明拿到锁了
//            if (oldValue != null && isTimeExpired(oldValue)) {
//                // TODO 成功获取到锁, 设置相关标识
//                locked = true;
//                setExclusiveOwnerThread(Thread.currentThread());
//                return true;
//            }
//        } else {
//            // TODO lock is not expired, enter next loop retrying
//        }
//
//        return false;
//    }

    /**
     * Queries if this lock is held by any thread.
     *
     * @return {@code true} if any thread holds this lock and
     *   {@code false} otherwise
     */
    public boolean isLocked() {
        if (locked) {
            return true;
        } else {
            String value = getResource().get(lockKey);
            // TODO 这里其实是有问题的, 想:当get方法返回value后, 假设这个value已经是过期的了,
            // 而就在这瞬间, 另一个节点set了value, 这时锁是被别的线程(节点持有), 而接下来的判断
            // 是检测不出这种情况的.不过这个问题应该不会导致其它的问题出现, 因为这个方法的目的本来就
            // 不是同步控制, 它只是一种锁状态的报告.
            return !isTimeExpired(value);
        }
    }

    private void checkInterruption() throws InterruptedException {
        if(Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private boolean isTimeExpired(String value) {
        return Long.parseLong(value) < System.currentTimeMillis();
    }

    private boolean isTimeout(long start, long timeout) {
        return start + timeout > System.currentTimeMillis();
    }

//    private void doUnlock() {
//        jedis.del(lockKey);
//    }

    /**
     * @return
     * @author http://blog.csdn.net/java2000_wl
     * @date 2013-7-22 下午9:33:45
     */
    private Jedis getResource() {
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
                ex = e;
                // 底层原因是SocketTimeoutException，不过redis已经捕捉且抛出JedisConnectionException，不继承于前者
                if (e instanceof JedisConnectionException || e instanceof SocketTimeoutException)
                {
                    timeoutCount++;
                    log.warn("getJedis timeoutCount={}", timeoutCount);
                }else
                {
                    log.warn("jedisInfo。NumActive=" + jedisPool.getNumActive() + ", NumIdle="
                            + jedisPool.getNumIdle() + ", NumWaiters="
                            + jedisPool.getNumWaiters() + ", isClosed="
                            + jedisPool.isClosed());
//                    log.error("getJedis error", e);
                    break;
                }
            }
        }
        throw new CacheException(ex);
//        return (Jedis)jedisPool.getResource();
    }

    /**
     * 销毁连接
     *
     * @param jedis
     * @author http://blog.csdn.net/java2000_wl
     */
    private Jedis returnBrokenResource(Jedis jedis) {
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
    private void returnResource(Jedis jedis) {
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
