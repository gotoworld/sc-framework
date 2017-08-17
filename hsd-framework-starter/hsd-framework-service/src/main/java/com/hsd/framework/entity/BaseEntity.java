package com.hsd.framework.entity;

import com.hsd.framework.IEntity;
import lombok.Data;

@Data
public class BaseEntity implements IEntity {
    /** 开始时间 */
    private String dateBegin;
    /** 结束时间 */
    private String dateEnd;
    /** 关键字 */
    String keyword;
    /** 用户名 */
    String account;
    /**  建立者ID */
    private Long createId;
}
