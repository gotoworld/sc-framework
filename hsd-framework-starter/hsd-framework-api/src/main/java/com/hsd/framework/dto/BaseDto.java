package com.hsd.framework.dto;

import com.hsd.framework.AbstractEntity;
import com.hsd.framework.IDto;
import lombok.Data;

@Data
public class BaseDto extends AbstractEntity implements IDto {
    private String token;
    /**新增标记0否1是*/
    private Integer newFlag;
    /** 开始时间 */
    private String dateBegin;
    /** 结束时间 */
    private String dateEnd;
    /** 页码 */
    private Integer pageNum;
    /** 每页显示条数 */
    private Integer pageSize;
    /** 关键字 */
    private  String keyword;
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
    /**鉴权token*/
    private String Authorization;

    private String str;
}
