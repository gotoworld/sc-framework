package com.hsd.framework.cache;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 16-10-24.
 */
public interface SetCache<T> extends Cache {
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
    public void add(T... member);

    public void add(List<T> member);
    /**随机获得一个元素，并删除*/
    public T pop() ;

    public Set<T> pops(long count) ;

    public Long size();


}
