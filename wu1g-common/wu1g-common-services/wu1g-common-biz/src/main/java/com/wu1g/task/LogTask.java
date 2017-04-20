package com.wu1g.task;

import com.wu1g.dao.sys.ISysUserLogDao;
import com.wu1g.framework.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 删除过期日志
 */
@Slf4j
public class LogTask {
    @Autowired
    private ISysUserLogDao sysUserLogDao;

    /**
     * <li>删除过期日志
     */
    @Scheduled(cron = "${wu1g.common.logClear.cron}")
    public void violationRobots() {
        log.info("--------------删除过期日志-----任务开关---------" + "true".equals(AppConfig.getProperty("common.logClear.enable")));
//        Lock lock = new RedisLock(cachePoolManager.get("wu1g-redis"), "lock:check-LogTask", 24 * 60 * 1000); //分布式redis锁
        if ("true".equals(AppConfig.getProperty("common.logClear.enable")) ) {//&& lock.tryLock(60 * 1000)
            try {
                Integer row=sysUserLogDao.deleteByExpireDay(Integer.parseInt(AppConfig.getProperty("common.logClear.expireDay")));
                log.info("删除["+AppConfig.getProperty("common.logClear.expireDay")+"]天前的日志["+row+"]条");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
//                lock.unlock();
            }
//        }
        }
    }
}
