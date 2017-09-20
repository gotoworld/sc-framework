package com.hsd.task;

import com.hsd.framework.cache.redis.RedisLock;
import com.hsd.framework.lock.Lock;
import com.hsd.util.dao.msg.IMsgVerifyDao;
import com.hsd.util.entity.msg.MsgVerify;
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
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * <p>清理已过期验证码未使用信息
     */
    @Scheduled(cron = "0/5 * * * * * ")
    public void clearVerifyExpire() {
        log.info("===============清理已过期验证码未使用信息====================");
        Lock lock = new RedisLock(redisTemplate,"lock:clear-verify-expire", 5 * 60 * 1000);
        if (lock.tryLock(3 * 60 * 1000)) {
            try {
                MsgVerify msgVerify=new MsgVerify();

                msgVerifyDao.clearVerifyExpire(msgVerify);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                //lock.unlock();
            }
        }
    }
}
