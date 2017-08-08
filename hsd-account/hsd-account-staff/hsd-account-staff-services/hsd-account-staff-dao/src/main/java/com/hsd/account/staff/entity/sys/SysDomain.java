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
public class SysDomain extends BaseEntity {
     /**CODE*/
     private String code;
     /**系统名称*/
     private String name;
     /**DNS*/
     private String dns;
     /**是否展示0否1是*/
     private Integer isShow;
     /**备注*/
     private String memo;
     /**排序*/
     private Integer orderNo;
     /**版本号*/
     private Integer version;
     /**关键字*/
     private String keyword;
     /**是否删除*/
     private Integer delFlag;
     /**建立者ID*/
     private Long createId;
     /**创建时间*/
     private Date dateCreated;
     /**更新时间*/
     private Date dateUpdated;
     /**BI时间戳*/
     private Date biUpdateTs;
}