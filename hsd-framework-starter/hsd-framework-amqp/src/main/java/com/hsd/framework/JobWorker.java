package com.hsd.framework;

import lombok.extern.slf4j.Slf4j;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class JobWorker {
    private static Map<String, MQQueue> mqQueueConcurrentHashMap = new ConcurrentHashMap<>();


    public JobWorker(MQQueue queue) {

    }

    public static MQQueue getQueue(String dtoName, MQQueue clazz) {
        MQQueue queue = mqQueueConcurrentHashMap.get(dtoName);
        if (queue == null) {
            /**队列名，【DTO类名】*/
            queue = clazz;
            mqQueueConcurrentHashMap.put(dtoName, queue);
        }
        return queue;
    }

    public static void doWorker(Job job) {
        new Thread(() -> {
            try {
                MQQueue queue = getQueue(job.getName(), null);
                queue.send(job);
            } catch (Exception e) {
                log.error("JobWorker:" + e.getMessage(), e);
            }
        }).start();
    }


}
