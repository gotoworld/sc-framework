package com.hsd.framework;

import lombok.Data;

/**
 * 一个数据（Job）实体
 */
@Data
public class Job extends AbstractEntity {

    protected Long time = System.currentTimeMillis();

    public String getName() {
        return this.getClass().getName();
    }

    /**
     * 提交当前的Job。(提交到指定队列)
     */
    public void commit() {
        this.time = System.currentTimeMillis();
        JobWorker.doWorker(this);
    }
}
