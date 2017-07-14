package com.hsd.framework.cache.distribute;

import com.hsd.framework.lock.AbstractLock;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 16-10-9.
 */
@Slf4j
public class RedisClusterLock extends AbstractLock {
    private final JedisCluster jedisCluster;

    // 锁的名字
    protected String lockKey;

    // 锁的有效时长(毫秒)
    protected long lockExpires = 2000;

    public RedisClusterLock(JedisCluster jedisCluster, String lockKey) {
        this(jedisCluster, lockKey, 2000);
    }

    /**
     *
     * @param jedisCluster
     * @param lockKey
     * @param lockExpires   毫秒
     */
    public RedisClusterLock(JedisCluster jedisCluster, String lockKey, long lockExpires) {
        this.jedisCluster = jedisCluster;
        this.lockKey = lockKey;
        this.lockExpires = lockExpires;
    }

    @Override
    protected void unlock0() {
        // TODO 判断锁是否过期
        String value = jedisCluster.get(lockKey);
        if (!isTimeExpired(value)) {
            jedisCluster.del(this.lockKey );
        }
//        Jedis jedis = null;
//        try {
//            jedis = getResource();
//            jedis.del(this.lockKey );
////            log.debug("release lock, keys :" + lockKey);
//        } catch (JedisConnectionException je) {
//            log.error(je.getMessage(), je);
//            returnBrokenResource(jedis);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            returnResource(jedis);
//        }

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

        boolean useTimeout = timeout > 0;
//        Jedis jedis = null;
//        try {
//            jedis = this.getResource();
            do {
                if (interrupt) {
                    checkInterruption();
                }

                long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;//锁超时时间
                String stringOfLockExpireTime = String.valueOf(lockExpireTime);

                if (jedisCluster.setnx(lockKey, stringOfLockExpireTime) == 1) { // 获取到锁
                    // TODO 成功获取到锁, 设置相关标识
                    locked = true;
                    setExclusiveOwnerThread(Thread.currentThread());
//                    log.debug(this.jedisPool + "获得锁1：");
                    return true;
                }

                String value = jedisCluster.get(lockKey);
//                log.debug(this.jedisPool + "等待锁1：");
                if (value != null && isTimeExpired(value)) { // lock is expired
//                    log.debug(this.jedisPool + "等待锁过期：");
                    // 假设多个线程(非单jvm)同时走到这里
                    String oldValue = jedisCluster.getSet(lockKey, stringOfLockExpireTime); // getset is atomic
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
                TimeUnit.MICROSECONDS.sleep(30);
            } while (isTimeout(start, timeout) ) ;

//        } catch (JedisConnectionException je) {
//            log.error(je.getMessage(), je);
//            returnBrokenResource(jedis);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            returnResource(jedis);
//        }
        return false;
    }

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
            String value = jedisCluster.get(lockKey);
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

//    /**
//     * @return
//     * @author http://blog.csdn.net/java2000_wl
//     * @date 2013-7-22 下午9:33:45
//     */
//    private Jedis getResource() {
//        return (Jedis)jedisPool.getResource();
//    }
//
//    /**
//     * 销毁连接
//     *
//     * @param jedis
//     * @author http://blog.csdn.net/java2000_wl
//     */
//    private void returnBrokenResource(Jedis jedis) {
//        if (jedis == null) {
//            return;
//        }
//        try {
//            //容错
//            jedisPool.returnBrokenResource(jedis);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    /**
//     * @param jedis
//     * @author http://blog.csdn.net/java2000_wl
//     */
//    private void returnResource(Jedis jedis) {
//        if (jedis == null) {
//            return;
//        }
//        try {
//            jedisPool.returnResource(jedis);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }
}
