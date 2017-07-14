package com.hsd.framework.cache;

import java.util.List;

/**
 * Created by Administrator on 16-10-24.
 */
public interface SortSetCache extends Cache {
    public static enum OpType implements CacheOperaterType{
        ADD,
        MEMBER,
        IS_MEMBER,
        DEL,
        POP,
        POPS,
        CARD,

        ;

        private Object other;
        @Override
        public void setOther(Object other) {
            this.other = other;
        }

        @Override
        public Object getOther() {
            return other;
        }
    };

    //添加元素
    public void add(String... member);

    public void add(List<String> member);
    /**随机获得一个元素，并删除*/
    public Object pop() ;

    public Object pops(long count) ;

    public Long size();


}
