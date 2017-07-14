package com.hsd.framework.cache;

import java.util.List;

/**
 * Created by Administrator on 16-10-24.
 */
public interface ListCache<T> extends Cache {
    public static enum OpType implements CacheOperaterType{
        LPUSH,
        RPUSH,
        LPOP,
        RPOP,
        BLPOP,
        BRPOP,

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

    //key对应list的头部添加字符串元素
    public void lpush(T object);
    //在key对应的list的尾部添加字符串元素
    public void rpush(T object);
    //从list头部删除元素，并返回删除元素
    public T lpop();
    //从list尾部删除元素，并返回删除元素
    public T rpop();
    //阻塞：从list头部删除元素，并返回删除元素
    public List<T> blpop();
    //阻塞：从list尾部删除元素，并返回删除元素
    public List<T> brpop();

    public void remove();

//    public Long size();

//    public void linsert();  //：在key对应list的特定位置前或后添加字符串，如 linsert list before world my 或 linsert list after hello you
//    public void lset();  //：设置list中指定下表的元素值，如 lset list 1 he
//    public void lrange();  //：返回指定范围内的元素，如 lrange list 0 -1
//    public void lrem();  //：从key对应的list中删除n个和value相同的元素，n<0从尾删除，n=0全部删除，如 lrem list 1 you
//    public void ltrim();  //：保留指定范围内的数据，如 ltrim list 1 -1
//
//    public Object lindex();  //：返回名称为key的list的index位置的元素，如 lindex list 1
//    public void llen();  //：返回key对应的list的长度，如 llen list

}
