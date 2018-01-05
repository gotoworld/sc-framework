package com.hsd.framework.entity;

import com.hsd.framework.AbstractEntity;
import com.hsd.framework.IEntity;
import lombok.Data;

@Data
public class BaseEntity extends AbstractEntity implements IEntity {
    /** 开始时间 */
    private String dateBegin;
    /** 结束时间 */
    private String dateEnd;
    /** 关键字 */
    private String keyword;
    /** 用户名 */
    private String account;
    /**  建立者ID */
    private Long createId;
    /** 用户id */
    private Long userId;
    /** app用户id */
    private Long appUserId;
    /** 员工id */
    private Long staffId;
    /** app员工id */
    private Long appStaffId;
    /** appId */
    private String appId;
    /** app名称 */
    private String appName;
}
