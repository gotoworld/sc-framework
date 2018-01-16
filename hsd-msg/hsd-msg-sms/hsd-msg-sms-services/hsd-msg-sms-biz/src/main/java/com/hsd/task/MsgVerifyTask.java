package com.hsd.task;

import com.hsd.framework.cache.redis.RedisLock;
import com.hsd.framework.lock.Lock;
import com.hsd.msg.dao.msg.IMsgVerifyDao;
import com.hsd.msg.entity.msg.MsgVerify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Slf4j
public class MsgVerifyTask {
    @Autowired
    private IMsgVerifyDao msgVerifyDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * <p>清理已过期验证码未使用信息
     */
    @Scheduled(cron = "0 0/30 * * * * ")
    public void clearVerifyExpire() {
        try {
            Lock lock = new RedisLock(redisTemplate,"lock:clear-verify-expire", 5 * 60 * 1000);
            if (lock.tryLock(3 * 60 * 1000)) {
                log.debug("===============清理已过期验证码未使用信息====================");
                try {
                    MsgVerify msgVerify = new MsgVerify();

                    msgVerifyDao.clearVerifyExpire(msgVerify);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                } finally {
//                    lock.unlock();
//                    log.debug("释放锁");
                }
            }
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }
}
