package com.hsd.framework;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 含@class的json序列化接口，所有含有多态类的序列化、反序列化均需实现此接口
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="@class")
public interface JsonClassSerializer {
}
