package com.hsd.framework.cache.redis;

import com.hsd.framework.lock.AbstractLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisLock extends AbstractLock {
    private RedisTemplate<String, Object> redisTemplate;
    // 锁的名字
    protected String lockKey;
    // 锁的有效时长(毫秒)
    protected long lockExpires = 2000;
    private RedisSerializer<String> serializer;
    public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this(redisTemplate, lockKey, 2000);
    }
    /**
     * @param lockExpires 毫秒，锁过期时间
     */
    public RedisLock(RedisTemplate redisTemplate, String lockKey, long lockExpires) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.lockExpires = lockExpires;
    }

//    public RedisLock(String lockKey, long lockExpires) {
//        this.lockKey = lockKey;
//        this.lockExpires = lockExpires;
//    }

    @Override
    protected void unlock0() {
        try {
            redisTemplate.opsForValue().getOperations().delete(this.lockKey);
            log.debug("release lock, keys :" + lockKey);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @param timeout   毫秒值
     * @param interrupt 是否响应中断
     */
    @Override
    protected boolean lock(long timeout, boolean interrupt) throws InterruptedException {
        if (interrupt) {
            checkInterruption();
        }
        long start = System.currentTimeMillis();
        //当不指定timeout时，按最长等待时间
        timeout = timeout > 0 ? timeout : this.lockExpires + 1;
        try {
            do {
                if (interrupt) {
                    checkInterruption();
                }
                long lockExpireTime = System.currentTimeMillis() + lockExpires + 1;//锁超时时间

                if (redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX(getByte(lockKey), getByte(""+lockExpireTime)))) { // 获取到锁
                    // 成功获取到锁, 设置相关标识
                    locked = true;
                    redisTemplate.expire(lockKey, lockExpires, TimeUnit.MILLISECONDS);//设置过期，防止异常中断锁未释放
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }else{
                    Long redisTimeExpired = (Long) redisTemplate.opsForValue().get(lockKey);
                    if (redisTimeExpired != null && isTimeExpired(redisTimeExpired)) { // lock is expired
                        // 假设多个线程(非单jvm)同时走到这里
                        Long oldValue = (Long) redisTemplate.opsForValue().getAndSet(lockKey, lockExpireTime); // getset is atomic
                        // 但是走到这里时每个线程拿到的oldValue肯定不可能一样(因为getset是原子性的)
                        // 加入拿到的oldValue依然是expired的，那么就说明拿到锁了
                        if (oldValue != null && isTimeExpired(oldValue)) {
                            // 成功获取到锁, 设置相关标识
                            locked = true;
                            redisTemplate.expire(lockKey, lockExpires, TimeUnit.MILLISECONDS);//设置过期，防止异常中断锁未释放
                            setExclusiveOwnerThread(Thread.currentThread());
                            return true;
                        }
                    } else {
                        // lock is not expired, enter next loop retrying
                    }
                }
                TimeUnit.MICROSECONDS.sleep(100);//睡眠100毫秒
            } while (isTimeout(start, timeout));
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {

        }
        return false;
    }

    public boolean isLocked() {
        if (locked) {
            return true;
        } else {
            Long value = (Long) redisTemplate.opsForValue().get(lockKey);
            // TODO 这里其实是有问题的, 想:当get方法返回value后, 假设这个value已经是过期的了,
            // 而就在这瞬间, 另一个节点set了value, 这时锁是被别的线程(节点持有), 而接下来的判断
            // 是检测不出这种情况的.不过这个问题应该不会导致其它的问题出现, 因为这个方法的目的本来就
            // 不是同步控制, 它只是一种锁状态的报告.
            return !isTimeExpired(value);
        }
    }

    private void checkInterruption() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
    }

    private boolean isTimeExpired(Long value) {
        return value < System.currentTimeMillis();
    }

    private boolean isTimeout(long start, long timeout) {
        return start + timeout > System.currentTimeMillis();
    }

    private byte[] getByte(String str) {
        if (serializer == null) serializer = redisTemplate.getStringSerializer();
        return serializer.serialize(str);
    }
}
