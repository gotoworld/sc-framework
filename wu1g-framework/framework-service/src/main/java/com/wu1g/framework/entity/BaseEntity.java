package com.wu1g.framework.entity;

import lombok.Data;

@Data
public class BaseEntity implements  IEntity{
    /** 开始时间 */
    private String dateBegin;
    /** 结束时间 */
    private String dateEnd;
}
