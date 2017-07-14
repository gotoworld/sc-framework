package com.hsd.framework.mq;

import lombok.extern.slf4j.Slf4j;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理统计数据接口（针对client，可发送到count-server进行处理）
 * Created by Administrator on 16-10-13.
 */
@Slf4j
public class JobWorker {
    private static Map<String, MQQueue> countQueue = new ConcurrentHashMap<>();


    public JobWorker(MQQueue queue) {

    }

    public static MQQueue getQueue(String dtoName, MQQueue clazz) {
        MQQueue queue = countQueue.get(dtoName);
        if (queue == null) {
            /**队列名，count:【DTO类名】*/
            queue = clazz;
            countQueue.put(dtoName, queue);
        }
        return queue;
    }

    public static void doWorker(Job countJob) {
        new Thread(() -> {
            try {
                MQQueue queue = getQueue(countJob.getName(), null);
                queue.send(countJob);
            } catch (Exception e) {
                log.error("JobWorker:" + e.getMessage(), e);
            }
        }).start();
    }


}
