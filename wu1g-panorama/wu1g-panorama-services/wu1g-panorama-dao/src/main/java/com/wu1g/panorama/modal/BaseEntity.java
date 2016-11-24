package com.wu1g.panorama.modal;

import lombok.Data;

@Data
public class BaseEntity extends com.wu1g.framework.modal.BaseEntity implements  IEntity{
    /** 开始时间 */
    private String dateBegin;
    /** 结束时间 */
    private String dateEnd;
}
