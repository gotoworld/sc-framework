package com.hsd.account.staff.entity.sys;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hsd.framework.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysApp extends BaseEntity {
     /**AppId*/
     private String id;
     /**系统名称*/
     private String name;
     /**登录链接*/
     private String loginUrl;
     /**主页登链*/
     private String mainUrl;
     /**备注*/
     private String memo;
     /**是否删除*/
     private Integer delFlag;
     /**建立者id*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**已存在的禁止删除(0否1是)*/
     private Integer noDelFlag;
}