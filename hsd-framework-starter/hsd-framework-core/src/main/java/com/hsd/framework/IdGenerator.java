package com.hsd.framework;

public interface IdGenerator {
    public long nextId();
    public long nextId(String tab);
    public long nextId(String tab, long shardId);
}
