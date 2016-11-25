package com.wu1g.framework.entity;

import com.wu1g.framework.IEntity;
import lombok.Data;

@Data
public class BaseEntity implements IEntity {
    /** 开始时间 */
    private String dateBegin;
    /** 结束时间 */
    private String dateEnd;
}
