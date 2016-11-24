package com.wu1g.framework;

import java.io.Serializable;

/**
 * Created by Administrator on 16-9-10.
 */
public class AbstractEntity implements Serializable{
    protected static long serialVersionUID = 1L;

    /**
     * 对象值拷贝
     * @param to    目标对象类
     * @param <T>
     * @return      目标对象
     * @throws Exception
     */
    public <T> T copyTo(Class<T> to) throws Exception {
        T obj = (T) ObjectCopy.copyTo(this, to);
        return obj;
    }

}
